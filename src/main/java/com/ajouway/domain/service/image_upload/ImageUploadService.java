package com.ajouway.domain.service.image_upload;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageUploadService {


    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private static final String BUCKET_IMAGE_PREFIX = "uploads/";

    private final AmazonS3 amazonS3;

    public String uploadImage(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null ||
                originalFilename.isEmpty() ||
                !originalFilename.matches(".*\\.(jpg|jpeg|png|gif)$")
        ) {
            throw new CustomException(CustomExceptionInfo.IMAGE_EXTENSION_NOT_SUPPORTED);
        }

        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

        String shortUuid = UUID.randomUUID().toString().substring(0, 5);

        String key = BUCKET_IMAGE_PREFIX + formattedDate + "/" + shortUuid + extension;

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(file.getContentType());

        try {
            metadata.setContentLength(file.getInputStream().available());
            amazonS3.putObject(bucket, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new CustomException(CustomExceptionInfo.IMAGE_UPLOAD_FAIL);
        }

        return amazonS3.getUrl(bucket, key).toString();
    }
}
