package com.osrs.hiscores.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "player_lookup_history")
public class PlayerLookupHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // De OSRS username die wordt opgezocht
    @Column(name = "player_username", nullable = false, unique = true)
    private String playerUsername;

    // Hoe vaak deze speler is opgezocht
    @Column(name = "lookup_count", nullable = false)
    private int lookupCount = 0;

    // Datum + tijd wanneer deze speler voor het laatst is opgezocht
    @Column(name = "last_lookup_at")
    private LocalDateTime lastLookupAt;

    public PlayerLookupHistory() {}

    public PlayerLookupHistory(String playerUsername) {
        this.playerUsername = playerUsername;
        this.lookupCount = 1;
        this.lastLookupAt = LocalDateTime.now();
    }

    // Getters & setters

    public Long getId() {
        return id;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public int getLookupCount() {
        return lookupCount;
    }

    public void setLookupCount(int lookupCount) {
        this.lookupCount = lookupCount;
    }

    public LocalDateTime getLastLookupAt() {
        return lastLookupAt;
    }

    public void setLastLookupAt(LocalDateTime lastLookupAt) {
        this.lastLookupAt = lastLookupAt;
    }
}
