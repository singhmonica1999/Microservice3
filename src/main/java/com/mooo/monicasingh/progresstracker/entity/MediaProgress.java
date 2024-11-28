package com.mooo.monicasingh.progresstracker.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "media_progress")
public class MediaProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "media_id", nullable = false)
    private Long mediaId;

    @Column(name = "current_position")
    private Integer currentPosition;

    @Column(name = "completed", nullable = false)
    private Boolean completed = false;

    @Column(name = "last_updated", nullable = false, updatable = false)
    private LocalDateTime lastUpdated = LocalDateTime.now();
}
