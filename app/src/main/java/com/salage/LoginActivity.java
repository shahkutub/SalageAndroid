package com.salage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.icteuro.salage.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.salage.Utils.AAPBDHttpClient;
import com.salage.Utils.AlertMessage;
import com.salage.Utils.AppConstant;
import com.salage.Utils.BusyDialog;
import com.salage.Utils.NetInfo;
import com.salage.Utils.PersistData;
import com.salage.Utils.PersistentUser;
import com.salage.model.LoginResponse;
import com.salage.model.UpojelaOfficerResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

import cz.msebera.android.httpclient.Header;

public class LoginActivity extends AppCompatActivity {



    Context context;
    private Button btnSave;
    private ImageView imgLoginBack;
    private EditText etCode,etAgent,etPassword,etUrl;
    private String code,agent,password,url;
    private LoginResponse loginResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.log_in_form);
        context=this;
        initUI();

    }

    private void initUI() {
        imgLoginBack = (ImageView)findViewById(R.id.imgLoginBack);
        imgLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave = (Button)findViewById(R.id.btnSave);
        etCode = (EditText) findViewById(R.id.etCode);
        etAgent = (EditText) findViewById(R.id.etAgent);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etUrl = (EditText) findViewById(R.id.etUrl);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(etCode.getText().toString())){
                    AlertMessage.showMessage(context,"Alert","Enter codice!");
                }else if(TextUtils.isEmpty(etAgent.getText().toString())){
                    AlertMessage.showMessage(context,"Alert","Enter agenti!");
                }else if(TextUtils.isEmpty(etPassword.getText().toString())){
                    AlertMessage.showMessage(context,"Alert","Enter password!");
                }else if(TextUtils.isEmpty(etUrl.getText().toString())){
                    AlertMessage.showMessage(context,"Alert","Enter url!");
                }else {
                    code = etCode.getText().toString();
                    agent = etAgent.getText().toString();
                    password = etPassword.getText().toString();
                    url = etUrl.getText().toString();
                    loginFromServer(url+"agentsync/index");

                }

            }
        });
    }


    protected void loginFromServer(final String url) {

        if (!NetInfo.isOnline(context)) {
            AlertMessage.showMessage(context, "Alert",
                    "NO internet");
            return;
        }

        final BusyDialog busyNow = new BusyDialog(context, true,false);
        busyNow.show();

        final AsyncHttpClient client = new AsyncHttpClient();


        final RequestParams param = new RequestParams();

        try {

            param.put("AGEN_CODE",agent);
            param.put("AGEN_PASSWORD",password);

        } catch (final Exception e1) {
            e1.printStackTrace();
        }

        client.post(url, param, new AsyncHttpResponseHandler() {

            @Override
            public void onStart() {
                // called before request is started
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                if (busyNow != null) {
                    busyNow.dismis();
                }
                try {
                    Log.e("Response", ">>" + new String(response));


                    if (!TextUtils.isEmpty(new String(response))) {
                        Gson g = new Gson();
                        loginResponse = g.fromJson(new String(response), LoginResponse.class);
                        if (loginResponse.getResponse().equalsIgnoreCase("1")) {
                            PersistentUser.setLogin(context);
                            PersistData.setStringData(context,AppConstant.agentCode,agent);

                            PersistData.setStringData(context,AppConstant.AGEN_CANADDCOMMENTS,loginResponse.getData().getAGEN_CANADDCOMMENTS());
                            PersistData.setStringData(context,AppConstant.AGEN_CANADDCUST,loginResponse.getData().getAGEN_CANADDCUST());
                            PersistData.setStringData(context,AppConstant.AGEN_CANADDDEST,loginResponse.getData().getAGEN_CANADDDEST());
                            PersistData.setStringData(context,AppConstant.AGEN_CANCHANGEDESC,loginResponse.getData().getAGEN_CANCHANGEDESC());
                            PersistData.setStringData(context,AppConstant.AGEN_CANCHANGEPAYM,loginResponse.getData().getAGEN_CANCHANGEPAYM());
                            PersistData.setStringData(context,AppConstant.AGEN_CANCHANGEPRICE,loginResponse.getData().getAGEN_CANCHANGEPRICE());
                            PersistData.setStringData(context,AppConstant.AGEN_CANCHANGEVAT,loginResponse.getData().getAGEN_CANCHANGEVAT());

                            PersistData.setStringData(context,AppConstant.AGEN_ENABLED,loginResponse.getData().getAGEN_ENABLED());
                            PersistData.setStringData(context,AppConstant.AGEN_CANUSENOCODE,loginResponse.getData().getAGEN_CANUSENOCODE());
                            PersistData.setStringData(context,AppConstant.AGEN_TYPE,loginResponse.getData().getAGEN_TYPE());


                            finish();

                        }
                    }

                } catch (final Exception e) {

                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)


            }

            @Override
            public void onRetry(int retryNo) {
                // called when request is retried

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (mAdView != null) {
//            mAdView.resume();
//        }
    }

    @Override
    public void onDestroy() {
//        if (mAdView != null) {
//            mAdView.destroy();
//        }
        super.onDestroy();
    }

}
