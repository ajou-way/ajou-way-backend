package com.ajouway.controller.admin;

import com.ajouway.domain.service.map.MapService;
import com.ajouway.dto.map.response.AmenityInfoResponse;
import com.ajouway.dto.map.request.BuildingAmenityAddRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/maps")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminMapsController {
    private final MapService mapService;

    @PostMapping("/buildings/{buildingId}/amenity")
    public AmenityInfoResponse addAmenityForBuilding(@PathVariable Long buildingId, @RequestBody @Valid BuildingAmenityAddRequest request) {
        return mapService.addAmenityForBuilding(buildingId, request);
    }

    @PutMapping("/buildings/{buildingId}/amenity/{amenityId}")
    public AmenityInfoResponse updateAmenityForBuilding(@PathVariable Long buildingId,
                                                        @PathVariable Long amenityId, @RequestBody @Valid BuildingAmenityAddRequest request) {
        return mapService.updateAmenityForBuilding(amenityId, request);
    }
}
