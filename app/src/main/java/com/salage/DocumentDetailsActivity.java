package com.salage;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.Utils.Helper;
import com.salage.Utils.PersistData;
import com.salage.Utils.UIUtils;
import com.salage.adapter.CustomAdapter;
import com.salage.adapter.CustomAdapterBrands;
import com.salage.adapter.CustomAdapterSubCat;
import com.salage.model.AgentTableInfo;
import com.salage.model.BrandsTableInfo;
import com.salage.model.CateGoryInfo;
import com.salage.model.CustomerProductTableInfo;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;
import com.salage.model.PaymentTableInfo;
import com.salage.model.PriceListTableInfo;
import com.salage.model.ProductTableInfo;
import com.salage.model.SubCatTableInfo;
import com.salage.model.VatTableInfo;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class DocumentDetailsActivity extends AppCompatActivity{
    Context con;
    private View view;
    private ImageView dissmissCatListBtn,imgProd,imgAddPro;
    private Spinner spinnerClient,spinnerTpo,spinnerPriceList,spinnerVat,spinnerPayment;
    private DatabaseHelper db;
    private List<String> agentCodeList = new ArrayList<>();
    private EditText etDate,etAegentCode,etClientCode,clientFirstName,etClientLastName,etAddress,
            etZip,etCity,etEmail,etProvince,etSconti,etNazion,etTotalAll,etQuantity,etTotAfDis
            ,etProNameSearch,etProName,etIva,etIvaInclode;
    private List<CustomerTableInfo> customerTableInfoList  = new ArrayList<>();
    private List<ProductTableInfo> proInfoAddList  = new ArrayList<>();
    private String custName,vatid,paymId;
    private ListView listProduct;
    private ScrollView scrollView;
    private CustomAdapterProduct customAdapterProduct;
    private Dialog dialogProduct;
    private Spinner spinnerClass,spinnerSottoClass,spinnerMarche,spinnerPaginator;
    private List<CateGoryInfo> cateGoryInfoList  = new ArrayList<>();
    private List<SubCatTableInfo> subCateGoryInfoList  = new ArrayList<>();
    private List<SubCatTableInfo> subCateGoryInfoSpinnerList  = new ArrayList<>();
    private List<BrandsTableInfo> barnadsList  = new ArrayList<>();
    private List<ProductTableInfo> productList  = new ArrayList<>();
    private List<ProductTableInfo> productDetailsList  = new ArrayList<>();
    private List<String> subDes = new ArrayList<String>();
    private List<String> proNameList = new ArrayList<String>();
    private String [] proName;
    private ArrayAdapter<String> listAdapter;
    List <String> listPayment = new ArrayList<>();
    List <String> listVat = new ArrayList<>();
    private List<String> listTotal = new ArrayList<String>();
    private ListView listProDetails, listProName;
    private TextView tvSearch;
    private boolean isSpinnerInitial = true;
    private CustomAdapterSubCat customAdapterSubCat;
    private CustomAdapterProductDialogue customAdapterProductDialogue;
    private String  catId,subCatId,fromDialog="",pricrCode,pricrNumber = "0",docType="";
    double subTottal,aftDiscTotal,vat;
    double tottal;
    private String discounts;
    int proAddPos,selectPos,proTotal;
    private LinearLayout linAddProduct;
    List <PriceListTableInfo> listPriceTable = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.document_detailse);
        con=this;
        initUi();
    }
    private void initUi() {
        db = new DatabaseHelper(this);
        productList = db.getAllProducts();
        customerTableInfoList = db.getAllCustomer();
        Log.e("customerList size",""+customerTableInfoList.size());

        for(int i = 0; i<productList.size();i++){
            proNameList.add(productList.get(i).getPROD_CODE());
            proName=proNameList.toArray(new String[i]);

        }


       // imgAddPro = (ImageView) findViewById(R.id.imgAddPro);
        linAddProduct = (LinearLayout) findViewById(R.id.linAddProduct);
        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//        spinnerClient = (Spinner)findViewById(R.id.spinnerClient);
//        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, agentCodeList);
//        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinnerClient.setAdapter(adp);
        etTotalAll = (EditText)findViewById(R.id.etTotalAll);
        etDate = (EditText)findViewById(R.id.etDate);
        etAegentCode = (EditText)findViewById(R.id.etAegentCode);
        etClientCode = (EditText)findViewById(R.id.etClientCode);
        clientFirstName = (EditText)findViewById(R.id.clientFirstName);
        etClientLastName = (EditText)findViewById(R.id.etClientLastName);
        etCity = (EditText)findViewById(R.id.etCity);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etProvince = (EditText)findViewById(R.id.etProvince);
        etSconti = (EditText)findViewById(R.id.etSconti);
        etZip = (EditText)findViewById(R.id.etZip);
        etAddress = (EditText)findViewById(R.id.etAddress);
        etNazion = (EditText)findViewById(R.id.etNazion);
        etIva = (EditText)findViewById(R.id.etIva);
        etIvaInclode = (EditText)findViewById(R.id.etIvaInclode);
        spinnerVat = (Spinner)findViewById(R.id.spinnerVat);
        spinnerPayment = (Spinner)findViewById(R.id.spinnerPayment);

        Date d = new Date();
        CharSequence s  = DateFormat.format("dd/MM/yyyy", d.getTime());
        etDate.setText(s);

        spinnerClient = (Spinner)findViewById(R.id.spinnerClient);


        spinnerTpo = (Spinner)findViewById(R.id.spinnerTpo);
        List <String> listTpo = new ArrayList<>();
        listTpo.add("Select document Type");
        listTpo.add("QUOTE");
        listTpo.add("ORDER");

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listTpo);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTpo.setAdapter(adp1);
        spinnerTpo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                List <String> listAgent = new ArrayList<>();
                docType = spinnerTpo.getSelectedItem().toString().trim();
                if(docType.equalsIgnoreCase("ORDER")){
                    if(AppConstant.isDoc.equalsIgnoreCase("true")){
                        listAgent.add(AppConstant.documentTableInfo.getCUST_CODE());
                    }else {
                        listAgent.add(PersistData.getStringData(con, AppConstant.agentCode));
                    }
                }else if(docType.equalsIgnoreCase("QUOTE")){
                    listAgent.add("nuovo cliente");
                }

                ArrayAdapter<String> adpAgent=new ArrayAdapter<String>(con,
                        android.R.layout.simple_list_item_1,listAgent);
                adpAgent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerClient.setAdapter(adpAgent);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {

                if(spinnerClient.getSelectedItem().toString().trim().equalsIgnoreCase("nuovo cliente")){
                    emptyEditField();
                }else {
                    custName=spinnerClient.getSelectedItem().toString().trim();

                    for(int i =0; i<customerTableInfoList.size();i++){
                        if(custName.equalsIgnoreCase(customerTableInfoList.get(i).getAGEN_CODE())){
                            etAegentCode.setText(customerTableInfoList.get(i).getCUST_CODE());

                            etClientCode.setText(customerTableInfoList.get(i).getCUST_CODE());
                            clientFirstName.setText(customerTableInfoList.get(i).getCUST_NAME1());
                            etClientLastName.setText(customerTableInfoList.get(i).getCUST_NAME2());
                            etAddress.setText(customerTableInfoList.get(i).getCUST_ADDRESS());
                            etZip.setText(customerTableInfoList.get(i).getCUST_ZIP());
                            etCity.setText(customerTableInfoList.get(i).getCUST_CITY());
                            etProvince.setText(customerTableInfoList.get(i).getCUST_PROVINCE());
                            etNazion.setText(customerTableInfoList.get(i).getCUST_COUNTRY());
                            vatid = customerTableInfoList.get(i).getVATT_ID();
                            Log.e("vatid",vatid);
                            paymId = customerTableInfoList.get(i).getPAYM_ID();
                            Log.e("paymId",paymId);
//               etNtel.setText(AppConstant.customerTableInfo.getCUST_TEL());
//               etFax.setText(AppConstant.customerTableInfo.getCUST_FAX());
//               etMobile.setText(AppConstant.customerTableInfo.getCUST_MOBILE());
                            etEmail.setText(customerTableInfoList.get(i).getCUST_MAIL());
//               etCodeFiscal.setText(AppConstant.customerTableInfo.getCUST_CF());
//               etPrtitaIVA.setText(AppConstant.customerTableInfo.getCUST_VATNUM());
//               etIbn.setText(AppConstant.customerTableInfo.getCUST_IBAN());
                            etNazion.setText(customerTableInfoList.get(i).getCUST_COUNTRY());
                            etSconti.setText(customerTableInfoList.get(i).getCUST_DISCOUNT());
                        }

                    }



                    List <VatTableInfo> vatListInfo = new ArrayList<>();
                    vatListInfo = db.getAllVats();

                    for(int j = 0; j<vatListInfo.size();j++){
                        if(!TextUtils.isEmpty(vatid)){
                            if(vatid.equalsIgnoreCase(vatListInfo.get(j).getVATT_ID())){
                                Log.e("vatid tabale",vatListInfo.get(j).getVATT_ID());
                                listVat.add(vatListInfo.get(j).getVATT_DESCRIPTION());
                            }
                        }

                    }
                    ArrayAdapter<String> adpVat=new ArrayAdapter<String>(con,
                            android.R.layout.simple_list_item_1,listVat);
                    adpVat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerVat.setAdapter(adpVat);

                    spinnerVat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                            String str=spinnerVat.getSelectedItem().toString().trim();
                            String numberOnly= str.replaceAll("[^0-9]", "");
                            vat= Double.parseDouble(numberOnly);


                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });



