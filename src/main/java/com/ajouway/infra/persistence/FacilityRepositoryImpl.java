package com.ajouway.infra.persistence;

import com.ajouway.domain.enums.FacilityType;
import com.ajouway.domain.repository.FacilityRepository;
import com.ajouway.storage.entity.map.FacilityEntity;
import com.ajouway.storage.repository.map.FacilityJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class FacilityRepositoryImpl implements FacilityRepository {
    private final FacilityJpaRepository facilityJpaRepository;


    @Override
    public List<FacilityEntity> findAll(){
        return facilityJpaRepository.findAll();
    }

    @Override
    public List<FacilityEntity> findByFacilityType(final FacilityType facilityType) {
        return facilityJpaRepository.findAllByFacilityType(facilityType);
    }
}
