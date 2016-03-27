package com.tanayagrawal.popularmoviesstageone.configuration;

import android.app.Application;
import android.content.Context;

/**
 * Created by tanayagrawal on 27/03/16.
 */
public class BootstrapApplication extends Application {


    private static BootstrapApplication instance;

    /**
     * Create main application
     */
    public BootstrapApplication() {

    }

    /**
     * Create main application
     *
     * @param context
     */
    public BootstrapApplication(final Context context) {
        this();
        attachBaseContext(context);
    }

    /**
     * Creates and returns the instance of Bootstrap Application
     * @return instance of BootstrapApplication
     */
    public static BootstrapApplication getInstance() {
        if (instance == null) {
            instance = new BootstrapApplication();
            return instance;
        } else {
            return instance;
        }
    }
}
