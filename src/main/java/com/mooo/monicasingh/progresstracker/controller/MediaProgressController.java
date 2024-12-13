package com.mooo.monicasingh.progresstracker.controller;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import com.mooo.monicasingh.progresstracker.publisher.ProgressPublisher;
import com.mooo.monicasingh.progresstracker.service.MediaProgressService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/progress")
@Validated
public class MediaProgressController {

    private final MediaProgressService service;
    private final ProgressPublisher progressPublisher;

    public MediaProgressController(MediaProgressService service, ProgressPublisher progressPublisher) {
        this.progressPublisher = progressPublisher;
        this.service = service;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MediaProgress>> getProgressByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getProgressByUserId(userId));
    }

    @GetMapping("/user/{userId}/type/{mediaType}")
    public ResponseEntity<List<MediaProgress>> getProgressByUserIdAndMediaType(
            @PathVariable Long userId,
            @PathVariable String mediaType) {
        return ResponseEntity.ok(service.getProgressByUserIdAndMediaType(userId, mediaType));
    }

    @PostMapping
    public ResponseEntity<MediaProgress> saveProgress(@RequestBody MediaProgress progress) {
        return ResponseEntity.ok(service.saveProgress(progress));
    }

    @DeleteMapping("/{progressId}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long progressId) {
        service.deleteProgress(progressId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("{userId}")
    public String updateProgress(@PathVariable String userId, @RequestBody String progressUpdate) {
        progressPublisher.publishProgressUpdate(userId, progressUpdate);
        return "Progress update sent for user: " + userId;
    }
}
