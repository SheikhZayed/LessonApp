package assignment.ashif.com.assignmentapp.ui.base;

import assignment.ashif.com.assignmentapp.data.DataManager;

/**
 * Created by ashif on 10/28/17.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

  private V mMvpView;
  private DataManager mDataManager;

  public BasePresenter(DataManager dataManager) {
    mDataManager = dataManager;
  }

  @Override public void onAttach(V mvpView) {
    mMvpView = mvpView;
  }

  public V getMvpView() {
    return mMvpView;
  }

  public DataManager getDataManager() {
    return mDataManager;
  }
}
