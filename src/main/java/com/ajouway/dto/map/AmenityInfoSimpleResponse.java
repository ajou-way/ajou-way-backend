package com.ajouway.dto.map;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.infra.persistence.entity.map.AmenityInfo;
import java.util.Map;
import lombok.Builder;

@Builder
public record AmenityInfoSimpleResponse (
        Long id,

        AmenityInfoType type,

        Map<String, Object> properties,

        Long buildingId
){
    public static AmenityInfoSimpleResponse fromEntity(final AmenityInfo amenityInfo){
        return AmenityInfoSimpleResponse.builder()
                .id(amenityInfo.getId())
                .type(amenityInfo.getAmenityInfoType())
                .properties(amenityInfo.getProperties())
                .buildingId(amenityInfo.getBuildingMarker().getId())
                .build();
    }
}
