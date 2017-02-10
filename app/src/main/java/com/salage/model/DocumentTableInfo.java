package com.salage.model;

public class DocumentTableInfo {

	String id;
	String DOCH_TERM_CODE;
	String DOCH_TERM_ID;
	String DOCH_TYPE;
	String DOCH_NUMBER;
	String DOCH_DATE;

	String AGEN_CODE;
	String DOCH_PRICELIST;
	String CUST_CODE;
	String CUST_NAME1;
	String CUST_NAME2;

	String CUST_ADDRESS;
	String CUST_ZIP;
	String CUST_CITY;
	String CUST_MAIL;
	String CUST_PROVINCE;

	String CUST_COUNTRY;
	String CUST_DISCOUNT;
	String SPECIAL_VATT;
	String DEST_ID;
	String DEST_NAME;


	String DEST_ADDRESS;
	String DEST_ZIP;
	String DEST_CITY;
	String DEST_PROVINCE;
	String DEST_COUNTRY;

	String VATT_ID;
	String PAYM_ID;
	String DOCH_NOTE;
	String DOCH_TAXABLE;
	String DOCH_VAT;

	String DOCH_TOTAL;
	String DOCH_SENT;
	String IS_DELETED;
	String DOCH_TIMESTAMP;

	public DocumentTableInfo(){

	}

	public DocumentTableInfo(String AGEN_CODE,String CUST_NAME1){
		this.AGEN_CODE = AGEN_CODE;
		this.CUST_NAME1 = CUST_NAME1;
	}

	public DocumentTableInfo(String DOCH_TERM_CODE, String DOCH_TERM_ID,
							 String DOCH_TYPE, String DOCH_NUMBER, String DOCH_DATE,
							 String AGEN_CODE, String DOCH_PRICELIST, String CUST_CODE,
							 String CUST_NAME1, String CUST_NAME2, String CUST_ADDRESS,
							 String CUST_ZIP, String CUST_CITY, String CUST_MAIL,
							 String CUST_PROVINCE, String CUST_COUNTRY,
							 String CUST_DISCOUNT, String SPECIAL_VATT,
							 String DEST_ID, String DEST_NAME, String DEST_ADDRESS,
							 String DEST_ZIP, String DEST_CITY, String DEST_PROVINCE,
							 String DEST_COUNTRY, String VATT_ID, String PAYM_ID,
							 String DOCH_NOTE, String DOCH_TAXABLE, String DOCH_VAT,
							 String DOCH_TOTAL, String DOCH_SENT, String IS_DELETED,
							 String DOCH_TIMESTAMP) {
		this.DOCH_TERM_CODE = DOCH_TERM_CODE;
		this.DOCH_TERM_ID = DOCH_TERM_ID;
		this.DOCH_TYPE = DOCH_TYPE;
		this.DOCH_NUMBER = DOCH_NUMBER;
		this.DOCH_DATE = DOCH_DATE;
		this.AGEN_CODE = AGEN_CODE;
		this.DOCH_PRICELIST = DOCH_PRICELIST;
		this.CUST_CODE = CUST_CODE;
		this.CUST_NAME1 = CUST_NAME1;
		this.CUST_NAME2 = CUST_NAME2;
		this.CUST_ADDRESS = CUST_ADDRESS;
		this.CUST_ZIP = CUST_ZIP;
		this.CUST_CITY = CUST_CITY;
		this.CUST_MAIL = CUST_MAIL;
		this.CUST_PROVINCE = CUST_PROVINCE;
		this.CUST_COUNTRY = CUST_COUNTRY;
		this.CUST_DISCOUNT = CUST_DISCOUNT;
		this.SPECIAL_VATT = SPECIAL_VATT;
		this.DEST_ID = DEST_ID;
		this.DEST_NAME = DEST_NAME;
		this.DEST_ADDRESS = DEST_ADDRESS;
		this.DEST_ZIP = DEST_ZIP;
		this.DEST_CITY = DEST_CITY;
		this.DEST_PROVINCE = DEST_PROVINCE;
		this.DEST_COUNTRY = DEST_COUNTRY;
		this.VATT_ID = VATT_ID;
		this.PAYM_ID = PAYM_ID;
		this.DOCH_NOTE = DOCH_NOTE;
		this.DOCH_TAXABLE = DOCH_TAXABLE;
		this.DOCH_VAT = DOCH_VAT;
		this.DOCH_TOTAL = DOCH_TOTAL;
		this.DOCH_SENT = DOCH_SENT;
		this.IS_DELETED = IS_DELETED;
		this.DOCH_TIMESTAMP = DOCH_TIMESTAMP;
	}

	public String getDOCH_TERM_CODE() {
		return DOCH_TERM_CODE;
	}

	public void setDOCH_TERM_CODE(String DOCH_TERM_CODE) {
		this.DOCH_TERM_CODE = DOCH_TERM_CODE;
	}

	public String getDOCH_TERM_ID() {
		return DOCH_TERM_ID;
	}

