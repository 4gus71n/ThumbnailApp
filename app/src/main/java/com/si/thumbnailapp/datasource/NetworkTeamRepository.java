package com.si.thumbnailapp.datasource;

import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.net.rest.RestApi;
import com.si.thumbnailapp.net.rest.ServiceRestApi;
import com.si.thumbnailapp.repository.TeamRepository;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * Concrete implementation of the {@link TeamRepository} interface. This repository gets the data
 * from the network.
 */
public class NetworkTeamRepository implements TeamRepository {
    private RestApi restApi;

    public NetworkTeamRepository(ServiceRestApi serviceRestApi) {
        this.restApi = serviceRestApi;
    }

    @Override
    public Observable<Team> getTeam() {
        return restApi.getTeam();
    }
}
