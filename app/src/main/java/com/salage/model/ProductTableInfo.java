package com.salage.model;

public class ProductTableInfo {

	String id;
	String PROD_CODE;
	String PROD_DESCRIPTION;
	String CATE_ID;
	String SUBC_ID;
	String BRAN_ID;

	String SUPP_ID;
	String PROD_MU;
	String PROD_MIN_QT;
	String PROD_P0;
	String PROD_P1;

	String PROD_P2;
	String PROD_P3;
	String PROD_P4;
	String PROD_P5;
	String PROD_P6;

	String PROD_P7;
	String PROD_P8;
	String PROD_P9;

	String VATT_ID;
	String PROD_AVL_QTY;
	String PROD_IMAGE;
	String PROD_PDF;
	String PROD_TIMESTAMP;
	String PROD_AVL_TIMESTAMP;
	String IS_DELETED;


	public ProductTableInfo() {
	}

	public ProductTableInfo(String PROD_CODE, String PROD_DESCRIPTION, String CATE_ID, String SUBC_ID, String BRAN_ID,
							String SUPP_ID, String PROD_MU, String PROD_MIN_QT, String PROD_P0, String PROD_P1,
							String PROD_P2, String PROD_P3, String PROD_P4, String PROD_P5, String PROD_P6,
							String PROD_P7, String PROD_P8, String PROD_P9, String VATT_ID, String PROD_AVL_QTY,
							String PROD_IMAGE, String PROD_PDF, String PROD_TIMESTAMP, String PROD_AVL_TIMESTAMP,
							String IS_DELETED) {
		this.PROD_CODE = PROD_CODE;
		this.PROD_DESCRIPTION = PROD_DESCRIPTION;
		this.CATE_ID = CATE_ID;
		this.SUBC_ID = SUBC_ID;
		this.BRAN_ID = BRAN_ID;
		this.SUPP_ID = SUPP_ID;
		this.PROD_MU = PROD_MU;
		this.PROD_MIN_QT = PROD_MIN_QT;
		this.PROD_P0 = PROD_P0;
		this.PROD_P1 = PROD_P1;
		this.PROD_P2 = PROD_P2;
		this.PROD_P3 = PROD_P3;
		this.PROD_P4 = PROD_P4;
		this.PROD_P5 = PROD_P5;
		this.PROD_P6 = PROD_P6;
		this.PROD_P7 = PROD_P7;
		this.PROD_P8 = PROD_P8;
		this.PROD_P9 = PROD_P9;
		this.VATT_ID = VATT_ID;
		this.PROD_AVL_QTY = PROD_AVL_QTY;
		this.PROD_IMAGE = PROD_IMAGE;
		this.PROD_PDF = PROD_PDF;
		this.PROD_TIMESTAMP = PROD_TIMESTAMP;
		this.PROD_AVL_TIMESTAMP = PROD_AVL_TIMESTAMP;
		this.IS_DELETED = IS_DELETED;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPROD_CODE() {
		return PROD_CODE;
	}

	public void setPROD_CODE(String PROD_CODE) {
		this.PROD_CODE = PROD_CODE;
	}

	public String getPROD_DESCRIPTION() {
		return PROD_DESCRIPTION;
	}

	public void setPROD_DESCRIPTION(String PROD_DESCRIPTION) {
		this.PROD_DESCRIPTION = PROD_DESCRIPTION;
	}

	public String getCATE_ID() {
		return CATE_ID;
	}

	public void setCATE_ID(String CATE_ID) {
		this.CATE_ID = CATE_ID;
	}

	public String getSUBC_ID() {
		return SUBC_ID;
	}

	public void setSUBC_ID(String SUBC_ID) {
		this.SUBC_ID = SUBC_ID;
	}

	public String getBRAN_ID() {
		return BRAN_ID;
	}

	public void setBRAN_ID(String BRAN_ID) {
		this.BRAN_ID = BRAN_ID;
	}

	public String getSUPP_ID() {
		return SUPP_ID;
	}

	public void setSUPP_ID(String SUPP_ID) {
		this.SUPP_ID = SUPP_ID;
	}

	public String getPROD_MU() {
		return PROD_MU;
	}

	public void setPROD_MU(String PROD_MU) {
		this.PROD_MU = PROD_MU;
	}

	public String getPROD_MIN_QT() {
		return PROD_MIN_QT;
	}

	public void setPROD_MIN_QT(String PROD_MIN_QT) {
		this.PROD_MIN_QT = PROD_MIN_QT;
	}

	public String getPROD_P0() {
		return PROD_P0;
	}

	public void setPROD_P0(String PROD_P0) {
		this.PROD_P0 = PROD_P0;
	}

	public String getPROD_P1() {
		return PROD_P1;
	}

	public void setPROD_P1(String PROD_P1) {
		this.PROD_P1 = PROD_P1;
	}

	public String getPROD_P2() {
		return PROD_P2;
	}

	public void setPROD_P2(String PROD_P2) {
		this.PROD_P2 = PROD_P2;
	}

	public String getPROD_P3() {
		return PROD_P3;
	}

	public void setPROD_P3(String PROD_P3) {
		this.PROD_P3 = PROD_P3;
	}

	public String getPROD_P4() {
		return PROD_P4;
	}

	public void setPROD_P4(String PROD_P4) {
		this.PROD_P4 = PROD_P4;
	}

	public String getPROD_P5() {
		return PROD_P5;
	}

	public void setPROD_P5(String PROD_P5) {
		this.PROD_P5 = PROD_P5;
	}

	public String getPROD_P6() {
		return PROD_P6;
	}

	public void setPROD_P6(String PROD_P6) {
		this.PROD_P6 = PROD_P6;
	}

	public String getPROD_P7() {
		return PROD_P7;
	}

	public void setPROD_P7(String PROD_P7) {
		this.PROD_P7 = PROD_P7;
	}

	public String getPROD_P8() {
		return PROD_P8;
	}

	public void setPROD_P8(String PROD_P8) {
		this.PROD_P8 = PROD_P8;
	}

	public String getPROD_P9() {
		return PROD_P9;
	}

	public void setPROD_P9(String PROD_P9) {
		this.PROD_P9 = PROD_P9;
	}

	public String getVATT_ID() {
		return VATT_ID;
	}

	public void setVATT_ID(String VATT_ID) {
		this.VATT_ID = VATT_ID;
	}

	public String getPROD_AVL_QTY() {
		return PROD_AVL_QTY;
	}

	public void setPROD_AVL_QTY(String PROD_AVL_QTY) {
		this.PROD_AVL_QTY = PROD_AVL_QTY;
	}

	public String getPROD_IMAGE() {
		return PROD_IMAGE;
	}

	public void setPROD_IMAGE(String PROD_IMAGE) {
		this.PROD_IMAGE = PROD_IMAGE;
	}

	public String getPROD_PDF() {
		return PROD_PDF;
	}

	public void setPROD_PDF(String PROD_PDF) {
		this.PROD_PDF = PROD_PDF;
	}

	public String getPROD_TIMESTAMP() {
		return PROD_TIMESTAMP;
	}

	public void setPROD_TIMESTAMP(String PROD_TIMESTAMP) {
		this.PROD_TIMESTAMP = PROD_TIMESTAMP;
	}

	public String getPROD_AVL_TIMESTAMP() {
		return PROD_AVL_TIMESTAMP;
	}

	public void setPROD_AVL_TIMESTAMP(String PROD_AVL_TIMESTAMP) {
		this.PROD_AVL_TIMESTAMP = PROD_AVL_TIMESTAMP;
	}

	public String getIS_DELETED() {
		return IS_DELETED;
	}

	public void setIS_DELETED(String IS_DELETED) {
		this.IS_DELETED = IS_DELETED;
	}
}
