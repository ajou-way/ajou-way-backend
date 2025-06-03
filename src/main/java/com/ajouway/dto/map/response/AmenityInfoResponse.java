package com.ajouway.dto.map.response;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.storage.entity.map.AmenityInfoEntity;
import lombok.Builder;

@Builder
public record AmenityInfoResponse(
        Long id,

        AmenityInfoType type,

        String contents,

        Long buildingId
){
    public static AmenityInfoResponse fromEntity(final AmenityInfoEntity amenityInfo){
        return AmenityInfoResponse.builder()
                .id(amenityInfo.getId())
                .type(amenityInfo.getAmenityInfoType())
                .contents(amenityInfo.getContents())
                .buildingId(amenityInfo.getBuilding().getId())
                .build();
    }
}
