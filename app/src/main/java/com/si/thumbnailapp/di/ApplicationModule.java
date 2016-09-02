package com.si.thumbnailapp.di;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * <ul>
 *  <li>
 *      This module provides a {@link Context}.
 *  </li>
 *  <li>
 *      This module provides a {@link LayoutInflater} so we can inflate easily any view such as
 *      ViewHolders.
 *  </li>
 * </ul>
 *
 */

import android.content.Context;
import android.view.LayoutInflater;

import com.si.thumbnailapp.BuildConfig;
import com.si.thumbnailapp.net.services.ChallengeService;
import com.si.thumbnailapp.net.services.ServiceFactory;

import dagger.Module;
import dagger.Provides;

@Module(library = true)
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @Provides
    LayoutInflater provideInflater() {
        return LayoutInflater.from(mContext);
    }

    @Provides
    ChallengeService provideChallengeService() {
        return ServiceFactory.createRetrofitService(ChallengeService.class, BuildConfig.BASE_HOST);
    }
}
