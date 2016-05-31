package badassapps.aaron.newshag;

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
    GetCurrentDateTime getCurrentDateTime = new GetCurrentDateTime();

    private static final String GUARDIAN_SEARCH_ALL = "http://content.guardianapis.com/search?";
    //Pattern is query and query,i.e. &tag=[selected_topic]/[selected_topic]
    private static final String GUARDIAN_TAG = "&tag=";
    private static final String CURRENT_DATE = "&from-date="+ getCurrentDateTime;

    private static final String API_KEY = GuardianAppData.API_KEY;
    private static final String APIKEY_SEARCH_STRING = "&api_key="+API_KEY;

    private static final String TAGS_STRING = "&tags=";
    private static final String FORMAT_STRING = "&nojsoncallback=1&format=json";

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

    public void doRequest(String parameter){
        AsyncHttpClient client = new AsyncHttpClient();

        //Ensure somewhere our wifi is on/off

        client.get(
                GUARDIAN_SEARCH_QUERY + GUARDIAN_PHOTOS_SEARCH_STRING + APIKEY_SEARCH_STRING + TAGS_STRING + parameter + FORMAT_STRING,null,
                new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        {
                            String id, secret, server, farm, address = null;
                            //Need to reconstruct and grab JSON object for image! Return image when done.
                            try {
                                JSONArray jArray = response.getJSONObject("photos").getJSONArray("photo");
                                JSONObject jObject = (JSONObject) jArray.get(0);
                                id = jObject.getString("id");
                                secret = jObject.getString("secret");
                                server = jObject.getString("server");
                                farm = jObject.getString("farm");
                                address = "https://farm"+farm+".staticGUARDIAN.com/"+server+"/"+id+"_"+secret+"_m.jpg";
                                responseHandler.handleResponse(address);

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
