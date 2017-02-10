package com.salage;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.Utils.AAPBDHttpClient;
import com.salage.Utils.AlertMessage;
import com.salage.Utils.NetInfo;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ClientDialogFragment extends DialogFragment {

    private Context con;
    private View view;
    private ImageView dissmissCatListBtn;
    private WebView webDjela;
    private EditText etCodeClient,etAgentFirstName,etAgentLastName,etAddress,etCap,etCity,
            etProvince,etNazion,etNtel,etFax,etMobile,etEmail,etCodeFiscal,etPrtitaIVA,etIbn,
            etSconti;
    private Spinner spinnerState,spinnerPagemanto,spinnerListano,spinnerIva;

    private Button btnSave;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.client_edit, container, true);
        con = getActivity();
        initUi();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initUi() {
        dissmissCatListBtn = (ImageView) view.findViewById(R.id.dissmissCatListBtn);
        etCodeClient = (EditText) view.findViewById(R.id.etCodeClient);
        etAgentFirstName = (EditText) view.findViewById(R.id.etAgentFirstName);
        etAgentLastName = (EditText) view.findViewById(R.id.etAgentLastName);
        etAddress = (EditText) view.findViewById(R.id.etAddress);
        etCap = (EditText) view.findViewById(R.id.etCap);
        etCity = (EditText) view.findViewById(R.id.etCity);
        etProvince = (EditText) view.findViewById(R.id.etProvince);
        etNazion = (EditText) view.findViewById(R.id.etNazion);
        etNtel = (EditText) view.findViewById(R.id.etNtel);
        etFax = (EditText) view.findViewById(R.id.etFax);
        etMobile = (EditText) view.findViewById(R.id.etMobile);
        etEmail = (EditText) view.findViewById(R.id.etEmail);
        etCodeFiscal = (EditText) view.findViewById(R.id.etCodeFiscal);
        etPrtitaIVA = (EditText) view.findViewById(R.id.etPrtitaIVA);
        etIbn = (EditText) view.findViewById(R.id.etIbn);
        etSconti = (EditText) view.findViewById(R.id.etSconti);

        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

    }

    private void requestOrderHistory(final String url) {
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, "Alert!","Please check Internet!");
            return;
        }

//        final BusyDialog busyNow = new BusyDialog(con, true,false);
//        busyNow.show();
        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {
            String response="";
            @Override
            public void run() {

                Map<String,String> param =new HashMap();
//
//                param.put("api_token", PersistData.getStringData(con, AppConstant.TOKEN));
//                param.put("type","all");
//                param.put("Time","");

                try {

                    response= AAPBDHttpClient.get(url).body();
                } catch (Exception e) {

                    Log.e("MYAPP", "exception", e);
//                    if(busyNow!=null){
//                        busyNow.dismis();
//                    }
                }

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

//                        if (busyNow != null) {
//                            busyNow.dismis();
//                        }
                        try {
                            Log.e("Response", ">>" + new String(response));


                            if (!TextUtils.isEmpty(new String(response))) {

//                                Gson gson = new Gson();
//                                NewsInfo details = gson.fromJson(new String(response), NewsInfo.class);
//                                if(details!=null){
//                                    if (!TextUtils.isEmpty(details.getFeatured_image())){
//                                        imgDefault.setVisibility(View.GONE);
//                                        Picasso.with(getActivity()).load(details.getFeatured_image()).error(R.drawable.icturo).into(imgDetail);
//                                    }else {
//                                        imgDefault.setVisibility(View.VISIBLE);
//                                    }
//
//                                    tvTitle.setText(Html.fromHtml(details.getTitle()).toString());
//                                    tvDetails.setText(Html.fromHtml(details.getContent()).toString());
//                                    tvDate.setText(Html.fromHtml(details.getCreate_date()).toString());
//                                }

                            }

                        } catch (final Exception e) {

                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }
}
