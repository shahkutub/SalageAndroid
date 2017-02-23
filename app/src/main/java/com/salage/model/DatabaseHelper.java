package com.salage.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "database_salage";

	// Table Names
	private static final String TABLE_DOCUMENTS = "documents";
	private static final String TABLE_AGENT = "agents";
	private static final String TABLE_BARCODE = "barcode";
	private static final String TABLE_BRANDS = "barnds";
	private static final String TABLE_vatt_vat = "vat";
	private static final String TABLE_user_users = "user_users";
	private static final String TABLE_subc_subcategories = "subc_subcategories";
	private static final String TABLE_prod_products = "prod_products";
	private static final String TABLE_pric_pricelists = "pric_pricelists";
	private static final String TABLE_paym_payments = "paym_payments";
	private static final String TABLE_doch_document_rows = "documentrow";
	private static final String TABLE_dest_destinations = "destinations";
	private static final String TABLE_cust_customers = "customer";
	private static final String TABLE_cupr_customersproducts = "customerproducts";
	private static final String TABLE_conf_config = "conf_config";
	private static final String TABLE_cate_categories = "cate_categories";
	private static final String TABLE_categoriesdiscounts = "categoriesdiscounts";



//	private static final String TABLE_TAG = "tags";
//	private static final String TABLE_TODO_TAG = "todo_tags";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";

	// Documents Table - column names
	private static final String DOCH_TERM_CODE = "DOCH_TERM_CODE";
	private static final String DOCH_TERM_ID = "DOCH_TERM_ID";
	private static final String DOCH_TYPE = "DOCH_TYPE";
	private static final String DOCH_NUMBER = "DOCH_NUMBER";
	private static final String DOCH_DATE = "DOCH_DATE";
	private static final String AGEN_CODE = "AGEN_CODE";
	private static final String DOCH_PRICELIST = "DOCH_PRICELIST";
	private static final String CUST_CODE = "CUST_CODE";
	private static final String CUST_NAME1 = "CUST_NAME1";
	private static final String CUST_NAME2 = "CUST_NAME2";
	private static final String CUST_ADDRESS = "CUST_ADDRESS";
	private static final String CUST_ZIP = "CUST_ZIP";
	private static final String CUST_CITY = "CUST_CITY";
	private static final String CUST_MAIL = "CUST_MAIL";
	private static final String CUST_PROVINCE = "CUST_PROVINCE";
	private static final String CUST_COUNTRY = "CUST_COUNTRY";
	private static final String CUST_DISCOUNT = "CUST_DISCOUNT";
	private static final String SPECIAL_VATT = "SPECIAL_VATT";
	private static final String DEST_ID = "DEST_ID";
	private static final String DEST_NAME = "DEST_NAME";
	private static final String DEST_ADDRESS = "DEST_ADDRESS";
	private static final String DEST_ZIP = "DEST_ZIP";
	private static final String DEST_CITY = "DEST_CITY";
	private static final String DEST_PROVINCE = "DEST_PROVINCE";
	private static final String DEST_COUNTRY = "DEST_COUNTRY";
	private static final String VATT_ID = "VATT_ID";
	private static final String PAYM_ID = "PAYM_ID";
	private static final String DOCH_NOTE = "DOCH_NOTE";
	private static final String DOCH_TAXABLE = "DOCH_TAXABLE";
	private static final String DOCH_VAT = "DOCH_VAT";
	private static final String DOCH_TOTAL = "DOCH_TOTAL";
	private static final String DOCH_SENT = "DOCH_SENT";
	private static final String IS_DELETED = "IS_DELETED";
	private static final String DOCH_TIMESTAMP = "DOCH_TIMESTAMP";

	// AGENT Table - column names
	private static final String AGEN_AGEN_CODE = "AGEN_CODE";
	private static final String AGEN_AGEN_NAME1 = "AGEN_NAME1";
	private static final String AGEN_AGEN_NAME2 = "AGEN_NAME2";
	private static final String AGEN_AGEN_EMAIL = "AGEN_EMAIL";
	private static final String AGEN_AGEN_PASSWORD = "AGEN_PASSWORD";
	private static final String AGEN_AGEN_ENABLED = "AGEN_ENABLED";
	private static final String AGEN_AGEN_CANCHANGEPRICE = "AGEN_CANCHANGEPRICE";
	private static final String AGEN_AGEN_CANCHANGEPAYM = "AGEN_CANCHANGEPAYM";
	private static final String AGEN_AGEN_CANCHANGEVAT = "AGEN_CANCHANGEVAT";
	private static final String AGEN_AGEN_CANADDCUST = "AGEN_CANADDCUST";
	private static final String AGEN_AGEN_CANADDDEST = "AGEN_CANADDDEST";
	private static final String AGEN_AGEN_CANCHANGEDESC = "AGEN_CANCHANGEDESC";
	private static final String AGEN_AGEN_CANUSENOCODE = "AGEN_CANUSENOCODE";
	private static final String AGEN_AGEN_CANADDCOMMENTS = "AGEN_CANADDCOMMENTS";
	private static final String AGEN_AGEN_TIMESTAMP = "AGEN_TIMESTAMP";
	private static final String AGEN_IS_DELETED = "IS_DELETED";
	private static final String AGEN_AGENT_TYPE = "AGENT_TYPE";

	// Barcode Table - column names
	private static final String BAR_CODE_BARC_BARCODE = "BARC_BARCODE";
	private static final String BAR_CODE_PROD_CODE = "PROD_CODE";
	private static final String BAR_CODE_BARC_TIMESTAMP = "BARC_TIMESTAMP";
	private static final String BAR_CODE_IS_DELETED = "IS_DELETED";


	// BRANDS Table - column names
	private static final String BRANDS_BRAN_ID = "BRAN_ID";
	private static final String BRANDS_BRAN_DESCRIPTION = "BRAN_DESCRIPTION";
	private static final String BRANDS_BRAN_TIMESTAMP = "BRAN_TIMESTAMP";
	private static final String BRANDS_IS_DELETED = "IS_DELETED";


	// VAT Table - column names
	private static final String VAT_VATT_ID = "VATT_ID";
	private static final String VAT_VATT_CODE = "VATT_CODE";
	private static final String VAT_VATT_DESCRIPTION = "VATT_DESCRIPTION";
	private static final String VAT_VATT_PERCENT = "VATT_PERCENT";
	private static final String VAT_VATT_TIMESTAMP = "VATT_TIMESTAMP";
	private static final String VAT_IS_DELETED = "IS_DELETED";


	// User Table - column names
	private static final String USER_USERNAME = "USER_USERNAME";
	private static final String USER_PASSWORD = "USER_PASSWORD";
	private static final String USER_DESCRIPTION = "USER_DESCRIPTION";
	private static final String USER_ISADMIN = "USER_ISADMIN";
	private static final String USER_STATUS = "USER_STATUS";


	// subc_subcategories Table - column names
	private static final String SUBC_ID = "SUBC_ID";
	private static final String SUBC_DESCRIPTION = "SUBC_DESCRIPTION";
	private static final String SUBC_CATE_ID = "CATE_ID";
	private static final String SUBC_TIMESTAMP = "SUBC_TIMESTAMP";
	private static final String SUBC_IS_DELETED = "IS_DELETED";


	// prod_products Table - column names
	private static final String PROD_PROD_CODE = "PROD_CODE";
	private static final String PROD_PROD_DESCRIPTION = "PROD_DESCRIPTION";
	private static final String PROD_CATE_ID = "CATE_ID";
	private static final String PROD_SUBC_ID = "SUBC_ID";
	private static final String PROD_BRAN_ID = "BRAN_ID";

	private static final String PROD_SUPP_ID = "SUPP_ID";
	private static final String PROD_PROD_MU = "PROD_MU";
	private static final String PROD_PROD_MIN_QT = "PROD_MIN_QT";

	private static final String PROD_PROD_P0= "PROD_P0";
	private static final String PROD_PROD_P1 = "PROD_P1";
	private static final String PROD_PROD_P2 = "PROD_P2";

	private static final String PROD_PROD_P3 = "PROD_P3";
	private static final String PROD_PROD_P4 = "PROD_P4";
	private static final String PROD_PROD_P5 = "PROD_P5";

	private static final String PROD_PROD_P6 = "PROD_P6";
	private static final String PROD_PROD_P7 = "PROD_P7";
	private static final String PROD_PROD_P8 = "PROD_P8";
	private static final String PROD_PROD_P9 = "PROD_P9";


	private static final String PROD_VATT_ID = "VATT_ID";
	private static final String PROD_PROD_AVL_QTY = "PROD_AVL_QTY";
	private static final String PROD_PROD_IMAGE = "PROD_IMAGE";
	private static final String PROD__PROD_PDF = "PROD_PDF";

	private static final String PROD_PROD_TIMESTAMP = "PROD_TIMESTAMP";
	private static final String PROD_PROD_AVL_TIMESTAMP = "PROD_AVL_TIMESTAMP";
	private static final String PROD_IS_DELETED = "IS_DELETED";


	// pric_pricelists Table - column names
	private static final String PRIC_DESC0 = "PRIC_DESC0";
	private static final String PRIC_DESC1 = "PRIC_DESC1";
	private static final String PRIC_DESC2 = "PRIC_DESC2";
	private static final String PRIC_DESC3 = "PRIC_DESC3";

	private static final String PRIC_DESC4 = "PRIC_DESC4";
	private static final String PRIC_DESC5 = "PRIC_DESC5";
	private static final String PRIC_DESC6 = "PRIC_DESC6";
	static final String PRIC_DESC7 = "PRIC_DESC7";

	private static final String PRIC_DESC8 = "PRIC_DESC8";
	private static final String PRIC_DESC9 = "PRIC_DESC9";
	private static final String PRIC_TIMESTAMP = "PRIC_TIMESTAMP";


	// paym_payments Table - column names
	private static final String PAYM_PAYM_ID = "PAYM_ID";
	private static final String PAYM_CODE = "PAYM_CODE";
	private static final String PAYM_DESCRIPTION = "PAYM_DESCRIPTION";
	private static final String PAYM_DISCOUNT = "PAYM_DISCOUNT";
	private static final String PAYM_TIMESTAMP = "PAYM_TIMESTAMP";
	private static final String PAYM_IS_DELETED = "IS_DELETED";


	// doch_document_rows Table - column names
	private static final String DOCR_ROWNUM = "DOCR_ROWNUM";
	private static final String DOCH_ID = "DOCH_ID";
	private static final String DOCH_PROD_CODE = "PROD_CODE";
	private static final String DOCR_DESCRIPTION = "DOCR_DESCRIPTION";
	private static final String DOCH_VATT_ID = "VATT_ID";
	private static final String DOCR_QUANTITY = "DOCR_QUANTITY";
	private static final String DOCR_PRICE = "DOCR_PRICE";
	private static final String DOCR_DISCOUNT = "DOCR_DISCOUNT";
	private static final String DOCR_TOTAL = "DOCR_TOTAL";
	private static final String DOCR_TIMESTAMP = "DOCR_TIMESTAMP";
	private static final String DOCR_IS_DELETED = "IS_DELETED";

	// dest_destinations Table - column names
	private static final String DEST_DEST_ID = "DEST_ID";
	private static final String DEST_CUST_CODE = "CUST_CODE";
	private static final String DEST_CUST_CODE_DEST_ID = "CUST_CODE_DEST_ID";
	private static final String DEST_DEST_NAME = "DOCR_DESCRIPTION";
	private static final String DEST_DEST_ADDRESS = "DEST_ADDRESS";
	private static final String DEST_DEST_ZIP = "DEST_ZIP";
	private static final String DEST_DEST_CITY = "DEST_CITY";
	private static final String DEST_DEST_PROVINCE = "DEST_PROVINCE";
	private static final String DEST_DEST_COUNTRYDEST_COUNTRY = "DEST_COUNTRY";
	private static final String DEST_DEST_MAIN = "DEST_MAIN";
	private static final String DEST_DEST_TIMESTAMP = "DEST_TIMESTAMP";
	private static final String DEST_IS_DELETED = "IS_DELETED";


	// cust_customers Table - column names
	private static final String CUST_CUST_CODE = "CUST_CODE";
	private static final String CUST_CUST_NAME1 = "CUST_NAME1";
	private static final String CUST_CUST_NAME2 = "CUST_NAME2";
	private static final String CUST_CUST_ADDRESS = "CUST_ADDRESS";
	private static final String CUST_CUST_ZIP = "CUST_ZIP";
	private static final String CUST_CUST_CITY = "CUST_CITY";
	private static final String CUST_CUST_PROVINCE = "CUST_PROVINCE";
	private static final String CUST_CUST_TEL = "CUST_TEL";
	private static final String CUST_CUST_FAX = "CUST_FAX";
	private static final String CUST_CUST_MOBILE = "CUST_MOBILE";
	private static final String CUST_CUST_MAIL = "CUST_MAIL";
	private static final String CUST_CUST_CF = "CUST_CF";
	private static final String CUST_CUST_VATNUM = "CUST_VATNUM";
	private static final String CUST_CUST_IBAN = "CUST_IBAN";
	private static final String CUST_VATT_ID = "VATT_ID";
	private static final String CUST_PAYM_ID = "PAYM_ID";
	private static final String CUST_AGEN_CODE = "AGEN_CODE";
	private static final String CUST_CUST_PRICELIST = "CUST_PRICELIST";

	private static final String CUST_CUST_DISCOUNT = "CUST_DISCOUNT";
	private static final String CUST_CUST_STATE = "CUST_STATE";

	private static final String CUST_CUST_TIMESTAMP = "CUST_TIMESTAMP";
	private static final String CUST_IS_DELETED = "IS_DELETED";


	// cupr_customersproducts Table - column names
	private static final String CUPR_CUST_CODE = "CUST_CODE";
	private static final String CUPR_PROD_CODE = "PROD_CODE";
	private static final String CUPR_CUPR_DISCOUNT = "CUPR_DISCOUNT";
	private static final String CUPR_CUPR_PRICE = "CUPR_PRICE";
	private static final String CUPR_CUPR_TIMESTAMP = "CUPR_TIMESTAMP";
	private static final String CUPR_IS_DELETED = "IS_DELETED";

	// conf_config Table - column names
	private static final String CONF_CO_CODE = "CONF_CO_CODE";
	private static final String CONF_CO_NAME = "CONF_CO_NAME";
	private static final String CONF_CO_ROW1 = "CONF_CO_ROW1";
	private static final String CONF_CO_ROW2 = "CONF_CO_ROW2";
	private static final String CONF_CO_ROW3 = "CONF_CO_ROW3";
	private static final String CONF_CO_ROW4 = "CONF_CO_ROW4";
	private static final String CONF_CO_ROW5 = "CONF_CO_ROW5";
	private static final String CONF_AGENTS_N = "CONF_AGENTS_N";
	private static final String CONF_LOGO = "CONF_LOGO";
	private static final String CONF_EMAIL = "CONF_EMAIL";
	private static final String CONF_KEY = "CONF_KEY";


	// cate_categories Table - column names
	private static final String CATE_CATE_ID = "CATE_ID";
	private static final String CATE_CATE_DESCRIPTION = "CATE_DESCRIPTION";
	private static final String CATE_CATE_TIMESTAMP = "CATE_TIMESTAMP";
	private static final String CATE_IS_DELETED = "IS_DELETED";

	// catd_categoriesdiscounts Table - column names
	private static final String CATE_DISCOUNT_CUST_CODE = "CUST_CODE";
	private static final String CATE_DISCOUNT_CATE_ID = "CATE_ID";
	private static final String CATE_DISCOUNT_SUBC_ID = "SUBC_ID";
	private static final String CATE_DISCOUNT_CATD_DISCOUNT = "CATD_DISCOUNT";
	private static final String CATE_DISCOUNT_CUPR_TIMESTAMP = "CUPR_TIMESTAMP";
	private static final String CATE_DISCOUNT_IS_DELETED = "IS_DELETED";



	// Table Create Statements
	// Document table create statement
	private static final String CREATE_TABLE_DOCUMENTS = "CREATE TABLE "
			+ TABLE_DOCUMENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ DOCH_TERM_CODE + " TEXT,"
			+ DOCH_TERM_ID + " TEXT,"

			+ DOCH_TYPE + " TEXT,"
			+ DOCH_NUMBER + " TEXT,"
			+ DOCH_DATE + " TEXT,"
			+ AGEN_CODE + " TEXT,"

			+ DOCH_PRICELIST + " TEXT,"
			+ CUST_CODE + " TEXT,"
			+ CUST_NAME1 + " TEXT,"
			+ CUST_NAME2 + " TEXT,"

			+ CUST_ADDRESS + " TEXT,"
			+ CUST_ZIP + " TEXT,"
			+ CUST_CITY + " TEXT,"
			+ CUST_MAIL + " TEXT,"


			+ CUST_PROVINCE + " TEXT,"
			+ CUST_COUNTRY + " TEXT,"
			+ CUST_DISCOUNT + " TEXT,"
			+ SPECIAL_VATT + " TEXT,"

			+ DEST_ID + " TEXT,"
			+ DEST_NAME + " TEXT,"
			+ DEST_ADDRESS + " TEXT,"
			+ DEST_ZIP + " TEXT,"

			+ DEST_CITY + " TEXT,"
			+ DEST_PROVINCE + " TEXT,"
			+ DEST_COUNTRY + " TEXT,"
			+ VATT_ID + " TEXT,"

			+ PAYM_ID + " TEXT,"
			+ DOCH_NOTE + " TEXT,"
			+ DOCH_TAXABLE + " TEXT,"
			+ DOCH_VAT + " TEXT,"

			+ DOCH_TOTAL + " TEXT,"
			+ DOCH_SENT + " TEXT,"
			+ IS_DELETED + " TEXT,"

			+ DOCH_TIMESTAMP + " DATETIME" + ")";

	//Agent table create sql
	private static final String CREATE_TABLE_AGENT = "CREATE TABLE "
			+ TABLE_AGENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ AGEN_AGEN_CODE + " TEXT,"
			+ AGEN_AGEN_NAME1 + " TEXT,"

			+ AGEN_AGEN_NAME2 + " TEXT,"
			+ AGEN_AGEN_EMAIL + " TEXT,"
			+ AGEN_AGEN_PASSWORD + " TEXT,"
			+ AGEN_AGEN_ENABLED + " TEXT,"

			+ AGEN_AGEN_CANCHANGEPRICE + " TEXT,"
			+ AGEN_AGEN_CANCHANGEPAYM + " TEXT,"
			+ AGEN_AGEN_CANCHANGEVAT + " TEXT,"
			+ AGEN_AGEN_CANADDCUST + " TEXT,"
			+ AGEN_AGEN_CANADDDEST + " TEXT,"

			+ AGEN_AGEN_CANCHANGEDESC + " TEXT,"
			+ AGEN_AGEN_CANUSENOCODE + " TEXT,"
			+ AGEN_AGEN_CANADDCOMMENTS + " TEXT,"
			+ AGEN_AGEN_TIMESTAMP + " DATETIME,"
			+ AGEN_IS_DELETED + " TEXT,"


			+ AGEN_AGENT_TYPE + " TEXT" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	//Barcode Table Create sql

	private static final String CREATE_TABLE_BARCODE = "CREATE TABLE "
			+ TABLE_BARCODE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ BAR_CODE_BARC_BARCODE + " TEXT,"
			+ BAR_CODE_PROD_CODE + " TEXT,"
			+  BAR_CODE_BARC_TIMESTAMP + " DATETIME,"
			+ BAR_CODE_IS_DELETED + " TEXT" + ")";

	//Brands Table Create sql

	private static final String CREATE_TABLE_BRANDS = "CREATE TABLE "
			+ TABLE_BRANDS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ BRANDS_BRAN_ID + " TEXT,"
			+ BRANDS_BRAN_DESCRIPTION + " TEXT,"
			+ BRANDS_IS_DELETED + " TEXT,"
			+ BRANDS_BRAN_TIMESTAMP + " DATETIME" + ")";

	//Vat Table Create sql
	private static final String CREATE_TABLE_VAT = "CREATE TABLE "
			+ TABLE_vatt_vat + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ VAT_VATT_ID + " TEXT,"
			+ VAT_VATT_CODE + " TEXT,"
			+ VAT_VATT_DESCRIPTION + " TEXT,"
			+ VAT_IS_DELETED + " TEXT,"
			+ VAT_VATT_TIMESTAMP + " DATETIME" + ")";

	//USER Table Create sql
	private static final String CREATE_TABLE_USER = "CREATE TABLE "
			+ TABLE_user_users + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ USER_USERNAME + " TEXT,"
			+ USER_PASSWORD + " TEXT,"
			+ USER_DESCRIPTION + " TEXT,"
			+ USER_ISADMIN + " TEXT,"
			+ USER_STATUS + " TEXT" + ")";


	//subc_subcategories Table Create sql
	private static final String CREATE_TABLE_subc_subcategories = "CREATE TABLE "
			+ TABLE_subc_subcategories + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ SUBC_ID + " TEXT,"
			+ SUBC_DESCRIPTION + " TEXT,"
			+ SUBC_CATE_ID + " TEXT,"
			+ SUBC_TIMESTAMP + " DATETIME,"
			+ SUBC_IS_DELETED + " TEXT" + ")";


	//subc_subcategories Table Create sql
	private static final String CREATE_TABLE_prod_products = "CREATE TABLE "
			+ TABLE_prod_products + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ PROD_PROD_CODE + " TEXT,"
			+ PROD_PROD_DESCRIPTION + " TEXT,"
			+ PROD_CATE_ID + " TEXT,"

			+ PROD_SUBC_ID + " TEXT,"
			+ PROD_BRAN_ID + " TEXT,"
			+ PROD_SUPP_ID + " TEXT,"
			+ PROD_PROD_MU + " TEXT,"
			+ PROD_PROD_MIN_QT + " TEXT,"
			+ PROD_PROD_P0 + " TEXT,"
			+ PROD_PROD_P1 + " TEXT,"
			+ PROD_PROD_P2 + " TEXT,"
			+ PROD_PROD_P3 + " TEXT,"
			+ PROD_PROD_P4 + " TEXT,"
			+ PROD_PROD_P5 + " TEXT,"
			+ PROD_PROD_P6 + " TEXT,"
			+ PROD_PROD_P7 + " TEXT,"
			+ PROD_PROD_P8 + " TEXT,"
			+ PROD_PROD_P9 + " TEXT,"
			+ PROD_VATT_ID + " TEXT,"
			+ PROD_PROD_AVL_QTY + " TEXT,"
			+ PROD_PROD_IMAGE + " TEXT,"
			+ PROD__PROD_PDF + " TEXT,"

			+ PROD_PROD_TIMESTAMP + " DATETIME,"
			+ PROD_PROD_AVL_TIMESTAMP + " DATETIME,"
			+ PROD_IS_DELETED + " TEXT" + ")";



	//Price list Table Create sql
	private static final String CREATE_TABLE_pric_pricelists = "CREATE TABLE "
			+ TABLE_pric_pricelists + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ PRIC_DESC0 + " TEXT,"
			+ PRIC_DESC1 + " TEXT,"
			+ PRIC_DESC2 + " TEXT,"
			+ PRIC_DESC3 + " TEXT,"
			+ PRIC_DESC4 + " TEXT,"
			+ PRIC_DESC5 + " TEXT,"
			+ PRIC_DESC6 + " TEXT,"
			+ PRIC_DESC7 + " TEXT,"
			+ PRIC_DESC8 + " TEXT,"
			+ PRIC_DESC9 + " TEXT,"
			+ PRIC_TIMESTAMP + " DATETIME" + ")";


	//paym_payments  Table Create sql
	private static final String CREATE_TABLE_paym_payments = "CREATE TABLE "
			+ TABLE_paym_payments + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ PAYM_PAYM_ID + " TEXT,"
			+ PAYM_CODE + " TEXT,"
			+ PAYM_DESCRIPTION + " TEXT,"
			+ PAYM_DISCOUNT + " TEXT,"
			+ PAYM_TIMESTAMP + " DATETIME,"
			+ PAYM_IS_DELETED + " TEXT" + ")";


	private static final String CREATE_TABLE_doch_document_rows = "CREATE TABLE "
			+ TABLE_doch_document_rows+ "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ DOCR_ROWNUM + " TEXT,"
			+ DOCH_ID + " TEXT,"
			+ DOCH_PROD_CODE + " TEXT,"
			+ DOCR_DESCRIPTION + " TEXT,"
			+ DOCH_VATT_ID + " TEXT,"
			+ DOCR_QUANTITY + " TEXT,"
			+ DOCR_PRICE + " TEXT,"
			+ DOCR_DISCOUNT + " TEXT,"
			+ DOCR_TOTAL + " TEXT,"
			+ DOCR_TIMESTAMP + " DATETIME,"
			+ DOCR_IS_DELETED + " TEXT" + ")";


	private static final String CREATE_TABLE_dest_destinations = "CREATE TABLE "
			+ TABLE_dest_destinations + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ DEST_DEST_ID+ " TEXT,"
			+ DEST_CUST_CODE + " TEXT,"
			+ DEST_CUST_CODE_DEST_ID + " TEXT,"
			+ DEST_DEST_NAME + " TEXT,"
			+ DEST_DEST_ADDRESS + " TEXT,"
			+ DEST_DEST_ZIP + " TEXT,"
			+ DEST_DEST_CITY + " TEXT,"
			+ DEST_DEST_PROVINCE + " TEXT,"
			+ DEST_COUNTRY + " TEXT,"
			+ DEST_DEST_TIMESTAMP + " DATETIME,"
			+ DEST_IS_DELETED + " TEXT" + ")";


	private static final String CREATE_TABLE_cust_customers = "CREATE TABLE "
			+ TABLE_cust_customers + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CUST_CUST_CODE+ " TEXT,"
			+ CUST_CUST_NAME1 + " TEXT,"
			+ CUST_CUST_NAME2 + " TEXT,"
			+ CUST_CUST_ADDRESS + " TEXT,"
			+ CUST_CUST_ZIP + " TEXT,"
			+ CUST_CUST_CITY + " TEXT,"
			+ CUST_CUST_PROVINCE + " TEXT,"
			+ CUST_COUNTRY + " TEXT,"
			+ CUST_CUST_TEL + " TEXT,"
			+ CUST_CUST_FAX + " TEXT,"
			+ CUST_CUST_MOBILE + " TEXT,"
			+ CUST_CUST_MAIL + " TEXT,"
			+ CUST_CUST_CF + " TEXT,"
			+ CUST_CUST_VATNUM + " TEXT,"
			+ CUST_CUST_IBAN + " TEXT,"
			+ CUST_VATT_ID + " TEXT,"
			+ CUST_PAYM_ID + " TEXT,"
			+ CUST_AGEN_CODE + " TEXT,"
			+ CUST_CUST_PRICELIST + " TEXT,"
			+ CUST_CUST_DISCOUNT + " TEXT,"
			+ CUST_CUST_STATE + " TEXT,"
			+ CUST_CUST_TIMESTAMP + " DATETIME,"
			+ CUST_IS_DELETED + " TEXT" + ")";


	//cupr_customersproducts  Table Create sql
	private static final String CREATE_TABLE_cupr_customersproducts = "CREATE TABLE "
			+ TABLE_cupr_customersproducts + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CUPR_CUST_CODE + " TEXT,"
			+ CUPR_PROD_CODE + " TEXT,"
			+ CUPR_CUPR_DISCOUNT + " TEXT,"
			+ CUPR_CUPR_PRICE + " TEXT,"
			+ CUPR_CUPR_TIMESTAMP + " DATETIME,"
			+ CUPR_IS_DELETED + " TEXT" + ")";

	//cupr_customersproducts  Table Create sql
	private static final String CREATE_TABLE_conf_config = "CREATE TABLE "
			+ TABLE_conf_config + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CONF_CO_CODE + " TEXT,"
			+ CONF_CO_NAME + " TEXT,"
			+ CONF_CO_ROW1 + " TEXT,"
			+ CONF_CO_ROW2 + " TEXT,"
			+ CONF_CO_ROW3 + " TEXT,"
			+ CONF_CO_ROW4 + " TEXT,"
			+ CONF_CO_ROW5 + " TEXT,"
			+ CONF_AGENTS_N + " TEXT,"
			+ CONF_LOGO + " TEXT,"
			+ CONF_EMAIL + " TEXT,"
			+ CONF_KEY + " TEXT" + ")";

	//cate_categories  Table Create sql
	private static final String CREATE_TABLE_cate_categories = "CREATE TABLE "
			+ TABLE_cate_categories + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CATE_CATE_ID + " TEXT,"
			+ CATE_CATE_DESCRIPTION + " TEXT,"
			+ CATE_CATE_TIMESTAMP + " DATETIME,"
			+ CATE_IS_DELETED + " TEXT" + ")";


	//cate_categories  Table Create sql
	private static final String CREATE_TABLE_catd_categoriesdiscounts = "CREATE TABLE "
			+ TABLE_categoriesdiscounts + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
			+ CATE_DISCOUNT_CUST_CODE + " TEXT,"
			+ CATE_DISCOUNT_CATE_ID + " TEXT,"
			+ CATE_DISCOUNT_SUBC_ID + " TEXT,"
			+ CATE_DISCOUNT_CATD_DISCOUNT + " TEXT,"
			+ CATE_DISCOUNT_CUPR_TIMESTAMP + " DATETIME,"
			+ CATE_IS_DELETED + " TEXT" + ")";

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_DOCUMENTS);
		db.execSQL(CREATE_TABLE_AGENT);
		db.execSQL(CREATE_TABLE_BARCODE);
		db.execSQL(CREATE_TABLE_BRANDS);
		db.execSQL(CREATE_TABLE_VAT);
		db.execSQL(CREATE_TABLE_USER);
		db.execSQL(CREATE_TABLE_subc_subcategories);
		db.execSQL(CREATE_TABLE_prod_products);
		db.execSQL(CREATE_TABLE_pric_pricelists);
		db.execSQL(CREATE_TABLE_paym_payments);
		db.execSQL(CREATE_TABLE_doch_document_rows);
		db.execSQL(CREATE_TABLE_dest_destinations);
		db.execSQL(CREATE_TABLE_cust_customers);
		db.execSQL(CREATE_TABLE_cupr_customersproducts);
		db.execSQL(CREATE_TABLE_conf_config);
		db.execSQL(CREATE_TABLE_cate_categories);
		db.execSQL(CREATE_TABLE_catd_categoriesdiscounts);


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCUMENTS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_AGENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BARCODE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_BRANDS);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_vatt_vat);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_user_users);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_subc_subcategories);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_prod_products);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_pric_pricelists);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_paym_payments);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_doch_document_rows);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_cust_customers);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_cupr_customersproducts);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_conf_config);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_cate_categories);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_categoriesdiscounts);


		onCreate(db);
	}

	/**
	 * All CRUD(Create, Read, Update, Delete) Operations
	 */

	// Adding new DocumentTableInfo
	public void addDocument(DocumentTableInfo documentTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(AGEN_CODE, documentTableInfo.getAGEN_CODE());
		values.put(CUST_ADDRESS, documentTableInfo.getCUST_ADDRESS());
		values.put(CUST_CITY, documentTableInfo.getCUST_CITY());
		values.put(CUST_CODE, documentTableInfo.getCUST_CODE());
		values.put(CUST_COUNTRY, documentTableInfo.getCUST_COUNTRY());
		values.put(CUST_DISCOUNT, documentTableInfo.getCUST_DISCOUNT());
		values.put(CUST_MAIL, documentTableInfo.getCUST_MAIL());
		values.put(CUST_NAME1, documentTableInfo.getCUST_NAME1());
		values.put(CUST_NAME2, documentTableInfo.getCUST_NAME2());
		values.put(CUST_PROVINCE, documentTableInfo.getCUST_PROVINCE());
		values.put(CUST_ZIP, documentTableInfo.getCUST_ZIP());
		values.put(DEST_ADDRESS, documentTableInfo.getDEST_ADDRESS());
		values.put(DEST_CITY, documentTableInfo.getDEST_CITY());
		values.put(DEST_COUNTRY, documentTableInfo.getDEST_COUNTRY());
		values.put(DEST_ID, documentTableInfo.getDEST_ID());
		values.put(DEST_NAME, documentTableInfo.getDEST_NAME());
		values.put(DEST_PROVINCE, documentTableInfo.getDEST_PROVINCE());
		values.put(DEST_ZIP, documentTableInfo.getDEST_ZIP());
		values.put(DOCH_DATE, documentTableInfo.getDOCH_DATE());
		values.put(DOCH_NOTE, documentTableInfo.getDOCH_NOTE());
		values.put(DOCH_NUMBER, documentTableInfo.getDOCH_NUMBER());
		values.put(DOCH_PRICELIST, documentTableInfo.getDOCH_PRICELIST());
		values.put(DOCH_SENT, documentTableInfo.getDOCH_SENT());
		values.put(DOCH_TAXABLE, documentTableInfo.getDOCH_TAXABLE());
		values.put(DOCH_TERM_CODE, documentTableInfo.getDOCH_TERM_CODE());
		values.put(DOCH_TERM_ID, documentTableInfo.getDOCH_TERM_ID());
		values.put(DOCH_TYPE, documentTableInfo.getDOCH_TYPE());
		values.put(DOCH_TIMESTAMP, documentTableInfo.getDOCH_TIMESTAMP());
		values.put(DOCH_TOTAL, documentTableInfo.getDOCH_TOTAL());
		values.put(DOCH_VAT, documentTableInfo.getDOCH_VAT());
		values.put(VATT_ID, documentTableInfo.getVATT_ID());
		values.put(PAYM_ID, documentTableInfo.getPAYM_ID());
		values.put(SPECIAL_VATT, documentTableInfo.getSPECIAL_VATT());
		values.put(IS_DELETED, documentTableInfo.getIS_DELETED());
		// Inserting Row
		db.insert(TABLE_DOCUMENTS, null, values);
		db.close(); // Closing database connection
	}


	// Adding new AgentTableInfo
	public void addAgents(AgentTableInfo agentTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(AGEN_AGEN_CODE, agentTableInfo.getAGEN_CODE());
		values.put(AGEN_AGEN_NAME1, agentTableInfo.getAGEN_NAME1());
		values.put(AGEN_AGEN_NAME2, agentTableInfo.getAGEN_NAME2());
		values.put(AGEN_AGEN_EMAIL, agentTableInfo.getAGEN_EMAIL());
		values.put(AGEN_AGEN_PASSWORD, agentTableInfo.getAGEN_PASSWORD());
		values.put(AGEN_AGEN_ENABLED, agentTableInfo.getAGEN_ENABLED());
		values.put(AGEN_AGEN_CANCHANGEPRICE, agentTableInfo.getAGEN_CANCHANGEPRICE());
		values.put(AGEN_AGEN_CANCHANGEPAYM, agentTableInfo.getAGEN_CANCHANGEPAYM());
		values.put(AGEN_AGEN_CANCHANGEVAT, agentTableInfo.getAGEN_CANCHANGEVAT());
		values.put(AGEN_AGEN_CANADDCUST, agentTableInfo.getAGEN_CANADDCUST());
		values.put(AGEN_AGEN_CANADDDEST, agentTableInfo.getAGEN_CANADDDEST());
		values.put(AGEN_AGEN_CANCHANGEDESC, agentTableInfo.getAGEN_CANCHANGEDESC());
		values.put(AGEN_AGEN_CANUSENOCODE, agentTableInfo.getAGEN_CANUSENOCODE());
		values.put(AGEN_AGEN_CANADDCOMMENTS, agentTableInfo.getAGEN_CANADDCOMMENTS());
		values.put(AGEN_AGEN_TIMESTAMP, agentTableInfo.getAGEN_TIMESTAMP());
		values.put(AGEN_IS_DELETED, agentTableInfo.getIS_DELETED());
		values.put(AGEN_AGENT_TYPE, agentTableInfo.getAGENT_TYPE());

		// Inserting Row
		db.insert(TABLE_AGENT, null, values);
		db.close(); // Closing database connection
	}


	public void addBarCode(BarCodeTableInfo barCodeTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(BAR_CODE_BARC_BARCODE, barCodeTableInfo.getBARC_BARCODE());
		values.put(BAR_CODE_PROD_CODE, barCodeTableInfo.getPROD_CODE());
		values.put(BAR_CODE_BARC_TIMESTAMP, barCodeTableInfo.getBARC_TIMESTAMP());
		values.put(BAR_CODE_IS_DELETED, barCodeTableInfo.getIS_DELETED());


		// Inserting Row
		db.insert(TABLE_BARCODE, null, values);
		db.close(); // Closing database connection
	}


	public void addBrand(BrandsTableInfo brandsTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(BRANDS_BRAN_ID, brandsTableInfo.getBRAN_ID());
		values.put(BRANDS_BRAN_DESCRIPTION, brandsTableInfo.getBRAN_DESCRIPTION());
		values.put(BRANDS_BRAN_TIMESTAMP, brandsTableInfo.getBRAN_TIMESTAMP());
		values.put(BRANDS_IS_DELETED, brandsTableInfo.getIS_DELETED());
		// Inserting Row
		db.insert(TABLE_BRANDS, null, values);
		db.close(); // Closing database connection
	}

	public void addVat(VatTableInfo vatTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(VATT_ID, vatTableInfo.getVATT_ID());
		values.put(VAT_VATT_CODE, vatTableInfo.getVATT_CODE());
		values.put(VAT_VATT_DESCRIPTION, vatTableInfo.getVATT_DESCRIPTION());
		values.put(VAT_VATT_PERCENT, vatTableInfo.getVATT_PERCENT());
		values.put(VAT_VATT_TIMESTAMP, vatTableInfo.getVATT_TIMESTAMP());
		values.put(VAT_IS_DELETED, vatTableInfo.getIS_DELETED());
		// Inserting Row
		db.insert(TABLE_vatt_vat, null, values);
		db.close(); // Closing database connection
	}


	public void addUser(UserTableInfo userTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(USER_USERNAME, userTableInfo.getUSER_USERNAME());
		values.put(USER_PASSWORD, userTableInfo.getUSER_PASSWORD());
		values.put(USER_DESCRIPTION, userTableInfo.getUSER_DESCRIPTION());
		values.put(USER_ISADMIN, userTableInfo.getUSER_ISADMIN());
		values.put(USER_STATUS, userTableInfo.getUSER_STATUS());
		// Inserting Row
		db.insert(TABLE_user_users, null, values);
		db.close(); // Closing database connection
	}

	public void addCategory(CateGoryInfo cateGoryInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CATE_CATE_ID, cateGoryInfo.getCATE_ID());
		values.put(CATE_CATE_DESCRIPTION, cateGoryInfo.getCATE_DESCRIPTION());
		values.put(CATE_CATE_TIMESTAMP, cateGoryInfo.getCATE_TIMESTAMP());
		values.put(CATE_IS_DELETED, cateGoryInfo.getIS_DELETED());
		// Inserting Row
		db.insert(TABLE_cate_categories, null, values);
		db.close(); // Closing database connection
	}


	public void addSubcategory(SubCatTableInfo subCatTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SUBC_ID, subCatTableInfo.getSUBC_ID());
		values.put(SUBC_CATE_ID, subCatTableInfo.getCATE_ID());
		values.put(SUBC_DESCRIPTION, subCatTableInfo.getSUBC_DESCRIPTION());
		values.put(SUBC_TIMESTAMP, subCatTableInfo.getSUBC_TIMESTAMP());
		values.put(SUBC_IS_DELETED, subCatTableInfo.getIS_DELETED());
		// Inserting Row
		db.insert(TABLE_subc_subcategories, null, values);
		db.close(); // Closing database connection
	}


	public void addProduct(ProductTableInfo productTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PROD_PROD_CODE, productTableInfo.getPROD_CODE());
		values.put(PROD_PROD_DESCRIPTION, productTableInfo.getPROD_DESCRIPTION());
		values.put(PROD_CATE_ID, productTableInfo.getCATE_ID());
		values.put(PROD_SUBC_ID, productTableInfo.getSUBC_ID());
		values.put(PROD_BRAN_ID, productTableInfo.getBRAN_ID());
		values.put(PROD_SUPP_ID, productTableInfo.getSUPP_ID());
		values.put(PROD_PROD_MU, productTableInfo.getPROD_MU());
		values.put(PROD_PROD_MIN_QT, productTableInfo.getPROD_MIN_QT());
		values.put(PROD_PROD_P0, productTableInfo.getPROD_P0());
		values.put(PROD_PROD_P1, productTableInfo.getPROD_P1());
		values.put(PROD_PROD_P2, productTableInfo.getPROD_P2());
		values.put(PROD_PROD_P3, productTableInfo.getPROD_P3());
		values.put(PROD_PROD_P4, productTableInfo.getPROD_P4());
		values.put(PROD_PROD_P5, productTableInfo.getPROD_P5());
		values.put(PROD_PROD_P6, productTableInfo.getPROD_P6());
		values.put(PROD_PROD_P7, productTableInfo.getPROD_P7());
		values.put(PROD_PROD_P8, productTableInfo.getPROD_P8());
		values.put(PROD_PROD_P9, productTableInfo.getPROD_P9());
		values.put(PROD_VATT_ID, productTableInfo.getVATT_ID());
		values.put(PROD_PROD_AVL_QTY, productTableInfo.getPROD_AVL_QTY());
		values.put(PROD_PROD_IMAGE, productTableInfo.getPROD_IMAGE());
		values.put(PROD__PROD_PDF, productTableInfo.getPROD_PDF());
		values.put(PROD_PROD_TIMESTAMP, productTableInfo.getPROD_TIMESTAMP());
		values.put(PROD_PROD_AVL_TIMESTAMP, productTableInfo.getPROD_AVL_TIMESTAMP());
		values.put(PROD_IS_DELETED, productTableInfo.getIS_DELETED());

		// Inserting Row
		db.insert(TABLE_prod_products, null, values);
		db.close(); // Closing database connection
	}



	public void addPriceList(PriceListTableInfo priceListTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PRIC_DESC0, priceListTableInfo.getPRIC_DESC0());
		values.put(PRIC_DESC1, priceListTableInfo.getPRIC_DESC1());
		values.put(PRIC_DESC2, priceListTableInfo.getPRIC_DESC2());
		values.put(PRIC_DESC3, priceListTableInfo.getPRIC_DESC3());
		values.put(PRIC_DESC4, priceListTableInfo.getPRIC_DESC4());
		values.put(PRIC_DESC5, priceListTableInfo.getPRIC_DESC5());
		values.put(PRIC_DESC6, priceListTableInfo.getPRIC_DESC6());
		values.put(PRIC_DESC7, priceListTableInfo.getPRIC_DESC7());
		values.put(PRIC_DESC8, priceListTableInfo.getPRIC_DESC8());
		values.put(PRIC_DESC9, priceListTableInfo.getPRIC_DESC9());
		values.put(PRIC_TIMESTAMP, priceListTableInfo.getPRIC_TIMESTAMP());
		// Inserting Row
		db.insert(TABLE_subc_subcategories, null, values);
		db.close(); // Closing database connection
	}


	public void addPayment(PaymentTableInfo paymentTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(PAYM_CODE, paymentTableInfo.getPAYM_CODE());
		values.put(PAYM_ID,   paymentTableInfo.getPAYM_ID());
		values.put(PAYM_DESCRIPTION, paymentTableInfo.getPAYM_DESCRIPTION());
		values.put(PAYM_DISCOUNT, paymentTableInfo.getPAYM_DISCOUNT());
		values.put(PAYM_TIMESTAMP, paymentTableInfo.getPAYM_TIMESTAMP());
		values.put(PAYM_IS_DELETED, paymentTableInfo.getIS_DELETED());
		// Inserting Row
		db.insert(TABLE_paym_payments, null, values);
		db.close(); // Closing database connection
	}


	public void addDocRows(DocRowTableInfo docRowTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(DOCR_ROWNUM, docRowTableInfo.getDOCR_ROWNUM());
		values.put(DOCH_ID,   docRowTableInfo.getDOCH_ID());
		values.put(DOCH_PROD_CODE, docRowTableInfo.getPROD_CODE());
		values.put(DOCR_DESCRIPTION, docRowTableInfo.getDOCR_DESCRIPTION());
		values.put(DOCR_QUANTITY, docRowTableInfo.getDOCR_QUANTITY());
		values.put(DOCR_PRICE, docRowTableInfo.getDOCR_PRICE());

		values.put(DOCR_DISCOUNT, docRowTableInfo.getDOCR_DISCOUNT());
		values.put(DOCR_TOTAL, docRowTableInfo.getDOCR_TOTAL());
		values.put(DOCR_TIMESTAMP, docRowTableInfo.getDOCR_TIMESTAMP());
		values.put(DOCR_IS_DELETED, docRowTableInfo.getIS_DELETED());
		// Inserting Row
		db.insert(TABLE_doch_document_rows, null, values);
		db.close(); // Closing database connection
	}


	public void addCustomer(CustomerTableInfo docRowTableInfo) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CUST_CUST_CODE, docRowTableInfo.getCUST_CODE());
		values.put(CUST_CUST_NAME1,   docRowTableInfo.getCUST_NAME1());
		values.put(CUST_CUST_NAME2, docRowTableInfo.getCUST_NAME2());
		values.put(CUST_CUST_ADDRESS, docRowTableInfo.getCUST_ADDRESS());
		values.put(CUST_CUST_ZIP, docRowTableInfo.getCUST_ZIP());
		values.put(CUST_CUST_CITY, docRowTableInfo.getCUST_CITY());
		values.put(CUST_CUST_PROVINCE, docRowTableInfo.getCUST_PROVINCE());
		values.put(CUST_COUNTRY, docRowTableInfo.getCUST_COUNTRY());
		values.put(CUST_CUST_TEL, docRowTableInfo.getCUST_TEL());
		values.put(CUST_CUST_FAX, docRowTableInfo.getCUST_FAX());
		values.put(CUST_CUST_MOBILE, docRowTableInfo.getCUST_MOBILE());
		values.put(CUST_CUST_MAIL, docRowTableInfo.getCUST_MAIL());
		values.put(CUST_CUST_CF, docRowTableInfo.getCUST_CF());
		values.put(CUST_CUST_VATNUM, docRowTableInfo.getCUST_VATNUM());
		values.put(CUST_CUST_IBAN, docRowTableInfo.getCUST_IBAN());
		values.put(CUST_VATT_ID, docRowTableInfo.getVATT_ID());
		values.put(CUST_PAYM_ID, docRowTableInfo.getPAYM_ID());
		values.put(CUST_AGEN_CODE, docRowTableInfo.getAGEN_CODE());
		values.put(CUST_CUST_PRICELIST, docRowTableInfo.getCUST_PRICELIST());
		values.put(CUST_CUST_DISCOUNT, docRowTableInfo.getCUST_DISCOUNT());
		values.put(CUST_CUST_STATE, docRowTableInfo.getCUST_STATE());
		values.put(CUST_CUST_TIMESTAMP, docRowTableInfo.getCUST_TIMESTAMP());
		values.put(CUST_IS_DELETED, docRowTableInfo.getIS_DELETED());


		// Inserting Row
		db.insert(TABLE_cust_customers, null, values);
		db.close(); // Closing database connection
	}

	// Getting single contact
