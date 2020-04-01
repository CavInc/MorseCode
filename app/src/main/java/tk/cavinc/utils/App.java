package tk.cavinc.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by cav on 24.03.20.
 */

public class App extends Application {
    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getBaseContext();
    }

    public static Context getContext() {
        return sContext;
    }
}
