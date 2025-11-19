package com.osrs.hiscores.service;

import com.osrs.hiscores.dto.SkillDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlayerStatsService {

    private List<String> skillNames = Arrays.asList(
                    "Overall", "Attack", "Defence", "Strength", "Hitpoints", "Ranged",
                    "Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing",
                    "Firemaking", "Crafting", "Smithing", "Mining", "Herblore", "Agility",
                    "Thieving", "Slayer", "Farming", "Runecraft", "Hunter", "Construction"
            );

    public List<SkillDto> parseSkillsFromCsv(List<String> csvLines) {

        List<SkillDto> skills = new ArrayList<>();

        for (int i = 0; i < 24; i++) {
            String[] parts = csvLines.get(i).split(",");

            String name = skillNames.get(i);
            long rank = Long.parseLong(parts[0]);
            int level = Integer.parseInt(parts[1]);
            long xp = Long.parseLong(parts[2]);

            SkillDto skill = new SkillDto(name, level, xp, rank);
            skills.add(skill);
        }
    return skills;

    }

    public Map<String, Object> getPlayerStats(String username) {
        Map<String, Object> stats = new HashMap<>();
        stats.put("username", username);
        stats.put("level", 126);
        stats.put("experience", 200_000_000);
        stats.put("rank", 114);
        return stats;
    }


}
