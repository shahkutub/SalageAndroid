package com.salage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;

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
    private Spinner spinnerState,spinnerPagemanto,spinnerListano,spinnerIva;

    private Button btnSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.client_edit);
        con=this;
        intUi();
    }

    private void intUi() {
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

        if(AppConstant.customerTableInfo!= null){
            etCodeClient.setText(AppConstant.customerTableInfo.getCUST_CODE());
            etAgentFirstName.setText(AppConstant.customerTableInfo.getCUST_NAME1());
            etAgentLastName.setText(AppConstant.customerTableInfo.getCUST_NAME2());
            etAddress.setText(AppConstant.customerTableInfo.getCUST_ADDRESS());
            //etCap.setText(AppConstant.customerTableInfo.getCUST_CODE());
            etCity.setText(AppConstant.customerTableInfo.getCUST_CITY());
            etProvince.setText(AppConstant.customerTableInfo.getCUST_PROVINCE());
            etNazion.setText(AppConstant.customerTableInfo.getCUST_COUNTRY());

            etNtel.setText(AppConstant.customerTableInfo.getCUST_TEL());
            etFax.setText(AppConstant.customerTableInfo.getCUST_FAX());
            etMobile.setText(AppConstant.customerTableInfo.getCUST_MOBILE());
            etEmail.setText(AppConstant.customerTableInfo.getCUST_MAIL());
            etCodeFiscal.setText(AppConstant.customerTableInfo.getCUST_CF());
            // etPrtitaIVA.setText(AppConstant.customerTableInfo.getCUST_CODE());
            etIbn.setText(AppConstant.customerTableInfo.getCUST_IBAN());
            etSconti.setText(AppConstant.customerTableInfo.getCUST_ZIP());
        }


//        etCodeClient.setText(AppConstant.customerTableInfo.getCUST_CODE());
//        etCodeClient.setText(AppConstant.customerTableInfo.getCUST_CODE());
//        etCodeClient.setText(AppConstant.customerTableInfo.getCUST_CODE());
//        etCodeClient.setText(AppConstant.customerTableInfo.getCUST_CODE());

        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
