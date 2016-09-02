package com.si.thumbnailapp.net.subscribers;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * This subscriber is supposed to work with {@link com.si.thumbnailapp.net.services.ChallengeService}
 */
public abstract class PlayerSubscriber extends Subscriber<ResponseBody> {
    @Override
    public void onCompleted() {
        //Empty by default
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        //Empty by default
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        try {
            onMessageReceived(responseBody.string());
        } catch (IOException e) {
            onError(e);
        }
    }

    public abstract void onMessageReceived(String message);
}
