package com.osrs.hiscores.controller;

import com.osrs.hiscores.entity.PlayerLookupHistory;
import com.osrs.hiscores.repository.PlayerLookupHistoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/player-lookup-history")
public class PlayerLookupHistoryController {

    private final PlayerLookupHistoryRepository repository;

    public PlayerLookupHistoryController(PlayerLookupHistoryRepository repository) {
        this.repository = repository;
    }

    // Haal alle history
    @GetMapping
    public List<PlayerLookupHistory> getAll() {
        return repository.findAll();
    }

    // Voeg een lookup toe of update count
    @PostMapping
    public ResponseEntity<?> addOrUpdate(@RequestBody Map<String, String> payload) {
        String username = payload.get("playerUsername");
        if (username == null || username.isBlank()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        PlayerLookupHistory existing = repository.findByPlayerUsernameIgnoreCase(username).orElse(null);

        if (existing != null) {
            existing.setLookupCount(existing.getLookupCount() + 1);
            existing.setLastLookupAt(LocalDateTime.now());
            repository.save(existing);
            return ResponseEntity.ok(existing);
        } else {
            // Voeg nieuwe speler toe als die nog niet bestaat
            PlayerLookupHistory newEntry = new PlayerLookupHistory(username);
            repository.save(newEntry);
            return ResponseEntity.status(HttpStatus.CREATED).body(newEntry);
        }
    }
}
