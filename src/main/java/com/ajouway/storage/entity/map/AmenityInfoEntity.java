package com.ajouway.storage.entity.map;

import com.ajouway.domain.enums.AmenityInfoType;
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
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "amenity_info")
public class AmenityInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amenity_info_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AmenityInfoType amenityInfoType;

    @Column(columnDefinition = "text", nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id", nullable = false)
    private BuildingEntity building;

    @Builder
    private AmenityInfoEntity(final AmenityInfoType amenityInfoType, final String contents,
                              final BuildingEntity building) {
        this.amenityInfoType = amenityInfoType;
        this.contents = contents;
        this.building = building;
    }

    public static AmenityInfoEntity create(final AmenityInfoType amenityInfoType, final String contents,
                                           final BuildingEntity building) {
        return AmenityInfoEntity.builder()
                .amenityInfoType(amenityInfoType)
                .contents(contents)
                .building(building)
                .build();
    }

    public AmenityInfoEntity update(final String contents) {
        this.contents = contents;
        return this;
    }

}
