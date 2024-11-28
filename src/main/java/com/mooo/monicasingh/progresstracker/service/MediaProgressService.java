package com.mooo.monicasingh.progresstracker.service;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import com.mooo.monicasingh.progresstracker.repository.MediaProgressRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MediaProgressService {

    private final MediaProgressRepository repository;

    public MediaProgressService(MediaProgressRepository repository) {
        this.repository = repository;
    }

    public MediaProgress saveOrUpdateProgress(MediaProgress progress) {
        Optional<MediaProgress> existingProgress = repository.findByUserIdAndMediaId(progress.getUserId(), progress.getMediaId());
        existingProgress.ifPresent(existing -> progress.setId(existing.getId()));
        return repository.save(progress);
    }

    public Optional<MediaProgress> getProgress(Long userId, Long mediaId) {
        return repository.findByUserIdAndMediaId(userId, mediaId);
    }
}

