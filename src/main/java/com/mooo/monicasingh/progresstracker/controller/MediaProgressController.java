package com.mooo.monicasingh.progresstracker.controller;

import com.mooo.monicasingh.progresstracker.entity.MediaProgress;
import com.mooo.monicasingh.progresstracker.service.MediaProgressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/progress")
public class MediaProgressController {

    private final MediaProgressService service;

    public MediaProgressController(MediaProgressService service) {
        this.service = service;
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
}
