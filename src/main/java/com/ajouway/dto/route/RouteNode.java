package com.ajouway.dto.route;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RouteNode {
    private Long nodeId;
    private double lat;
    private double lng;
    private double aggCost;
}
