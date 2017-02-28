package com.salage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.Utils.PersistData;
import com.salage.model.AgentTableInfo;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;
import com.salage.model.PaymentTableInfo;
import com.salage.model.PriceListTableInfo;
import com.salage.model.ProductTableInfo;
import com.salage.model.VatTableInfo;

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
            etZip,etCity,etEmail,etProvince,etSconti,etNazion;
    private List<CustomerTableInfo> customerTableInfoList  = new ArrayList<>();
    private List<ProductTableInfo> proInfoAddList  = new ArrayList<>();
    private String custName,vatid,paymId;
    private ListView listProduct;
    private CustomAdapterProduct customAdapterProduct;
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
        customerTableInfoList = db.getAllCustomer();
        Log.e("customerList size",""+customerTableInfoList.size());


        imgAddPro = (ImageView) findViewById(R.id.imgAddPro);
        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spinnerClient = (Spinner)findViewById(R.id.spinnerClient);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, agentCodeList);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClient.setAdapter(adp);

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

        spinnerVat = (Spinner)findViewById(R.id.spinnerVat);
        spinnerPayment = (Spinner)findViewById(R.id.spinnerPayment);

        Date d = new Date();
        CharSequence s  = DateFormat.format("dd/MM/yyyy", d.getTime());
        etDate.setText(s);

        spinnerTpo = (Spinner)findViewById(R.id.spinnerTpo);
        List <String> listTpo = new ArrayList<>();
        listTpo.add("Select document Type");
        listTpo.add("PREVENTOVO");
        listTpo.add("ORDINE");

        ArrayAdapter<String> adp1=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listTpo);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTpo.setAdapter(adp1);


        spinnerClient = (Spinner)findViewById(R.id.spinnerClient);
        List <String> listAgent = new ArrayList<>();
        listAgent.add("Select Agent");
        listAgent.add("nuovo cliente");
        listAgent.add(PersistData.getStringData(con, AppConstant.agentCode));

        ArrayAdapter<String> adpAgent=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,listAgent);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClient.setAdapter(adpAgent);

        spinnerClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
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


                List <String> listVat = new ArrayList<>();
                listVat.add("Select IVA spacial");

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


                List <String> listPayment = new ArrayList<>();
                listPayment.add("Select payment");

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
                adpVat.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPayment.setAdapter(adpPaym);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerPriceList = (Spinner)findViewById(R.id.spinnerPriceList);
        List <String> listPrice = new ArrayList<>();
        listPrice.add("Select price");
        List <PriceListTableInfo> listPriceTable = new ArrayList<>();
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
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPriceList.setAdapter(adpprice);

        listProduct = (ListView)findViewById(R.id.listProduct);
        listProduct.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        ProductTableInfo pinf = new ProductTableInfo();
        proInfoAddList.add(pinf);

        customAdapterProduct = new CustomAdapterProduct(con);
        listProduct.setAdapter(customAdapterProduct);
        customAdapterProduct.notifyDataSetChanged();

        imgAddPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductTableInfo pinf = new ProductTableInfo();
                proInfoAddList.add(pinf);
                customAdapterProduct = new CustomAdapterProduct(con);
                listProduct.setAdapter(customAdapterProduct);
                customAdapterProduct.notifyDataSetChanged();
            }
        });

    }


    private class CustomAdapterProduct extends ArrayAdapter<ProductTableInfo> {
        Context context;

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
                final EditText etProName = (EditText) v.findViewById(R.id.etProName);
                final EditText etDescript = (EditText) v.findViewById(R.id.etDescript);
                final EditText etQuantity = (EditText) v.findViewById(R.id.etQuantity);
                final EditText etLista = (EditText) v.findViewById(R.id.etLista);
                final EditText etScont = (EditText) v.findViewById(R.id.etScont);


                final ImageView imgMinus = (ImageView) v.findViewById(R.id.imgMinus);


                imgMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                   proInfoAddList.remove(position);
                   customAdapterProduct.notifyDataSetChanged();
                    }
                });



            }

            return v;

        }

    }

}
