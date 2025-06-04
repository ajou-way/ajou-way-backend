package com.ajouway.dto;

import lombok.Builder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

@Builder
public record GeoJsonPoint(
        String type,
        double[] coordinates
) {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();

    public static GeoJsonPoint converter(final Point point) {
        return GeoJsonPoint.builder()
                .type("Point")
                .coordinates(new double[]{point.getX(), point.getY()})
                .build();
    }

    public Point toPoint() {
        return GEOMETRY_FACTORY.createPoint(new Coordinate(
                coordinates[0],
                coordinates[1]
        ));
    }
}
