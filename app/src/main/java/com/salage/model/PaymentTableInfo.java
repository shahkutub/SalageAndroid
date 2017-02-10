package com.salage.model;

/**
 * Created by User on 2/7/2017.
 */

public class PaymentTableInfo {
    private String ID;
    private String PAYM_ID;
    private String PAYM_CODE;
    private String PAYM_DESCRIPTION;
    private String PAYM_DISCOUNT;
    private String PAYM_TIMESTAMP;
    private String IS_DELETED;

    public PaymentTableInfo() {
    }

    public PaymentTableInfo(String PAYM_ID, String PAYM_CODE, String PAYM_DESCRIPTION, String PAYM_DISCOUNT, String PAYM_TIMESTAMP, String IS_DELETED) {
        this.PAYM_ID = PAYM_ID;
        this.PAYM_CODE = PAYM_CODE;
        this.PAYM_DESCRIPTION = PAYM_DESCRIPTION;
        this.PAYM_DISCOUNT = PAYM_DISCOUNT;
        this.PAYM_TIMESTAMP = PAYM_TIMESTAMP;
        this.IS_DELETED = IS_DELETED;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPAYM_ID() {
        return PAYM_ID;
    }

    public void setPAYM_ID(String PAYM_ID) {
        this.PAYM_ID = PAYM_ID;
    }

    public String getPAYM_CODE() {
        return PAYM_CODE;
    }

    public void setPAYM_CODE(String PAYM_CODE) {
        this.PAYM_CODE = PAYM_CODE;
    }

    public String getPAYM_DESCRIPTION() {
        return PAYM_DESCRIPTION;
    }

    public void setPAYM_DESCRIPTION(String PAYM_DESCRIPTION) {
        this.PAYM_DESCRIPTION = PAYM_DESCRIPTION;
    }

    public String getPAYM_DISCOUNT() {
        return PAYM_DISCOUNT;
    }

    public void setPAYM_DISCOUNT(String PAYM_DISCOUNT) {
        this.PAYM_DISCOUNT = PAYM_DISCOUNT;
    }

    public String getPAYM_TIMESTAMP() {
        return PAYM_TIMESTAMP;
    }

    public void setPAYM_TIMESTAMP(String PAYM_TIMESTAMP) {
        this.PAYM_TIMESTAMP = PAYM_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }
}
