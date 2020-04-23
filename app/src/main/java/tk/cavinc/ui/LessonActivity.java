package tk.cavinc.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import tk.cavinc.R;
import tk.cavinc.data.managers.DataManager;
import tk.cavinc.data.models.LessonModel;
import tk.cavinc.utils.Func;

/**
 * Created by cav on 22.04.20.
 */

public class LessonActivity extends AppCompatActivity implements View.OnClickListener{
    private DataManager mDataManager;

    private TextView mLessonSpeed;
    private TextView mCurrentLesson;
    private int speed;
    private ArrayList<LessonModel> mLessonModels;
    private int currentLesson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        mDataManager = DataManager.getInstance();

        findViewById(R.id.lesson_prev).setOnClickListener(this);
        findViewById(R.id.lesson_next).setOnClickListener(this);

        speed = mDataManager.getPreManager().getWorkSpeed();

        mLessonSpeed = findViewById(R.id.lesson_speed);
        mLessonSpeed.setText("скорость "+mDataManager.getPreManager().getWorkSpeed()+" зкн/мин");

        mLessonModels = mDataManager.loadLesson();
        mCurrentLesson = findViewById(R.id.current_lesson);
        mCurrentLesson.setText(String.valueOf(mDataManager.getPreManager().getCurrentLesson()));
        currentLesson = mDataManager.getPreManager().getCurrentLesson();
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLesson(currentLesson);
    }

    private void startLesson(int lesson){
        mCurrentLesson.setText(String.valueOf(lesson));
        LessonModel lessonModel = mLessonModels.get(currentLesson);
        String[] letter = Func.splitLesson(lessonModel.getLesson());

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.lesson_prev) {
            if (currentLesson > 1) {
                currentLesson -= 1;
                mDataManager.getPreManager().setCurrentLesson(currentLesson);
            }
        }
        if (view.getId() == R.id.lesson_next) {
            if (currentLesson < mLessonModels.size()) {
                currentLesson += 1;
                mDataManager.getPreManager().setCurrentLesson(currentLesson);
            }
        }
    }
}
