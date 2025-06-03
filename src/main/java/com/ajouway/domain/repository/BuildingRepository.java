package com.ajouway.domain.repository;

import com.ajouway.storage.entity.map.BuildingEntity;

import java.util.List;

public interface BuildingRepository {
    BuildingEntity getById(Long id);

    List<BuildingEntity> findAll();
}
