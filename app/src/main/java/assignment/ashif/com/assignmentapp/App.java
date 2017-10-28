package assignment.ashif.com.assignmentapp;

import android.app.Application;
import assignment.ashif.com.assignmentapp.data.DataManager;

/**
 * Created by ashif on 10/28/17.
 */

public class App extends Application {
  private DataManager mDataManager;

  @Override public void onCreate() {
    super.onCreate();

    mDataManager = new DataManager();
  }

  public DataManager getDataManager() {
    return mDataManager;
  }
}
