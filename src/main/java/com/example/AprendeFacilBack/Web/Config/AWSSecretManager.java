package com.example.AprendeFacilBack.Web.Config;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

import java.util.HashMap;
import java.util.Map;

public class AWSSecretManager {
    public static Map<String, String> getSecret(String secretName) {
        Region region = Region.of("us-east-2");

        // Crear un cliente de AWS Secrets Manager
        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .build();

        // Obtener el valor del secreto que contiene las claves privada y pública
        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        try {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            // Manejar excepciones
            throw e;
        }

        String secretString = getSecretValueResponse.secretString();

        // Analizar la cadena JSON
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(secretString, JsonObject.class);

        // Acceder al valor de la clave "private_key"
        String privateKey = jsonObject.get("private_key").getAsString();

        // Acceder al valor de la clave "public_key"
        String publicKey = jsonObject.get("public_key").getAsString();

        HashMap<String, String> secretsKey = new HashMap<>();
        secretsKey.put("private_key", privateKey);
        secretsKey.put("public_key", publicKey);

        return secretsKey;
    }
}
