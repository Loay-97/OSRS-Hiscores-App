package com.osrs.hiscores.controller;

import com.osrs.hiscores.service.PlayerStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class PlayerStatsController {

    @Autowired
    private PlayerStatsService playerStatsService;

    @GetMapping("/player/{username}")
    public Map<String, Object> getPlayerStats(@PathVariable String username) {
        return playerStatsService.getPlayerStats(username);
    }




    }


