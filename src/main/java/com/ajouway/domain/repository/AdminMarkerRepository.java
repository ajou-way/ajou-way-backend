package com.ajouway.domain.repository;

import com.ajouway.storage.entity.map.AdminMarkerEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminMarkerRepository {
    AdminMarkerEntity save(AdminMarkerEntity adminMarkerEntity);

    void deleteById(Long id);

    AdminMarkerEntity getById(Long id);

    List<AdminMarkerEntity> findAllByDeadlineAfter(LocalDateTime now);
}
