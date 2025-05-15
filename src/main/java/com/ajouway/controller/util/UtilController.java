package com.ajouway.controller.util;

import com.ajouway.domain.service.image_upload.ImageUploadService;
import com.ajouway.dto.util.ImageUrlResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/utils")
public class UtilController {

    private final ImageUploadService imageUploadService;

    @PostMapping("/images/upload")
    public ImageUrlResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return ImageUrlResponse.of(imageUploadService.uploadImage(file));
    }

}