//                listPayment.add("Select payment");
                    List <PaymentTableInfo> paymentTableInfoListInfo = new ArrayList<>();
                    paymentTableInfoListInfo = db.getPaymentList();
                    Log.e("paymId tabale size",""+paymentTableInfoListInfo.size());

                    for(int n = 0; n<paymentTableInfoListInfo.size();n++){
                        if(!TextUtils.isEmpty(paymId)){
                            if(paymId.equalsIgnoreCase(paymentTableInfoListInfo.get(n).getPAYM_ID())){
                                Log.e("paymId tabale",paymentTableInfoListInfo.get(n).getPAYM_DESCRIPTION());
                                listPayment.add(paymentTableInfoListInfo.get(n).getPAYM_DESCRIPTION());
                            }
                        }

                    }

                    ArrayAdapter<String> adpPaym=new ArrayAdapter<String>(con,
                            android.R.layout.simple_list_item_1,listPayment);
                    adpPaym.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPayment.setAdapter(adpPaym);
                }



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerPriceList = (Spinner)findViewById(R.id.spinnerPriceList);
        List <String> listPrice = new ArrayList<>();
        //listPrice.add("Select price");

        listPriceTable = db.getAllPriceList();
        for(int i=0;i<listPriceTable.size();i++){
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC0())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC0());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC1())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC1());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC2())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC2());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC3())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC3());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC4())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC4());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC5())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC5());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC6())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC6());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC7())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC7());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC8())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC8());
            }
            if(!TextUtils.isEmpty(listPriceTable.get(i).getPRIC_DESC9())){
                listPrice.add(listPriceTable.get(i).getPRIC_DESC9());
            }

        }

        ArrayAdapter<String> adpprice=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listPrice);
        adpprice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriceList.setAdapter(adpprice);

        spinnerPriceList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                if(spinnerPriceList.getSelectedItem().toString().trim().equalsIgnoreCase("Select price")){
//                    pricrCode ="";
//                }else {
                    pricrCode =spinnerPriceList.getSelectedItem().toString().trim();
                //}


                    for(int j=0;j<listPriceTable.size();j++){
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC0())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC0())){
                                pricrNumber = "0";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC1())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC1())){
                                pricrNumber = "1";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC2())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC2())){
                                pricrNumber = "2";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC3())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC3())){
                                pricrNumber = "3";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC4())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC4())){
                                pricrNumber = "4";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC5())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC5())){
                                pricrNumber = "5";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC6())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC6())){
                                pricrNumber = "6";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC7())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC7())){
                                pricrNumber = "7";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC8())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC8())){
                                pricrNumber = "8";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }
                        if(!TextUtils.isEmpty(listPriceTable.get(j).getPRIC_DESC9())){
                            if(pricrCode.equalsIgnoreCase(listPriceTable.get(j).getPRIC_DESC9())){
                                pricrNumber = "9";
                                customAdapterProduct.notifyDataSetChanged();
                            }
                        }

                    }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        listProduct = (ListView)findViewById(R.id.listProduct);
       // setListViewHeightBasedOnChildren(listProduct);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        //smartScroll(scrollView,listProduct);


        ProductTableInfo pinf = new ProductTableInfo();
        proInfoAddList.add(pinf);

        customAdapterProduct = new CustomAdapterProduct(con);
        listProduct.setAdapter(customAdapterProduct);
        UIUtils.setListViewHeightBasedOnItems(listProduct);
        //customAdapterProduct.notifyDataSetChanged();

        //Helper.getListViewSize(listProduct);

        linAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductTableInfo pinf = new ProductTableInfo();
                proInfoAddList.add(pinf);
                customAdapterProduct = new CustomAdapterProduct(con);
                listProduct.setAdapter(customAdapterProduct);
                UIUtils.setListViewHeightBasedOnItems(listProduct);
                //listProduct.setSelection(proAddPos-1);
                listProduct.setItemChecked(proAddPos-1,true);
                //customAdapterProduct.notifyDataSetChanged();

            }
        });

    }


    private class CustomAdapterProduct extends ArrayAdapter<ProductTableInfo> {
        Context context;
        int count;

        CustomAdapterProduct(Context context) {
            super(context, R.layout.raw_add_product, proInfoAddList);

            this.context = context;

        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_add_product, null);
            }

            if (position < proInfoAddList.size()) {

                final ProductTableInfo query = proInfoAddList.get(position);
                final Spinner spinnerProduct = (Spinner)v.findViewById(R.id.spinnerProduct);
                final LinearLayout linProduct = (LinearLayout)v.findViewById(R.id.linProduct);
                etProName = (EditText) v.findViewById(R.id.etProName);
                final EditText etDescript = (EditText) v.findViewById(R.id.etDescript);
                 etQuantity = (EditText) v.findViewById(R.id.etQuantity);
                 etTotAfDis = (EditText) v.findViewById(R.id.etTotAfDis);
                final EditText etLista = (EditText) v.findViewById(R.id.etLista);
                final EditText etSubTotal = (EditText) v.findViewById(R.id.etSubTotal);
                final EditText etDiscount = (EditText) v.findViewById(R.id.etDiscount);
                etProNameSearch = (EditText) v.findViewById(R.id.etProNameSearch);

                etDescript.setText(query.getPROD_DESCRIPTION());
                etLista.setText(query.getPriceData());
                etQuantity.setText(query.getQuantity());
                etSubTotal.setText(query.getTotal());
                etProName.setText(query.getPROD_CODE());
                etProName.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                       if(s.length()==0){
                           etProName.setVisibility(View.GONE);
                           etProNameSearch.setVisibility(View.VISIBLE);
                       }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });

                if(pricrNumber.equalsIgnoreCase("0")){
                    etLista.setText(query.getPROD_P0());
                }
                if(pricrNumber.equalsIgnoreCase("0")){
                    etLista.setText(query.getPROD_P0());
                }
                if(pricrNumber.equalsIgnoreCase("1")){
                    etLista.setText(query.getPROD_P1());
                }
                if(pricrNumber.equalsIgnoreCase("2")){
                    etLista.setText(query.getPROD_P2());
                }
                if(pricrNumber.equalsIgnoreCase("3")){
                    etLista.setText(query.getPROD_P3());
                }
                if(pricrNumber.equalsIgnoreCase("4")){
                    etLista.setText(query.getPROD_P4());
                }
                if(pricrNumber.equalsIgnoreCase("5")){
                    etLista.setText(query.getPROD_P5());
                }
                if(pricrNumber.equalsIgnoreCase("6")){
                    etLista.setText(query.getPROD_P6());
                }
                if(pricrNumber.equalsIgnoreCase("7")){
                    etLista.setText(query.getPROD_P7());
                }
                if(pricrNumber.equalsIgnoreCase("8")){
                    etLista.setText(query.getPROD_P8());
                }
                if(pricrNumber.equalsIgnoreCase("9")){
                    etLista.setText(query.getPROD_P9());
                }
                etSubTotal.setFocusable(false);

                etQuantity.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        if(!TextUtils.isEmpty(etQuantity.getText().toString())&& !TextUtils.isEmpty(etLista.getText().toString())){
                            double price = Double.parseDouble(etLista.getText().toString());
                            double quantity = Double.parseDouble(etQuantity.getText().toString());
                            double totali = price*quantity;
                            etSubTotal.setText(String.valueOf(totali));

                            subTottal = 0;
                            proInfoAddList.set(position,new ProductTableInfo(etSubTotal.getText().toString(),etLista.getText().toString(),etQuantity.getText().toString()));
                            for(int p = 0; p<proInfoAddList.size();p++){
                                if(!TextUtils.isEmpty(proInfoAddList.get(p).getTotal())){
                                    tottal = Double.parseDouble(proInfoAddList.get(p).getTotal());
                                    subTottal += tottal;
                                    Log.e("tottalResult",""+subTottal);
                                    etTotalAll.setText(subTottal+"");
                                    double vatTotal = (subTottal*vat)/100;
                                    etIva.setText(String.valueOf(vatTotal));
                                    etIvaInclode.setText(String.valueOf(subTottal+vatTotal));
                                }
                            }
                        }

                        if(!TextUtils.isEmpty(discounts)){
                            String[] parts = discounts.split("\\+");
                            List<String> values = new ArrayList<String>();
                            for(int s = 0 ; s<parts.length;s++){
                                values.add(parts[s]);
                                Log.e("dis size",""+values.size());
                                Log.e("dis value",""+values.get(s));
                                double singleVal = Double.parseDouble(values.get(s));
                               double aftDisTottal = (subTottal*singleVal)/100;
                                subTottal -= aftDisTottal;
                                Log.e("aftDisTottal",""+aftDisTottal);
                                etTotAfDis.setText(String.valueOf(subTottal));

                            }
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                });


                final TextView tvViewNum = (TextView) v.findViewById(R.id.tvViewNum);
                listProName = (ListView) v.findViewById(R.id.listProName);
                listProName.setTextFilterEnabled(false);
                //setListViewHeightBasedOnChildren(listProName);
                listAdapter = new ArrayAdapter<String>(con, R.layout.item_layout, R.id.tvtext1, proNameList);
                listProName.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();
                UIUtils.setListViewHeightBasedOnItems(listProName);
                final Filter filter = listAdapter.getFilter();

                etProNameSearch.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
