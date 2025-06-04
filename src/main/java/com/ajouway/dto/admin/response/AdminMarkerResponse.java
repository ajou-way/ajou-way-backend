package com.ajouway.dto.admin.response;

import com.ajouway.controller.util.JsonConverter;
import com.ajouway.domain.enums.MajorName;
import com.ajouway.dto.GeoJsonPoint;
import com.ajouway.storage.entity.map.AdminMarkerEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record AdminMarkerResponse (
        String title,
        String contents,
        GeoJsonPoint geometry,
        List<MajorName> targetMajors,
        LocalDateTime deadline
){
    public static AdminMarkerResponse fromEntity(final AdminMarkerEntity adminMarkerEntity) {
        return AdminMarkerResponse.builder()
                .title(adminMarkerEntity.getTitle())
                .contents(adminMarkerEntity.getContents())
                .geometry(GeoJsonPoint.converter(adminMarkerEntity.getGeometry()))
                .targetMajors(JsonConverter.fromJson(adminMarkerEntity.getMajorJson(), new TypeReference<List<MajorName>>() {}))
                .deadline(adminMarkerEntity.getDeadline())
                .build();
    }
}
