package com.salage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;

/**
 * Created by User on 7/20/2016.
 */
public class LoginCofigFragement extends BaseFragment {

        Context con;

        Button btnSave;
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
        return inflater.inflate(R.layout.log_in_form, container, false);
    }


        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        con = getActivity();

            btnSave = (Button)getView().findViewById(R.id.btnSave);

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // AppConstant.visibility = "true";
//                    Toast.makeText(getActivity(),"clicked",Toast.LENGTH_SHORT).show();
                    //openMenuOnBackPress();
                }
            });

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
