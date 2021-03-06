package com.salage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.icteuro.salage.R;
import com.salage.Utils.AAPBDHttpClient;
import com.salage.Utils.AlertMessage;
import com.salage.Utils.AppConstant;
import com.salage.Utils.BusyDialog;
import com.salage.Utils.NetInfo;
import com.salage.model.AgentInfo;
import com.salage.model.AgentTableInfo;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;
import com.salage.model.ProductTableInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by User on 7/20/2016.
 */
public class ClientFragement extends BaseFragment {
    Context con;
    private TextView tvSubmit,tvTitleContact;

    private String name,email,message;
    private List<CustomerTableInfo> customerTableInfo  = new ArrayList<>();
    private List<CustomerTableInfo> customerTableInfoList  = new ArrayList<>();
    private ListView listClient;
    private CustomAdapterProduct customAdapterProduct;
    public static ClientFragement instanse;
    DatabaseHelper db;
    public static ClientFragement getInstanse(){
        return instanse;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.client_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();
        db = new DatabaseHelper(con);
        customerTableInfo = db.getAllCustomer();
        customerTableInfoList.clear();
       for(int i=0;i<customerTableInfo.size();i++){
           if(customerTableInfo.get(i).getIS_DELETED().equalsIgnoreCase("0")){
               customerTableInfoList.add(customerTableInfo.get(i));
           }
       }

        listClient = (ListView)getView().findViewById(R.id.listClient);
        customAdapterProduct = new CustomAdapterProduct(con);
        listClient.setAdapter(customAdapterProduct);
        customAdapterProduct.notifyDataSetChanged();




    }

    private class CustomAdapterProduct extends ArrayAdapter<CustomerTableInfo> {
        Context context;

        CustomAdapterProduct(Context context) {
            super(context, R.layout.raw_client, customerTableInfoList);

            this.context = context;

        }

        @SuppressLint("NewApi")

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_client, null);

            }

            if (position < customerTableInfoList.size()) {

                final CustomerTableInfo query = customerTableInfoList.get(position);

                final TextView tvAgentId = (TextView) v.findViewById(R.id.tvAgentId);
                final TextView tvCustomer = (TextView) v.findViewById(R.id.tvCustomer);
                final TextView tvAddress = (TextView) v.findViewById(R.id.tvAddress);

                final ImageView imgEdit = (ImageView) v.findViewById(R.id.imgEdit);
                if(!TextUtils.isEmpty(query.getCUST_CODE())){
                    tvAgentId.setText(query.getCUST_CODE().toString());
                }

                if(!TextUtils.isEmpty(query.getCUST_NAME1())){
                    tvCustomer.setText(query.getCUST_NAME1().toString());
                }

                if(!TextUtils.isEmpty(query.getCUST_ADDRESS())){
                    tvAddress.setText(query.getCUST_ADDRESS().toString());
                }
//                Log.e("CUST_CODE()",""+query.getCUST_CODE());
//                Log.e("CUST_ADDRESS()",""+query.getCUST_ADDRESS());



                imgEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClientDialogFragment motamotDialogFragment = new ClientDialogFragment();
                        motamotDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
                        motamotDialogFragment.show(getActivity().getFragmentManager(), "");
                    }
                });



            }

            return v;

        }

    }

    @Override
    public void onResume() {
        super.onResume();
        db = new DatabaseHelper(con);
        customerTableInfo = db.getAllCustomer();
        customerTableInfoList.clear();
        for(int i=0;i<customerTableInfo.size();i++){
            if(customerTableInfo.get(i).getIS_DELETED().equalsIgnoreCase("0")){
                customerTableInfoList.add(customerTableInfo.get(i));
            }
        }

        //listClient = (ListView)getView().findViewById(R.id.listClient);
        customAdapterProduct = new CustomAdapterProduct(con);
        listClient.setAdapter(customAdapterProduct);
        customAdapterProduct.notifyDataSetChanged();

        Log.e("callonresue","callonresue");

    }
}
