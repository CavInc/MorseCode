package tk.cavinc.data.models;

/**
 * Created by cav on 12.04.20.
 */

public class LessonModel {
    private int mId;
    private String mLesson;

    public LessonModel(int id, String lesson) {
        mId = id;
        mLesson = lesson;
    }

    public int getId() {
        return mId;
    }

    public String getLesson() {
        return mLesson;
    }

}