//                        listProName.setFilterText(s.toString());
//                        listAdapter.getFilter().filter(s);
//                       // listAdapter.notifyDataSetChanged();
                        //dialogueProductMenu(proName);
                        filter.filter(s);
                        listAdapter.notifyDataSetChanged();
                        getListViewHeight(listProName,listAdapter.getCount());
                        UIUtils.setListViewHeightBasedOnItems(listProName);
                        listProName.setVisibility(View.VISIBLE);
                        if(s.length()<1){
                            listProName.setVisibility(View.GONE);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listProName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        etQuantity.requestFocus();
                        etProNameSearch.setText(listProName.getItemAtPosition(i).toString());

                        List<CustomerProductTableInfo> crpList = new ArrayList<CustomerProductTableInfo>();
                        crpList = db.getAllCusProduct();
                        for(int p = 0; p<crpList.size();p++){
                            if(listProName.getItemAtPosition(i).toString().trim().equalsIgnoreCase(crpList.get(p).getPROD_CODE())){
                               if(!TextUtils.isEmpty(crpList.get(p).getCUPR_DISCOUNT())) {
                                   etDiscount.setText(crpList.get(p).getCUPR_DISCOUNT());
                               }



                            }else {
                                etDiscount.setText(etSconti.getText().toString());
                                discounts = etDiscount.getText().toString();


                            }
                        }

                        listProName.setVisibility(View.GONE);
                        for(int j = 0; j<productList.size();j++){

                            if(etProNameSearch.getText().toString().equalsIgnoreCase(productList.get(j).getPROD_CODE())){

                                proInfoAddList.set(position,productList.get(j));
                                customAdapterProduct.notifyDataSetChanged();
                                UIUtils.setListViewHeightBasedOnItems(listProduct);
                            }
                        }
                    }
                });



                int pnum = position+1;
                tvViewNum.setText(""+pnum);
                final ImageView imgMinus = (ImageView) v.findViewById(R.id.imgMinus);
                etProNameSearch.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        proAddPos = position;
                        showProDialogue();

                        return false;
                    }
                });

                imgMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        if(!TextUtils.isEmpty(etTotal.getText().toString())){
//                            double totall = Double.parseDouble(etTotal.getText().toString());
//                            tottalResult -= totall;
//                            etTotalAll.setText(tottalResult+"");
//                        }
                   proInfoAddList.remove(position);
                        if(!TextUtils.isEmpty(etQuantity.getText().toString())&& !TextUtils.isEmpty(etLista.getText().toString())){
                            double price = Double.parseDouble(etLista.getText().toString());
                            double quantity = Double.parseDouble(etQuantity.getText().toString());
                            double totali = price*quantity;
                            etSubTotal.setText(String.valueOf(totali));

                            subTottal = 0;
                        //    proInfoAddList.set(position-1,new ProductTableInfo(etTotal.getText().toString(),etLista.getText().toString(),etQuantity.getText().toString()));
                            for(int p = 0; p<proInfoAddList.size();p++){
                                if(!TextUtils.isEmpty(proInfoAddList.get(p).getTotal())){
                                    tottal = Double.parseDouble(proInfoAddList.get(p).getTotal());
                                    subTottal += tottal;
                                    Log.e("tottalResult",""+subTottal);
                                    etTotalAll.setText(round(subTottal,3)+"");
                                    double vatTotal = (subTottal*vat)/100;
                                    round(vatTotal,3);
                                    etIva.setText(String.valueOf(vatTotal));
                                    etIvaInclode.setText(String.valueOf(subTottal+vatTotal));
                                }
                            }
                        }
                   customAdapterProduct.notifyDataSetChanged();
                        UIUtils.setListViewHeightBasedOnItems(listProduct);

                      // Log.e("tottalResult",""+tottalResult);
                       if(proInfoAddList.size()==0){
                           etTotalAll.setText("");
                           etIvaInclode.setText("");
                           etIva.setText("");
                       }

                    }
                });



            }

            return v;

        }

    }


    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ActionBar.LayoutParams.WRAP_CONTENT));
            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    public void smartScroll(final ScrollView scroll, final ListView list){
        list.setOnTouchListener(new View.OnTouchListener() {
            private boolean isListTop = false, isListBottom = false;
            private float delta = 0, oldY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        scroll.requestDisallowInterceptTouchEvent(true);
                        list.requestDisallowInterceptTouchEvent(false);
                        oldY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        delta = 0;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        delta = event.getY() - oldY;
                        oldY = event.getY();

                        isListTop = false;
                        isListBottom = false;

                        View first = list.getChildAt(0);
                        View last = list.getChildAt(list.getChildCount()-1);
                        if(first != null && list.getFirstVisiblePosition() == 0 && first.getTop() == 0 && delta > 0.0f){
                            isListTop = true;
                        }
                        if(last != null && list.getLastVisiblePosition() == list.getCount()-1 && last.getBottom() <= list.getHeight() && delta < 0.0f){
                            isListBottom = true;
                        }

                        if( (isListTop && delta > 0.0f) || (isListBottom && delta < 0.0f) ){
                            scroll.requestDisallowInterceptTouchEvent(false);
                            list.requestDisallowInterceptTouchEvent(true);
                        }
                        break;
                    default: break;
                }
                return false;
            }
        });
    }


    private void showProDialogue(){


        dialogProduct = new Dialog(con);
        cateGoryInfoList = db.getAllCategories();
        CateGoryInfo cainf = new CateGoryInfo();
        cainf.setCATE_DESCRIPTION("Select class");
        cateGoryInfoList.add(cainf);

        subCateGoryInfoList = db.getAllSubcategories();


        barnadsList = db.getAllBrands();


        subCateGoryInfoSpinnerList.add(new SubCatTableInfo("select"));
        dialogProduct.setContentView(R.layout.dialogue_product);
        listProDetails = (ListView)dialogProduct.findViewById(R.id.listProDetails);
        tvSearch = (TextView)dialogProduct.findViewById(R.id.tvSearch);
        dissmissCatListBtn = (ImageView) dialogProduct.findViewById(R.id.dissmissCatListBtn);

        ImageView imageCross = (ImageView) dialogProduct.findViewById(R.id.imageCross);

        imageCross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogProduct.dismiss();
            }
        });
        spinnerClass = (Spinner)dialogProduct.findViewById(R.id.spinnerClass);
        spinnerSottoClass = (Spinner)dialogProduct.findViewById(R.id.spinnerSottoClass);
        spinnerMarche = (Spinner)dialogProduct.findViewById(R.id.spinnerMarche);
        spinnerPaginator = (Spinner)dialogProduct.findViewById(R.id.spinnerPaginator);

        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        listProDetails.setOnTouchListener(new View.OnTouchListener() {
//            // Setting on Touch Listener for handling the touch inside ScrollView
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                // Disallow the touch request for parent scroll on touch of child view
//                v.getParent().requestDisallowInterceptTouchEvent(true);
//                return false;
//            }
//        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDetailsList.clear();

                if(!TextUtils.isEmpty(subCatId)){
                    for(int i=0;i<productList.size();i++){
                        if(!TextUtils.isEmpty(catId)&& !TextUtils.isEmpty(catId)){
                            if(catId.equalsIgnoreCase(productList.get(i).getCATE_ID())&& subCatId.equalsIgnoreCase(productList.get(i).getSUBC_ID())){
                                productDetailsList.add(productList.get(i));
                                Log.e("productDetailsList size",""+productDetailsList.size());
                            }
                        }
                    }
                }else {
                    for(int i=0;i<productList.size();i++){
                        if(!TextUtils.isEmpty(catId)){
                            if(catId.equalsIgnoreCase(productList.get(i).getCATE_ID())){
                                productDetailsList.add(productList.get(i));
                                Log.e("productDetailsList size",""+productDetailsList.size());
                            }
                        }
                    }
                }


                if(productDetailsList.size()>0){
                    customAdapterProductDialogue = new CustomAdapterProductDialogue(con);
                    listProDetails.setAdapter(customAdapterProductDialogue);
                    listProDetails.setVisibility(View.VISIBLE);
                    customAdapterProductDialogue.notifyDataSetChanged();
                    UIUtils.setListViewHeightBasedOnItems(listProDetails);
                }else {
                    customAdapterProductDialogue = new CustomAdapterProductDialogue(con);
                    listProDetails.setAdapter(customAdapterProductDialogue);
                    customAdapterProductDialogue.notifyDataSetChanged();
                    listProDetails.setVisibility(View.GONE);
                    UIUtils.setListViewHeightBasedOnItems(listProDetails);
                }

            }


        });



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaginator.setAdapter(adapter);
        spinnerPaginator.setSelection(2);
        // dataLimit = Integer.parseInt(spinnerPaginator.getSelectedItem().toString().trim());
        spinnerPaginator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                dataLimit = Integer.parseInt(spinnerPaginator.getSelectedItem().toString().trim());
