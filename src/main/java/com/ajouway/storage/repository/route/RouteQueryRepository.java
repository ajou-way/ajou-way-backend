package com.ajouway.storage.repository.route;

import com.ajouway.dto.route.RouteNode;
import com.ajouway.dto.route.RouteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RouteQueryRepository {
    private final JdbcTemplate jdbcTemplate;

    public RouteResult findPath(Long startNode, Long endNode) {
        String sql = String.format("""
                SELECT r.seq, r.node, r.edge, r.cost, r.agg_cost,
                       ST_Y(ST_StartPoint(w.geom)) AS lat,
                       ST_X(ST_StartPoint(w.geom)) AS lng
                FROM pgr_dijkstra(
                    'SELECT id, source, target, cost, reverse_cost
                     FROM ways',
                    %d, %d, false
                ) AS r
                JOIN ways w ON r.edge = w.id
                WHERE r.edge != -1
                ORDER BY r.seq
                """, startNode, endNode);
        List<RouteNode> nodes = jdbcTemplate.query(sql, (rs, rowNum) ->
                new RouteNode(
                        rs.getLong("node"),
                        rs.getDouble("lat"),
                        rs.getDouble("lng"),
                        rs.getDouble("agg_cost")
                )
        );

        if (nodes.isEmpty()) return null;

        double totalCost = nodes.get(nodes.size() - 1).getAggCost();

        return new RouteResult(nodes, totalCost);
    }

}
