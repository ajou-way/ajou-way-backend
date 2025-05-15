package com.ajouway.storage.entity.map;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="building_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
    private Point geometry;

    private String remarks;

    @Column(name = "img_url")
    private String imgUrl;

//  todo 배리어프리
//    @OneToMany(mappedBy = "buildingMarker", cascade = CascadeType.REMOVE, orphanRemoval = true)
//    private final List<FacilityMarker> facilityMarkers = new ArrayList<>();

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private final List<AmenityInfo> amenityInfos = new ArrayList<>();
}
