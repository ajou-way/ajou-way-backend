package com.ajouway.controller.route;

import com.ajouway.domain.service.route.RouteService;
import com.ajouway.dto.ListWrapperResponse;
import com.ajouway.dto.route.RouteResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/maps")
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/routes")
    public ListWrapperResponse<RouteResult> findRoute(@RequestParam Double startLng, @RequestParam Double startLat,
                                                      @RequestParam Double endLng, @RequestParam Double endLat) {
        return ListWrapperResponse.of(routeService.findRouteCandidates(startLng, startLat, endLng, endLat));
    }
}
