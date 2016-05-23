package com.telestra.demoApp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.telestra.demoApp.Adapter.DetailsListAdapter;
import com.telestra.demoApp.Adapter.MyRecyclerViewAdapter;
import com.telestra.demoApp.Dialog.NoNetworkConnection;
import com.telestra.demoApp.Pojo.ImageData;
import com.telestra.demoApp.Pojo.ImageDesc;
import com.telestra.demoApp.Util.AsyncWebService;
import com.telestra.demoApp.Util.DataDownload;
import com.telestra.demoApp.Util.Util;

import java.util.List;

public class SplashActivity extends AppCompatActivity implements DataDownload {

    ProgressBar loadingURL;

    Util util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        util = new Util(this);
        if (util.isNetworkAvailable()) {
            loadJSONFromUrl();
        } else {
            new NoNetworkConnection(this, getString(R.string.alert_no_network_title), getString(R.string.alert_no_network_text)).show();
        }
    }


    private void loadJSONFromUrl() {
        findViewById(R.id.refreshButton).setClickable(false);
        loadingURL = (ProgressBar) findViewById(R.id.progressBar1);
        loadingURL.setVisibility(View.VISIBLE);
        new AsyncWebService(this, this);
    }

    @Override
    public void receivedData(String responseData) {
        findViewById(R.id.refreshButton).setClickable(true);
        loadingURL.setVisibility(View.GONE);
        if (responseData != null && responseData.length() > 0) {
            ImageData listOfData = new Gson().fromJson(responseData, ImageData.class);
            changeActionBarTitle(listOfData.getTitle());
            detailsListView(listOfData.getRows());
        } else {
            new NoNetworkConnection(this, getString(R.string.alert_data_error), getString(R.string.alert_no_data_text)).show();
        }
    }

    private void changeActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(title);
    }

    private void detailsListView(List<ImageDesc> rows) {
       /* ListView detailsList = (ListView) findViewById(R.id.detailsListView);
        detailsList.setAdapter(new DetailsListAdapter(this, rows));*/

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter(rows,this));
    }

    public void refreshListView(View v) {
        findViewById(R.id.refreshButton).setClickable(false);
        loadJSONFromUrl();
    }
}
