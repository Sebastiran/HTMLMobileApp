package com.example.sebastiaan.minecraftbuildchallenge;

public class Assignment {

    private long id;
    private String build, block, biome;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getBuild() {
        return build;
    }
    public void setBuild(String build) {
        this.build = build;
    }
    public String getBlock() {
        return block;
    }
    public void setBlock(String block) {
        this.block = block;
    }
    public String getBiome() {
        return biome;
    }
    public void setBiome(String biome) {
        this.biome = biome;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return build;
    }
}