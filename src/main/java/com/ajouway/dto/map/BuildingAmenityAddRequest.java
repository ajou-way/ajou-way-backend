package com.ajouway.dto.map;

import com.ajouway.domain.enums.AmenityInfoType;
import com.ajouway.infra.persistence.entity.map.AmenityInfo;
import com.ajouway.infra.persistence.entity.map.BuildingMarker;
import java.util.Map;
import lombok.Builder;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

@Builder
public record BuildingAmenityAddRequest (
        @NotBlank
        AmenityInfoType type,

        @NotBlank
        Map<String, Object> properties,

        @NotBlank
        Long buildingId

){
    public AmenityInfo toEntity(final BuildingMarker buildingMarker){
        return AmenityInfo.create(type, properties, buildingMarker);
    }
}
