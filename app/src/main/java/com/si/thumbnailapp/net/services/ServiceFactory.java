package com.si.thumbnailapp.net.services;


import android.content.Context;

import com.si.thumbnailapp.BuildConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 * Email: agustin.tomas.larghi@gmail.com
 */

public class ServiceFactory {

    private final Context mContext;

    public ServiceFactory(Context context) {
        mContext = context;
    }

    /**
     * Creates a retrofit service from an arbitrary class (clazz)
     * @param clazz Java interface of the retrofit service
     * @param endPoint REST endpoint url
     * @return retrofit service with defined endpoint
     */
    public <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.computation());
        //TODO Inject the retrofit client.

        OkHttpClient.Builder client = new OkHttpClient().newBuilder();
        if (BuildConfig.DEBUG) {
            //Added a logging to check out the http response
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client.addInterceptor(interceptor);
            //client.addInterceptor(new FakeInterceptor());
        }
        final Retrofit retrofit = new Retrofit.Builder()
                .client(client.build())
                .baseUrl(endPoint)
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        T service = retrofit.create(clazz);

        return service;
    }

    public class FakeInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Response response = null;
            if(BuildConfig.DEBUG) {
                // Get Request URI.
                final HttpUrl url = chain.request().url();
                StringBuilder stringBuilder = new StringBuilder();
                InputStream json= mContext.getAssets().open("json/" + url.encodedPath().replaceAll("\\/", "_"));
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(json, "UTF-8"));
                String responseString = "";
                while ( ( responseString = bufferedReader.readLine() ) != null) {
                    stringBuilder.append(responseString);
                }
                bufferedReader.close();
                response = new Response.Builder()
                        .code(200)
                        .message(responseString)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_0)
                        .body(ResponseBody.create(MediaType.parse("application/json"), responseString.getBytes()))
                        .addHeader("content-type", "application/json")
                        .build();
            }
            else {
                response = chain.proceed(chain.request());
            }
            return response;
        }
    }
}
