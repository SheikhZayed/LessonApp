package assignment.ashif.com.assignmentapp.ui.main;

import assignment.ashif.com.assignmentapp.ui.base.MvpPresenter;
import assignment.ashif.com.assignmentapp.ui.base.MvpView;

/**
 * Created by ashif on 10/28/17.
 */

public class MainContract {
  public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void startNextSlide();
  }

  public interface MainMvpView extends MvpView {
    void onNextClicked();
    void startNextLesson();
    void onPlayAudioButtonClicked();
    void onRecordAudioClicked();
  }
}
