package assignment.ashif.com.assignmentapp.ui.base;

/**
 * Created by ashif on 10/28/17.
 */

public interface MvpPresenter<V extends MvpView> {
  void onAttach(V mvpView);
}
