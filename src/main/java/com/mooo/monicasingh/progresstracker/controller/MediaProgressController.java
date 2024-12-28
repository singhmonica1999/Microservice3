package com.mooo.monicasingh.progresstracker.controller;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import com.mooo.monicasingh.progresstracker.publisher.ProgressPublisher;
import com.mooo.monicasingh.progresstracker.service.MediaProgressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/progress")
public class MediaProgressController {

    private final MediaProgressService service;
    private final ProgressPublisher progressPublisher;

    public MediaProgressController(MediaProgressService service, ProgressPublisher progressPublisher) {
        this.service = service;
        this.progressPublisher = progressPublisher;
    }

    @PostMapping
    public ResponseEntity<MediaProgress> saveOrUpdateProgress(@Valid @RequestBody MediaProgress progress) {
        return ResponseEntity.ok(service.saveOrUpdateProgress(progress));
    }

    @GetMapping
    public ResponseEntity<MediaProgress> getProgress(
            @RequestParam Long userId,
            @RequestParam Long mediaId) {
        return service.getProgress(userId, mediaId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}")
    public String updateProgress(@PathVariable String userId, @RequestBody String progressUpdate) {
        progressPublisher.sendProgressUpdate(userId, progressUpdate);
        return "Progress update sent for user: " + userId;
    }
}
