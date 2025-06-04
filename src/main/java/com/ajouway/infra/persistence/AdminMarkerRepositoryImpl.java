package com.ajouway.infra.persistence;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.domain.repository.AdminMarkerRepository;
import com.ajouway.storage.entity.map.AdminMarkerEntity;
import com.ajouway.storage.repository.map.AdminMarkerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AdminMarkerRepositoryImpl implements AdminMarkerRepository {
    private final AdminMarkerJpaRepository adminMarkerJpaRepository;

    @Override
    public AdminMarkerEntity save(final AdminMarkerEntity adminMarkerEntity) {
        return adminMarkerJpaRepository.save(adminMarkerEntity);
    }

    @Override
    public void deleteById(final Long id) {
        adminMarkerJpaRepository.deleteById(id);
    }

    @Override
    public AdminMarkerEntity getById(final Long id) {
        return adminMarkerJpaRepository.findById(id)
                .orElseThrow(() -> new CustomException(CustomExceptionInfo.NOT_FOUND_MARKER));
    }

    @Override
    public List<AdminMarkerEntity> findAllByDeadlineAfter(final LocalDateTime now) {
        return adminMarkerJpaRepository.findAllByDeadlineAfter(now);
    }
}
