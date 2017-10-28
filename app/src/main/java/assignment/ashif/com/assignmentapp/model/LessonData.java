
package assignment.ashif.com.assignmentapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class LessonData {

    @SerializedName("audio_url")
    private String mAudioUrl;
    @SerializedName("conceptName")
    private String mConceptName;
    @SerializedName("pronunciation")
    private String mPronunciation;
    @SerializedName("targetScript")
    private String mTargetScript;
    @SerializedName("type")
    private String mType;

    public String getAudioUrl() {
        return mAudioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        mAudioUrl = audioUrl;
    }

    public String getConceptName() {
        return mConceptName;
    }

    public void setConceptName(String conceptName) {
        mConceptName = conceptName;
    }

    public String getPronunciation() {
        return mPronunciation;
    }

    public void setPronunciation(String pronunciation) {
        mPronunciation = pronunciation;
    }

    public String getTargetScript() {
        return mTargetScript;
    }

    public void setTargetScript(String targetScript) {
        mTargetScript = targetScript;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

}
