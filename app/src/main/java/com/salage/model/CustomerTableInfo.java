package com.salage.model;

/**
 * Created by User on 2/17/2017.
 */

public class CustomerTableInfo {
    private String id,CUST_CODE,CUST_NAME1,CUST_NAME2,CUST_ADDRESS,CUST_ZIP,CUST_CITY,CUST_PROVINCE,CUST_COUNTRY ,CUST_TEL ,CUST_FAX ,CUST_MOBILE,
    CUST_MAIL, CUST_CF ,CUST_VATNUM ,CUST_IBAN ,VATT_ID ,PAYM_ID ,AGEN_CODE ,CUST_PRICELIST, CUST_DISCOUNT ,CUST_STATE, CUST_TIMESTAMP, IS_DELETED;


    public CustomerTableInfo() {
    }

    public CustomerTableInfo(String CUST_CODE, String CUST_NAME1, String CUST_NAME2,
                             String CUST_ADDRESS, String CUST_ZIP, String CUST_CITY,
                             String CUST_PROVINCE, String CUST_COUNTRY,
                             String CUST_TEL, String CUST_FAX, String CUST_MOBILE,
                             String CUST_MAIL, String CUST_CF, String CUST_VATNUM,
                             String CUST_IBAN, String VATT_ID, String PAYM_ID,
                             String AGEN_CODE, String CUST_PRICELIST,
                             String CUST_DISCOUNT, String CUST_STATE,
                             String CUST_TIMESTAMP, String IS_DELETED) {
        this.CUST_CODE = CUST_CODE;
        this.CUST_NAME1 = CUST_NAME1;
        this.CUST_NAME2 = CUST_NAME2;
        this.CUST_ADDRESS = CUST_ADDRESS;
        this.CUST_ZIP = CUST_ZIP;
        this.CUST_CITY = CUST_CITY;
        this.CUST_PROVINCE = CUST_PROVINCE;
        this.CUST_COUNTRY = CUST_COUNTRY;
        this.CUST_TEL = CUST_TEL;
        this.CUST_FAX = CUST_FAX;
        this.CUST_MOBILE = CUST_MOBILE;
        this.CUST_MAIL = CUST_MAIL;
        this.CUST_CF = CUST_CF;
        this.CUST_VATNUM = CUST_VATNUM;
        this.CUST_IBAN = CUST_IBAN;
        this.VATT_ID = VATT_ID;
        this.PAYM_ID = PAYM_ID;
        this.AGEN_CODE = AGEN_CODE;
        this.CUST_PRICELIST = CUST_PRICELIST;
        this.CUST_DISCOUNT = CUST_DISCOUNT;
        this.CUST_STATE = CUST_STATE;
        this.CUST_TIMESTAMP = CUST_TIMESTAMP;
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

    public String getCUST_NAME1() {
        return CUST_NAME1;
    }

    public void setCUST_NAME1(String CUST_NAME1) {
        this.CUST_NAME1 = CUST_NAME1;
    }

    public String getCUST_NAME2() {
        return CUST_NAME2;
    }

    public void setCUST_NAME2(String CUST_NAME2) {
        this.CUST_NAME2 = CUST_NAME2;
    }

    public String getCUST_ADDRESS() {
        return CUST_ADDRESS;
    }

    public void setCUST_ADDRESS(String CUST_ADDRESS) {
        this.CUST_ADDRESS = CUST_ADDRESS;
    }

    public String getCUST_ZIP() {
        return CUST_ZIP;
    }

    public void setCUST_ZIP(String CUST_ZIP) {
        this.CUST_ZIP = CUST_ZIP;
    }

    public String getCUST_CITY() {
        return CUST_CITY;
    }

    public void setCUST_CITY(String CUST_CITY) {
        this.CUST_CITY = CUST_CITY;
    }

    public String getCUST_PROVINCE() {
        return CUST_PROVINCE;
    }

    public void setCUST_PROVINCE(String CUST_PROVINCE) {
        this.CUST_PROVINCE = CUST_PROVINCE;
    }

    public String getCUST_COUNTRY() {
        return CUST_COUNTRY;
    }

    public void setCUST_COUNTRY(String CUST_COUNTRY) {
        this.CUST_COUNTRY = CUST_COUNTRY;
    }

    public String getCUST_TEL() {
        return CUST_TEL;
    }

    public void setCUST_TEL(String CUST_TEL) {
        this.CUST_TEL = CUST_TEL;
    }

    public String getCUST_FAX() {
        return CUST_FAX;
    }

    public void setCUST_FAX(String CUST_FAX) {
        this.CUST_FAX = CUST_FAX;
    }

    public String getCUST_MOBILE() {
        return CUST_MOBILE;
    }

    public void setCUST_MOBILE(String CUST_MOBILE) {
        this.CUST_MOBILE = CUST_MOBILE;
    }

    public String getCUST_MAIL() {
        return CUST_MAIL;
    }

    public void setCUST_MAIL(String CUST_MAIL) {
        this.CUST_MAIL = CUST_MAIL;
    }

    public String getCUST_CF() {
        return CUST_CF;
    }

    public void setCUST_CF(String CUST_CF) {
        this.CUST_CF = CUST_CF;
    }

    public String getCUST_VATNUM() {
        return CUST_VATNUM;
    }

    public void setCUST_VATNUM(String CUST_VATNUM) {
        this.CUST_VATNUM = CUST_VATNUM;
    }

    public String getCUST_IBAN() {
        return CUST_IBAN;
    }

    public void setCUST_IBAN(String CUST_IBAN) {
        this.CUST_IBAN = CUST_IBAN;
    }

    public String getVATT_ID() {
        return VATT_ID;
    }

    public void setVATT_ID(String VATT_ID) {
        this.VATT_ID = VATT_ID;
    }

    public String getPAYM_ID() {
        return PAYM_ID;
    }

    public void setPAYM_ID(String PAYM_ID) {
        this.PAYM_ID = PAYM_ID;
    }

    public String getAGEN_CODE() {
        return AGEN_CODE;
    }

    public void setAGEN_CODE(String AGEN_CODE) {
        this.AGEN_CODE = AGEN_CODE;
    }

    public String getCUST_PRICELIST() {
        return CUST_PRICELIST;
    }

    public void setCUST_PRICELIST(String CUST_PRICELIST) {
        this.CUST_PRICELIST = CUST_PRICELIST;
    }

    public String getCUST_DISCOUNT() {
        return CUST_DISCOUNT;
    }

    public void setCUST_DISCOUNT(String CUST_DISCOUNT) {
        this.CUST_DISCOUNT = CUST_DISCOUNT;
    }

    public String getCUST_STATE() {
        return CUST_STATE;
    }

    public void setCUST_STATE(String CUST_STATE) {
        this.CUST_STATE = CUST_STATE;
    }

    public String getCUST_TIMESTAMP() {
        return CUST_TIMESTAMP;
    }

    public void setCUST_TIMESTAMP(String CUST_TIMESTAMP) {
        this.CUST_TIMESTAMP = CUST_TIMESTAMP;
    }

    public String getIS_DELETED() {
        return IS_DELETED;
    }

    public void setIS_DELETED(String IS_DELETED) {
        this.IS_DELETED = IS_DELETED;
    }
}
