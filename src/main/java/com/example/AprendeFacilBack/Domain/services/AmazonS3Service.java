package com.example.AprendeFacilBack.Domain.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.example.AprendeFacilBack.Domain.dto.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class AmazonS3Service implements AmazonS3ServiceInter {

    private static final String BUCKET = "bucket-aprende-facil";

    private final AmazonS3 amazonS3;

    @Autowired
    public AmazonS3Service(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }


    @Override
    public String putFResourcePDFToTopic(MultipartFile doc){
        String extensionDoc = StringUtils.getFilenameExtension(doc.getOriginalFilename());
        //Llave que se usa para acceder al la imagen en el bucket
        String key = String.format("%s.%s", UUID.randomUUID(), extensionDoc);

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(doc.getContentType());
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET, key, doc.getInputStream(), objectMetadata);
            amazonS3.putObject(putObjectRequest);
        } catch (IOException e) {
            throw new RuntimeException("Error al subir el documento a S3", e);

        }
        return key;
    }

    @Override
    public Asset getImg(String key) throws IOException {
        S3Object s3Object = amazonS3.getObject(BUCKET, key);
        ObjectMetadata objectMetadata = s3Object.getObjectMetadata();
        try {
            S3ObjectInputStream s3ObjectInputStream = s3Object.getObjectContent();
            byte[] bytes = IOUtils.toByteArray(s3ObjectInputStream);
            return new Asset(bytes, objectMetadata.getContentType());
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @Override
    public String getDocUrl(String key) {
        return String.format("https://%s.s3.amazonaws.com/%s", BUCKET, key);
    }


}

