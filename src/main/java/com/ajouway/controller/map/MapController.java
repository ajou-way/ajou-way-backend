package com.ajouway.controller.map;

import com.ajouway.common.security.jwt.JwtUtil;
import com.ajouway.domain.enums.FacilityType;
import com.ajouway.domain.service.map.FacilityService;
import com.ajouway.dto.ListWrapperResponse;
import com.ajouway.dto.map.response.FacilityResponse;
import com.ajouway.dto.map.response.BuildingResponse;
import com.ajouway.domain.service.map.MapService;
import com.ajouway.dto.map.response.BuildingSimpleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maps")
public class MapController {

    private final MapService mapService;
    private final FacilityService facilityService;

    @GetMapping("/facilities")
    public ListWrapperResponse<FacilityResponse> getFacilityMarkers() {
        return ListWrapperResponse.of(facilityService.getFacilityMarkers());
    }

    @GetMapping("/facilities/type")
    public ListWrapperResponse<FacilityResponse> getFacilityMarkersForType(@RequestParam("facilityMarkerType") FacilityType facilityMarkerType) {
        return ListWrapperResponse.of(facilityService.getFacilityMarkersForType(facilityMarkerType));
    }

    @GetMapping("/buildings/{buildingId}")
    public BuildingResponse getBuildingMarker(@PathVariable("buildingId") Long buildingId) {
        return mapService.getBuildingMarker(buildingId);
    }

    @GetMapping("/buildings")
    public ListWrapperResponse<BuildingSimpleResponse> getBuildingMarkers() {
        return ListWrapperResponse.of(mapService.getBuildingMarkers());
    }
}
