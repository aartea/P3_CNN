package badassapps.aaron.newshag;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewsHagModel
        .ApiResponseHandler {


    ListView listView;
    LinkedList<Article> items;
    CustomAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        items = new LinkedList<>();
        listView = (ListView) findViewById(R.id.listView);
        adapter = new CustomAdapter(this, R.layout.list_item, items);
        listView.setAdapter(adapter);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        //If there is internet connection then the user will be presented with a notification that displays the top story
        if (networkInfo != null && networkInfo.isConnected()) {
            Intent intent1 = new Intent(this, MainActivity.class);

            NewsHagModel.getInstance(MainActivity.this).doRequest(items);

            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent1, 0);

            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.news)).build();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(R.drawable.ic_chrome_reader_mode_black_24dp);
            mBuilder.setContentTitle("BREAKING NEWS!");
            mBuilder.setContentText("The News Hag team: Check out the latest story!");
            mBuilder.setContentIntent(pendingIntent1);
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigPictureStyle);
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(1, bigPictureStyle.build());
            notificationManager.cancel(6);


        } else {
            //If there is no internet connection present, the user is presented with a notification that lasts 30 seconds with the option to go into settings and turn it on via click
            Intent intent = new Intent(this, MainActivity.class);
            Intent intent1 = new Intent(Settings.ACTION_WIFI_SETTINGS);

            PendingIntent pendingIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
            PendingIntent pendingIntent1 = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent1, 0);


            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);
            mBuilder.setContentTitle("No internet connection!");
            mBuilder.setContentText("To use the app, please enable WIFI, Thanks!");
            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setStyle(bigTextStyle);
            mBuilder.addAction(android.R.drawable.ic_menu_info_details, "Connect WIFI", pendingIntent1);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(6, bigTextStyle.build());
            notificationManager.cancel(1);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(MainActivity.this, Top10News.class);
                myIntent.putExtra("position", position);
                Article article = items.get(position);
                myIntent.putExtra("article", article);
                startActivity(myIntent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_poly) {

        } else if (id == R.id.nav_weather) {

        } else if (id == R.id.nav_worldnews) {

        } else if (id == R.id.nav_fontsize) {

        } else if (id == R.id.nav_favorites) {

        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void clickingPolitics(MenuItem item) {
        Intent clickingPoly = new Intent(MainActivity.this, ClickingPolitics.class);
        startActivity(clickingPoly);
    }

    public void clickingWeather(MenuItem item) {
        Intent clickingWeather = new Intent(MainActivity.this, ClickingWeather.class);
        startActivity(clickingWeather);
    }

    public void clickingWorldNews(MenuItem item) {
        Intent clickingWorldNews = new Intent(MainActivity.this, ClickingWorldNews.class);
        startActivity(clickingWorldNews);
    }

    public void clickingFontSize(MenuItem item) {
        Intent clickingFontSize = new Intent(MainActivity.this, ChangingFontSize.class);
        startActivity(clickingFontSize);

    }

    public void clickingFavorites(MenuItem item) {
        Intent clickingFavorites = new Intent(MainActivity.this, ClickingFavorites.class);
        startActivity(clickingFavorites);
    }

    public void clickingShare(MenuItem item) {
    }


    @Override
    public void handleResponse(LinkedList response) {
        items = response;
        adapter.clear();
        adapter.notifyDataSetChanged();
        adapter.addAll(items);
    }

    public class CustomAdapter extends ArrayAdapter {

        Context mContext;
        int mLayoutResource;
        LinkedList<Article> mList;

        public CustomAdapter(Context context, int layoutResource, LinkedList<Article> list) {
            super(context, layoutResource, list);
            mContext = context;
            mLayoutResource = layoutResource;
            mList = list;
        }

    }

}

