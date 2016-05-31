package badassapps.aaron.newshag;

import android.util.Log;
import android.widget.TextView;

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

    final String CONSTRUCT = GUARDIAN_SEARCH_ALL + GUARDIAN_TAG + "world/world"+
            CURRENT_DATE + APIKEY_SEARCH_STRING + FORMAT_STRING;


    //Empty constructor
    private NewsHagModel(){
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

        client.get(
                "http://content.guardianapis.com/search?&tag=world/world&from-date=2016-05-31&api-key=6c07024d-c4eb-41c9-9184-2641163338a1" ,null,
                new JsonHttpResponseHandler(){
                    String title;
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        {
//                            String postList = "";

                            try {
                                JSONArray jArray = response.getJSONObject("response").getJSONArray
                                        ("results");
                                JSONObject jObject = (JSONObject) jArray.get(2);

                                title = jObject.getString("webTitle");
                                responseHandler.handleResponse(title);
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                });
    }

    public interface ApiResponseHandler{
        void handleResponse(String response);
    }
}
