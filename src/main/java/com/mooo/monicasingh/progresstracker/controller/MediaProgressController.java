package com.mooo.monicasingh.progresstracker.controller;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import com.mooo.monicasingh.progresstracker.service.MediaProgressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/progress")
public class MediaProgressController {

    private final MediaProgressService service;

    public MediaProgressController(MediaProgressService service) {
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
}
