package badassapps.aaron.newshag;

import android.util.Log;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by aaron on 5/31/2016.
 */
public class NewsHagModel {
    private static NewsHagModel instance;
    private static ApiResponseHandler responseHandler;
    private static GetCurrentDateTime getCurrentDateTime = new GetCurrentDateTime();

    private static final String GUARDIAN_SEARCH_ALL = "http://content.guardianapis.com/search?";
    //Pattern is query and query,i.e. &tag=[selected_topic]/[selected_topic]
    private static final String GUARDIAN_TAG = "&tag=";
    private static final String CURRENT_DATE = "&from-date="+ getCurrentDateTime.getDate()
            .toString();

    private static final String API_KEY = GuardianAppData.API_KEY;
    private static final String APIKEY_SEARCH_STRING = "&api_key="+API_KEY;

    private static final String FORMAT_STRING = "&nojsoncallback=1&format=json";

    private static final String CONSTRUCT = GUARDIAN_SEARCH_ALL + GUARDIAN_TAG + "world/world"+
            APIKEY_SEARCH_STRING +
            CURRENT_DATE + APIKEY_SEARCH_STRING + FORMAT_STRING;

    //Empty constructor
    NewsHagModel(){
    }


    //Creates our singleton
    public static NewsHagModel getInstance(ApiResponseHandler handler){
        responseHandler = handler;
        if(instance == null){
            instance = new NewsHagModel();
        }
        return instance;
    }

    public void doRequest(){
        AsyncHttpClient client = new AsyncHttpClient();

        //Ensure somewhere our wifi is on/off

        client.get(
                CONSTRUCT ,null,
                new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        {
                            String postList = "";

                            try {
                                JSONObject jsonObject = response.getJSONObject("response");
                                JSONArray array = jsonObject.getJSONArray("results");
                                    for(int i = 0; i < array.length(); i++) {
                                        JSONObject title = array.getJSONObject(i);
                                        String postTitle = title.getString("webTitle");
                                        postList += postTitle + "\n";
                                    }
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    public interface ApiResponseHandler{
        void handleResponse();
    }
}
