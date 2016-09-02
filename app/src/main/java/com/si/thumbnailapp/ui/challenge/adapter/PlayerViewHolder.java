package com.si.thumbnailapp.ui.challenge.adapter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.si.thumbnailapp.R;
import com.si.thumbnailapp.model.Player;
import com.si.thumbnailapp.model.Team;
import com.si.thumbnailapp.ui.widgets.TriangleShapeView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */
public class PlayerViewHolder extends RecyclerView.ViewHolder {

    //region View variables declaration
    @BindView(R.id.view_holder_player_first_name)
    TextView mFirstNameTextView;
    @BindView(R.id.view_holder_player_last_name)
    TextView mLastNameTextView;
    @BindView(R.id.view_holder_player_avatar)
    ImageView mThumbnailImageView;
    @BindView(R.id.view_holder_player_position)
    TextView mPositionTextView;
    @BindView(R.id.view_holder_container)
    View mContainer;
    @BindView(R.id.view_holder_player_separator)
    TriangleShapeView mSeparator;
    //endregion

    public PlayerViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void loadView(Team team, Player player) {
        if (player.getPerson() != null && !TextUtils.isEmpty(player.getPerson().getFirstName()))
            mFirstNameTextView.setText(player.getPerson().getFirstName());
        if (player.getPerson() != null && !TextUtils.isEmpty(player.getPerson().getLastName()))
            mLastNameTextView.setText(player.getPerson().getLastName());
        if (player.getPerson() != null && !TextUtils.isEmpty(player.getJerseyNumber()))
            mPositionTextView.setText(player.getJerseyNumber());
        if (player.getPerson() != null && !TextUtils.isEmpty(player.getPerson().getImageUrl()))
            Picasso.with(itemView.getContext()).load(player.getPerson().getImageUrl()).into(mThumbnailImageView);
        try {
            int highlightColor = Color.parseColor("#" + team.getSetting().getHighlightColor());
            mContainer.setBackgroundColor(highlightColor);
            mSeparator.setSeparatorColor(highlightColor);
        } catch (Exception e) {
            //Something went wrong NP because didn't had a highlight color or format exception
            //because It wasn't a valid color. Set the default color.
            mContainer.setBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.default_green));
            mSeparator.setSeparatorColor(ContextCompat.getColor(itemView.getContext(), R.color.default_green));
        }
    }

    public void cancelLoading() {
        Picasso.with(itemView.getContext()).cancelRequest(mThumbnailImageView);
    }

    public void setOnClickListener(final OnItemClicked onClickListener) {
        mContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onPositionClicked(getAdapterPosition());
            }
        });
    }

    public interface OnItemClicked {
        void onPositionClicked(int position);
    }
}
