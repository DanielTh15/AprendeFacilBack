package com.example.AprendeFacilBack.Web.controllers;

import com.example.AprendeFacilBack.Domain.dto.Asset;
import com.example.AprendeFacilBack.Domain.services.AmazonS3ServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/s3")
public class AmazonS3Controller {


    private final AmazonS3ServiceInter amazonS3ServiceInter;

    @Autowired
    public AmazonS3Controller(AmazonS3ServiceInter amazonS3ServiceInter) {
        this.amazonS3ServiceInter = amazonS3ServiceInter;
    }


    @PostMapping("/upload-doc")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "doc") MultipartFile doc){
        try {
            String key = amazonS3ServiceInter.putFResourcePDFToTopic(doc);
            return ResponseEntity.ok().body(key);
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/get-doc", params = "key")
    public ResponseEntity<ByteArrayResource> getDoc(@RequestParam String key) throws IOException {
        Asset asset = amazonS3ServiceInter.getImg(key);
        ByteArrayResource resource = new ByteArrayResource(asset.getContent());
        return ResponseEntity
                .ok()
                .header("Content-Type", asset.getContentType())
                .contentLength(asset.getContent().length)
                .body(resource);
    }

}
