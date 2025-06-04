package com.ajouway.dto.admin.request;

import com.ajouway.controller.util.JsonConverter;
import com.ajouway.domain.enums.MajorName;
import com.ajouway.dto.GeoJsonPoint;
import com.ajouway.storage.entity.map.AdminMarkerEntity;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.util.List;

public record AdminMarkerRequest(
        @NotBlank
        String title,

        @NotBlank
        String contents,

        @NonNull
        GeoJsonPoint geometry,

        List<MajorName> targetMajors,

        @NonNull
        LocalDateTime deadline
) {

    public AdminMarkerEntity toEntity(){
        return AdminMarkerEntity.builder()
                .title(title)
                .contents(contents)
                .geometry(geometry.toPoint())
                .majorJson(JsonConverter.toJson(targetMajors))
                .deadline(deadline)
                .build();
    }
}
