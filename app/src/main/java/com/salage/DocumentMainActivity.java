package com.salage;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.icteuro.salage.R;
import com.salage.model.DatabaseHelper;
import com.salage.model.DocumentTableInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class DocumentMainActivity extends AppCompatActivity{

    Context con;
    private ImageView img_add,dissmissCatListBtn;
    private List<DocumentTableInfo> documentTableInfo  = new ArrayList<>();
    private List<DocumentTableInfo> documentTableInfoList  = new ArrayList<>();
    private CustomAdapterProduct customAdapterProduct;
    private ListView listDocDetails;
    DatabaseHelper db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.document_main);
        con=this;
        initUi();
    }

    private void initUi() {
        db = new DatabaseHelper(con);
        documentTableInfoList = db.getAllDocumentss();
        img_add = (ImageView)findViewById(R.id.img_add);
        img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(con,DocumentDetailsActivity.class));

            }
        });

        dissmissCatListBtn = (ImageView) findViewById(R.id.dissmissCatListBtn);
        dissmissCatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listDocDetails = (ListView)findViewById(R.id.listDocDetails);
        customAdapterProduct = new CustomAdapterProduct(con);
        listDocDetails.setAdapter(customAdapterProduct);
        customAdapterProduct.notifyDataSetChanged();
    }

    private class CustomAdapterProduct extends ArrayAdapter<DocumentTableInfo> {
        Context context;

        CustomAdapterProduct(Context context) {
            super(context, R.layout.raw_product_details, documentTableInfoList);

            this.context = context;

        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_product_details, null);

            }

            if (position < documentTableInfoList.size()) {

                final DocumentTableInfo query = documentTableInfoList.get(position);

                final TextView tvCode = (TextView) v.findViewById(R.id.tvCode);
                final TextView tvProdotti = (TextView) v.findViewById(R.id.tvProdotti);
                final TextView tvClasse = (TextView) v.findViewById(R.id.tvClasse);
                final TextView tvMarche = (TextView) v.findViewById(R.id.tvMarche);
                final ImageView imgVisibility = (ImageView) v.findViewById(R.id.imgVisibility);

//                tvCode.setText(query.getPROD_CODE());
//                tvProdotti.setText(query.getPROD_DESCRIPTION());
//                tvClasse.setText(query.getCATE_ID());
//                tvMarche.setText(query.getBRAN_ID());

                imgVisibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //AppConstant.productTableInfo = query;
                        startActivity(new Intent(con, DocumentDetailsActivity.class));
                    }
                });

            }

            return v;

        }

    }

}
