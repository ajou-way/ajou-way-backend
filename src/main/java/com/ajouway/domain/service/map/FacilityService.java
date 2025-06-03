package com.ajouway.domain.service.map;

import com.ajouway.domain.enums.FacilityType;
import com.ajouway.domain.repository.FacilityRepository;
import com.ajouway.dto.map.FacilityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FacilityService {
    private final FacilityRepository facilityRepository;

    public List<FacilityResponse> getFacilityMarkers() {
        return facilityRepository.findAll()
                .stream()
                .map(FacilityResponse::fromEntity)
                .toList();
    }

    public List<FacilityResponse> getFacilityMarkersForType(final FacilityType facilityMarkerType) {
        return facilityRepository.findByFacilityType(facilityMarkerType)
                .stream()
                .map(FacilityResponse::fromEntity)
                .toList();
    }
}
