package tk.cavinc.ui;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import tk.cavinc.R;
import tk.cavinc.data.managers.DataManager;
import tk.cavinc.data.models.LeterMorseModel;

/**
 * Created by cav on 24.03.20.
 */

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MMA";
    private DataManager mDataManager;

    private TextView mMsg;
    private EditText mEditText;
    private ArrayList<LeterMorseModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataManager = DataManager.getInstance();

        mMsg = findViewById(R.id.morze_symbol);
        //mEditText = (EditText) findViewById(R.id.letter_et);

        //mEditText.addTextChangedListener(mTextWatcher);
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = mDataManager.loadMorseData();
    }

    boolean editlock = false;
    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            Log.d(TAG,charSequence.toString());
            Log.d(TAG,"START:"+start+" BEF:"+before+" COUNT:"+count);
            String letter = charSequence.toString();
            Log.d(TAG,"LETTER:"+letter);
            AudioTrack tone = generateTone(1000, 100);
            tone.play();
            clearMemory(tone);
            //tone = generateTone(4000,15000);
            //tone.play();
            //clearMemory(tone);
        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (editlock ) {
                editlock = false;
                return;
            }
            Log.d(TAG,"EDD: "+editable.toString());
            editable.clear();
            editlock = true;
        }
    };


    public void onButtonClick(View v){
        switch (v.getId()){
            case R.id.L_Q:
                morseKey("Q");
                break;
            case R.id.L_W:
                morseKey("W");
                break;
            case R.id.L_E:
                morseKey("E");
                break;
            case R.id.L_R:
                morseKey("R");
                break;
            case R.id.L_T:
                morseKey("T");
                break;
            case R.id.L_Y:
                morseKey("Y");
                break;
            case R.id.L_U:
                morseKey("U");
                break;
            case R.id.L_I:
                morseKey("I");
                break;
            case R.id.L_O:
                morseKey("O");
                break;
            case R.id.L_P:
                morseKey("P");
                break;
            case R.id.L_A:
                morseKey("A");
                break;
            case R.id.L_S:
                morseKey("S");
                break;
            case R.id.L_D:
                morseKey("D");
                break;
            case R.id.L_F:
                morseKey("F");
                break;
            case R.id.L_G:
                morseKey("G");
                break;
            case R.id.L_H:
                morseKey("H");
                break;
            case R.id.L_J:
                morseKey("J");
                break;
            case R.id.L_K:
                morseKey("K");
                break;
            case R.id.L_L:
                morseKey("L");
                break;
            case R.id.L_Z:
                morseKey("Z");
                break;
            case R.id.L_X:
                morseKey("X");
                break;
            case R.id.L_C:
                morseKey("C");
                break;
            case R.id.L_V:
                morseKey("V");
                break;
            case R.id.L_B:
                morseKey("B");
                break;
            case R.id.L_N:
                morseKey("N");
                break;
            case R.id.L_M:
                morseKey("M");
                break;
            case R.id.L_1:
                morseKey("1");
                break;
            case R.id.L_2:
                morseKey("2");
                break;
            case R.id.L_3:
                morseKey("3");
                break;
            case R.id.L_4:
                morseKey("4");
                break;
            case R.id.L_5:
                morseKey("5");
                break;
            case R.id.L_6:
                morseKey("6");
                break;
            case R.id.L_7:
                morseKey("7");
                break;
            case R.id.L_8:
                morseKey("8");
                break;
            case R.id.L_9:
                morseKey("9");
                break;
            case R.id.L_0:
                morseKey("0");
                break;
        }
    }

    private void morseKey(String letter){
        int id = data.indexOf(new LeterMorseModel(letter,letter,null));
        Log.d(TAG,"ID :"+id);
        if (id != -1) {
            LeterMorseModel item = data.get(id);
            mMsg.setText(item.getMnemonik()+"\n"+item.getCode());
            playMorze();
        }
    }

    private void playMorze(){
        AudioTrack tone = generateTone(1000, 100);
        tone.play();
        clearMemory(tone);
    }

    // https://www.cyberforum.ru/android-dev/thread1803841.html
    // https://www.cyberforum.ru/android-dev/thread1792511.html
    // https://riptutorial.com/ru/android/example/28432/%D0%B3%D0%B5%D0%BD%D0%B5%D1%80%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D1%82%D1%8C-%D1%82%D0%BE%D0%BD-%D0%BE%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D0%BE%D0%B9-%D1%87%D0%B0%D1%81%D1%82%D0%BE%D1%82%D1%8B
    // https://overcoder.net/q/533848/%D0%BA%D0%B0%D0%BA-%D1%81%D0%B3%D0%B5%D0%BD%D0%B5%D1%80%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D1%82%D1%8C-%D0%BE%D0%BF%D1%80%D0%B5%D0%B4%D0%B5%D0%BB%D0%B5%D0%BD%D0%BD%D1%83%D1%8E-%D1%87%D0%B0%D1%81%D1%82%D0%BE%D1%82%D1%83-%D0%B7%D0%B2%D1%83%D0%BA%D0%B0
    // https://habr.com/ru/post/126835/

    // https://habr.com/ru/post/267605/


    private AudioTrack generateTone(double freqHz, int durationMs){
        int sampleRate = 48000;  // 44100 Hz
        int count = (int)( sampleRate * 2.0 * (durationMs / 1000.0)) & ~1;
        short[] samples = new short[count];
        for(int i = 0; i < count; i += 2){
            short sample = (short)(Math.sin(2 * Math.PI * i / (sampleRate / freqHz)) * 0x7FFF);
            samples[i + 0] = sample;
        }
        AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC, sampleRate,
                AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
                count * (Short.SIZE / 8), AudioTrack.MODE_STATIC);
        track.write(samples, 0, count);
        return track;
    }


    private void clearMemory(AudioTrack track) {
        try {
            track.pause();
        } catch (IllegalStateException e) {
        }
        track.flush();
        track.release();
        track = null;
    }



}
