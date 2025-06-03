package com.ajouway.dto.map.request;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.storage.entity.map.AmenityInfoEntity;
import com.ajouway.storage.entity.map.BuildingEntity;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Builder
public record BuildingAmenityAddRequest(
        @NotBlank
        AmenityInfoType amenityInfoType,

        @NotBlank
        String contents

) {
    public AmenityInfoEntity toEntity(final BuildingEntity building) {
        return AmenityInfoEntity.create(amenityInfoType, contents, building);
    }
}
