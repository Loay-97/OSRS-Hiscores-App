package com.osrs.hiscores.dto;

public class SkillDto {

    private String name;
    private int level;
    private long xp;
    private long rank;

    public SkillDto(String name, int level, long xp, long rank){
        this.name = name;
        this.level = level;
        this.xp = xp;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public long getRank() {
        return rank;
    }

    public void setRank(long rank) {
        this.rank = rank;
    }

}
