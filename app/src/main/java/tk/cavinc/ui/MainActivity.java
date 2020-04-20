package tk.cavinc.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import tk.cavinc.R;
import tk.cavinc.data.managers.DataManager;
import tk.cavinc.data.models.LessonModel;
import tk.cavinc.data.models.LeterMorseModel;
import tk.cavinc.utils.ConstantManager;
import tk.cavinc.utils.Func;

import static android.media.AudioAttributes.USAGE_GAME;

/**
 * Created by cav on 24.03.20.
 */

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MMA";
    private DataManager mDataManager;

    private TextView mMsg;
    private TextView mLessonTxt;
    private EditText mEditText;
    private ArrayList<LeterMorseModel> data;
    private ArrayList<LessonModel> mLessonModels;
    private boolean letterRus = false;

    private SoundPool mSoundPool;
    private int mStreamID;

    MediaPlayer mPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataManager = DataManager.getInstance();

        mMsg = findViewById(R.id.morze_symbol);
        //mEditText = (EditText) findViewById(R.id.letter_et);
        mLessonTxt = findViewById(R.id.morze_lesson);

        //mEditText.addTextChangedListener(mTextWatcher);
        if (! new File(getFilesDir()+"/"+"dot.wav").exists()) {
            mDataManager.createFile();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        data = mDataManager.loadMorseData();
        mLessonModels = mDataManager.loadLesson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.setting) {
            Intent pref = new Intent(this,PrefActivity.class);
            startActivity(pref);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onButtonClick(View v){
        switch (v.getId()){
            case R.id.L_Q:
                if (!letterRus) {
                    morseKey("Q");
                } else {
                    morseKey("Й");
                }
                break;
            case R.id.L_W:
                if (!letterRus) {
                    morseKey("W");
                } else {
                    morseKey("Ц");
                }
                break;
            case R.id.L_E:
                if (!letterRus) {
                    morseKey("E");
                } else {
                    morseKey("У");
                }
                break;
            case R.id.L_R:
                if (!letterRus) {
                    morseKey("R");
                } else {
                    morseKey("К");
                }
                break;
            case R.id.L_T:
                if (!letterRus) {
                    morseKey("T");
                } else {
                    morseKey("Е");
                }
                break;
            case R.id.L_Y:
                if (!letterRus) {
                    morseKey("Y");
                } else {
                    morseKey("Н");
                }
                break;
            case R.id.L_U:
                if (!letterRus) {
                    morseKey("U");
                } else {
                    morseKey("Г");
                }
                break;
            case R.id.L_I:
                if (!letterRus) {
                    morseKey("I");
                } else {
                    morseKey("Ш");
                }
                break;
            case R.id.L_O:
                if (!letterRus) {
                    morseKey("O");
                } else {
                    morseKey("Щ");
                }
                break;
            case R.id.L_P:
                if (!letterRus) {
                    morseKey("P");
                } else {
                    morseKey("З");
                }
                break;
            case R.id.L_A:
                if (!letterRus) {
                    morseKey("A");
                } else {
                    morseKey("Ф");
                }
                break;
            case R.id.L_S:
                if (!letterRus) {
                    morseKey("S");
                } else {
                    morseKey("Ы");
                }
                break;
            case R.id.L_D:
                if (letterRus) {
                    morseKey("В");
                } else {
                    morseKey("D");
                }
                break;
            case R.id.L_F:
                if (letterRus){
                    morseKey("А");
                } else {
                    morseKey("F");
                }
                break;
            case R.id.L_G:
                if (letterRus){
                    morseKey("П");
                } else {
                    morseKey("G");
                }
                break;
            case R.id.L_H:
                if (letterRus) {
                    morseKey("Р");
                } else {
                    morseKey("H");
                }
                break;
            case R.id.L_J:
                if (letterRus) {
                    morseKey("О");
                } else {
                    morseKey("J");
                }
                break;
            case R.id.L_K:
                if (letterRus){
                    morseKey("Л");
                } else {
                    morseKey("K");
                }
                break;
            case R.id.L_L:
                if (letterRus) {
                    morseKey("Д");
                } else {
                    morseKey("L");
                }
                break;
            case R.id.L_Z:
                if (letterRus) {
                    morseKey("Я");
                } else {
                    morseKey("Z");
                }
                break;
            case R.id.L_X:
                if (letterRus) {
                    morseKey("Ч");
                } else {
                    morseKey("X");
                }
                break;
            case R.id.L_C:
                if (letterRus) {
                    morseKey("С");
                } else {
                    morseKey("C");
                }
                break;
            case R.id.L_V:
                if (letterRus) {
                    morseKey("М");
                } else {
                    morseKey("V");
                }
                break;
            case R.id.L_B:
                if (letterRus) {
                    morseKey("И");
                } else {
                    morseKey("B");
                }
                break;
            case R.id.L_N:
                if (letterRus) {
                    morseKey("Т");
                } else {
                    morseKey("N");
                }
                break;
            case R.id.L_M:
                if (letterRus) {
                    morseKey("Ь");
                } else {
                    morseKey("M");
                }
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
            case R.id.L_BB:
                morseKey("Б");
                break;
            case R.id.L_XX:
                morseKey("Х");
                break;
            case R.id.L_EE:
                morseKey("Э");
                break;
            case R.id.L_TSIG:
                morseKey("Ъ");
                break;
            case R.id.L_JJ:
                morseKey("Ж");
                break;
            case R.id.L_UU:
                morseKey("Ю");
                break;
            case R.id.L_CHG:
                letterRus = !letterRus;
                if (letterRus) {
                    findViewById(R.id.key_panel_lat).setVisibility(View.GONE);
                    findViewById(R.id.key_panel_rus).setVisibility(View.VISIBLE);
                } else {
                    findViewById(R.id.key_panel_lat).setVisibility(View.VISIBLE);
                    findViewById(R.id.key_panel_rus).setVisibility(View.GONE);
                }
                break;
            case R.id.lesson_bt:
                startLesson();
                break;
        }
    }

    private void startLesson() {
        int currentLesson = mDataManager.getPreManager().getCurrentLesson();
        LessonModel lesson = mLessonModels.get(currentLesson);
        String[] letter = Func.splitLesson(lesson.getLesson());

        mLessonTxt.setText(lesson.getLesson());
    }


    private void morseKey(String letter){
        int id = data.indexOf(new LeterMorseModel(letter,letter,null));
        Log.d(TAG,"ID :"+id);
        if (id != -1) {
            LeterMorseModel item = data.get(id);
            mMsg.setText(item.getMnemonik()+"\n"+item.getCode());
            //playMorze(item.getCode());
            playMorze2(item.getCode());
        }
    }

    private void playMorze2(final String code) {
        if (Build.VERSION.SDK_INT < 21) {
            createOldSoundPool();
        } else {
            createNewSoundPool();
        }

        final int soundDot = loadSound("dot.wav");
        final int soundDash = loadSound("dash.wav");
        final int durationDot = 6000 / ConstantManager.SPEED;
        final int durationDash = durationDot * 3;

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < code.length(); i++) {
                        String m = code.substring(i, i + 1);
                        Log.d(TAG, m);
                        if (m.equals(".")) {
                            playSound(soundDot);
                            Thread.sleep((long) (durationDot * 2));
                        } else if (m.equals("-")) {
                            playSound(soundDash);
                            Thread.sleep((long) (durationDash + durationDot));
                        }
                        //Thread.sleep((long) (durationDash + durationDot));

                    }
                    mSoundPool.release();
                    mSoundPool = null;
                } catch (Exception e){
                    e.printStackTrace();
                }
                /*
                try {
                    playSound(soundDot);
                    Thread.sleep((long) (durationDot * 2));
                    playSound(soundDot);
                    Thread.sleep((long) (durationDot * 2));
                    playSound(soundDot);
                    Thread.sleep((long) (durationDot * 2));
                    playSound(soundDash);
                    Thread.sleep((long) (durationDash + durationDot));
                    mSoundPool.release();
                    mSoundPool = null;
                } catch (InterruptedException e7) {
                    e7.printStackTrace();
                }
                */
            }
        }).start();

    }

    private void playMorze(final String code){
        new Thread(new Runnable() {
            @Override
            public void run() {
                AudioTrack tone = null;
                for (int i=0;i<code.length();i++){
                    String m = code.substring(i,i+1);
                    Log.d(TAG,m);
                    if (m.equals(".")) {
                        tone = generateTone(800,100);
                    } else if (m.equals("-")){
                        tone = generateTone(800,300);
                    }
                    tone.play();
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                clearMemory(tone);
            }
        }).start();

        /*
        AudioTrack tone = generateTone(1000, 100);
        tone.play();
        clearMemory(tone);
        */
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
                AudioFormat.CHANNEL_IN_STEREO, AudioFormat.ENCODING_PCM_16BIT,
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


    @TargetApi(21)
    private void createNewSoundPool() {
        //setUsage(14)
        mSoundPool = new Builder().setAudioAttributes(new AudioAttributes.Builder()
                .setUsage(USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()).build();
    }

    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, 3, 0);
    }

    private int playSound(int sound) {
        if (sound > 0) {
            mStreamID = mSoundPool.play(sound, 1.0f, 1.0f, 1, 0, 1.0f);
        }
        return mStreamID;
    }

    private int loadSound(String fileName) {
        //return mSoundPool.load(getApplicationInfo().dataDir + "/files/" + fileName, 1);
        return mSoundPool.load(getFilesDir()+"/"+fileName,1);
    }


}
