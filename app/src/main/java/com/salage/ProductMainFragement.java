package com.salage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.adapter.CustomAdapter;
import com.salage.model.CateGoryInfo;
import com.salage.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 7/20/2016.
 */
public class ProductMainFragement extends BaseFragment {

        Context con;
        private Spinner spinnerClass;
    private List<CateGoryInfo> cateGoryInfoList  = new ArrayList<>();
       DatabaseHelper db;
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
        return inflater.inflate(R.layout.product_main, container, false);
    }


        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        con = getActivity();

            db = new DatabaseHelper(con);
            cateGoryInfoList = db.getAllCategories();
            spinnerClass = (Spinner)getView().findViewById(R.id.spinnerClass);

            spinnerClass.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) con);

            CustomAdapter customAdapter=new CustomAdapter(con,cateGoryInfoList);
            spinnerClass.setAdapter(customAdapter);
    }

}
