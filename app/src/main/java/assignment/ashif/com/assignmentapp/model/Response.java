
package assignment.ashif.com.assignmentapp.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("lesson_data")
    private List<LessonData> mLessonData;

    public List<LessonData> getLessonData() {
        return mLessonData;
    }

    public void setLessonData(List<LessonData> lessonData) {
        mLessonData = lessonData;
    }

}
