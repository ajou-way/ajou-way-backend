package com.ajouway.storage.entity.map;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "admin_marker")
public class AdminMarkerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_marker_id")
    private Long id;

    @Column(nullable = false)
    @Size(min = 2, max = 20)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private Point geometry;

    @Lob
    @Column(name = "major_json", columnDefinition = "TEXT", nullable = false)
    private String majorJson;

    @Column(nullable = false)
    private LocalDateTime deadline;

    @Builder
    private AdminMarkerEntity(
            final String title,
            final String contents,
            final Point geometry,
            final String majorJson,
            final LocalDateTime deadline
    ) {
        this.title = title;
        this.contents = contents;
        this.geometry = geometry;
        this.majorJson = majorJson;
        this.deadline = deadline;
    }

    AdminMarkerEntity create(
            final String title,
            final String contents,
            final Point geometry,
            final String majorJson,
            final LocalDateTime deadline
    ) {
        return AdminMarkerEntity.builder()
                .title(title)
                .contents(contents)
                .geometry(geometry)
                .majorJson(majorJson)
                .deadline(deadline)
                .build();
    }
}
