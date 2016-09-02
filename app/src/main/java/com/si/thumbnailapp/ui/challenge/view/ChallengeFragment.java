package com.si.thumbnailapp.ui.challenge.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.si.thumbnailapp.R;
import com.si.thumbnailapp.ThumbnailApp;
import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.ui.challenge.adapter.ChallengeAdapter;
import com.si.thumbnailapp.ui.challenge.presenter.ChallengePresenter;
import com.soundcloud.lightcycle.LightCycle;
import com.soundcloud.lightcycle.LightCycleSupportFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class ChallengeFragment extends LightCycleSupportFragment<ChallengeFragment> implements
        ChallengePresenter.View, SwipeRefreshLayout.OnRefreshListener, ChallengeAdapter.OnPlayerClicked {

    public static final int SPAN_COUNT = 3;
    //region Views declaration
    @BindView(R.id.challenge_fragment_recycler_view)
    RecyclerView mChallengeRecyclerView;
    @BindView(R.id.challenge_fragment_swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    //endregion

    //region Variables declaration
    private ChallengeAdapter mChallengeAdapter;
    @Inject @LightCycle ChallengePresenter challengePresenter;
    //endregion

    //region ChallengePresenter.View implementation
    public void loadPlayersList(Team team, List<Player> playersList) {
        mChallengeAdapter.setPlayerList(team, playersList);
    }

    @Override
    public void setTeamTitle(Team team) {
        if (team.getTeamName() != null)
            getActivity().setTitle(team.getTeamName());
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void hideRefreshingSpinner() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
    //endregion

    //region Constructors declarations
    public ChallengeFragment() {
        ThumbnailApp.getObjectGraph().inject(this);
    }

    public static ChallengeFragment newInstance() {
        return new ChallengeFragment();
    }
    //endregion

    //region Fragment's lifecycle
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_challenge, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        ButterKnife.bind(this, view);
        mChallengeAdapter = new ChallengeAdapter();
        mChallengeAdapter.setOnPlayerClickedCallback(this);
        mChallengeRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), SPAN_COUNT));
        mChallengeRecyclerView.setAdapter(mChallengeAdapter);
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //This is the only way to show the spinner as soon as the activity/fragment starts
        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });
        //We request the initial list
        challengePresenter.onLoadPlayerList();
    }

    //endregion

    //region SwipeRefreshLayout.OnRefreshListener implementation
    @Override
    public void onRefresh() {
        challengePresenter.onLoadPlayerList();
    }
    //endregion

    //region ChallengeAdapter.OnPlayerClicked implementation
    @Override
    public void onPlayerClicked(Player player, Team team) {
        challengePresenter.onLoadSinglePlayer(team, player);
    }
    //endregion
}
