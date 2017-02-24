package com.salage.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/24/2017.
 */

public class JsonToSendServer {

    public List<AgentInfo> info =new ArrayList<AgentInfo>();
    public SyncData data =new SyncData();

    public List<AgentInfo> getInfo() {
        return info;
    }

    public void setInfo(List<AgentInfo> info) {
        this.info = info;
    }

    public SyncData getData() {
        return data;
    }

    public void setData(SyncData data) {
        this.data = data;
    }
}
