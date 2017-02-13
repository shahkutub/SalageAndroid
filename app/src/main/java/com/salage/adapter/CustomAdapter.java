package com.salage.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.model.CateGoryInfo;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter implements AdapterView.OnItemSelectedListener{
    Context context;
    int flags[];
    List <CateGoryInfo> lisCat = new ArrayList<>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<CateGoryInfo> lisCat ) {
        this.context = applicationContext;
        this.flags = flags;
        this.lisCat = lisCat;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
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
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView tvCtName = (TextView)view.findViewById(R.id.tvCtName);
        tvCtName.setText(lisCat.get(i).getCATE_DESCRIPTION());
       // icon.setImageResource(flags[i]);
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
