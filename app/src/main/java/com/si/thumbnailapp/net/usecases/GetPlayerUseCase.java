package com.si.thumbnailapp.net.usecases;

import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.repository.PlayerRepository;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class GetPlayerUseCase extends UseCase {
    private PlayerRepository playerRepository;
    private Player player;
    private Team team;

    public GetPlayerUseCase(PlayerRepository networkPlayerRepository) {
        this.playerRepository = networkPlayerRepository;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return playerRepository.getMessage(player, team);
    }
}
