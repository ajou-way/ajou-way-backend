package com.ajouway.controller.map;

import com.ajouway.dto.ListWrapperResponse;
import com.ajouway.dto.map.BuildingAmenityAddRequest;
import com.ajouway.dto.map.BuildingResponse;
import com.ajouway.domain.service.map.MapService;
import com.ajouway.dto.map.BuildingSimpleResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;

//    todo 배리어프리
//    @GetMapping
//    public ListWrapperResponse<FacilityMarkerResponse> getFacilityMarkers() {
//        return ListWrapperResponse.of(markerService.getFacilityMarkers());
//    }
//
//    @GetMapping("/type")
//    public ListWrapperResponse<FacilityMarkerResponse> getFacilityMarkersForType(@RequestParam("facilityMarkerType") FacilityMarkerType facilityMarkerType) {
//        return ListWrapperResponse.of(markerService.getFacilityMarkersForType(facilityMarkerType));
//    }

    @GetMapping("/buildings/{buildingId}")
    public BuildingResponse getBuildingMarker(@PathVariable("buildingId") Long buildingId) {
        return mapService.getBuildingMarker(buildingId);
    }

    @CrossOrigin("*")
    @GetMapping("/buildings")
    public ListWrapperResponse<BuildingSimpleResponse> getBuildingMarkers(){
        return ListWrapperResponse.of(mapService.getBuildingMarkers());
    }
}
