package com.salage;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.google.gson.Gson;
import com.icteuro.salage.R;
import com.salage.Utils.AAPBDHttpClient;
import com.salage.Utils.AllURL;
import com.salage.Utils.AppConstant;
import com.salage.Utils.BusyDialog;
import com.salage.model.UpojelaOfficerInfo;
import com.salage.model.UpojelaOfficerResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;


/**
 * Created by User on 7/20/2016.
 */
public class UpojelaNirbahiFragement extends BaseFragment {
    Context con;
    private UpojelaOfficerResponse mOrderResponse;
    private ListView listUjelaOfficer;
    HistoryAdapter historyAdapter;
    public static final String JSON_URL = "http://dhakadivision.com/api/getdata/upozila_nirbahi_officer";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.upojela_officer, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();


        listUjelaOfficer = (ListView)getView().findViewById(R.id.listUjelaOfficer);
        //getdata();
        //sendRequest();
        requestOrderHistory(AllURL.upjelaNirbahiDataUrl());
    }

    private void requestOrderHistory(final String url) {

        final BusyDialog busyNow = new BusyDialog(con, true,false);
        busyNow.show();
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
                    if(busyNow!=null){
                        busyNow.dismis();
                    }
                }

                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        if (busyNow != null) {
                            busyNow.dismis();
                        }
                        try {
                            Log.e("Response", ">>" + new String(response));


                            if (!TextUtils.isEmpty(new String(response))) {
                                Gson g = new Gson();
                                mOrderResponse = g.fromJson(new String(response), UpojelaOfficerResponse.class);
                                if (mOrderResponse.getMessage().equalsIgnoreCase("success")) {

                                    historyAdapter = new HistoryAdapter(con);
                                    listUjelaOfficer.setAdapter(historyAdapter);
                                    historyAdapter.notifyDataSetChanged();                         }
                           }

                        } catch (final Exception e) {

                            e.printStackTrace();
                        }

                    }
                });
            }
        });

    }


    private class HistoryAdapter extends ArrayAdapter<UpojelaOfficerInfo> {
        Context context;

        public HistoryAdapter(Context context) {
            super(context, R.layout.raw_upjela_nirbahi, mOrderResponse.getData());

            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_upjela_nirbahi, null);
            }

            if( position<mOrderResponse.getData().size()){

                final UpojelaOfficerInfo query = mOrderResponse.getData().get(position);

                final TextView tvDhaka = (TextView)v.findViewById(R.id.tvDhaka);
                final TextView bistarito = (TextView)v.findViewById(R.id.bistarito);

                tvDhaka.setText(query.getCadre_name().toString());

                bistarito.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onClick(View v) {
                        AppConstant.upojelaOfficerInfo = query;
                        CommonDialogFragment motamotDialogFragment = new CommonDialogFragment();
                        motamotDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Black_NoTitleBar);
                        motamotDialogFragment.show(getActivity().getFragmentManager(), "");
                    }
                });
            }


            return v;
        }
    }


}
