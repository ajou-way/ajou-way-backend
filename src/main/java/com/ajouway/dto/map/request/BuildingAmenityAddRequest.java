package com.ajouway.dto.map.request;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.storage.entity.map.AmenityInfo;
import com.ajouway.storage.entity.map.Building;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Builder
public record BuildingAmenityAddRequest(
        @NotBlank
        AmenityInfoType amenityInfoType,

        @NotBlank
        String contents

) {
    public AmenityInfo toEntity(final Building building) {
        return AmenityInfo.create(amenityInfoType, contents, building);
    }
}
