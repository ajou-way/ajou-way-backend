package com.ajouway.infra.persistence;

import com.ajouway.domain.repository.AdminMarkerRepository;
import com.ajouway.storage.entity.map.AdminMarkerEntity;
import com.ajouway.storage.repository.map.AdminMarkerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminMarkerRepositoryImpl implements AdminMarkerRepository {
    private final AdminMarkerJpaRepository adminMarkerJpaRepository;

    @Override
    public AdminMarkerEntity save(AdminMarkerEntity adminMarkerEntity) {
        return adminMarkerJpaRepository.save(adminMarkerEntity);
    }

    @Override
    public void deleteById(Long id) {
        adminMarkerJpaRepository.deleteById(id);
    }
}
