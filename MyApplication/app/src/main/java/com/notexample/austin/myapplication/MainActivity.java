package com.notexample.austin.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = (TextView) findViewById(R.id.text);
        textView.setTextSize(30);

        final AsyncHttpClient client = new AsyncHttpClient();

        client.get("http://content.guardianapis.com/search?q=debate%20AND%20economy&tag=politics/politics&from-date=2014-01-01&api-key=6c07024d-c4eb-41c9-9184-2641163338a1",
                new JsonHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject responseBody) {


                try {
                    JSONObject jsonObject = responseBody.getJSONObject("response");
                    JSONArray jsonArray = jsonObject.getJSONArray("results");



                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject photo = jsonArray.getJSONObject(i);
                        if (!photo.has("webTitle")) continue;

                        textView.setText(photo.getString("webTitle"));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(getApplicationContext(), "Process Not Successful",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



}