	public void setDOCH_TERM_ID(String DOCH_TERM_ID) {
		this.DOCH_TERM_ID = DOCH_TERM_ID;
	}

	public String getDOCH_TYPE() {
		return DOCH_TYPE;
	}

	public void setDOCH_TYPE(String DOCH_TYPE) {
		this.DOCH_TYPE = DOCH_TYPE;
	}

	public String getDOCH_NUMBER() {
		return DOCH_NUMBER;
	}

	public void setDOCH_NUMBER(String DOCH_NUMBER) {
		this.DOCH_NUMBER = DOCH_NUMBER;
	}

	public String getDOCH_DATE() {
		return DOCH_DATE;
	}

	public void setDOCH_DATE(String DOCH_DATE) {
		this.DOCH_DATE = DOCH_DATE;
	}

	public String getAGEN_CODE() {
		return AGEN_CODE;
	}

	public void setAGEN_CODE(String AGEN_CODE) {
		this.AGEN_CODE = AGEN_CODE;
	}

	public String getDOCH_PRICELIST() {
		return DOCH_PRICELIST;
	}

	public void setDOCH_PRICELIST(String DOCH_PRICELIST) {
		this.DOCH_PRICELIST = DOCH_PRICELIST;
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

	public String getCUST_MAIL() {
		return CUST_MAIL;
	}

	public void setCUST_MAIL(String CUST_MAIL) {
		this.CUST_MAIL = CUST_MAIL;
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

	public String getCUST_DISCOUNT() {
		return CUST_DISCOUNT;
	}

	public void setCUST_DISCOUNT(String CUST_DISCOUNT) {
		this.CUST_DISCOUNT = CUST_DISCOUNT;
	}

	public String getSPECIAL_VATT() {
		return SPECIAL_VATT;
	}

	public void setSPECIAL_VATT(String SPECIAL_VATT) {
		this.SPECIAL_VATT = SPECIAL_VATT;
	}

	public String getDEST_ID() {
		return DEST_ID;
	}

	public void setDEST_ID(String DEST_ID) {
		this.DEST_ID = DEST_ID;
	}

	public String getDEST_NAME() {
		return DEST_NAME;
	}

	public void setDEST_NAME(String DEST_NAME) {
		this.DEST_NAME = DEST_NAME;
	}

	public String getDEST_ADDRESS() {
		return DEST_ADDRESS;
	}

	public void setDEST_ADDRESS(String DEST_ADDRESS) {
		this.DEST_ADDRESS = DEST_ADDRESS;
	}

	public String getDEST_ZIP() {
		return DEST_ZIP;
	}

	public void setDEST_ZIP(String DEST_ZIP) {
		this.DEST_ZIP = DEST_ZIP;
	}

	public String getDEST_CITY() {
		return DEST_CITY;
	}

	public void setDEST_CITY(String DEST_CITY) {
		this.DEST_CITY = DEST_CITY;
	}

	public String getDEST_PROVINCE() {
		return DEST_PROVINCE;
	}

	public void setDEST_PROVINCE(String DEST_PROVINCE) {
		this.DEST_PROVINCE = DEST_PROVINCE;
	}

	public String getDEST_COUNTRY() {
		return DEST_COUNTRY;
	}

	public void setDEST_COUNTRY(String DEST_COUNTRY) {
		this.DEST_COUNTRY = DEST_COUNTRY;
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

	public String getDOCH_NOTE() {
		return DOCH_NOTE;
	}

	public void setDOCH_NOTE(String DOCH_NOTE) {
		this.DOCH_NOTE = DOCH_NOTE;
	}

	public String getDOCH_TAXABLE() {
		return DOCH_TAXABLE;
	}

	public void setDOCH_TAXABLE(String DOCH_TAXABLE) {
		this.DOCH_TAXABLE = DOCH_TAXABLE;
	}

	public String getDOCH_VAT() {
		return DOCH_VAT;
	}

	public void setDOCH_VAT(String DOCH_VAT) {
		this.DOCH_VAT = DOCH_VAT;
	}

	public String getDOCH_TOTAL() {
		return DOCH_TOTAL;
	}

	public void setDOCH_TOTAL(String DOCH_TOTAL) {
		this.DOCH_TOTAL = DOCH_TOTAL;
	}

	public String getDOCH_SENT() {
		return DOCH_SENT;
	}

	public void setDOCH_SENT(String DOCH_SENT) {
		this.DOCH_SENT = DOCH_SENT;
	}

	public String getIS_DELETED() {
		return IS_DELETED;
	}

	public void setIS_DELETED(String IS_DELETED) {
		this.IS_DELETED = IS_DELETED;
	}

	public String getDOCH_TIMESTAMP() {
		return DOCH_TIMESTAMP;
	}

	public void setDOCH_TIMESTAMP(String DOCH_TIMESTAMP) {
		this.DOCH_TIMESTAMP = DOCH_TIMESTAMP;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
