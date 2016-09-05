package com.si.thumbnailapp;

import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;

/**
 * Created by Agustin Tomas Larghi on 05/09/2016.
 * Email: agustin.tomas.larghi@gmail.com
 *
 * A custom Robolectric Application class so we can inject the Mock Modules in the Unit Tests.
 */
public class TestThumbnailApp extends ThumbnailApp implements TestLifecycleApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void beforeTest(Method method) {
    }

    @Override
    public void prepareTest(Object test) {
    }

    @Override
    public void afterTest(Method method) {
    }
}
