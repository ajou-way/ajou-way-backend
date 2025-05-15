package com.ajouway.domain.repository;

import com.ajouway.storage.entity.map.Building;

import java.util.List;

public interface BuildingRepository {
    Building getById(Long id);

    List<Building> findAll();
}
