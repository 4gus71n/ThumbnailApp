package com.si.thumbnailapp.net.rest;

import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.net.services.ChallengeService;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * Concrete implementation of the {@link RestApi} interface. The network layer implementation,
 * in this class is where we call the Retrofit services.
 * Commonly known as "RestApiImpl", but since I don't like using "Impl" as suffix I change It to this.
 * {@see http://stackoverflow.com/a/2814831/1403997}
 */
public class ServiceRestApi implements RestApi {
    private ChallengeService challengeService;

    public ServiceRestApi(ChallengeService challengeService) {
        this.challengeService = challengeService;
    }

    @Override
    public Observable<ResponseBody> getMessage(Player player, Team team) {
        return challengeService.getMessage(team.getId(), player.getId(),
                player.getPerson().getFirstName(), player.getPerson().getLastName());
    }

    @Override
    public Observable<Team> getTeam() {
        return challengeService.getTeam();
    }
}
