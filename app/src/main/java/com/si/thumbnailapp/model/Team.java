package com.si.thumbnailapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class Team {

    @SerializedName("Id")
    private int id;
    @SerializedName("Name")
    private String teamName;
    @SerializedName("Settings")
    private Setting setting;
    @SerializedName("Players")
    private List<Player> players;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
