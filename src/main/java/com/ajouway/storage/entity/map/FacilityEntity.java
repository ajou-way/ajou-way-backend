package com.ajouway.storage.entity.map;

import com.ajouway.domain.enums.FacilityType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "facility")
public class FacilityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="facility_id")
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FacilityType facilityType;

    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
    private Point geometry;

    private String remarks;

    @Column(name = "img_url")
    private String imgUrl;

    private String locationInform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private BuildingEntity building;
}
