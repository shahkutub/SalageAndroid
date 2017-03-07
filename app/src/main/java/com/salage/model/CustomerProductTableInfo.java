package com.salage.model;

/**
 * Created by User on 3/7/2017.
 */

public class CustomerProductTableInfo {

    private String id,CUST_CODE,PROD_CODE,CUPR_DISCOUNT,CUPR_PRICE,CUPR_TIMESTAMP,IS_DELETED;

    public CustomerProductTableInfo() {
    }

    public CustomerProductTableInfo(String CUST_CODE, String PROD_CODE, String CUPR_DISCOUNT,
                                    String CUPR_PRICE, String CUPR_TIMESTAMP, String IS_DELETED) {
        this.CUST_CODE = CUST_CODE;
        this.PROD_CODE = PROD_CODE;
        this.CUPR_DISCOUNT = CUPR_DISCOUNT;
        this.CUPR_PRICE = CUPR_PRICE;
        this.CUPR_TIMESTAMP = CUPR_TIMESTAMP;
        this.IS_DELETED = IS_DELETED;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCUST_CODE() {
        return CUST_CODE;
    }

    public void setCUST_CODE(String CUST_CODE) {
        this.CUST_CODE = CUST_CODE;
    }

    public String getPROD_CODE() {
        return PROD_CODE;
    }

    public void setPROD_CODE(String PROD_CODE) {
        this.PROD_CODE = PROD_CODE;
    }

    public String getCUPR_DISCOUNT() {
        return CUPR_DISCOUNT;
    }

    public void setCUPR_DISCOUNT(String CUPR_DISCOUNT) {
        this.CUPR_DISCOUNT = CUPR_DISCOUNT;
    }

    public String getCUPR_PRICE() {
        return CUPR_PRICE;
    }

    public void setCUPR_PRICE(String CUPR_PRICE) {
        this.CUPR_PRICE = CUPR_PRICE;
    }

    public String getCUPR_TIMESTAMP() {
        return CUPR_TIMESTAMP;
    }

    public void setCUPR_TIMESTAMP(String CUPR_TIMESTAMP) {
        this.CUPR_TIMESTAMP = CUPR_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }
}
