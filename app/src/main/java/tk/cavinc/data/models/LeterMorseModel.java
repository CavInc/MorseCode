package tk.cavinc.data.models;

/**
 * Created by cav on 30.03.20.
 */

public class LeterMorseModel {
    private String mLatLeter; // латинская буква
    private String mRusLeter; // русская буква
    private String mMnemonik; // напев

    public LeterMorseModel(String latLeter, String rusLeter, String mnemonik) {
        mLatLeter = latLeter;
        mRusLeter = rusLeter;
        mMnemonik = mnemonik;
    }

    public LeterMorseModel(String latLeter, String mnemonik) {
        mLatLeter = latLeter;
        mMnemonik = mnemonik;
    }

    public String getLatLeter() {
        return mLatLeter;
    }

    public String getRusLeter() {
        return mRusLeter;
    }

    public String getMnemonik() {
        return mMnemonik;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(!(getClass() == obj.getClass())) {
            return false;
        }else {
            LeterMorseModel tmp = (LeterMorseModel) obj;
            if (tmp.getLatLeter().equals(mLatLeter)) {
                return true;
            }
            /*
            if (tmp.getRusLeter().equals(mRusLeter)) {
                return true;
            }
            */
        }
        return false;
    }
}
