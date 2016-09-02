package com.si.thumbnailapp.repository;

import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public interface PlayerRepository {
    
    Observable<ResponseBody> getMessage(Player player, Team team);

}
