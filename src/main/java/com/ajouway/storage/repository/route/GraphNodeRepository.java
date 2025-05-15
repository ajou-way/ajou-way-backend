package com.ajouway.storage.repository.route;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class GraphNodeRepository {
    private final JdbcTemplate jdbcTemplate;

    public Long findNearestGraphNode(double lng, double lat) {
        String sql = """
                    SELECT id
                    FROM edges_vertices_pgr
                    ORDER BY the_geom <-> ST_Transform(ST_SetSRID(ST_Point(?, ?), 4326),3857)
                    LIMIT 1;
                """;
        try {
            return jdbcTemplate.queryForObject(sql, Long.class, lng, lat);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}