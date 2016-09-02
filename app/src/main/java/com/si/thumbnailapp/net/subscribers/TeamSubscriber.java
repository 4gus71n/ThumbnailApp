package com.si.thumbnailapp.net.subscribers;

import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;

import java.util.List;

import rx.Subscriber;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * This subscriber is supposed to work with {@link com.si.thumbnailapp.net.services.ChallengeService}
 */
public abstract class TeamSubscriber extends Subscriber<Team> {
    @Override
    public void onCompleted() {
        //Empty by default
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //Empty by default
    }

    @Override
    public void onNext(Team team) {
        onTeamReceived(team);
        onPlayersReceived(team, team.getPlayers());
    }

    protected abstract void onTeamReceived(Team team);

    protected abstract void onPlayersReceived(Team team, List<Player> players);
}
