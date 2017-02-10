package com.salage.model;

/**
 * Created by User on 2/7/2017.
 */

public class BarCodeTableInfo {
    private String id;
    private String BARC_BARCODE;
    private String PROD_CODE;
    private String BARC_TIMESTAMP;
    private String IS_DELETED;

    public BarCodeTableInfo(String BARC_BARCODE, String PROD_CODE, String BARC_TIMESTAMP, String IS_DELETED) {
        this.BARC_BARCODE = BARC_BARCODE;
        this.PROD_CODE = PROD_CODE;
        this.BARC_TIMESTAMP = BARC_TIMESTAMP;
        this.IS_DELETED = IS_DELETED;
    }

    public BarCodeTableInfo() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBARC_BARCODE() {
        return BARC_BARCODE;
    }

    public void setBARC_BARCODE(String BARC_BARCODE) {
        this.BARC_BARCODE = BARC_BARCODE;
    }

    public String getPROD_CODE() {
        return PROD_CODE;
    }

    public void setPROD_CODE(String PROD_CODE) {
        this.PROD_CODE = PROD_CODE;
    }

    public String getBARC_TIMESTAMP() {
        return BARC_TIMESTAMP;
    }

    public void setBARC_TIMESTAMP(String BARC_TIMESTAMP) {
        this.BARC_TIMESTAMP = BARC_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }
}
