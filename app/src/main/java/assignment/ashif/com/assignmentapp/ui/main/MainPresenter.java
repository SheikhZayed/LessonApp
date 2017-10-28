package assignment.ashif.com.assignmentapp.ui.main;

import assignment.ashif.com.assignmentapp.data.DataManager;
import assignment.ashif.com.assignmentapp.ui.base.BasePresenter;

/**
 * Created by ashif on 10/28/17.
 */

public class MainPresenter<V extends MainContract.MainMvpView> extends BasePresenter<V>
    implements MainContract.MainMvpPresenter<V> {

  public MainPresenter(DataManager dataManager) {
    super(dataManager);
  }

  @Override public void startNextSlide() {
    getMvpView().startNextLesson();
  }
}
