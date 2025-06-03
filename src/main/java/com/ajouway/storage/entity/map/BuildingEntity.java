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
public class BuildingEntity {
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

    @OneToMany(mappedBy = "building", fetch = FetchType.LAZY)
    private final List<AmenityInfoEntity> amenityInfos = new ArrayList<>();
}
