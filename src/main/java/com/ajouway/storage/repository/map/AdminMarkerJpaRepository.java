package com.ajouway.storage.repository.map;

import com.ajouway.storage.entity.map.AdminMarkerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AdminMarkerJpaRepository extends JpaRepository<AdminMarkerEntity, Long> {
    List<AdminMarkerEntity> findAllByDeadlineAfter(LocalDateTime now);
}
