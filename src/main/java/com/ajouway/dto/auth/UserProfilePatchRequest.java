package com.ajouway.dto.auth;

public record UserProfilePatchRequest(
        String major,
        String studentId
) {
    public void validate() {
        if (major == null || major.isBlank()) {
            throw new IllegalArgumentException("전공은 필수 입력 사항입니다.");
        }
        if (studentId == null || studentId.isBlank()) {
            throw new IllegalArgumentException("학번은 필수 입력 사항입니다.");
        }
    }
}
