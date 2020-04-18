package tk.cavinc.ui.fragments;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import tk.cavinc.R;
import tk.cavinc.data.managers.DataManager;

/**
 * Created by cav on 10.04.20.
 */

public class PreferenseFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
    private DataManager mDataManager;
    private Preference mSpeed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);
        mDataManager = DataManager.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();
        mSpeed = findPreference("count_sign_minute");
        mSpeed.setSummary(mDataManager.getPreManager().getWorkSpeed());
        mSpeed.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference.getKey().equals("count_sign_minute")) {
            mSpeed.setSummary((String) newValue);
        }
        return true;
    }
}
