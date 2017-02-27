package com.salage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.Utils.PersistData;
import com.salage.model.AgentTableInfo;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class DocumentDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Context con;
    private View view;
    private ImageView dissmissCatListBtn,imgProd;
    private Spinner spinnerClient,spinnerTpo;
    private DatabaseHelper db;
    private List<String> agentCodeList = new ArrayList<>();
    private EditText etDate,etAegentCode,etClientCode,clientFirstName,etClientLastName,etAddress,
            etZip,etCity,etEmail,etProvince,etSconti,etNazion;
    private List<CustomerTableInfo> customerTableInfoList  = new ArrayList<>();
    private String custName;
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
        spinnerClient.setOnItemSelectedListener(this);

        spinnerClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                custName=spinnerClient.getSelectedItem().toString().trim();
                Log.e("custname",custName);
                for(int i =0; i<customerTableInfoList.size();i++){
                    Log.e("custname for",customerTableInfoList.get(i).getAGEN_CODE());
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
