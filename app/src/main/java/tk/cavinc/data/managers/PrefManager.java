package tk.cavinc.data.managers;

import android.content.SharedPreferences;

import tk.cavinc.utils.App;

/**
 * Created by cav on 30.03.20.
 */

public class PrefManager {
    private static final String CURRENT_LESSON = "LESSON";
    private SharedPreferences mSharedPreferences;

    public PrefManager(){
        mSharedPreferences = App.sSharedPreferences;
    }

    // скорость символов в уроке
    public int getWorkSpeed(){
        return Integer.parseInt(mSharedPreferences.getString("count_sign_minute","20"));
    }

    // текущий урок
    public int getCurrentLesson(){
        return mSharedPreferences.getInt(CURRENT_LESSON,1);
    }

    public void setCurrentLesson(int currentLesson){
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(CURRENT_LESSON,currentLesson);
        editor.apply();
    }
}
