package com.example.AprendeFacilBack.Domain.services;

import com.example.AprendeFacilBack.Domain.dto.Asset;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AmazonS3ServiceInter {
    String putFResourcePDFToTopic(MultipartFile doc);

    Asset getImg(String key) throws IOException;

    String getDocUrl(String key);
}
