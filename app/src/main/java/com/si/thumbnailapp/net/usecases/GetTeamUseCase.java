package com.si.thumbnailapp.net.usecases;

import com.si.thumbnailapp.repository.TeamRepository;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class GetTeamUseCase extends UseCase {
    private TeamRepository teamRepository;

    public GetTeamUseCase(TeamRepository networkTeamRepository) {
        this.teamRepository = networkTeamRepository;
    }

    @Override
    public Observable buildUseCaseObservable() {
        return teamRepository.getTeam();
    }
}