//
//                if(dataLimit>productDetailsList.size()){
//                    dataLimit = productDetailsList.size();
//                    customAdapterProduct = new CustomAdapterProduct(con);
//                    listProDetails.setAdapter(customAdapterProduct);
//                    customAdapterProduct.notifyDataSetChanged();
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        //spinnerClass.setPrompt("Select Classe");
        //spinnerSottoClass.setPrompt("Select Sotto classe");
        spinnerMarche.setPrompt("Select marche");
//            spinnerClass.setOnItemSelectedListener(this);
//            spinnerSottoClass.setOnItemSelectedListener(this);

        CustomAdapter customAdapter=new CustomAdapter(con,cateGoryInfoList);
        spinnerClass.setAdapter(customAdapter);
        for(int i =0;i<cateGoryInfoList.size();i++){
            if(cateGoryInfoList.get(i).getCATE_DESCRIPTION().equalsIgnoreCase("Select class")){
                spinnerClass.setSelection(i);
            }
        }

//        for(int i =0;i<subCateGoryInfoList.size();i++){
//            if(subCateGoryInfoList.get(i).getSUBC_DESCRIPTION().equalsIgnoreCase("Select sotto class")){
//                spinnerSottoClass.setSelection(i);
//            }
//        }

        CustomAdapterBrands customAdapterBrands=new CustomAdapterBrands(con,barnadsList);
        spinnerMarche.setAdapter(customAdapterBrands);


        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                subCateGoryInfoSpinnerList.clear();
                catId = cateGoryInfoList.get(i).getCATE_ID();

                for(int s = 0; s<subCateGoryInfoList.size();s++){
                    if (!cateGoryInfoList.get(i).getCATE_DESCRIPTION().equalsIgnoreCase("Select class")){
                        if(cateGoryInfoList.get(i).getCATE_ID().equalsIgnoreCase(subCateGoryInfoList.get(s).getCATE_ID())) {
                            //Log.e("click","click");
                            //subDes.add(subCateGoryInfoList.get(s).getSUBC_DESCRIPTION());
                            subCateGoryInfoSpinnerList.add(subCateGoryInfoList.get(s));
                            Log.e("subSpinnerList size",""+subCateGoryInfoSpinnerList.size());
                        }
                    }


                }

                if(subCateGoryInfoSpinnerList.size()>0){
                    customAdapterSubCat = new CustomAdapterSubCat(con,subCateGoryInfoSpinnerList,subDes);
                    spinnerSottoClass.setAdapter(customAdapterSubCat);
                    customAdapterSubCat.notifyDataSetChanged();
                    //subDes.clear();
                }else {
                    customAdapterSubCat = new CustomAdapterSubCat(con,subCateGoryInfoSpinnerList,subDes);
                    spinnerSottoClass.setAdapter(customAdapterSubCat);
                    customAdapterSubCat.notifyDataSetChanged();
                }

