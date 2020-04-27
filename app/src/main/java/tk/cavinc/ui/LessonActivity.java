package tk.cavinc.ui;

import android.media.AudioTrack;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import tk.cavinc.R;
import tk.cavinc.data.managers.DataManager;
import tk.cavinc.data.models.LessonModel;
import tk.cavinc.data.models.LeterMorseModel;
import tk.cavinc.utils.ConstantManager;
import tk.cavinc.utils.Func;

/**
 * Created by cav on 22.04.20.
 */

public class LessonActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "LA";
    private DataManager mDataManager;

    private TextView mLessonSpeed;
    private TextView mCurrentLesson;
    private TextView mLessonTxt;

    private int speed;
    private ArrayList<LessonModel> mLessonModels;
    private int currentLesson;
    private LessonModel lessonModel;
    private ArrayList<LeterMorseModel> data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        mDataManager = DataManager.getInstance();

        findViewById(R.id.lesson_prev).setOnClickListener(this);
        findViewById(R.id.lesson_next).setOnClickListener(this);
        findViewById(R.id.lesson_start).setOnClickListener(this);

        speed = mDataManager.getPreManager().getWorkSpeed();
        data = mDataManager.loadMorseData();

        mLessonSpeed = findViewById(R.id.lesson_speed);
        mLessonSpeed.setText("скорость "+mDataManager.getPreManager().getWorkSpeed()+" зкн/мин");

        mLessonModels = mDataManager.loadLesson();
        mCurrentLesson = findViewById(R.id.current_lesson);
        mCurrentLesson.setText(String.valueOf(mDataManager.getPreManager().getCurrentLesson()));
        currentLesson = mDataManager.getPreManager().getCurrentLesson();

        mLessonTxt = findViewById(R.id.lesson_text);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupLesson(currentLesson);
    }

    private void setupLesson(int lesson){
        mCurrentLesson.setText(String.valueOf(lesson));
        lessonModel = mLessonModels.get(currentLesson-1);

        //System.out.println(letter);
        //int count = letter.size();
        mLessonTxt.setText(lessonModel.getLesson().toUpperCase());

    }

    private void startLesson(){
        int timeSleep = ConstantManager.SPEED / speed;
        ArrayList<String> letter = Func.splitLesson(lessonModel.getLesson());
        for (String l:letter){
            if (!l.equals("-")){
                // пищим
                int id = data.indexOf(new LeterMorseModel(l.toUpperCase(),l.toUpperCase(),null));
                String code = data.get(id).getCode();
                playMorze(code);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.lesson_prev) {
            if (currentLesson > 1) {
                currentLesson -= 1;
                mDataManager.getPreManager().setCurrentLesson(currentLesson);
                setupLesson(currentLesson);
            }
        }
        if (view.getId() == R.id.lesson_next) {
            if (currentLesson < mLessonModels.size()) {
                currentLesson += 1;
                mDataManager.getPreManager().setCurrentLesson(currentLesson);
                setupLesson(currentLesson);
            }
        }
        if (view.getId() == R.id.lesson_start) {
            startLesson();
        }
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
                        tone = Func.generateTone(800,100);
                    } else if (m.equals("-")){
                        tone = Func.generateTone(800,300);
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
                Func.clearMemory(tone);
            }
        }).start();
    }
}
