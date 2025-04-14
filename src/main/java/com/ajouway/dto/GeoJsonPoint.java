package com.ajouway.dto;

import lombok.Builder;
import org.locationtech.jts.geom.Point;

@Builder
public record GeoJsonPoint(
        String type,
        double[] coordinates
) {
    public static GeoJsonPoint converter(final Point point) {
        return GeoJsonPoint.builder()
                .type("Point")
                .coordinates(new double[]{point.getX(), point.getY()})
                .build();
    }
}
