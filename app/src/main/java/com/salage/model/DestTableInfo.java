package com.salage.model;

public class DestTableInfo {

	String id;
	String DEST_ID;
	String CUST_CODE;
	String CUST_CODE_DEST_ID;
	String DEST_NAME;
	String DEST_ADDRESS;
	String DEST_ZIP;
	String DEST_CITY;
	String DEST_PROVINCE;
	String DEST_COUNTRY;
	String DEST_MAIN;
	String DEST_TIMESTAMP;
	String IS_DELETED;


	public DestTableInfo() {
	}

	public DestTableInfo(String DEST_ID, String CUST_CODE, String CUST_CODE_DEST_ID, String DEST_NAME, String DEST_ADDRESS, String DEST_ZIP, String DEST_CITY, String DEST_PROVINCE, String DEST_COUNTRY, String DEST_MAIN, String DEST_TIMESTAMP, String IS_DELETED) {
		this.DEST_ID = DEST_ID;
		this.CUST_CODE = CUST_CODE;
		this.CUST_CODE_DEST_ID = CUST_CODE_DEST_ID;
		this.DEST_NAME = DEST_NAME;
		this.DEST_ADDRESS = DEST_ADDRESS;
		this.DEST_ZIP = DEST_ZIP;
		this.DEST_CITY = DEST_CITY;
		this.DEST_PROVINCE = DEST_PROVINCE;
		this.DEST_COUNTRY = DEST_COUNTRY;
		this.DEST_MAIN = DEST_MAIN;
		this.DEST_TIMESTAMP = DEST_TIMESTAMP;
		this.IS_DELETED = IS_DELETED;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDEST_ID() {
		return DEST_ID;
	}

	public void setDEST_ID(String DEST_ID) {
		this.DEST_ID = DEST_ID;
	}

	public String getCUST_CODE() {
		return CUST_CODE;
	}

	public void setCUST_CODE(String CUST_CODE) {
		this.CUST_CODE = CUST_CODE;
	}

	public String getCUST_CODE_DEST_ID() {
		return CUST_CODE_DEST_ID;
	}

	public void setCUST_CODE_DEST_ID(String CUST_CODE_DEST_ID) {
		this.CUST_CODE_DEST_ID = CUST_CODE_DEST_ID;
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

	public String getDEST_MAIN() {
		return DEST_MAIN;
	}

	public void setDEST_MAIN(String DEST_MAIN) {
		this.DEST_MAIN = DEST_MAIN;
	}

	public String getDEST_TIMESTAMP() {
		return DEST_TIMESTAMP;
	}

	public void setDEST_TIMESTAMP(String DEST_TIMESTAMP) {
		this.DEST_TIMESTAMP = DEST_TIMESTAMP;
	}

	public String getIS_DELETED() {
		return IS_DELETED;
	}

	public void setIS_DELETED(String IS_DELETED) {
		this.IS_DELETED = IS_DELETED;
	}
}
