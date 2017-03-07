package com.salage;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.icteuro.salage.R;
import com.salage.Utils.AlertMessage;
import com.salage.Utils.AppConstant;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;
import com.salage.model.PaymentTableInfo;
import com.salage.model.PriceListTableInfo;
import com.salage.model.ProductTableInfo;
import com.salage.model.VatTableInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/17/2017.
 */

public class ClientDetailsActivity extends AppCompatActivity {

    private Context con;
    private View view;
    private ImageView dissmissCatListBtn;
    private EditText etCodeClient,etAgentFirstName,etAgentLastName,etAddress,etCap,etCity,
            etProvince,etNazion,etNtel,etFax,etMobile,etEmail,etCodeFiscal,etPrtitaIVA,etIbn,
            etSconti;
    private Spinner spinnerState,spinnerPagemanto,spinnerListano,spinnerIvat;
    DatabaseHelper db;
    private Button btnSave;
    private String vatid,paymId,paymentName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.client_edit);
        con=this;
        db = new DatabaseHelper(this);
        Log.e("cust Size1",""+db.getAllCustomer().size());
        //db.getAllCustomer().size();
        intUi();
    }

    private void intUi() {
        btnSave = (Button)findViewById(R.id.btnSave);
        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        etCodeClient = (EditText) findViewById(R.id.etCodeClient);
        etAgentFirstName = (EditText) findViewById(R.id.etAgentFirstName);
        etAgentLastName = (EditText) findViewById(R.id.etAgentLastName);
        etAddress = (EditText)findViewById(R.id.etAddress);
        etCap = (EditText) findViewById(R.id.etCap);
        etCity = (EditText) findViewById(R.id.etCity);
        etProvince = (EditText) findViewById(R.id.etProvince);
        etNazion = (EditText) findViewById(R.id.etNazion);
        etNtel = (EditText)findViewById(R.id.etNtel);
        etFax = (EditText) findViewById(R.id.etFax);
        etMobile = (EditText) findViewById(R.id.etMobile);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etCodeFiscal = (EditText) findViewById(R.id.etCodeFiscal);
        etPrtitaIVA = (EditText) findViewById(R.id.etPrtitaIVA);
        etIbn = (EditText) findViewById(R.id.etIbn);
        etSconti = (EditText) findViewById(R.id.etSconti);
        spinnerPagemanto = (Spinner) findViewById(R.id.spinnerPagemanto);
        spinnerListano = (Spinner) findViewById(R.id.spinnerListano);
        spinnerIvat = (Spinner) findViewById(R.id.spinnerIvat);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(TextUtils.isEmpty(etCodeClient.getText().toString())){
                AlertMessage.showMessage(con,"Alert","Enter client code");
                etCodeClient.requestFocus();
            }else if(TextUtils.isEmpty(etAgentFirstName.getText().toString())){
                AlertMessage.showMessage(con,"Alert","Enter client first name");
                etAgentFirstName.requestFocus();
            }else if(TextUtils.isEmpty(etAddress.getText().toString())){
                AlertMessage.showMessage(con,"Alert","Enter client address");
                etAddress.requestFocus();
            }else if(TextUtils.isEmpty(etCity.getText().toString())){
                AlertMessage.showMessage(con,"Alert","Enter client City");
                etCity.requestFocus();
            }else if(TextUtils.isEmpty(etEmail.getText().toString())){
                AlertMessage.showMessage(con,"Alert","Enter client City");
                etEmail.requestFocus();
            }else {

                if(AppConstant.isCustEdit.equalsIgnoreCase("true")){

                    dialogueEdit();

                }else {
                    addDialogue();
                }

            }


            }
        });

        if(AppConstant.customerTableInfo!= null){
            etCodeClient.setText(AppConstant.customerTableInfo.getCUST_CODE());
            etAgentFirstName.setText(AppConstant.customerTableInfo.getCUST_NAME1());
            etAgentLastName.setText(AppConstant.customerTableInfo.getCUST_NAME2());
            etAddress.setText(AppConstant.customerTableInfo.getCUST_ADDRESS());
            etCap.setText(AppConstant.customerTableInfo.getCUST_ZIP());
            etCity.setText(AppConstant.customerTableInfo.getCUST_CITY());
            etProvince.setText(AppConstant.customerTableInfo.getCUST_PROVINCE());
            etNazion.setText(AppConstant.customerTableInfo.getCUST_COUNTRY());

            etNtel.setText(AppConstant.customerTableInfo.getCUST_TEL());
            etFax.setText(AppConstant.customerTableInfo.getCUST_FAX());
            etMobile.setText(AppConstant.customerTableInfo.getCUST_MOBILE());
            etEmail.setText(AppConstant.customerTableInfo.getCUST_MAIL());
            etCodeFiscal.setText(AppConstant.customerTableInfo.getCUST_CF());
             etPrtitaIVA.setText(AppConstant.customerTableInfo.getCUST_VATNUM());
            etIbn.setText(AppConstant.customerTableInfo.getCUST_IBAN());
            etSconti.setText(AppConstant.customerTableInfo.getCUST_DISCOUNT());
            paymId = AppConstant.customerTableInfo.getPAYM_ID();
            vatid = AppConstant.customerTableInfo.getVATT_ID();
        }

        List <String> listPaymentSpinner = new ArrayList<>();
        listPaymentSpinner.add("Select payment");

        List<PaymentTableInfo> listPayment = new ArrayList<>();
        listPayment = db.getPaymentList();

        for(int i = 0;i<listPayment.size();i++){
            if(!TextUtils.isEmpty(paymId)){
                if(paymId.equalsIgnoreCase(listPayment.get(i).getPAYM_ID())){
                    listPaymentSpinner.add(listPayment.get(i).getPAYM_DESCRIPTION());
                }
            }
        }
        ArrayAdapter<String> adpPaym=new ArrayAdapter<String>(con,
                android.R.layout.simple_list_item_1,listPaymentSpinner);
        adpPaym.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPagemanto.setAdapter(adpPaym);
        spinnerPagemanto.setSelection(0);
        spinnerPagemanto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!spinnerPagemanto.getSelectedItem().toString().trim().equalsIgnoreCase("Select payment")){
                    paymentName = spinnerPagemanto.getSelectedItem().toString().trim();
                    Log.e("paymentName",paymentName);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
        adpprice.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerListano.setAdapter(adpprice);
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
        spinnerIvat.setAdapter(adpVat);

        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void dialogueEdit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure want to update this data?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                db.updateCustomer(new CustomerTableInfo(etCodeClient.getText().toString(),
                        etAgentFirstName.getText().toString(),etAgentLastName.getText().toString(),
                        etAddress.getText().toString(),etCap.getText().toString(),
                        etCity.getText().toString(),etProvince.getText().toString(),
                        etNazion.getText().toString(),etNtel.getText().toString(),
                        etFax.getText().toString(),etMobile.getText().toString(),
                        etEmail.getText().toString(),etCodeFiscal.getText().toString(),
                        etPrtitaIVA.getText().toString(),etIbn.getText().toString(),
                        "","",
                        "","",
                        etSconti.getText().toString(),"",
                        "","0"));
                AppConstant.isCustEdit = "false";
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    private void addDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure want to add this data?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                db.addCustomer(new CustomerTableInfo(etCodeClient.getText().toString(),
                        etAgentFirstName.getText().toString(),etAgentLastName.getText().toString(),
                        etAddress.getText().toString(),etCap.getText().toString(),
                        etCity.getText().toString(),etProvince.getText().toString(),
                        etNazion.getText().toString(),etNtel.getText().toString(),
                        etFax.getText().toString(),etMobile.getText().toString(),
                        etEmail.getText().toString(),etCodeFiscal.getText().toString(),
                        etPrtitaIVA.getText().toString(),etIbn.getText().toString(),
                        "","",
                        "","",
                        etSconti.getText().toString(),"",
                        "","0"));
                Log.e("cust Size2",""+db.getAllCustomer().size());
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
