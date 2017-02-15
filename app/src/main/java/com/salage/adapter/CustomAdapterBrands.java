package com.salage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.model.BrandsTableInfo;
import com.salage.model.CateGoryInfo;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapterBrands extends BaseAdapter implements AdapterView.OnItemSelectedListener{
    Context context;
    int flags[];
    List <BrandsTableInfo> lisCat = new ArrayList<>();
    LayoutInflater inflter;
    private RelativeLayout relView;
    private List<String> subDes = new ArrayList<String>();
    public CustomAdapterBrands(Context applicationContext, List<BrandsTableInfo> lisCat) {
        this.context = applicationContext;
        this.lisCat = lisCat;
        inflter = (LayoutInflater.from(applicationContext));
    }

//    public CustomAdapter(Context applicationContext, List<String> subDes ) {
//        this.context = applicationContext;
//        this.flags = flags;
//        this.subDes = subDes;
//        inflter = (LayoutInflater.from(applicationContext));
//    }
    @Override
    public int getCount() {
        return lisCat.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        relView  = (RelativeLayout) view.findViewById(R.id.relView);
        TextView tvCtName = (TextView)view.findViewById(R.id.tvCtName);

        tvCtName.setText(lisCat.get(i).getBRAN_DESCRIPTION());
       // icon.setImageResource(flags[i]);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        relView.setBackgroundColor(Color.MAGENTA);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
