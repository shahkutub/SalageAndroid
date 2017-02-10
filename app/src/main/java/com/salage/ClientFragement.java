package com.salage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.icteuro.salage.R;
import com.salage.Utils.AAPBDHttpClient;
import com.salage.Utils.AlertMessage;
import com.salage.Utils.BusyDialog;
import com.salage.Utils.NetInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * Created by User on 7/20/2016.
 */
public class ClientFragement extends BaseFragment {
    Context con;
    private TextView tvSubmit,tvTitleContact;
    private EditText etName,etEmail,etMessage;
    String aDataRow = "";
    String aBuffer = "";
    private String name,email,message;
    String date;
    private ImageView imgEdit;
    public static ClientFragement instanse;

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
//        final Typeface face_bold = Typeface.createFromAsset(getActivity().getApplication().getAssets(), "fonts/SolaimanLipi_Bold.ttf");
        imgEdit = (ImageView) getView().findViewById(R.id.imgEdit);
//        etEmail = (EditText)getView().findViewById(R.id.etEmail);
//        etMessage = (EditText)getView().findViewById(R.id.etMessage);
//        tvSubmit = (TextView) getView().findViewById(R.id.tvSubmit);
//        tvTitleContact = (TextView) getView().findViewById(R.id.tvTitleContact);
//        etName.setTypeface(face_bold);
//        etEmail.setTypeface(face_bold);
//        etMessage.setTypeface(face_bold);
//        tvSubmit.setTypeface(face_bold);
//        tvTitleContact.setTypeface(face_bold);
//
        imgEdit.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                ClientDialogFragment motamotDialogFragment = new ClientDialogFragment();
                motamotDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme);
                motamotDialogFragment.show(getActivity().getFragmentManager(), "");
            }
        });

    }

    private void writeToFile(){
        try {
            DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
            date = df.format(Calendar.getInstance().getTime());
            File myFile = new File("/sdcard/"+date+"contacts.doc");
            myFile.createNewFile();
            readFromFile();
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter =
                    new OutputStreamWriter(fOut);
           myOutWriter.append(aBuffer+ "\n"+name+" "+email+" "+message);
            //myOutWriter.append(  "\n"+name+" "+email+" "+message);
            myOutWriter.close();
            fOut.close();
            //readFromFile();
        } catch (Exception e) {
            Toast.makeText(getActivity().getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void readFromFile() {

        try {
            aBuffer="";
            File myFile = new File("/sdcard/"+date+"contacts.doc");
            FileInputStream fIn = new FileInputStream(myFile);

            BufferedReader myReader = new BufferedReader(
                    new InputStreamReader(fIn));
            while ((aDataRow = myReader.readLine()) != null) {

                aBuffer += aDataRow + "\n";

                Log.e("bufferText >> ",aBuffer);
            }
            myReader.close();

        } catch (Exception e) {
            Toast.makeText(getActivity().getBaseContext(), e.getMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }
    private void contactSend(final String url) {
        // TODO Auto-generated method stub
        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, getString(R.string.app_name),"");
            return;
        }
        Log.e("URL : ", url);
        final BusyDialog busyNow = new BusyDialog(con, true,false);
        busyNow.show();
        Executors.newSingleThreadScheduledExecutor().submit(new Runnable() {
            String response="";
            @Override
            public void run() {
                Map<String,String> param =new HashMap();
                param.put("contactName", etName.getText().toString());
                param.put("contactEmail", etEmail.getText().toString());
                param.put("contactMessage", etMessage.getText().toString());
                param.put("secretKey","ICTAppSecretKey123");
                try {
                    response= AAPBDHttpClient.post(url).form(param).body();
                } catch (Exception e) {
                    // TODO: handle exception
                    Log.e("MYAPP", "exception", e);
                    if(busyNow!=null){
                        busyNow.dismis();
                    }
                }
                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        if (busyNow != null) {
                            busyNow.dismis();
                        }
                        try {
                            Log.e("Response", ">>" + new String(response));
                        } catch (final Exception e) {

                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
