package com.salage;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.squareup.picasso.Picasso;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CommonDialogFragment extends DialogFragment {

    private Context con;
    private View view;
    private ImageView imgProfile;
    private WebView webDjela;
    TextView name,idNo,post,bach,bcsJoindate,prestPosting,prestPostingJoinDate,birthDate,wonDistrict,spousDist,phoneNo,email,
    marriStaus,education,educationAll,previousWorkStation,oldWorkPlace,timepresentWork;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.details, container, true);
        con = getActivity();
        initUi();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private void initUi() {


        name = (TextView)view.findViewById(R.id.name);
        idNo = (TextView)view.findViewById(R.id.idNo);
        post = (TextView)view.findViewById(R.id.post);
        bach = (TextView)view.findViewById(R.id.bach);
        bcsJoindate = (TextView)view.findViewById(R.id.bcsJoindate);
        prestPosting = (TextView)view.findViewById(R.id.prestPosting);

        prestPostingJoinDate = (TextView)view.findViewById(R.id.prestPostingJoinDate);
        birthDate = (TextView)view.findViewById(R.id.birthDate);
        wonDistrict = (TextView)view.findViewById(R.id.wonDistrict);
        spousDist = (TextView)view.findViewById(R.id.spousDist);
        phoneNo = (TextView)view.findViewById(R.id.phoneNo);
        email = (TextView)view.findViewById(R.id.email);
        marriStaus = (TextView)view.findViewById(R.id.marriStaus);
        education = (TextView)view.findViewById(R.id.education);
        educationAll = (TextView)view.findViewById(R.id.educationAll);
        previousWorkStation = (TextView)view.findViewById(R.id.previousWorkStation);
        oldWorkPlace = (TextView)view.findViewById(R.id.oldWorkPlace);
        timepresentWork = (TextView)view.findViewById(R.id.timepresentWork);

        imgProfile = (ImageView)view.findViewById(R.id.imgProfile);

        Picasso.with(con).load(AppConstant.upojelaOfficerInfo.getPhoto()).error(R.drawable.logo).into(imgProfile);

        name.setText(AppConstant.upojelaOfficerInfo.getCadre_name());
        idNo.setText(AppConstant.upojelaOfficerInfo.getIntroducing_no());
        post.setText(AppConstant.upojelaOfficerInfo.getPost());
        bach.setText(AppConstant.upojelaOfficerInfo.getBatch());
        bcsJoindate.setText(AppConstant.upojelaOfficerInfo.getJoin_date_of_bcs_cadre());
        prestPosting.setText(AppConstant.upojelaOfficerInfo.getPresent_work_station());
        prestPostingJoinDate.setText(AppConstant.upojelaOfficerInfo.getJoin_date_of_present_work_station());
        birthDate.setText(AppConstant.upojelaOfficerInfo.getDate_of_birth());
        wonDistrict.setText(AppConstant.upojelaOfficerInfo.getOwn_district());
        spousDist.setText(AppConstant.upojelaOfficerInfo.getSpous_district());
        phoneNo.setText(AppConstant.upojelaOfficerInfo.getMobile_no());
        email.setText(AppConstant.upojelaOfficerInfo.getEmail());
        marriStaus.setText(AppConstant.upojelaOfficerInfo.getMarital_status());
        education.setText(AppConstant.upojelaOfficerInfo.getMost_educational_qualification());
        educationAll.setText(AppConstant.upojelaOfficerInfo.getMost_educational_qualification_all());
        previousWorkStation.setText(AppConstant.upojelaOfficerInfo.getPre_work_station());
        oldWorkPlace.setText(AppConstant.upojelaOfficerInfo.getPre_work_station_all());
        //timepresentWork.setText(AppConstant.upojelaOfficerInfo.getPre_work_station_all());
    }
}
