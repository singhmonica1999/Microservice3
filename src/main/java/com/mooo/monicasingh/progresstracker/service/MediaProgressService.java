package com.mooo.monicasingh.progresstracker.service;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import com.mooo.monicasingh.progresstracker.repository.MediaProgressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MediaProgressService {
    private final MediaProgressRepository MediaProgressRepository;

    public MediaProgressService(MediaProgressRepository MediaProgressRepository) {
        this.MediaProgressRepository = MediaProgressRepository;
    }

    public List<MediaProgress> getProgressByUserId(Long userId) {
        return MediaProgressRepository.findByUserId(userId);
    }

    public List<MediaProgress> getProgressByUserIdAndMediaType(Long userId, String mediaType) {
        return MediaProgressRepository.findByUserIdAndMediaType(userId, mediaType);
    }

    public MediaProgress saveProgress(MediaProgress progress) {
        progress.setLastUpdated(LocalDateTime.now());
        return MediaProgressRepository.save(progress);
    }

    public void deleteProgress(Long progressId) {
        MediaProgressRepository.deleteById(progressId);
    }
}