//                SubCatTableInfo subInf = new SubCatTableInfo();
//                subInf.setSUBC_DESCRIPTION("Select sotto class");
//                subCateGoryInfoList.add(subInf);


// else {
//                    if(subCateGoryInfoSpinnerList.size()>0){
//                        customAdapterSubCat = new CustomAdapterSubCat(con,subCateGoryInfoSpinnerList,subDes);
//                        spinnerSottoClass.setAdapter(customAdapterSubCat);
//                        customAdapterSubCat.notifyDataSetChanged();
//                        //subDes.clear();
//                    }
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerSottoClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                subCatId = subCateGoryInfoSpinnerList.get(i).getSUBC_ID();
                Log.e("subCatId",""+subCatId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        dialogProduct.show();

    }


    private class CustomAdapterProductDialogue extends ArrayAdapter<ProductTableInfo> {
        Context context;

        CustomAdapterProductDialogue(Context context) {
            super(context, R.layout.raw_product_details, productDetailsList);

            this.context = context;

        }



        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_product_details, null);

            }

            if (position < productDetailsList.size()) {

                final ProductTableInfo query = productDetailsList.get(position);

                final TextView tvCode = (TextView) v.findViewById(R.id.tvCode);
                final TextView tvProdotti = (TextView) v.findViewById(R.id.tvProdotti);
                final TextView tvClasse = (TextView) v.findViewById(R.id.tvClasse);
                final TextView tvMarche = (TextView) v.findViewById(R.id.tvMarche);
                final ImageView imgVisibility = (ImageView) v.findViewById(R.id.imgVisibility);

                tvCode.setText(query.getPROD_CODE());
                tvProdotti.setText(query.getPROD_DESCRIPTION());
                tvClasse.setText(query.getCATE_ID());
                tvMarche.setText(query.getBRAN_ID());

                Log.e("imageUrl", ""+query.getPROD_IMAGE());
                imgVisibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //AppConstant.productTableInfo = query;
                        proInfoAddList.set(proAddPos,query);
                        customAdapterProduct.notifyDataSetChanged();
                        dialogProduct.dismiss();
                        etProNameSearch.setVisibility(View.GONE);
                        etProName.setVisibility(View.VISIBLE);
                        etQuantity.requestFocus();

                    }
                });



            }

            return v;

        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public  void hideKeyboard(Activity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }


    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private void dialogueProductMenu(String[] items){
       // final String[] items = {"AAAAAA", "BBBBBBB", "CCCCCCC", "DDDDDDDD"};


        final TextView textView = (TextView)findViewById(R.id.tvtext1);

        AlertDialog.Builder builder = new AlertDialog.Builder(con);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               // textView.setText();
            }
        });

        // Creating alert dialog

        AlertDialog alert = builder.create();



        //Showing alert dialog

        alert.show();
    }

    private int getListViewHeight(ListView list,int count) {
        ListAdapter adapter = list.getAdapter();

        int listviewHeight = 0;

        list.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        listviewHeight = list.getMeasuredHeight() * count + (adapter.getCount() * list.getDividerHeight());

        return listviewHeight;
    }


    private void emptyEditField(){
        etAegentCode.setText("");
        etClientCode.setText("");
        clientFirstName.setText("");
        etClientLastName.setText("");
        etAddress.setText("");
        etZip.setText("");
        etCity.setText("");
        etEmail.setText("");
        etProvince.setText("");
        etSconti.setText("");
        etNazion.setText("");
        listPayment.clear();
        listVat.clear();
        ArrayAdapter<String> adpVat=new ArrayAdapter<String>(con,
                android.R.layout.simple_list_item_1,listVat);
        adpVat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerVat.setAdapter(adpVat);

        ArrayAdapter<String> adpPaym=new ArrayAdapter<String>(con,
                android.R.layout.simple_list_item_1,listPayment);
        adpPaym.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPayment.setAdapter(adpPaym);

    }


}
