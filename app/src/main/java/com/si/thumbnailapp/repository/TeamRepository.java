package com.si.thumbnailapp.repository;

import com.si.thumbnailapp.model.Team;

import rx.Observable;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public interface TeamRepository {

    Observable<Team> getTeam();

}
