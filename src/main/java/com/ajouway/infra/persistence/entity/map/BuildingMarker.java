package com.ajouway.infra.persistence.entity.map;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "building_marker")
public class BuildingMarker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="building_marker_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
    private Point geometry;

    private String remarks;

    @Column(name = "img_url")
    private String imgUrl;

//    @OneToMany(mappedBy = "buildingMarker", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private final List<FacilityMarker> facilityMarkers = new ArrayList<>();

    @OneToMany(mappedBy = "buildingMarker")
    private final List<AmenityInfo> amenityInfos = new ArrayList<>();
}
