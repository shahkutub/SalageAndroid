package com.salage.model;

/**
 * Created by User on 2/13/2017.
 */

public class CateGoryInfo {

    private String id;
    private String CATE_ID;
    private String CATE_DESCRIPTION;
    private String CATE_TIMESTAMP;
    private String IS_DELETED;

    public CateGoryInfo() {
    }

    public CateGoryInfo(String CATE_ID, String CATE_DESCRIPTION, String CATE_TIMESTAMP, String IS_DELETED) {
        this.CATE_ID = CATE_ID;
        this.CATE_DESCRIPTION = CATE_DESCRIPTION;
        this.CATE_TIMESTAMP = CATE_TIMESTAMP;
        this.IS_DELETED = IS_DELETED;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCATE_ID() {
        return CATE_ID;
    }

    public void setCATE_ID(String CATE_ID) {
        this.CATE_ID = CATE_ID;
    }

    public String getCATE_DESCRIPTION() {
        return CATE_DESCRIPTION;
    }

    public void setCATE_DESCRIPTION(String CATE_DESCRIPTION) {
        this.CATE_DESCRIPTION = CATE_DESCRIPTION;
    }

    public String getCATE_TIMESTAMP() {
        return CATE_TIMESTAMP;
    }

    public void setCATE_TIMESTAMP(String CATE_TIMESTAMP) {
        this.CATE_TIMESTAMP = CATE_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }
}
