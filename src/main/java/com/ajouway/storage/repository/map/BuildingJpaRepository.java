package com.ajouway.storage.repository.map;

import com.ajouway.storage.entity.map.BuildingEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BuildingJpaRepository extends JpaRepository<BuildingEntity, Long> {
}
