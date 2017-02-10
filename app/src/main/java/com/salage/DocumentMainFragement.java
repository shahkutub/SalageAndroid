package com.salage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.icteuro.salage.R;

/**
 * Created by User on 7/20/2016.
 */
public class DocumentMainFragement extends BaseFragment {

        Context con;

        private ImageView img_add;
        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState){
        return inflater.inflate(R.layout.document_main, container, false);
    }


        @Override
        public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        con = getActivity();


            img_add = (ImageView)getView().findViewById(R.id.img_add);
            img_add.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("NewApi")
                @Override
                public void onClick(View view) {
                    DocumentDialougeFragment motamotDialogFragment = new DocumentDialougeFragment();
                    motamotDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
                    motamotDialogFragment.show(getActivity().getFragmentManager(), "");
                }
            });

    }

}
