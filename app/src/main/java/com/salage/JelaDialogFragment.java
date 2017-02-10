package com.salage;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.icteuro.salage.R;


public class JelaDialogFragment extends DialogFragment {

    private Context con;
    private View view;
    private ImageView dissmissHelpBtn;
    private WebView webDjela;
    TextView tvDhakaJela;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.help_dialog, container, true);
        con = getActivity();
        initUi();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initUi() {

        webDjela = (WebView) view.findViewById(R.id.webDjela);
//        tvDhakaJela = (TextView)view.findViewById(R.id.tvDhakaJela);
//        tvDhakaJela.setMovementMethod(new ScrollingMovementMethod());


        dissmissHelpBtn = (ImageView) view.findViewById(R.id.dissmissHelpBtn);
        dissmissHelpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });

        //webDjela.loadUrl("file:///android_asset/dhakajela.html");
        webDjela.getSettings().setJavaScriptEnabled(true);
        webDjela.getSettings().setSupportZoom(true);
        //webDjela.loadUrl("https://drive.google.com/file/d/0B8S-BoszKyjPdVU3X0J5YW9iY1E/view");
        webDjela.loadUrl("http://dhakadivision.com/dhakadiv/");

    }
}
