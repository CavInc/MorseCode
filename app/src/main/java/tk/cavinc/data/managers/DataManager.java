package tk.cavinc.data.managers;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import tk.cavinc.data.models.LeterMorseModel;
import tk.cavinc.utils.App;

/**
 * Created by cav on 30.03.20.
 */

public class DataManager {
    private static DataManager INSTANCE = null;

    private Context mContext;
    private PrefManager mPreManager;

    private int[] delaySignal = {100,200};

    public static DataManager getInstance() {
        if (INSTANCE==null){
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public DataManager() {
        mContext = App.getContext();
        mPreManager = new PrefManager();
    }

    public ArrayList<LeterMorseModel> loadMorseData(){
        ArrayList<LeterMorseModel> rec = new ArrayList<>();

        String json = null;
        try {
            InputStream is = mContext.getAssets().open("morse.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            JSONObject obj = new JSONObject(json);
            JSONArray jArr = obj.getJSONArray("data");
            for (int i= 0 ;i<jArr.length();i++){
                JSONObject item = jArr.getJSONObject(i);
                String letter = item.getString("leter");
                String mnem = item.getString("mnemonik");
                String code = item.getString("code");
                String letterR = letter;
                if (item.has("letterR")) {
                    letterR = item.getString("letterR");
                }
                rec.add(new LeterMorseModel(letter,letterR,mnem,code));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rec;
    }

    public PrefManager getPreManager() {
        return mPreManager;
    }

    public int[] getDelaySignal() {
        return delaySignal;
    }
}
