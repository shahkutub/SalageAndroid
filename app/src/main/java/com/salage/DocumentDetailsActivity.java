package com.salage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.icteuro.salage.R;
import com.salage.model.AgentTableInfo;
import com.salage.model.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class DocumentDetailsActivity extends AppCompatActivity {
    Context con;
    private View view;
    private ImageView dissmissCatListBtn,imgProd;
    private Spinner spinnerClient;
    private DatabaseHelper db;
    private List<AgentTableInfo> agentDataList =new ArrayList<AgentTableInfo>();
    private List<String> agentCodeList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.document_detailse);
        con=this;
        initUi();
    }
    private void initUi() {
        db = new DatabaseHelper(this);
        agentDataList = db.getAllAgents();
        for(int i =0; i<agentDataList.size();i++){
            agentCodeList.add(agentDataList.get(i).getAGEN_CODE());
        }

        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        spinnerClient = (Spinner)findViewById(R.id.spinnerClient);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, agentCodeList);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerClient.setAdapter(adp);

    }
}
