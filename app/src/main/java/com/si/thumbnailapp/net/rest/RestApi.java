package com.si.thumbnailapp.net.rest;

import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public interface RestApi {

    Observable<ResponseBody> getMessage(Player player, Team team);
    Observable<Team> getTeam();

}
