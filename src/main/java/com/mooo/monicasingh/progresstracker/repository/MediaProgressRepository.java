package com.mooo.monicasingh.progresstracker.repository;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MediaProgressRepository extends JpaRepository<MediaProgress, Long> {
    List<MediaProgress> findByUserId(Long userId);
    List<MediaProgress> findByUserIdAndMediaType(Long userId, String mediaType);
}

