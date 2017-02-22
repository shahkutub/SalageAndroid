package com.salage;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.adapter.CustomAdapter;
import com.salage.adapter.CustomAdapterBrands;
import com.salage.adapter.CustomAdapterSubCat;
import com.salage.model.BrandsTableInfo;
import com.salage.model.CateGoryInfo;
import com.salage.model.DatabaseHelper;
import com.salage.model.ProductTableInfo;
import com.salage.model.SubCatTableInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class ProductMainActivity extends AppCompatActivity {

    Context con;
    private Spinner spinnerClass,spinnerSottoClass,spinnerMarche,spinnerPaginator;
    private List<CateGoryInfo> cateGoryInfoList  = new ArrayList<>();
    private List<SubCatTableInfo> subCateGoryInfoList  = new ArrayList<>();
    private List<SubCatTableInfo> subCateGoryInfoSpinnerList  = new ArrayList<>();
    private List<BrandsTableInfo> barnadsList  = new ArrayList<>();
    private List<ProductTableInfo> productList  = new ArrayList<>();
    private List<ProductTableInfo> productDetailsList  = new ArrayList<>();
    private List<String> subDes = new ArrayList<String>();
    private ListView listProDetails;
    private TextView tvSearch;
    private boolean isSpinnerInitial = true;
    private CustomAdapterSubCat customAdapterSubCat;
    private CustomAdapterProduct customAdapterProduct;
    String  catId,subCatId;
    DatabaseHelper db;
    private ImageView dissmissCatListBtn;
    int dataLimit = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.product_main);
        con=this;


        db = new DatabaseHelper(con);
        cateGoryInfoList = db.getAllCategories();
        subCateGoryInfoList = db.getAllSubcategories();
        barnadsList = db.getAllBrands();
        productList = db.getAllProducts();

        subCateGoryInfoSpinnerList.add(new SubCatTableInfo("select"));

        listProDetails = (ListView)findViewById(R.id.listProDetails);
        tvSearch = (TextView)findViewById(R.id.tvSearch);
        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);

        spinnerClass = (Spinner)findViewById(R.id.spinnerClass);
        spinnerSottoClass = (Spinner)findViewById(R.id.spinnerSottoClass);
        spinnerMarche = (Spinner)findViewById(R.id.spinnerMarche);
        spinnerPaginator = (Spinner)findViewById(R.id.spinnerPaginator);

        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listProDetails.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productDetailsList.clear();

                if(!TextUtils.isEmpty(subCatId)){
                    for(int i=0;i<productList.size();i++){
                        if(catId.equalsIgnoreCase(productList.get(i).getCATE_ID())&& subCatId.equalsIgnoreCase(productList.get(i).getSUBC_ID())){
                            productDetailsList.add(productList.get(i));
                            Log.e("productDetailsList size",""+productDetailsList.size());
                        }


                    }
                }else {
                    for(int i=0;i<productList.size();i++){
                        if(catId.equalsIgnoreCase(productList.get(i).getCATE_ID())){
                            productDetailsList.add(productList.get(i));
                            Log.e("productDetailsList size",""+productDetailsList.size());
                        }
                    }
                }


                if(productDetailsList.size()>0){
                    customAdapterProduct = new CustomAdapterProduct(con);
                    listProDetails.setAdapter(customAdapterProduct);
                    listProDetails.setVisibility(View.VISIBLE);
                    customAdapterProduct.notifyDataSetChanged();
                }else {
                    customAdapterProduct = new CustomAdapterProduct(con);
                    listProDetails.setAdapter(customAdapterProduct);
                    customAdapterProduct.notifyDataSetChanged();
                    listProDetails.setVisibility(View.GONE);
                }

            }


        });



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.array_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPaginator.setAdapter(adapter);
        spinnerPaginator.setSelection(2);
       // dataLimit = Integer.parseInt(spinnerPaginator.getSelectedItem().toString().trim());
        spinnerPaginator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                dataLimit = Integer.parseInt(spinnerPaginator.getSelectedItem().toString().trim());
//
//                if(dataLimit>productDetailsList.size()){
//                    dataLimit = productDetailsList.size();
//                    customAdapterProduct = new CustomAdapterProduct(con);
//                    listProDetails.setAdapter(customAdapterProduct);
//                    customAdapterProduct.notifyDataSetChanged();
//                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        spinnerClass.setPrompt("Select Classe");
        spinnerSottoClass.setPrompt("Select Sotto classe");
        spinnerMarche.setPrompt("Select marche");
//            spinnerClass.setOnItemSelectedListener(this);
//            spinnerSottoClass.setOnItemSelectedListener(this);

        CustomAdapter customAdapter=new CustomAdapter(con,cateGoryInfoList);
        spinnerClass.setAdapter(customAdapter);

        CustomAdapterBrands customAdapterBrands=new CustomAdapterBrands(con,barnadsList);
        spinnerMarche.setAdapter(customAdapterBrands);


        spinnerClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                subCateGoryInfoSpinnerList.clear();
                catId = cateGoryInfoList.get(i).getCATE_ID();

                for(int s = 0; s<subCateGoryInfoList.size();s++){

                    if(cateGoryInfoList.get(i).getCATE_ID().equalsIgnoreCase(subCateGoryInfoList.get(s).getCATE_ID())) {
                        //Log.e("click","click");
                        //subDes.add(subCateGoryInfoList.get(s).getSUBC_DESCRIPTION());
                        subCateGoryInfoSpinnerList.add(subCateGoryInfoList.get(s));
                        Log.e("subSpinnerList size",""+subCateGoryInfoSpinnerList.size());
                    }
                }

                if(subCateGoryInfoSpinnerList.size()>0){
                    customAdapterSubCat = new CustomAdapterSubCat(con,subCateGoryInfoSpinnerList,subDes);
                    spinnerSottoClass.setAdapter(customAdapterSubCat);
                    customAdapterSubCat.notifyDataSetChanged();
                    //subDes.clear();
                }else {
                    customAdapterSubCat.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerSottoClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subCatId = subCateGoryInfoSpinnerList.get(i).getSUBC_ID();
                Log.e("subCatId",""+subCatId);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private class CustomAdapterProduct extends ArrayAdapter<ProductTableInfo> {
        Context context;

        CustomAdapterProduct(Context context) {
            super(context, R.layout.raw_product_details, productDetailsList);

            this.context = context;

        }



        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_product_details, null);

            }

            if (position < productDetailsList.size()) {

                final ProductTableInfo query = productDetailsList.get(position);

                final TextView tvCode = (TextView) v.findViewById(R.id.tvCode);
                final TextView tvProdotti = (TextView) v.findViewById(R.id.tvProdotti);
                final TextView tvClasse = (TextView) v.findViewById(R.id.tvClasse);
                final TextView tvMarche = (TextView) v.findViewById(R.id.tvMarche);
                final ImageView imgVisibility = (ImageView) v.findViewById(R.id.imgVisibility);

                tvCode.setText(query.getPROD_CODE());
                tvProdotti.setText(query.getPROD_DESCRIPTION());
                tvClasse.setText(query.getCATE_ID());
                tvMarche.setText(query.getBRAN_ID());

                imgVisibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppConstant.productTableInfo = query;
                        startActivity(new Intent(con, ProductDetailsActivity.class));
                    }
                });



            }

            return v;

        }

    }

}
