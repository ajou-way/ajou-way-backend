package com.ajouway.infra.persistence.entity.map;

import com.ajouway.domain.enums.AmenityInfoType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Map;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "amenity_info")
public class AmenityInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_info_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AmenityInfoType amenityInfoType;

    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> properties;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_makrer_id", nullable = false)
    private BuildingMarker buildingMarker;

    @Builder
    private AmenityInfo(final AmenityInfoType amenityInfoType, final Map<String, Object> properties,
                        final BuildingMarker buildingMarker) {
        this.amenityInfoType = amenityInfoType;
        this.properties = properties;
        this.buildingMarker = buildingMarker;
    }

    public static AmenityInfo create(final AmenityInfoType amenityInfoType, final Map<String, Object> properties,
                                     final BuildingMarker buildingMarker) {
        return AmenityInfo.builder()
                .amenityInfoType(amenityInfoType)
                .properties(properties)
                .buildingMarker(buildingMarker)
                .build();
    }

}
