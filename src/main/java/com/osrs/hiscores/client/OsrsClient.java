package com.osrs.hiscores.client;

import com.osrs.hiscores.dto.PlayerStatsDto;
import com.osrs.hiscores.dto.SkillDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OsrsClient {

    private final List<String> skillNames = Arrays.asList(
            "Overall", "Attack", "Defence", "Strength", "Hitpoints", "Ranged",
            "Prayer", "Magic", "Cooking", "Woodcutting", "Fletching", "Fishing",
            "Firemaking", "Crafting", "Smithing", "Mining", "Herblore", "Agility",
            "Thieving", "Slayer", "Farming", "Runecraft", "Hunter", "Construction", "Sailing"
    );

    public PlayerStatsDto getPlayerStats(String username) {
        // 1. Bouw de URL
        try {
            String url = "https://secure.runescape.com/m=hiscore_oldschool/index_lite.ws?player=" + username;

            // 2. Download de CSV als 1 grote tekst
            String csv = new String(new java.net.URL(url).openStream().readAllBytes());

            // 3. Split de tekst in regels
            List<String> lines = Arrays.asList(csv.split("\n"));

            // 4. Parser gebruiken
            List<SkillDto> skills = HiscoresParser(lines);

            // 5. DTO aanmaken en username + skills erin
            PlayerStatsDto playerStats = new PlayerStatsDto();
            playerStats.setSkills(skills);
            playerStats.setUsername(username);

            // 6. Combat lvl krijgen
            String[] combatParts = lines.get(25).split(",");
            int combatLevel = Integer.parseInt(combatParts[1]);
            playerStats.setCombatLevel(combatLevel);

            return playerStats;

        } catch (Exception e) {
            throw new RuntimeException("Could not get data for player: " + username);
        }
    }

    public List<SkillDto> HiscoresParser(List<String> csvLines) {
        List<SkillDto> skills = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
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
}
