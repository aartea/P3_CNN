package badassapps.aaron.newshag;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

public class ClickingPolitics extends AppCompatActivity implements NewsHagModel
        .ApiResponseHandler {

    ArrayAdapter<String> mAdapter;
    ListView listView;
    LinkedList<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicking_politics);

        items = new LinkedList<>();
        listView = (ListView) findViewById(R.id.listView);
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(mAdapter);

        NewsHagModel.getInstance((NewsHagModel.ApiResponseHandler) ClickingPolitics.this).doRequest(items);
    }

    @Override
    public void handleResponse(LinkedList response) {
        items = response;
        mAdapter.clear();
        mAdapter.notifyDataSetChanged();
        mAdapter.addAll(items);
    }
}
