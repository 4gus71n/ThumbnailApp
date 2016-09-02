package com.si.thumbnailapp.ui.challenge.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.si.thumbnailapp.R;
import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class ChallengeAdapter extends RecyclerView.Adapter<PlayerViewHolder> implements
        PlayerViewHolder.OnItemClicked {

    private Team mTeam;
    private ArrayList<Player> mPlayersList = new ArrayList<>();
    private OnPlayerClicked mPlayerClickedCallback;

    public void setOnPlayerClickedCallback(ChallengeAdapter.OnPlayerClicked callback) {
        this.mPlayerClickedCallback = callback;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PlayerViewHolder(inflater.inflate(R.layout.view_holder_player, parent, false));
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.loadView(mTeam, mPlayersList.get(position));
        holder.setOnClickListener(this);
    }

    @Override
    public void onViewRecycled(PlayerViewHolder holder) {
        super.onViewRecycled(holder);
        holder.cancelLoading();
    }

    @Override
    public int getItemCount() {
        return mPlayersList.size();
    }

    /**
     * Loads a collection of {@link Player} in the current adapter. Removes any old one.
     *
     * @param playerList
     */
    public void setPlayerList(Team team, List<Player> playerList) {
        mTeam = team;
        mPlayersList.clear();
        mPlayersList.addAll(playerList);
        notifyDataSetChanged();
    }

    @Override
    public void onPositionClicked(int position) {
        if (mPlayerClickedCallback != null && mPlayersList.size() > position && mTeam != null)
            mPlayerClickedCallback.onPlayerClicked(mPlayersList.get(position), mTeam);
    }

    /**
     * Simple interface to communicate the click events within the adapter to the fragment.
     */
    public interface OnPlayerClicked {
        void onPlayerClicked(Player player, Team team);
    }
}