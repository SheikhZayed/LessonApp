package assignment.ashif.com.assignmentapp.data;

import assignment.ashif.com.assignmentapp.model.Response;
import assignment.ashif.com.assignmentapp.utils.AppConstants;
import com.google.gson.Gson;

/**
 * Created by ashif on 10/28/17.
 */

public class DataManager {

  Gson gson = new Gson();

  public Response getResponse(){
    Response response = gson.fromJson(AppConstants.JSON_DATA,Response.class);
    return response;
  }
}
