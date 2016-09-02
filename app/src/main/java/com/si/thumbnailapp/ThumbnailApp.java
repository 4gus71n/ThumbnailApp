package com.si.thumbnailapp;

/**
 * Created by Agustin Tomas Larghi on 31/08/2016.
 */
import android.app.Application;

import com.si.thumbnailapp.di.ApplicationModule;
import com.si.thumbnailapp.di.UseCaseModule;

import dagger.ObjectGraph;

public class ThumbnailApp extends Application {

    private static ThumbnailApp instance;

    protected ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();
        objectGraph = ObjectGraph.create(new ApplicationModule(getBaseContext()),
                new UseCaseModule());
        instance = this;
    }

    public static ObjectGraph getObjectGraph() {
        if (instance == null || instance.objectGraph == null) {
            throw new IllegalStateException("Cannot access the app graph before the application has been created");
        }
        return instance.objectGraph;
    }
}
