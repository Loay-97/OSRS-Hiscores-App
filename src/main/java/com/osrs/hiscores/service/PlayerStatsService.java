package com.osrs.hiscores.service;

import com.osrs.hiscores.client.OsrsClient;
import com.osrs.hiscores.dto.PlayerStatsDto;
import com.osrs.hiscores.exception.PlayerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerStatsService {

    @Autowired
    private OsrsClient osrsClient;

    public PlayerStatsDto getPlayerStats(String username) {
        try {
            return osrsClient.getPlayerStats(username);
        } catch (RuntimeException e) {
            // Fout opgevangen van OsrsClient → speler bestaat niet
            throw new PlayerNotFoundException(username);
        }
    }
}
