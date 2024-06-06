package com.example.AprendeFacilBack.Domain.services;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Service
public class JWTServiceImp implements JWTService{

    @Value("classpath:jwtKeys/private_key.pem")
    private Resource privateKeyResource;

    @Value("classpath:jwtKeys/public_key.pem")
    private Resource publicKeyResource;


    @Override
    public String createToken(String emailUser) throws JOSEException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // Obtener la zona horaria de Colombia
        TimeZone timeZone = TimeZone.getTimeZone("America/Bogota");
        Calendar calendar = Calendar.getInstance(timeZone);
        // Ajustar el tiempo de expiración a una hora desde el tiempo actual
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        Date expirationTime = calendar.getTime();

        PrivateKey privateKey = loadPrivateKey(privateKeyResource);
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
            PublicKey publicKey = loadPublicKey(publicKeyResource);
            SignedJWT signed = SignedJWT.parse(token);
            JWSVerifier verifier = new RSASSAVerifier((RSAPublicKey) publicKey);
            if(!signed.verify(verifier)) {
                throw new JOSEException("Invalid Signature");
            }
            JWTClaimsSet jwtClaimsSet = signed.getJWTClaimsSet();

            if(jwtClaimsSet.getExpirationTime().before(new Date())){
                throw new JOSEException("Token is expired");
            }
            return signed.verify(verifier);
        }catch (Exception e){
            return false;
        }
    }



    //Extraemos el usuario del token
    @Override
    public String getUsername(String token) throws ParseException {
        SignedJWT signed = SignedJWT.parse(token);
        return signed.getJWTClaimsSet().getSubject();
    }

    private PrivateKey loadPrivateKey(Resource resource) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String privateKey = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decodeKey = Base64.getDecoder().decode(privateKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodeKey));
    }

    private PublicKey loadPublicKey(Resource resource) throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] keyBytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String publicKey = new String(keyBytes, StandardCharsets.UTF_8)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");

        byte[] decodeKey = Base64.getDecoder().decode(publicKey);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(decodeKey));


    }

}
