package com.telestra.demoApp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.gson.Gson;
import com.telestra.demoApp.Adapter.DetailsViewAdapter;
import com.telestra.demoApp.Dialog.NoNetworkConnection;
import com.telestra.demoApp.Pojo.ImageData;
import com.telestra.demoApp.Pojo.ImageDesc;
import com.telestra.demoApp.Util.AsyncWebService;
import com.telestra.demoApp.Util.DataDownload;
import com.telestra.demoApp.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListLoadingActivity extends AppCompatActivity implements DataDownload {

    private ProgressBar loadingURL;

    private DetailsViewAdapter recycleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_loading);
        initialScreen();
        if (Util.isNetworkAvailable(this)) {
            loadJSONFromUrl();
        } else {
            new NoNetworkConnection(this, getString(R.string.alert_no_network_title), getString(R.string.alert_no_network_text)).show();
        }
    }

    //Used to initialize the screen
    private void initialScreen() {
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        recycleAdapter = new DetailsViewAdapter();
        mRecyclerView.setAdapter(recycleAdapter);
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

    //Used to change title of actionbar
    private void changeActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(title);
    }

    //Update the recyclerview adapter
    private void detailsListView(List<ImageDesc> descRows) {
        recycleAdapter.updateList(getTableLists(descRows));
    }


    //will call the load function to get data from server
    public void refreshListView(View v) {
        findViewById(R.id.refreshButton).setClickable(false);
        loadJSONFromUrl();
    }

    //Get list of Imade from given row list
    private List<ImageDesc> getTableLists(List<ImageDesc> descRows) {
        List<ImageDesc> imageDescServer = new ArrayList<>();
        for (ImageDesc data : descRows) {
            if (data.getImageHref() != null || data.getDescription() != null || data.getTitle() != null) {
                imageDescServer.add(data);
            }
        }
        return imageDescServer;
    }
}
