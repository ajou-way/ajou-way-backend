package com.ajouway.domain.service.admin;

import com.ajouway.domain.repository.AdminMarkerRepository;
import com.ajouway.dto.admin.request.AdminMarkerRequest;
import com.ajouway.dto.admin.request.AdminMarkerUpdateRequest;
import com.ajouway.dto.admin.response.AdminMarkerResponse;
import com.ajouway.storage.entity.map.AdminMarkerEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminMarkerService {
    private final AdminMarkerRepository adminMarkerRepository;


    @Transactional
    public AdminMarkerResponse addMarker(final AdminMarkerRequest request) {
        AdminMarkerEntity adminMarkerEntity = request.toEntity();
        adminMarkerRepository.save(adminMarkerEntity);
        return AdminMarkerResponse.fromEntity(adminMarkerEntity);
    }

    @Transactional
    public void deleteMarker(final Long markerId) {
        adminMarkerRepository.deleteById(markerId);
    }

    @Transactional
    public AdminMarkerResponse updateMarker(final Long markerId, final AdminMarkerUpdateRequest request) {
        AdminMarkerEntity adminMarkerEntity = adminMarkerRepository.getById(markerId);
        adminMarkerEntity.update(
                request.title(),
                request.contents(),
                request.toJson(),
                request.deadline()
        );
        return AdminMarkerResponse.fromEntity(adminMarkerRepository.save(adminMarkerEntity));
    }
}
