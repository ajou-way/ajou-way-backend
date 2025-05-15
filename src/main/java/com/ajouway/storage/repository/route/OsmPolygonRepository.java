package com.ajouway.storage.repository.route;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OsmPolygonRepository {
    private final EntityManager em;

    public Optional<Long> findContainingBuilding(double lng, double lat) {
        String sql = """
            SELECT osm_id
            FROM planet_osm_polygon
            WHERE building IS NOT NULL
              AND ST_Contains(
                way,
                ST_Transform(ST_SetSRID(ST_MakePoint(:lng, :lat), 4326), 3857)
              )
            LIMIT 1
        """;

        List<?> result = em.createNativeQuery(sql)
                .setParameter("lng", lng)
                .setParameter("lat", lat)
                .getResultList();

        return result.isEmpty() ? Optional.empty() : Optional.of(((Number) result.get(0)).longValue());
    }
}
