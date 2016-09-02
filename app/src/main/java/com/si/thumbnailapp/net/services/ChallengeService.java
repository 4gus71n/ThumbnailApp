package com.si.thumbnailapp.net.services;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
import com.si.thumbnailapp.model.Team;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ChallengeService {
    @GET("bcl/challenge/team.json")
    Observable<Team> getTeam();
    @GET("bcl/challenge/tapped.php")
    Observable<ResponseBody> getMessage(@Query("teamId") int teamId, @Query("playerid") int playerId,
                                        @Query("firstname") String playerFirstName, @Query("lastname") String playerLastName);
}
