package com.salage;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;

/**
 * Created by User on 2/22/2017.
 */

public class ProductDetailsActivity extends AppCompatActivity{

    Context con;
    private View view;
    private ImageView dissmissCatListBtn,imgProd;
    TextView tvCodedPro,tvDescriptPro,tvLinkPdf,tvCat,tvSubCat,tvMarche,tvIva,tvSup,tvBarcode;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.product_details);
        con=this;
        initUi();
    }

    private void initUi() {
        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });
        imgProd = (ImageView) findViewById(R.id.imgProd);
        tvCodedPro = (TextView) findViewById(R.id.tvCodedPro);
        tvDescriptPro = (TextView) findViewById(R.id.tvDescriptPro);
        tvLinkPdf = (TextView) findViewById(R.id.tvLinkPdf);
        tvCat = (TextView) findViewById(R.id.tvCat);
        tvSubCat = (TextView) findViewById(R.id.tvSubCat);

        tvMarche = (TextView) findViewById(R.id.tvMarche);
        tvIva = (TextView) findViewById(R.id.tvIva);
        tvSup = (TextView) findViewById(R.id.tvSup);
        tvBarcode = (TextView) findViewById(R.id.tvBarcode);


        tvCodedPro.setText("codice prodotti:" + AppConstant.productTableInfo.getPROD_CODE());
        tvDescriptPro.setText("discrizion prodotti:" + AppConstant.productTableInfo.getPROD_DESCRIPTION());
        if (!TextUtils.isEmpty(AppConstant.productTableInfo.getPROD_PDF())) {
            tvLinkPdf.setTextColor(Color.parseColor("#4059BA"));
        }
        tvLinkPdf.setText("link pdf:");
        tvCat.setText("classe:" + AppConstant.productTableInfo.getCATE_ID());
        tvSubCat.setText("sottoclasse:" + AppConstant.productTableInfo.getSUBC_ID());
        tvMarche.setText("marche:" + AppConstant.productTableInfo.getBRAN_ID());
        tvIva.setText("IVA Code:" + AppConstant.productTableInfo.getVATT_ID());
        tvSup.setText("id supp:" + AppConstant.productTableInfo.getSUPP_ID());
        tvBarcode.setText("Barcode:");
    }
}
