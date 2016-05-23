package com.telestra.demoApp.Util;

/**
 * This interface is used to pass data from Async task to activity
 */
public interface DataDownload {

    void receivedData(String responseData);
}
