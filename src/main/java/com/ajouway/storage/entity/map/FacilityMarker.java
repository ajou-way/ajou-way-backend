//package com.ajouway.infra.persistence.entity.map;
//
//import com.ajouway.domain.enums.FacilityMarkerType;
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.locationtech.jts.geom.Point;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Table(name = "facility_marker")
//public class FacilityMarker {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="facility_marker_id")
//    private Long id;
//
//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private FacilityMarkerType facilityMarkerType;
//
//    @Column(columnDefinition = "geometry(Point, 4326)", nullable = false)
//    private Point geometry;
//
//    private String remarks;
//
//    @Column(name = "img_url")
//    private String imgUrl;
//
//    private String locationInform;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "building_makrer_id")
//    private BuildingMarker buildingMarker;
//}
