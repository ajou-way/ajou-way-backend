package com.ajouway.infra.persistence;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.domain.repository.BuildingRepository;
import com.ajouway.storage.entity.map.BuildingEntity;
import com.ajouway.storage.repository.map.BuildingJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BuildingRepositoryImpl implements BuildingRepository {
    private final BuildingJpaRepository buildingJpaRepository;


    @Override
    public BuildingEntity getById(final Long id) {
        return buildingJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionInfo.BUILDING_NOT_FOUND));
    }

    @Override
    public List<BuildingEntity> findAll() {
        return buildingJpaRepository.findAll();
    }
}
