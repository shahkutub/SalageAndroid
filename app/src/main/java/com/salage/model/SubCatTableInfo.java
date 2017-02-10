package com.salage.model;

/**
 * Created by User on 2/7/2017.
 */

public class SubCatTableInfo {
    private String ID;
    private String SUBC_ID;
    private String SUBC_DESCRIPTION;
    private String CATE_ID;
    private String SUBC_TIMESTAMP;

    private String IS_DELETED;

    public SubCatTableInfo() {
    }

    public SubCatTableInfo(String SUBC_ID, String SUBC_DESCRIPTION, String CATE_ID,
                           String SUBC_TIMESTAMP, String IS_DELETED) {
        this.SUBC_ID = SUBC_ID;
        this.SUBC_DESCRIPTION = SUBC_DESCRIPTION;
        this.CATE_ID = CATE_ID;
        this.SUBC_TIMESTAMP = SUBC_TIMESTAMP;
        this.IS_DELETED = IS_DELETED;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSUBC_ID() {
        return SUBC_ID;
    }

    public void setSUBC_ID(String SUBC_ID) {
        this.SUBC_ID = SUBC_ID;
    }

    public String getSUBC_DESCRIPTION() {
        return SUBC_DESCRIPTION;
    }

    public void setSUBC_DESCRIPTION(String SUBC_DESCRIPTION) {
        this.SUBC_DESCRIPTION = SUBC_DESCRIPTION;
    }

    public String getCATE_ID() {
        return CATE_ID;
    }

    public void setCATE_ID(String CATE_ID) {
        this.CATE_ID = CATE_ID;
    }

    public String getSUBC_TIMESTAMP() {
        return SUBC_TIMESTAMP;
    }

    public void setSUBC_TIMESTAMP(String SUBC_TIMESTAMP) {
        this.SUBC_TIMESTAMP = SUBC_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }
}
