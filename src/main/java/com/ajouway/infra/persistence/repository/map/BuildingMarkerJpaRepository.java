package com.ajouway.infra.persistence.repository.map;

import com.ajouway.infra.persistence.entity.map.BuildingMarker;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BuildingMarkerJpaRepository extends JpaRepository<BuildingMarker, Long> {
}
