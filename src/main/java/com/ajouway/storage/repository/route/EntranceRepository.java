package com.ajouway.storage.repository.route;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EntranceRepository {
    private final JdbcTemplate jdbcTemplate;
    private final GraphNodeRepository graphNodeRepository;

    public List<Long> findEntranceNodeIdsNearBuilding(Long osmId) {
        String sql = """
                    SELECT DISTINCT ON (p.osm_id)
                       w_dist.source AS node_id
                     FROM planet_osm_point p
                     JOIN LATERAL (
                         SELECT w.source,
                                ST_Distance(
                                    ST_Transform(p.way, 3857),
                                    w.geom
                                ) AS dist
                         FROM ways w
                         ORDER BY dist
                         LIMIT 1
                     ) w_dist ON TRUE
                     WHERE p.tags -> 'entrance' IS NOT NULL
                       AND ST_DWithin(
                         ST_Transform(p.way, 3857),
                         ST_Transform((SELECT way FROM planet_osm_polygon WHERE osm_id = ?), 3857),
                         5
                     );
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("node_id"), osmId);
    }
}