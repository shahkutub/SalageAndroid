package com.salage.model;

/**
 * Created by User on 2/13/2017.
 */

public class SyncResponse {

    private String result;
    private String message;
    private String syncDate;

    private SyncData data = new SyncData();

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSyncDate() {
        return syncDate;
    }

    public void setSyncDate(String syncDate) {
        this.syncDate = syncDate;
    }

    public SyncData getData() {
        return data;
    }

    public void setData(SyncData data) {
        this.data = data;
    }
}
