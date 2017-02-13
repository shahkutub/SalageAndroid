package com.salage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/13/2017.
 */

public class JsonStructure {


    public List<AgentInfo> info =new ArrayList<AgentInfo>();
    public List<JsonInfo> data =new ArrayList<JsonInfo>();

    public List<JsonInfo> getData() {
        return data;
    }

    public void setData(List<JsonInfo> data) {
        this.data = data;
    }

    public List<AgentInfo> getInfo() {
        return info;
    }

    public void setInfo(List<AgentInfo> info) {
        this.info = info;
    }
}
