package com.mooo.monicasingh.progresstracker.repository;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaProgressRepository extends JpaRepository<MediaProgress, Long> {
    Optional<MediaProgress> findByUserIdAndMediaId(Long userId, Long mediaId);
}

