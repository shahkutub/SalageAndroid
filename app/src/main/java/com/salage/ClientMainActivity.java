package com.salage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
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
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/17/2017.
 */

public class ClientMainActivity extends AppCompatActivity {
    Context context;

    private LinearLayout linPlus;
    private TextView tvSubmit,tvTitleContact;
    private ImageView dissmissCatListBtn,img_add;
    private String name,email,message;
    private List<CustomerTableInfo> customerTableInfo  = new ArrayList<>();
    private List<CustomerTableInfo> customerTableInfoList  = new ArrayList<>();
    private ListView listClient;
    private CustomAdapterProduct customAdapterProduct;
    DatabaseHelper db;
    private Spinner spinnerShowClient;
    int dataLimit = 0;
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
        listClient = (ListView)findViewById(R.id.listClient);
        for(int i=0;i<customerTableInfo.size();i++){
            if(customerTableInfo.get(i).getIS_DELETED().equalsIgnoreCase("0")){
                customerTableInfoList.add(customerTableInfo.get(i));
                customAdapterProduct = new CustomAdapterProduct(context);
                listClient.setAdapter(customAdapterProduct);
                customAdapterProduct.notifyDataSetChanged();
            }
        }

        spinnerShowClient = (Spinner)findViewById(R.id.spinnerShowClient);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerShowClient.setAdapter(adapter);
        spinnerShowClient.setSelection(2);
        dataLimit = Integer.parseInt(spinnerShowClient.getSelectedItem().toString().trim());

        spinnerShowClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                dataLimit = Integer.parseInt(spinnerShowClient.getSelectedItem().toString().trim());

                if(dataLimit>customerTableInfoList.size()){
                    dataLimit = customerTableInfoList.size();
                    customAdapterProduct = new CustomAdapterProduct(context);
                    listClient.setAdapter(customAdapterProduct);
                    customAdapterProduct.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        img_add = (ImageView) findViewById(R.id.img_add);
        linPlus = (LinearLayout) findViewById(R.id.linPlus);
        linPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppConstant.customerTableInfo=null;
                startActivity(new Intent(context,ClientDetailsActivity.class));
            }
        });

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


        @Override
        public int getCount() {
            return dataLimit;
        }

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
                        AppConstant.customerColumId = query.getId();
                        Log.e("customerColumId",""+AppConstant.customerColumId);
                        AppConstant.isCustEdit = "true";
                    startActivity(new Intent(context,ClientDetailsActivity.class));
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
}
