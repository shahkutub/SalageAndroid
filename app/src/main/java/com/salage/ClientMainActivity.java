package com.salage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/17/2017.
 */

public class ClientMainActivity extends AppCompatActivity {
    Context context;

    private TextView tvSubmit,tvTitleContact;
    private ImageView dissmissCatListBtn;
    private String name,email,message;
    private List<CustomerTableInfo> customerTableInfo  = new ArrayList<>();
    private List<CustomerTableInfo> customerTableInfoList  = new ArrayList<>();
    private ListView listClient;
    private CustomAdapterProduct customAdapterProduct;
    DatabaseHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.client_main);
        context=this;

        db = new DatabaseHelper(this);
        customerTableInfo = db.getAllCustomer();
        customerTableInfoList.clear();
        for(int i=0;i<customerTableInfo.size();i++){
            if(customerTableInfo.get(i).getIS_DELETED().equalsIgnoreCase("0")){
                customerTableInfoList.add(customerTableInfo.get(i));
            }
        }

        listClient = (ListView)findViewById(R.id.listClient);
        customAdapterProduct = new CustomAdapterProduct(context);
        listClient.setAdapter(customAdapterProduct);
        customAdapterProduct.notifyDataSetChanged();

        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
                        AppConstant.customerTableInfo=query;
                    startActivity(new Intent(context,ClientDetailsActivity.class));
                    }
                });



            }

            return v;

        }

    }


}
