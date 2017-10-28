package assignment.ashif.com.assignmentapp.ui.main;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import assignment.ashif.com.assignmentapp.App;
import assignment.ashif.com.assignmentapp.R;
import assignment.ashif.com.assignmentapp.data.DataManager;
import assignment.ashif.com.assignmentapp.model.LessonData;
import assignment.ashif.com.assignmentapp.ui.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.w3c.dom.Text;

import static assignment.ashif.com.assignmentapp.utils.AppConstants.AUDIO_TYPE;
import static assignment.ashif.com.assignmentapp.utils.AppConstants.TYPE_LEARN;
import static assignment.ashif.com.assignmentapp.utils.AppConstants.TYPE_QUESTION;

public class MainActivity extends BaseActivity implements MainContract.MainMvpView {

  @BindView(R.id.tv_heading) TextView mTvHeading;
  @BindView(R.id.tv_subtext) TextView mTvSubText;
  @BindView(R.id.fab_start_audio) FloatingActionButton mFabPlayAudio;
  @BindView(R.id.fab_start_audio_activity) FloatingActionButton mStartNextSlide;
  @BindView(R.id.fab_record_audio) FloatingActionButton mStartSpeechRecog;
  @BindView(R.id.tv_hold_record) TextView mHoldToRecord;

  private MainPresenter mMainPresenter;
  private DataManager mDataManager;
  private int index = 0;
  private List<LessonData> mLessonData;
  private int MAX_LIMIT;
  private static final int REQ_CODE_SPEECH_INPUT = 007;

  public static final String TAG = MainActivity.class.getSimpleName();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mDataManager = ((App)getApplication()).getDataManager();
    mMainPresenter = new MainPresenter(mDataManager);
    mMainPresenter.onAttach(this);

    mLessonData = mDataManager.getResponse().getLessonData();
    MAX_LIMIT = mLessonData.size();
    populateData();
  }

  private void populateData() {
    if (mLessonData.get(index).getType().equals(TYPE_LEARN)){
      mTvHeading.setText(mLessonData.get(index).getConceptName());
      mTvSubText.setText(mLessonData.get(index).getTargetScript());
      mStartSpeechRecog.setVisibility(View.INVISIBLE);
      mHoldToRecord.setVisibility(View.INVISIBLE);
    }
  }

  @OnClick(R.id.fab_start_audio_activity)
  public void onStartActivityClicked(){
    onNextClicked();
  }
  @OnClick(R.id.fab_start_audio)
  public void onPlayAudioClicked(){
    onPlayAudioButtonClicked();
  }

  @OnClick(R.id.fab_record_audio)
  public void onRecordClicked(){
    onRecordAudioClicked();
  }

  @Override public void onNextClicked() {
    mMainPresenter.startNextSlide();
  }

  @Override public void startNextLesson() {
    if (index != MAX_LIMIT - 1) {
      index++;
      if (mLessonData.get(index).getType().equals(TYPE_QUESTION)) {
        mTvHeading.setText(mLessonData.get(index).getConceptName());
        mTvSubText.setText(mLessonData.get(index).getTargetScript());
        mStartSpeechRecog.setVisibility(View.VISIBLE);
        mHoldToRecord.setVisibility(View.VISIBLE);
      } else {
        populateData();
      }
    }
    if (index == MAX_LIMIT - 1){
      Toast.makeText(getApplicationContext(),"Lessons completed",Toast.LENGTH_SHORT).show();
    }
  }

  @Override public void onPlayAudioButtonClicked() {
    Intent intent = new Intent();
    intent.setAction(Intent.ACTION_VIEW);
    intent.setDataAndType(Uri.parse(mLessonData.get(index).getAudioUrl()), AUDIO_TYPE);
    startActivity(intent);
  }

  @Override public void onRecordAudioClicked() {
    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello,Speak the word on the screen");
    try {
      startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
    } catch (ActivityNotFoundException a) {

    }
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch (requestCode) {
      case REQ_CODE_SPEECH_INPUT: {
        if (resultCode == RESULT_OK && null != data) {
          ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
          Toast.makeText(getApplicationContext(),"You told - " + result.get(0),Toast.LENGTH_SHORT).show();
          checkIfDataMatch(result.get(0));
        }
        break;
      }

    }
  }

  private void checkIfDataMatch(String result) {
    if (mLessonData.get(index).getPronunciation().toString().equalsIgnoreCase(result)){
      new AlertDialog.Builder(this)
          .setTitle("voice feedback")
          .setMessage("you told the exact word,kudos")
          .create().show();
    }
  }
}
