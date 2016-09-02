package com.si.thumbnailapp.net.rest;

import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * Mocked implementations used for testing purposes.
 */
public class MockRestApi implements RestApi {
    @Override
    public Observable<ResponseBody> getMessage(Player player, Team team) {
        return null;
    }

    @Override
    public Observable<Team> getTeam() {
        return null;
    }
}
