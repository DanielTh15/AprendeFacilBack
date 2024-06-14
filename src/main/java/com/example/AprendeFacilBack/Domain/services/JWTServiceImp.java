
package com.example.AprendeFacilBack.Domain.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.*;
import java.util.logging.Logger;



@Component
public class JWTServiceImp implements JWTService {

    private static final Logger log = Logger.getLogger(JWTServiceImp.class.getName());

    private String privateKeyPem;

    private String publicKeyPem;

    @Autowired
    public JWTServiceImp(Map<String, String> jwtKeys) {
        this.privateKeyPem = jwtKeys.get("private_key");
        this.publicKeyPem = jwtKeys.get("public_key");
    }

    @Override
    public String createToken(String emailUser) throws JOSEException, NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        // Obtener la zona horaria de Colombia
        TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");
        Calendar calendar = Calendar.getInstance(timeZone);
        // Ajustar el tiempo de expiración a una hora desde el tiempo actual
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date expirationTime = calendar.getTime();

        PrivateKey privateKey = loadPrivateKey(privateKeyPem);
        JWSSigner signer = new RSASSASigner(privateKey);

        Date now = new Date();
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(emailUser)
                .issueTime(now)
                .expirationTime(expirationTime)
                .build();

        SignedJWT signed = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claims);
        signed.sign(signer);
        return signed.serialize();
    }

    @Override
    public boolean isValid(String token) throws ParseException {
        try {
            PublicKey publicKey = loadPublicKey(publicKeyPem);
            SignedJWT signed = SignedJWT.parse(token);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
            if (!signed.verify(verifier)) {
                throw new JOSEException("Invalid Signature");
            }
            JWTClaimsSet jwtClaimsSet = signed.getJWTClaimsSet();

            if (jwtClaimsSet.getExpirationTime().before(new Date())) {
                throw new JOSEException("Token is expired");
            }
            return signed.verify(verifier);
        } catch (Exception e) {
            log.severe("Error al validar el token: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getUsername(String token) throws ParseException {
        SignedJWT signed = SignedJWT.parse(token);
        return signed.getJWTClaimsSet().getSubject();
    }

    private String sanitizePemKey(String pemKey) {
        return pemKey.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "")
                .replace("_", "/")
                .replace("-", "+");
    }

    private PrivateKey loadPrivateKey(String privateKeyPem) throws NoSuchAlgorithmException, InvalidKeySpecException {
            String sanitizedPrivateKey = sanitizePemKey(privateKeyPem);

            byte[] decodeKey = Base64.getDecoder().decode(sanitizedPrivateKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodeKey));
    }

    private PublicKey loadPublicKey(String publicKeyPem) throws NoSuchAlgorithmException, InvalidKeySpecException {
        try {
            String sanitizedPublicKey = sanitizePemKey(publicKeyPem);
            byte[] decodeKey = Base64.getDecoder().decode(sanitizedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(new X509EncodedKeySpec(decodeKey));
        } catch (IllegalArgumentException e) {
            log.severe("Error en la decodificación Base64 de la clave pública: " + e.getMessage());
            throw e;
        }
    }
}
