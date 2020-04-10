package tk.cavinc.data.managers;

import android.content.SharedPreferences;

import tk.cavinc.utils.App;

/**
 * Created by cav on 30.03.20.
 */

public class PrefManager {
    private SharedPreferences mSharedPreferences;

    public PrefManager(){
        mSharedPreferences = App.sSharedPreferences;
    }

}
