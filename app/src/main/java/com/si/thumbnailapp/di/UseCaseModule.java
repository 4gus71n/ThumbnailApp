package com.si.thumbnailapp.di;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * <ul>
 *  <li>
 *      This module depends on {@link com.si.thumbnailapp.di.ApplicationModule}
 *  </li>
 *  <li>
 *      This module provides a {@link com.si.thumbnailapp.net.usecases.GetPlayerUseCase} to be use in any
 *      presenter.
 *  </li>
 *  <li>
 *      This module provides a {@link com.si.thumbnailapp.net.usecases.GetTeamUseCase} to be use in any
 *      presenter.
 *  </li>
 * </ul>
 *
 */

import com.si.thumbnailapp.datasource.NetworkPlayerRepository;
import com.si.thumbnailapp.datasource.NetworkTeamRepository;
import com.si.thumbnailapp.net.rest.ServiceRestApi;
import com.si.thumbnailapp.net.services.ChallengeService;
import com.si.thumbnailapp.net.usecases.GetPlayerUseCase;
import com.si.thumbnailapp.net.usecases.GetTeamUseCase;
import com.si.thumbnailapp.ui.challenge.adapter.ChallengeAdapter;
import com.si.thumbnailapp.ui.challenge.presenter.ChallengePresenter;
import com.si.thumbnailapp.ui.challenge.view.ChallengeFragment;

import dagger.Module;
import dagger.Provides;

@Module(includes = ApplicationModule.class,
        injects = {ChallengeFragment.class, ChallengePresenter.class, ChallengeAdapter.class})
public class UseCaseModule {

    @Provides
    GetPlayerUseCase provideGetPlayerUseCase(ChallengeService challengeService) {
        return new GetPlayerUseCase(new NetworkPlayerRepository(new ServiceRestApi(challengeService)));
    }

    @Provides
    GetTeamUseCase provideGetTeamUseCase(ChallengeService challengeService) {
        return new GetTeamUseCase(new NetworkTeamRepository(new ServiceRestApi(challengeService)));
    }

}
