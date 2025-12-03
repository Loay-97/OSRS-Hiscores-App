package com.osrs.hiscores.dto;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatsDto {
    private String username;
    private int combatLevel;
    private List<SkillDto> skills;

    public PlayerStatsDto(){
        this.skills = new ArrayList<>();
    }

    public PlayerStatsDto(String username, int combatLevel, List<SkillDto> skills) {
        this.username = username;
        this.combatLevel = combatLevel;
        this.skills = skills;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getCombatLevel() {
        return combatLevel;
    }

    public void setCombatLevel(int combatLevel) {
        this.combatLevel = combatLevel;
    }

    public List<SkillDto> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillDto> skills) {
        this.skills = skills;
    }




}