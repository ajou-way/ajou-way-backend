package com.ajouway.dto.route;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RouteResult {
    private List<RouteNode> nodes;
    private double cost;
}
