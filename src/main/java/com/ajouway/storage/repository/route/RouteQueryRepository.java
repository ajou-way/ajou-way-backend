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
        // A* 알고리즘 쿼리
        String sql = String.format("""
                SELECT r.seq, r.node, r.edge, r.cost, r.agg_cost,
                       ST_Y(ST_StartPoint(e.geom)) AS lat,
                       ST_X(ST_StartPoint(e.geom)) AS lng
                FROM pgr_astar(
                    'SELECT id, source, target, cost, reverse_cost,
                            ST_X(ST_StartPoint(geom)) AS x1,
                            ST_Y(ST_StartPoint(geom)) AS y1,
                            ST_X(ST_EndPoint(geom)) AS x2,
                            ST_Y(ST_EndPoint(geom)) AS y2
                     FROM edges',
                    %d, %d, false
                ) AS r
                JOIN edges e ON r.edge = e.id
                WHERE r.edge != -1
                ORDER BY r.seq
                """, startNode, endNode);

        // 경로 노드 조회
        List<RouteNode> nodes = jdbcTemplate.query(sql, (rs, rowNum) ->
                new RouteNode(
                        rs.getLong("node"),
                        rs.getDouble("lat"),
                        rs.getDouble("lng"),
                        rs.getDouble("agg_cost")
                )
        );

        if (nodes.isEmpty()) return null;

        // 누적 거리 계산
        double totalCost = nodes.get(nodes.size() - 1).getAggCost(); // agg_cost 마지막 값 사용

        return new RouteResult(nodes, totalCost);
    }

}
