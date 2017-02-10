package com.salage.model;

public class DocRowTableInfo {

	String id;
	String DOCR_ROWNUM;
	String DOCH_ID;
	String PROD_CODE;
	String DOCR_DESCRIPTION;
	String VATT_ID;
	String DOCR_QUANTITY;
	String DOCR_PRICE;
	String DOCR_DISCOUNT;
	String DOCR_TOTAL;
	String DOCR_TIMESTAMP;
	String IS_DELETED;


	public DocRowTableInfo() {
	}

	public DocRowTableInfo(String DOCR_ROWNUM, String DOCH_ID, String PROD_CODE,
						   String DOCR_DESCRIPTION, String VATT_ID,
						   String DOCR_QUANTITY, String DOCR_PRICE,
						   String DOCR_DISCOUNT, String DOCR_TOTAL,
						   String DOCR_TIMESTAMP, String IS_DELETED) {
		this.DOCR_ROWNUM = DOCR_ROWNUM;
		this.DOCH_ID = DOCH_ID;
		this.PROD_CODE = PROD_CODE;
		this.DOCR_DESCRIPTION = DOCR_DESCRIPTION;
		this.VATT_ID = VATT_ID;
		this.DOCR_QUANTITY = DOCR_QUANTITY;
		this.DOCR_PRICE = DOCR_PRICE;
		this.DOCR_DISCOUNT = DOCR_DISCOUNT;
		this.DOCR_TOTAL = DOCR_TOTAL;
		this.DOCR_TIMESTAMP = DOCR_TIMESTAMP;
		this.IS_DELETED = IS_DELETED;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDOCR_ROWNUM() {
		return DOCR_ROWNUM;
	}

	public void setDOCR_ROWNUM(String DOCR_ROWNUM) {
		this.DOCR_ROWNUM = DOCR_ROWNUM;
	}

	public String getDOCH_ID() {
		return DOCH_ID;
	}

	public void setDOCH_ID(String DOCH_ID) {
		this.DOCH_ID = DOCH_ID;
	}

	public String getPROD_CODE() {
		return PROD_CODE;
	}

	public void setPROD_CODE(String PROD_CODE) {
		this.PROD_CODE = PROD_CODE;
	}

	public String getDOCR_DESCRIPTION() {
		return DOCR_DESCRIPTION;
	}

	public void setDOCR_DESCRIPTION(String DOCR_DESCRIPTION) {
		this.DOCR_DESCRIPTION = DOCR_DESCRIPTION;
	}

	public String getVATT_ID() {
		return VATT_ID;
	}

	public void setVATT_ID(String VATT_ID) {
		this.VATT_ID = VATT_ID;
	}

	public String getDOCR_QUANTITY() {
		return DOCR_QUANTITY;
	}

	public void setDOCR_QUANTITY(String DOCR_QUANTITY) {
		this.DOCR_QUANTITY = DOCR_QUANTITY;
	}

	public String getDOCR_PRICE() {
		return DOCR_PRICE;
	}

	public void setDOCR_PRICE(String DOCR_PRICE) {
		this.DOCR_PRICE = DOCR_PRICE;
	}

	public String getDOCR_DISCOUNT() {
		return DOCR_DISCOUNT;
	}

	public void setDOCR_DISCOUNT(String DOCR_DISCOUNT) {
		this.DOCR_DISCOUNT = DOCR_DISCOUNT;
	}

	public String getDOCR_TOTAL() {
		return DOCR_TOTAL;
	}

	public void setDOCR_TOTAL(String DOCR_TOTAL) {
		this.DOCR_TOTAL = DOCR_TOTAL;
	}

	public String getDOCR_TIMESTAMP() {
		return DOCR_TIMESTAMP;
	}

	public void setDOCR_TIMESTAMP(String DOCR_TIMESTAMP) {
		this.DOCR_TIMESTAMP = DOCR_TIMESTAMP;
	}

	public String getIS_DELETED() {
		return IS_DELETED;
	}

	public void setIS_DELETED(String IS_DELETED) {
		this.IS_DELETED = IS_DELETED;
	}
}
