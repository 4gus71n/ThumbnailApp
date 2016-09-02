package com.si.thumbnailapp.datasource;

import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.net.rest.RestApi;
import com.si.thumbnailapp.net.rest.ServiceRestApi;
import com.si.thumbnailapp.repository.PlayerRepository;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * Concrete implementation of the {@link PlayerRepository} interface. This repository gets the data
 * from the network.
 */
public class NetworkPlayerRepository implements PlayerRepository {
    private RestApi restApi;

    public NetworkPlayerRepository(ServiceRestApi serviceRestApi) {
        this.restApi = serviceRestApi;
    }

    @Override
    public Observable<ResponseBody> getMessage(Player player, Team team) {
        return restApi.getMessage(player, team);
    }
}
