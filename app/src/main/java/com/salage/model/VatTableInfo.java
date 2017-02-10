package com.salage.model;

/**
 * Created by User on 2/7/2017.
 */

public class VatTableInfo {
    private String ID;
    private String VATT_ID;
    private String VATT_CODE;
    private String VATT_DESCRIPTION;
    private String VATT_PERCENT;

    private String VATT_TIMESTAMP;
    private String IS_DELETED;

    public VatTableInfo() {
    }

    public VatTableInfo(String VATT_ID, String VATT_CODE, String VATT_DESCRIPTION,
                        String VATT_PERCENT, String VATT_TIMESTAMP, String IS_DELETED) {
        this.VATT_ID = VATT_ID;
        this.VATT_CODE = VATT_CODE;
        this.VATT_DESCRIPTION = VATT_DESCRIPTION;
        this.VATT_PERCENT = VATT_PERCENT;
        this.VATT_TIMESTAMP = VATT_TIMESTAMP;
        this.IS_DELETED = IS_DELETED;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getVATT_ID() {
        return VATT_ID;
    }

    public void setVATT_ID(String VATT_ID) {
        this.VATT_ID = VATT_ID;
    }

    public String getVATT_CODE() {
        return VATT_CODE;
    }

    public void setVATT_CODE(String VATT_CODE) {
        this.VATT_CODE = VATT_CODE;
    }

    public String getVATT_DESCRIPTION() {
        return VATT_DESCRIPTION;
    }

    public void setVATT_DESCRIPTION(String VATT_DESCRIPTION) {
        this.VATT_DESCRIPTION = VATT_DESCRIPTION;
    }

    public String getVATT_PERCENT() {
        return VATT_PERCENT;
    }

    public void setVATT_PERCENT(String VATT_PERCENT) {
        this.VATT_PERCENT = VATT_PERCENT;
    }

    public String getVATT_TIMESTAMP() {
        return VATT_TIMESTAMP;
    }

    public void setVATT_TIMESTAMP(String VATT_TIMESTAMP) {
        this.VATT_TIMESTAMP = VATT_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }
}
