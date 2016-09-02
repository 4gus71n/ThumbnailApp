package com.si.thumbnailapp.ui.challenge.presenter;

import android.os.Bundle;

import com.si.thumbnailapp.ThumbnailApp;
import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.net.subscribers.PlayerSubscriber;
import com.si.thumbnailapp.net.subscribers.TeamSubscriber;
import com.si.thumbnailapp.net.usecases.GetPlayerUseCase;
import com.si.thumbnailapp.net.usecases.GetTeamUseCase;
import com.si.thumbnailapp.ui.challenge.view.ChallengeFragment;
import com.soundcloud.lightcycle.DefaultSupportFragmentLightCycle;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Agustin Tomas Larghi on 30/07/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class ChallengePresenter extends DefaultSupportFragmentLightCycle<ChallengeFragment> {

    //region Variables declaration
    @Inject
    GetTeamUseCase getTeamUseCase;
    @Inject
    GetPlayerUseCase getPlayerUseCase;
    ChallengePresenter.View view;
    //endregion

    //region Subscribers
    private class TeamSubscriber2 extends TeamSubscriber {
        @Override
        protected void onPlayersReceived(Team team, List<Player> players) {
            view.loadPlayersList(team, team.getPlayers());
        }

        @Override
        protected void onTeamReceived(Team team) {
            view.setTeamTitle(team);
        }

        @Override
        public void onCompleted() {
            view.hideRefreshingSpinner();
        }
    };

    private class PlayerSubscriber2  extends PlayerSubscriber {
        @Override
        public void onMessageReceived(String message) {
            view.showMessage(message);
        }
    };
    //endregion


    @Override
    public void onCreate(ChallengeFragment fragment, Bundle bundle) {
        super.onCreate(fragment, bundle);
        ThumbnailApp.getObjectGraph().inject(this);
    }

    @Override
    public void onResume(ChallengeFragment fragment) {
        super.onResume(fragment);
        this.view = fragment;

    }

    @Override
    public void onDestroy(ChallengeFragment fragment) {
        super.onDestroy(fragment);
        this.view = null;
        getPlayerUseCase.unsubscribe();
        getTeamUseCase.unsubscribe();
    }

    public void onLoadPlayerList() {
        getTeamUseCase.execute(new TeamSubscriber2());

    }

    public void onLoadSinglePlayer(Team team, Player player) {
        getPlayerUseCase.setTeam(team);
        getPlayerUseCase.setPlayer(player);
        getPlayerUseCase.execute(new PlayerSubscriber2());

    }

    //region Presenter-View Interface
    public interface View {
        /**
         * Loads the player list into the UI grid.
         * @param playersList
         */
        void loadPlayersList(Team team, List<Player> playersList);

        /**
         * Shows a message in the screen.
         * @param message
         */
        void showMessage(String message);

        /**
         * Hide the spinner from the {@link android.support.v4.widget.SwipeRefreshLayout}
         */
        void hideRefreshingSpinner();

        /**
         * Sets the team name in the {@link android.widget.Toolbar}
         * @param team
         */
        void setTeamTitle(Team team);
    }
    //endregion
}