//	DocumentTableInfo getContact(int id) {
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
//						KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
//				new String[] { String.valueOf(id) }, null, null, null, null);
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//				cursor.getString(1), cursor.getString(2));
//		// return contact
//		return contact;
//	}
// Getting All DocumentTableInfo
	public List<CustomerTableInfo> getAllCustomer() {
		List<CustomerTableInfo> docList = new ArrayList<CustomerTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_cust_customers;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {

				CustomerTableInfo document = new CustomerTableInfo();
				document.setId(cursor.getString(0));
				document.setCUST_CODE(cursor.getString(1));
				document.setCUST_NAME1(cursor.getString(2));
				document.setCUST_NAME2(cursor.getString(3));
				document.setCUST_ADDRESS(cursor.getString(4));
				document.setCUST_ZIP(cursor.getString(5));
				document.setCUST_CITY(cursor.getString(6));
				document.setCUST_PROVINCE(cursor.getString(7));
				document.setCUST_COUNTRY(cursor.getString(8));
				document.setCUST_TEL(cursor.getString(9));
				document.setCUST_FAX(cursor.getString(10));
				document.setCUST_MOBILE(cursor.getString(11));
				document.setCUST_MAIL(cursor.getString(12));
				document.setCUST_CF(cursor.getString(13));
				document.setCUST_VATNUM(cursor.getString(14));
				document.setCUST_IBAN(cursor.getString(15));
				document.setVATT_ID(cursor.getString(16));

				document.setPAYM_ID(cursor.getString(17));
				document.setAGEN_CODE(cursor.getString(18));
				document.setCUST_PRICELIST(cursor.getString(19));
				document.setCUST_DISCOUNT(cursor.getString(20));
				document.setCUST_STATE(cursor.getString(21));
				document.setCUST_TIMESTAMP(cursor.getString(22));
				document.setIS_DELETED(cursor.getString(23));

				docList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return docList;
	}



	// Getting All DocumentTableInfo
	public List<DocumentTableInfo> getAllDocumentss() {
		List<DocumentTableInfo> docList = new ArrayList<DocumentTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_DOCUMENTS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				DocumentTableInfo document = new DocumentTableInfo();
				document.setId(cursor.getString(0));
				document.setDOCH_TERM_CODE(cursor.getString(1));
				document.setDOCH_TERM_ID(cursor.getString(2));
				document.setDOCH_TYPE(cursor.getString(3));
				document.setDOCH_NUMBER(cursor.getString(4));
				document.setDOCH_DATE(cursor.getString(5));
				document.setAGEN_CODE(cursor.getString(6));
				document.setDOCH_PRICELIST(cursor.getString(7));
				document.setCUST_CODE(cursor.getString(8));
				document.setCUST_NAME1(cursor.getString(9));
				document.setCUST_NAME2(cursor.getString(10));
				document.setCUST_ADDRESS(cursor.getString(11));
				document.setCUST_ZIP(cursor.getString(12));
				document.setCUST_CITY(cursor.getString(13));
				document.setCUST_CITY(cursor.getString(14));
				document.setCUST_MAIL(cursor.getString(15));
				document.setCUST_PROVINCE(cursor.getString(16));
				document.setCUST_COUNTRY(cursor.getString(17));

				document.setCUST_DISCOUNT(cursor.getString(18));
				document.setSPECIAL_VATT(cursor.getString(19));
				document.setDEST_ID(cursor.getString(20));
				document.setDEST_NAME(cursor.getString(21));
				document.setDEST_ADDRESS(cursor.getString(22));
				document.setDEST_ZIP(cursor.getString(23));
				document.setDEST_CITY(cursor.getString(24));
				document.setDEST_PROVINCE(cursor.getString(25));
				document.setDEST_COUNTRY(cursor.getString(26));

				document.setVATT_ID(cursor.getString(27));
				document.setPAYM_ID(cursor.getString(28));
				document.setDOCH_NOTE(cursor.getString(29));
				document.setDOCH_SENT(cursor.getString(30));
				document.setIS_DELETED(cursor.getString(31));
				document.setDOCH_TIMESTAMP(cursor.getString(32));


				// Adding contact to list
				docList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return docList;
	}


	// Getting All DocumentTableInfo
	public List<AgentTableInfo> getAllAgents() {
		List<AgentTableInfo> agentList = new ArrayList<AgentTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_AGENT;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				AgentTableInfo document = new AgentTableInfo();
				document.setId(cursor.getString(0));
				document.setAGEN_CODE(cursor.getString(1));
				document.setAGEN_NAME1(cursor.getString(2));
				document.setAGEN_NAME2(cursor.getString(3));
				document.setAGEN_EMAIL(cursor.getString(4));
				document.setAGEN_PASSWORD(cursor.getString(5));
				document.setAGEN_ENABLED(cursor.getString(6));
				document.setAGEN_CANCHANGEPRICE(cursor.getString(7));
				document.setAGEN_CANCHANGEPAYM(cursor.getString(8));
				document.setAGEN_CANCHANGEVAT(cursor.getString(9));
				document.setAGEN_CANADDCUST(cursor.getString(10));
				document.setAGEN_CANADDDEST(cursor.getString(11));
				document.setAGEN_CANCHANGEDESC(cursor.getString(12));
				document.setAGEN_CANUSENOCODE(cursor.getString(13));
				document.setAGEN_CANADDCOMMENTS(cursor.getString(14));
				document.setAGEN_TIMESTAMP(cursor.getString(15));
				document.setIS_DELETED(cursor.getString(16));
				document.setAGENT_TYPE(cursor.getString(17));

				// Adding contact to list
				agentList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return agentList;
	}


	// Getting All BarCodeTableInfo
	public List<BarCodeTableInfo> getAllBarCodes() {
		List<BarCodeTableInfo> barcodeList = new ArrayList<BarCodeTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_BARCODE;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				BarCodeTableInfo document = new BarCodeTableInfo();
				document.setId(cursor.getString(0));
				document.setBARC_BARCODE(cursor.getString(1));
				document.setPROD_CODE(cursor.getString(2));
				document.setBARC_TIMESTAMP(cursor.getString(3));
				document.setIS_DELETED(cursor.getString(4));

				// Adding contact to list
				barcodeList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return barcodeList;
	}


	// Getting All BrandsTableInfo
	public List<BrandsTableInfo> getAllBrands() {
		List<BrandsTableInfo> barcodeList = new ArrayList<BrandsTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_BRANDS;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				BrandsTableInfo document = new BrandsTableInfo();
				document.setID(cursor.getString(0));
				document.setBRAN_ID(cursor.getString(1));
				document.setBRAN_DESCRIPTION(cursor.getString(2));
				document.setBRAN_TIMESTAMP(cursor.getString(3));
				document.setIS_DELETED(cursor.getString(4));
				// Adding contact to list
				barcodeList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return barcodeList;
	}



	// Getting All VatTableInfo
	public List<VatTableInfo> getAllVats() {
		List<VatTableInfo> barcodeList = new ArrayList<VatTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_vatt_vat;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				VatTableInfo document = new VatTableInfo();
				document.setID(cursor.getString(0));
				document.setVATT_ID(cursor.getString(1));
				document.setVATT_CODE(cursor.getString(2));
				document.setVATT_PERCENT(cursor.getString(3));
				document.setIS_DELETED(cursor.getString(4));
				// Adding contact to list
				barcodeList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return barcodeList;
	}


	// Getting All UserTableInfo
	public List<UserTableInfo> getAllUsers() {
		List<UserTableInfo> barcodeList = new ArrayList<UserTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_user_users;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				UserTableInfo document = new UserTableInfo();
				document.setID(cursor.getString(0));
				document.setUSER_USERNAME(cursor.getString(1));
				document.setUSER_PASSWORD(cursor.getString(2));
				document.setUSER_DESCRIPTION(cursor.getString(3));
				document.setUSER_ISADMIN(cursor.getString(4));
				document.setUSER_STATUS(cursor.getString(5));
				// Adding contact to list
				barcodeList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return barcodeList;
	}



	// Getting All CatTableInfo
	public List<CateGoryInfo> getAllCategories() {
		List<CateGoryInfo> catList = new ArrayList<CateGoryInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_cate_categories;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				CateGoryInfo document = new CateGoryInfo();
				document.setId(cursor.getString(0));
				document.setCATE_ID(cursor.getString(1));
				document.setCATE_DESCRIPTION(cursor.getString(2));
				document.setCATE_TIMESTAMP(cursor.getString(3));
				document.setIS_DELETED(cursor.getString(4));
				// Adding contact to list
				catList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return catList;
	}

	// Getting All SubCatTableInfo
	public List<SubCatTableInfo> getAllSubcategories() {
		List<SubCatTableInfo> subCatList = new ArrayList<SubCatTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_subc_subcategories;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				SubCatTableInfo document = new SubCatTableInfo();
				document.setID(cursor.getString(0));
				document.setSUBC_ID(cursor.getString(1));
				document.setSUBC_DESCRIPTION(cursor.getString(2));
				document.setCATE_ID(cursor.getString(3));
				document.setSUBC_TIMESTAMP(cursor.getString(4));
				document.setIS_DELETED(cursor.getString(5));
				// Adding contact to list
				subCatList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return subCatList;
	}


	// Getting All ProductTableInfo
	public List<ProductTableInfo> getAllProducts() {
		List<ProductTableInfo> subCatList = new ArrayList<ProductTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_prod_products;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				ProductTableInfo document = new ProductTableInfo();
				document.setId(cursor.getString(0));
				document.setPROD_CODE(cursor.getString(1));
				document.setPROD_DESCRIPTION(cursor.getString(2));
				document.setCATE_ID(cursor.getString(3));
				document.setSUBC_ID(cursor.getString(4));
				document.setBRAN_ID(cursor.getString(5));
				document.setSUPP_ID(cursor.getString(6));
				document.setPROD_MU(cursor.getString(7));
				document.setPROD_MIN_QT(cursor.getString(8));
				document.setPROD_P0(cursor.getString(9));
				document.setPROD_P1(cursor.getString(10));
				document.setPROD_P2(cursor.getString(11));
				document.setPROD_P3(cursor.getString(12));
				document.setPROD_P4(cursor.getString(13));
				document.setPROD_P5(cursor.getString(14));
				document.setPROD_P6(cursor.getString(15));
				document.setPROD_P7(cursor.getString(16));
				document.setPROD_P8(cursor.getString(17));
				document.setPROD_P9(cursor.getString(18));
				document.setVATT_ID(cursor.getString(19));
				document.setPROD_AVL_QTY(cursor.getString(20));
				document.setPROD_IMAGE(cursor.getString(21));
				document.setPROD_PDF(cursor.getString(22));
				document.setPROD_TIMESTAMP(cursor.getString(23));
				document.setPROD_AVL_TIMESTAMP(cursor.getString(24));
				document.setIS_DELETED(cursor.getString(25));


				// Adding contact to list
				subCatList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return subCatList;
	}


	// Getting All PriceListTableInfo
	public List<PriceListTableInfo> getAllPriceList() {
		List<PriceListTableInfo> subCatList = new ArrayList<PriceListTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_pric_pricelists;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				PriceListTableInfo document = new PriceListTableInfo();
				document.setID(cursor.getString(0));
				document.setPRIC_DESC0(cursor.getString(1));
				document.setPRIC_DESC1(cursor.getString(2));
				document.setPRIC_DESC2(cursor.getString(3));
				document.setPRIC_DESC3(cursor.getString(4));
				document.setPRIC_DESC4(cursor.getString(5));
				document.setPRIC_DESC5(cursor.getString(6));
				document.setPRIC_DESC6(cursor.getString(7));
				document.setPRIC_DESC7(cursor.getString(8));

				document.setPRIC_DESC8(cursor.getString(9));
				document.setPRIC_DESC9(cursor.getString(10));
				document.setPRIC_TIMESTAMP(cursor.getString(11));

				// Adding contact to list
				subCatList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return subCatList;
	}


	// Getting All PaymentTableInfo
	public List<PaymentTableInfo> getPaymentList() {
		List<PaymentTableInfo> subCatList = new ArrayList<PaymentTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_paym_payments;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				PaymentTableInfo document = new PaymentTableInfo();
				document.setID(cursor.getString(0));
				document.setPAYM_ID(cursor.getString(1));
				document.setPAYM_CODE(cursor.getString(2));
				document.setPAYM_DESCRIPTION(cursor.getString(3));
				document.setPAYM_DISCOUNT(cursor.getString(4));
				document.setPAYM_TIMESTAMP(cursor.getString(5));
				document.setIS_DELETED(cursor.getString(6));
				// Adding contact to list
				subCatList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return subCatList;
	}


	// Getting All DocRowTableInfo
	public List<DocRowTableInfo> getDocRowList() {
		List<DocRowTableInfo> subCatList = new ArrayList<DocRowTableInfo>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + TABLE_paym_payments;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				DocRowTableInfo document = new DocRowTableInfo();
				document.setId(cursor.getString(0));
				document.setDOCR_ROWNUM(cursor.getString(1));
				document.setDOCH_ID(cursor.getString(2));
				document.setDOCR_DESCRIPTION(cursor.getString(3));
				document.setVATT_ID(cursor.getString(4));
				document.setDOCR_QUANTITY(cursor.getString(5));
				document.setDOCR_PRICE(cursor.getString(6));
				document.setDOCR_DISCOUNT(cursor.getString(7));
				document.setDOCR_TOTAL(cursor.getString(8));
				document.setDOCR_TIMESTAMP(cursor.getString(9));
				document.setIS_DELETED(cursor.getString(10));
				// Adding contact to list
				subCatList.add(document);
			} while (cursor.moveToNext());
		}

		// return contact list
		return subCatList;
	}



	// Updating single contact
//	public int updateContact(ProductTableInfo contact,String name) {
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		ContentValues values = new ContentValues();
//		values.put(KEY_NAME, contact.getName());
//		values.put(KEY_PH_NO, contact.getPhoneNumber());
//
//		// updating row
//		return db.update(TABLE_CONTACTS, values, KEY_NAME + " = ?",
//				new String[] { String.valueOf(name)});
//	}

	// Deleting single contact
	public void deleteCatagory() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_cate_categories);
		db.close();
	}

	public void deleteCustomer() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_cust_customers);
		db.close();
	}


	public void deleteBrands() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_BRANDS);
		db.close();
	}

	public void deleteSubCatagory() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_subc_subcategories);
		db.close();
	}

	public void deleteAgent() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_AGENT);
		db.close();
	}

	public void deleteProduct() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_prod_products);
		db.close();
	}

	public void deleteDocument() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from "+ TABLE_DOCUMENTS);
		db.close();
	}

	// Getting contacts Count
//	public int getContactsCount() {
//		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
//		SQLiteDatabase db = this.getReadableDatabase();
//		Cursor cursor = db.rawQuery(countQuery, null);
//		cursor.close();
//
//		// return count
//		return cursor.getCount();
//	}
}
