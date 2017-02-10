package com.salage.model;

/**
 * Created by User on 2/7/2017.
 */

public class BrandsTableInfo {
    private String ID;
    private String BRAN_ID;
    private String BRAN_DESCRIPTION;
    private String BRAN_TIMESTAMP;
    private String IS_DELETED;

    public BrandsTableInfo() {
    }

    public BrandsTableInfo(String BRAN_ID, String BRAN_DESCRIPTION, String BRAN_TIMESTAMP,
                           String IS_DELETED) {
        this.BRAN_ID = BRAN_ID;
        this.BRAN_DESCRIPTION = BRAN_DESCRIPTION;
        this.BRAN_TIMESTAMP = BRAN_TIMESTAMP;
        this.IS_DELETED = IS_DELETED;
    }

    public String getBRAN_ID() {
        return BRAN_ID;
    }

    public void setBRAN_ID(String BRAN_ID) {
        this.BRAN_ID = BRAN_ID;
    }

    public String getBRAN_DESCRIPTION() {
        return BRAN_DESCRIPTION;
    }

    public void setBRAN_DESCRIPTION(String BRAN_DESCRIPTION) {
        this.BRAN_DESCRIPTION = BRAN_DESCRIPTION;
    }

    public String getBRAN_TIMESTAMP() {
        return BRAN_TIMESTAMP;
    }

    public void setBRAN_TIMESTAMP(String BRAN_TIMESTAMP) {
        this.BRAN_TIMESTAMP = BRAN_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
