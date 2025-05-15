package com.ajouway.dto.util;

public record ImageUrlResponse(
        String imageUrl
) {
    public static ImageUrlResponse of(String imageUrl) {
        return new ImageUrlResponse(imageUrl);
    }
}
