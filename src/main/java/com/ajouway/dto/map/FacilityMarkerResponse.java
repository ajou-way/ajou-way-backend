//package com.ajouway.dto.marker;
//
//import com.ajouway.domain.enums.FacilityMarkerType;
//import com.ajouway.domain.persistence.entity.map.FacilityMarker;
//import com.ajouway.dto.GeoJsonPoint;
//import lombok.Builder;
//
//@Builder
//public record FacilityMarkerResponse(
//        Long id,
//        FacilityMarkerType facilityMarkerType,
//        GeoJsonPoint geometry,
//        String remarks,
//        String imgUrl,
//        Long buildingId,
//        String buildingName
//) {
//    public static FacilityMarkerResponse fromEntity(final FacilityMarker marker){
//        return FacilityMarkerResponse.builder()
//                .id(marker.getId())
//                .markerType(marker.getFacilityMarkerType())
//                .geometry(GeoJsonPoint.converter(marker.getGeometry()))
//                .remarks(marker.getRemarks())
//                .imgUrl(marker.getImgUrl())
//                .buildingId(marker.getBuildingMarker().getId())
//                .buildingName(marker.getBuildingMarker().getName())
//                .build();
//    }
//}
