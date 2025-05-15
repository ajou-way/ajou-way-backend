package com.ajouway.domain.service.route;

import com.ajouway.common.exception.CustomException;
import com.ajouway.common.exception.CustomExceptionInfo;
import com.ajouway.dto.route.RouteResult;
import com.ajouway.storage.repository.route.EntranceRepository;
import com.ajouway.storage.repository.route.GraphNodeRepository;
import com.ajouway.storage.repository.route.OsmPolygonRepository;
import com.ajouway.storage.repository.route.RouteQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteService {
    private final OsmPolygonRepository osmPolygonRepository;
    private final GraphNodeRepository graphNodeRepository;
    private final EntranceRepository entranceRepository;
    private final RouteQueryRepository routeQueryRepository;

    public List<RouteResult> findRouteCandidates(double startLng, double startLat,
                                                 double endLng, double endLat) {

        Optional<Long> startOsmId = osmPolygonRepository.findContainingBuilding(startLng, startLat);
        Optional<Long> endOsmId = osmPolygonRepository.findContainingBuilding(endLng, endLat);

        List<Long> startNodes = startOsmId.isPresent()
                ? entranceRepository.findEntranceNodeIdsNearBuilding(startOsmId.get())
                : Optional.ofNullable(graphNodeRepository.findNearestGraphNode(startLng, startLat))
                .map(List::of).orElseThrow(() -> new CustomException(CustomExceptionInfo.ROUTE_ERROR));

        List<Long> endNodes = endOsmId.isPresent()
                ? entranceRepository.findEntranceNodeIdsNearBuilding(endOsmId.get())
                : Optional.ofNullable(graphNodeRepository.findNearestGraphNode(endLng, endLat))
                .map(List::of).orElseThrow(() -> new CustomException(CustomExceptionInfo.ROUTE_ERROR));

        List<RouteResult> candidates = new ArrayList<>();
        for (Long s : startNodes) {
            for (Long e : endNodes) {
                RouteResult route =  routeQueryRepository.findPath(s, e);
                if (route != null && route.getCost() > 0) {
                    candidates.add(route);
                }
            }
        }

        if (candidates.isEmpty()) {
            throw new CustomException(CustomExceptionInfo.ROUTE_ERROR);
        }

        return candidates.stream()
                .sorted(Comparator.comparingDouble(RouteResult::getCost))
                .limit(3)
                .toList();
    }
}
