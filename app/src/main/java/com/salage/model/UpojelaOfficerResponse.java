package com.salage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 11/7/2016.
 */

public class UpojelaOfficerResponse {

    private String message;
    private List<UpojelaOfficerInfo> data = new ArrayList<UpojelaOfficerInfo>();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<UpojelaOfficerInfo> getData() {
        return data;
    }

    public void setData(List<UpojelaOfficerInfo> data) {
        this.data = data;
    }
}
