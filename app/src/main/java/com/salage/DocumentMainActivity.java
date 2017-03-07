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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
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
    private LinearLayout linPlus;
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
        linPlus = (LinearLayout)findViewById(R.id.linPlus);
        linPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppConstant.isDoc = "false";
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
            super(context, R.layout.raw_document, documentTableInfoList);

            this.context = context;

        }

        @Override
        public View getView(final int position, View convertView,
                            ViewGroup parent) {
            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_document, null);

            }

            if (position < documentTableInfoList.size()) {

                final DocumentTableInfo query = documentTableInfoList.get(position);

                final TextView tvCustomer = (TextView) v.findViewById(R.id.tvCustomer);
                final TextView tvAgentCode = (TextView) v.findViewById(R.id.tvAgentCode);
                final TextView tvDocumentNumber = (TextView) v.findViewById(R.id.tvDocumentNumber);
                final TextView tvDocumentType = (TextView) v.findViewById(R.id.tvDocumentType);
                final ImageView imgVisibility = (ImageView) v.findViewById(R.id.imgVisibility);

                tvDocumentType.setText(query.getDOCH_TYPE());
                tvAgentCode.setText(query.getAGEN_CODE());
                tvDocumentNumber.setText(query.getDOCH_NUMBER());
                tvCustomer.setText(query.getCUST_NAME1()+" "+query.getCUST_NAME2());

                imgVisibility.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppConstant.documentTableInfo = query;
                        AppConstant.isDoc = "true";
                        startActivity(new Intent(con, DocumentDetailsActivity.class));
                    }
                });

            }

            return v;

        }

    }

}
