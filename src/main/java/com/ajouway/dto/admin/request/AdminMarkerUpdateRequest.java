package com.ajouway.dto.admin.request;

import com.ajouway.controller.util.JsonConverter;
import com.ajouway.domain.enums.MajorName;
import com.ajouway.dto.GeoJsonPoint;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public record AdminMarkerUpdateRequest(
        @NotBlank
        String title,

        @NotBlank
        String contents,

        List<MajorName> targetMajors,

        @NonNull
        LocalDateTime deadline
) {
    public String toJson() {
        return JsonConverter.toJson(targetMajors);
    }
}
