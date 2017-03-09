package com.salage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.icteuro.salage.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.salage.Utils.AlertMessage;
import com.salage.Utils.AppConstant;
import com.salage.Utils.BusyDialog;
import com.salage.Utils.FileDownloader;
import com.salage.Utils.FileUtils;
import com.salage.Utils.NetInfo;
import com.salage.Utils.PersistData;
import com.salage.Utils.PersistentUser;
import com.salage.model.AgentInfo;
import com.salage.model.AgentTableInfo;
import com.salage.model.BrandsTableInfo;
import com.salage.model.CateGoryInfo;
import com.salage.model.CustomerProductTableInfo;
import com.salage.model.CustomerTableInfo;
import com.salage.model.DatabaseHelper;
import com.salage.model.DocumentTableInfo;
import com.salage.model.DownLoadFile;
import com.salage.model.JsonInfo;
import com.salage.model.JsonStructure;
import com.salage.model.JsonToSendServer;
import com.salage.model.PaymentTableInfo;
import com.salage.model.PriceListTableInfo;
import com.salage.model.ProductTableInfo;
import com.salage.model.RowItem;
import com.salage.model.SubCatTableInfo;
import com.salage.model.SyncData;
import com.salage.model.SyncResponse;
import com.salage.model.VatTableInfo;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cz.msebera.android.httpclient.Header;

/**
 * Created by User on 2/4/2017.
 */

public class MainActivityDemo extends AppCompatActivity implements OnFragmentInteractionListener{

    Context con;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    TextView tvToolBarTitle,tvSyncConfig,tvConfig;
    private LinearLayout linNav,linConfig,linClientMenu,linProductMenu,linDocumentMenu,linSincroMenu,
            linConfigMain,linClientMain,linProductMain,linDocumentMain,linSincroMain;
    Button btnSave;
    EditText etUrl,etAgent,etPassword,etCode;
    ImageView  img_home;
    private ScrollView buttonView;
    Activity activity;
    private DatabaseHelper db;
    private List<DocumentTableInfo> documentTableInfoList  = new ArrayList<>();
    private List<ProductTableInfo> ProductTableInfoList  = new ArrayList<>();
    private List<CateGoryInfo> cateGoryInfoList  = new ArrayList<>();
    private List<SubCatTableInfo> subcateGoryInfoList  = new ArrayList<>();
    private List<BrandsTableInfo> brandsTableInfoList  = new ArrayList<>();
    private List<CustomerTableInfo> CustomerTableInfoList  = new ArrayList<>();
    private List<AgentTableInfo> agentTableInfoList  = new ArrayList<>();

    String isdataSync = "false";
    private String json,jsonServer;
    private JsonStructure jsonStructure;
    private Vector<JsonInfo> listVec = new Vector<JsonInfo>();
    SyncResponse syncResponse;
    ProgressDialog progressDialog;
    private String [] arrayImagUrl;
    private List<String> listImageUrl = new ArrayList<>();
    private List<String> listPdfUrl = new ArrayList<>();
    private List<String> listPdfName = new ArrayList<>();
    private String [] arrayPdfurl;
    private String [] arrayPdfName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.t1_demo);
        con = this;
        db = new DatabaseHelper(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        initUi();
        img_home = (ImageView)toolbar.findViewById(R.id.img_home);

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductTableInfoList = db.getAllProducts();

                //Pdf download

                listPdfUrl.clear();
                listPdfName.clear();

                for(int i = 0; i<ProductTableInfoList.size();i++){
                    listPdfUrl.add(i,"http://www.ict-euro.com/demo/salage/uploads/"+ProductTableInfoList.get(i).getPROD_PDF());
                    listPdfName.add(i,ProductTableInfoList.get(i).getPROD_CODE());
                }
                for(int i =0; i<listPdfUrl.size();i++){
                    arrayPdfurl = listPdfUrl.toArray(new String[i]);
                    arrayPdfName = listPdfName.toArray(new String[i]);
                }

               // new DownloadFile().execute(arrayPdfurl, arrayPdfName);

                //Image download
                listImageUrl.clear();

                for(int i = 0; i<ProductTableInfoList.size();i++){
                    listImageUrl.add(i,"http://www.ict-euro.com/demo/salage/uploads/"+ProductTableInfoList.get(i).getPROD_IMAGE());
                }
                for(int i =0; i<listImageUrl.size();i++){
                    arrayImagUrl = listImageUrl.toArray(new String[i]);
                }

                GetXMLTask task = new GetXMLTask(MainActivityDemo.this);
                task.execute(arrayImagUrl);

                progressDialog = new ProgressDialog(MainActivityDemo.this);
                progressDialog.setTitle("In progress...");
                progressDialog.setMessage("Loading...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setIndeterminate(false);
                progressDialog.setMax(100);
                //progressDialog.setIcon(R.drawable.arrow_stop_down);
                progressDialog.setCancelable(true);
                progressDialog.show();

               // onBackPressed();
            }
        });
                navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return false;
            }
        });
        buttonView = (ScrollView) findViewById(R.id.buttonView);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();



    }


    private void initUi() {
        buttonView = (ScrollView)findViewById(R.id.buttonView);
        linNav = (LinearLayout)findViewById(R.id.linNav);
        linConfig = (LinearLayout)findViewById(R.id.linConfig);
        linClientMenu = (LinearLayout)findViewById(R.id.linClientMenu);
        linProductMenu= (LinearLayout)findViewById(R.id.linProductMenu);
        linDocumentMenu= (LinearLayout)findViewById(R.id.linDocumentMenu);
        linSincroMenu= (LinearLayout)findViewById(R.id.linSincroMenu);

        linConfigMain = (LinearLayout)findViewById(R.id.linConfigMain);
        linClientMain = (LinearLayout)findViewById(R.id.linClientMain);
        linProductMain = (LinearLayout)findViewById(R.id.linProductMain);
        linDocumentMain = (LinearLayout)findViewById(R.id.linDocumentMain);
        linSincroMain = (LinearLayout)findViewById(R.id.linSincroMain);
        tvSyncConfig = (TextView)findViewById(R.id.tvSyncConfig);
        tvConfig = (TextView)findViewById(R.id.tvConfig);

        btnSave = (Button)findViewById(R.id.btnSave);

        linConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                if(tvSyncConfig.getText().toString().equalsIgnoreCase("CONFIGARAZION")){
                    startActivity(new Intent(con, LoginActivity.class));
                }else{
                    makeJson();
                }
            }
        });

        linNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               drawerLayout.closeDrawers();
                ProductTableInfoList = db.getAllProducts();
        Log.e("PROD size: ", ""+ProductTableInfoList.size());


            for (int i = 0; i<ProductTableInfoList.size();i++) {
                Log.e("PROD size: lop", ""+ProductTableInfoList.size());
                new ImageLoadTask("http://www.ict-euro.com/demo/salage/uploads/"+ProductTableInfoList.get(i).getPROD_IMAGE());
            }



            }
        });
        linClientMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                startActivity(new Intent(con, ClientMainActivity.class));
                //setContentFragment(new ClientFragement(), false,"Active Work");
            }
        });

        linProductMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                startActivity(new Intent(con, ProductMainActivity.class));
                //setContentFragment(new ProductMainFragement(), false,"Active Work");
            }
        });

        linDocumentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                startActivity(new Intent(con, DocumentMainActivity.class));
               // setContentFragment(new DocumentMainFragement(), false,"Active Work");
            }
        });

        linSincroMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                makeJson();
            }
        });


        linConfigMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(tvSyncConfig.getText().toString().equalsIgnoreCase("CONFIGARAZION")){
                    startActivity(new Intent(con, LoginActivity.class));
                }else{
                    makeJson();
                }
               // setContentFragment(new LoginCofigFragement(), false,"Active Work");
            }
        });

        linClientMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                startActivity(new Intent(con, ClientMainActivity.class));
                //setContentFragment(new ClientFragement(), false,"Active Work");
            }
        });

        linProductMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                startActivity(new Intent(con, ProductMainActivity.class));
                //setContentFragment(new ProductMainFragement(), false,"Active Work");
            }
        });

        linDocumentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                startActivity(new Intent(con, DocumentMainActivity.class));
                //setContentFragment(new DocumentMainFragement(), false,"Active Work");
            }
        });

        linSincroMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeJson();
                //makeJsonTosendServer();
            }
        });


    }


    private void makeJson() {

        JsonInfo jsonInfo = new JsonInfo();
        jsonInfo.setTableName("cate_categories");
        jsonInfo.setIdName("CATE_ID");
        listVec.add(jsonInfo);

        JsonInfo jsonInfo1 = new JsonInfo();
        jsonInfo1.setTableName("subc_subcategories");
        jsonInfo1.setIdName("SUBC_ID");
        listVec.add(jsonInfo1);

        JsonInfo jsonInfo2 = new JsonInfo();
        jsonInfo2.setTableName("conf_config");
        jsonInfo2.setIdName("CONF_CO_CODE");
        listVec.add(jsonInfo2);

        JsonInfo jsonInfo3 = new JsonInfo();
        jsonInfo3.setTableName("doch_document_heads");
        jsonInfo3.setIdName("DOCH_ID");
        listVec.add(jsonInfo3);

        JsonInfo jsonInfo4 = new JsonInfo();
        jsonInfo4.setTableName("doch_document_rows");
        jsonInfo4.setIdName("DOCR_ROWNUM");
        listVec.add(jsonInfo4);

        JsonInfo jsonInfo5 = new JsonInfo();
        jsonInfo5.setTableName("cust_customers");
        jsonInfo5.setIdName("CUST_CODE");
        listVec.add(jsonInfo5);

        JsonInfo jsonInfo6 = new JsonInfo();
        jsonInfo6.setTableName("prod_products");
        jsonInfo6.setIdName("PROD_CODE");
        listVec.add(jsonInfo6);

        JsonInfo jsonInfo7 = new JsonInfo();
        jsonInfo7.setTableName("paym_payments");
        jsonInfo7.setIdName("PAYM_ID");
        listVec.add(jsonInfo7);

        JsonInfo jsonInfo8 = new JsonInfo();
        jsonInfo8.setTableName("vatt_vat");
        jsonInfo8.setIdName("VATT_ID");
        listVec.add(jsonInfo8);

        JsonInfo jsonInfo9 = new JsonInfo();
        jsonInfo9.setTableName("conf_config");
        jsonInfo9.setIdName("CONF_CO_CODE");
        listVec.add(jsonInfo9);


        JsonInfo jsonInfo10 = new JsonInfo();
        jsonInfo10.setTableName("pric_pricelists");
        jsonInfo10.setIdName("");
        listVec.add(jsonInfo10);

        JsonInfo jsonInfo11 = new JsonInfo();
        jsonInfo11.setTableName("barc_barcodes");
        jsonInfo11.setIdName("BARC_BARCODE");
        listVec.add(jsonInfo11);

        JsonInfo jsonInfo12 = new JsonInfo();
        jsonInfo12.setTableName("bran_brands");
        jsonInfo12.setIdName("BRAN_ID");
        listVec.add(jsonInfo12);

        JsonInfo jsonInfo13 = new JsonInfo();
        jsonInfo13.setTableName("agen_agents");
        jsonInfo13.setIdName("AGEN_CODE");
        listVec.add(jsonInfo13);

        JsonInfo jsonInfo14 = new JsonInfo();
        jsonInfo14.setTableName("dest_destinations");
        jsonInfo14.setIdName("DEST_ID");
        listVec.add(jsonInfo14);

        JsonInfo jsonInfo15 = new JsonInfo();
        jsonInfo15.setTableName("catd_categoriesdiscounts");
        jsonInfo15.setIdName("");
        listVec.add(jsonInfo15);

        JsonInfo jsonInfo16 = new JsonInfo();
        jsonInfo16.setTableName("cupr_customersproducts");
        jsonInfo16.setIdName("");
        listVec.add(jsonInfo16);

         Vector<AgentInfo> listAgent = new Vector<AgentInfo>();
         AgentInfo agentInfo = new AgentInfo();
         agentInfo.setAGEN_CODE(PersistData.getStringData(con,AppConstant.agentCode));
        listAgent.add(agentInfo);


        jsonStructure = new JsonStructure();
        jsonStructure.setInfo(listAgent);
        jsonStructure.setData(listVec);

        Gson gson = new Gson();
        json = gson.toJson(jsonStructure);
        Log.e("gson Data", "" + json);

   syncData("http://www.ict-euro.com/demo/salage/websync_native/index");

        listVec.clear();
    }


    private void makeJsonTosendServer(){
        List<CustomerTableInfo> cstomerTableInfoList  = new ArrayList<>();
        List<DocumentTableInfo> doch_document_heads =new ArrayList<DocumentTableInfo>();
        cstomerTableInfoList = db.getAllCustomer();
        doch_document_heads = db.getAllDocumentss();
         List<AgentInfo> agentList  = new ArrayList<>();
        AgentInfo aifo = new AgentInfo();
        aifo.setAGEN_CODE(PersistData.getStringData(con,AppConstant.agentCode));
        agentList.add(aifo);

        SyncData sdta = new SyncData();
        sdta.setCust_customers(cstomerTableInfoList);
        sdta.setDoch_document_heads(doch_document_heads);
        JsonToSendServer jsonToSendServer = new JsonToSendServer();
        jsonToSendServer.setInfo(agentList);
        jsonToSendServer.setData(sdta);


        Gson gson = new Gson();
        jsonServer = gson.toJson(jsonToSendServer);
        Log.d("gson TosendServer", "" + json);
        sendDataServer("http://www.ict-euro.com/demo/salage/websync_native/index");
        agentList.clear();
    }

    private void insertDocumentHed() {

        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addDocument(new DocumentTableInfo("sd","dff","ff","ff","ff","ss","ff","vv","bb",
                "ss","cc","tt","tt","j","hj","jj","h","h","h","j","m","m",
                "x","c","c","c","v","v","c","c","v","v","v","v"));
//        db.addContact(new Contact("Srinivas", "9199999999"));
//        db.addContact(new Contact("Tommy", "9522222222"));
//        db.addContact(new Contact("Karthik", "9533333333"));

        // Reading all contacts
        Log.d("Reading: ", "Reading all contacts..");

        documentTableInfoList = db.getAllDocumentss();

        for (DocumentTableInfo cn : documentTableInfoList) {
            String log = "Id: "+cn.getAGEN_CODE()+" ,Name: " + cn.getCUST_NAME1() + " ,Phone: " + cn.getDOCH_TERM_ID();
            // Writing Contacts to log
            Log.d("Name: ", log);

        }

    }


    @Override
    public void setContentFragment(Fragment fragment, boolean addToBackStack,String title) {
        if (fragment == null) {
            return;
        }

        // tvToolBarTitle.setText(title);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);

        //show only if current fragment is not same as given fragment
        /*if(currentFragment != null && fragment.getClass().equals("SeguiSuFragment")){

        }else{
            return;
        }*/
        if (currentFragment != null && fragment.getClass().isAssignableFrom(currentFragment.getClass())) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();

        /*if (fragment == null) {
            finish();
            //Log.e(tag, "Content fragment cannot be null");
            return;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        //Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        fragmentTransaction.replace(R.id.content_frame, fragment, ((Object) fragment).getClass().getName());

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(((Object) fragment).getClass().getName());
        }

        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();*/
    }


    @Override
    public void addContentFragment(Fragment fragment, boolean addToBackStack,String title) {
        if (fragment == null) {
            return;
        }


        //tvToolBarTitle.setText(title);
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);

        if (currentFragment != null && fragment.getClass().isAssignableFrom(currentFragment.getClass())) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();

    }

    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {
//
//        if (getFragmentManager().getBackStackEntryCount() > 0) {
//            getFragmentManager().popBackStack();
//        }
//        else {
//            super.onBackPressed();
//        }

        getSupportFragmentManager().getFragments()
                .get(getSupportFragmentManager().getBackStackEntryCount() - 1).onResume();
        super.onBackPressed();

        /*final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);
        Log.d("", "Current fragment before pop: ");
        if (fragmentManager.getBackStackEntryCount() == 0) {
            finish();
        }
        else {
            fragmentManager.popBackStack();
        }*/
    }


    public void exitFromApp() {
        final CharSequence[] items = { "NO", "YES" };
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit from app?");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                switch (item) {
                    case 0:
                        return;
                    case 1:
                        // onStopRecording();

                        finish();


                        break;
                    default:
                        return;
                }
            }
        });
        builder.show();
        builder.create();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(buttonView.getVisibility()==View.GONE){
                buttonView.setVisibility(View.VISIBLE);
            }else {
                exitFromApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

 //   private void createSwipeTimer() {
//        swipeTimer = new Timer();
//        swipeTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                handler.post(Update);
//            }
//        }, 3000, 3000);
//
//
//    }


    @Override
    protected void onResume() {
        super.onResume();
//        ProductTableInfoList = db.getAllProducts();
//        Log.e("PROD size: ", ""+ProductTableInfoList.size());
//        if(isdataSync.equalsIgnoreCase("true")){
//
//            for (ProductTableInfo pd : ProductTableInfoList) {
//                new ImageLoadTask("http://www.ict-euro.com/demo/salage/uploads/"+pd.getPROD_IMAGE());
//            }
//
//            isdataSync = "false";
//        }

        if(PersistentUser.isLogged(con)&& AppConstant.logInt==1){
            tvSyncConfig.setText("SYNCRO");
            tvConfig.setText("SYNCRO");
        }

        if (PersistData.getStringData(con,AppConstant.isSync).equalsIgnoreCase("true")){
            tvSyncConfig.setText("CONFIGARAZION");
            tvConfig.setText("CONFIGARAZION");

            buttonView.setVisibility(View.VISIBLE);
            linClientMain.setVisibility(View.VISIBLE);
            linProductMain.setVisibility(View.VISIBLE);
            linDocumentMain.setVisibility(View.VISIBLE);
            linSincroMain.setVisibility(View.VISIBLE);

            linClientMenu.setVisibility(View.VISIBLE);
            linProductMenu.setVisibility(View.VISIBLE);
            linDocumentMenu.setVisibility(View.VISIBLE);
            linSincroMenu.setVisibility(View.VISIBLE);
        }
    }


    protected void syncData(final String url) {

        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, "Alert",
                    "No Internet");
            return;
        }

        final BusyDialog busyNow = new BusyDialog(con, true, false);
        busyNow.show();

        final AsyncHttpClient client = new AsyncHttpClient();

        final RequestParams param = new RequestParams();

        try {

            param.put("json_data", json);

        } catch (final Exception e1) {
            e1.printStackTrace();
        }

        client.post(url, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (busyNow != null) {
                    busyNow.dismis();
                }

                Log.e("resposne ", ">>" + new String(responseBody));

                Gson g = new Gson();
                syncResponse = g.fromJson(new String(responseBody), SyncResponse.class);

                if(syncResponse!=null){

                    PersistData.setStringData(con,AppConstant.isSync,"true");

                    tvSyncConfig.setText("CONFIGARAZION");
                    tvConfig.setText("CONFIGARAZION");

                    buttonView.setVisibility(View.VISIBLE);
                    linClientMain.setVisibility(View.VISIBLE);
                    linProductMain.setVisibility(View.VISIBLE);
                    linDocumentMain.setVisibility(View.VISIBLE);
                    linSincroMain.setVisibility(View.VISIBLE);

                    linClientMenu.setVisibility(View.VISIBLE);
                    linProductMenu.setVisibility(View.VISIBLE);
                    linDocumentMenu.setVisibility(View.VISIBLE);
                    linSincroMenu.setVisibility(View.VISIBLE);
                    Log.e("crpData sizeres: ", ""+syncResponse.getData().getCupr_customersproducts().size());
                    if(syncResponse.getData().getCupr_customersproducts().size()>0){
                        db.deleteCustmerProduct();
                        List<CustomerProductTableInfo> crpData =new ArrayList<>();
                        crpData.addAll(syncResponse.getData().getCupr_customersproducts());

                        for(int i = 0; i < crpData.size(); i++) {

                            db.addCustomersproducts(new CustomerProductTableInfo(crpData.get(i).getCUST_CODE(),
                                    crpData.get(i).getPROD_CODE(),crpData.get(i).getCUPR_DISCOUNT(),
                                    crpData.get(i).getCUPR_PRICE(),crpData.get(i).getCUPR_TIMESTAMP(),
                                    crpData.get(i).getIS_DELETED()));

                        }

                        crpData = db.getAllCusProduct();
                        Log.e("crpData size: ", ""+crpData.size());
                        for (CustomerProductTableInfo cd : crpData) {
                            String log = "price: "+cd.getCUPR_PRICE();
                            // Writing Contacts to log
                            Log.e("crpData: ", log);
                        }
                    }


                    if(syncResponse.getData().getAgen_agents().size()>0){
                        List<AgentTableInfo> agentData =new ArrayList<AgentTableInfo>();
                        agentData.addAll(syncResponse.getData().getAgen_agents());
                        db.deleteAgent();
                        for(int i = 0; i < agentData.size(); i++) {

                            db.addAgents(new AgentTableInfo(agentData.get(i).getAGEN_CODE(),
                                    agentData.get(i).getAGEN_NAME1(),agentData.get(i).getAGEN_NAME2(),
                                    agentData.get(i).getAGEN_EMAIL(),agentData.get(i).getAGEN_PASSWORD(),
                                    agentData.get(i).getAGEN_ENABLED(),agentData.get(i).getAGEN_CANCHANGEPRICE(),
                                    agentData.get(i).getAGEN_CANCHANGEPAYM(),agentData.get(i).getAGEN_CANCHANGEVAT(),
                                    agentData.get(i).getAGEN_CANADDCUST(),agentData.get(i).getAGEN_CANADDDEST(),
                                    agentData.get(i).getAGEN_CANCHANGEDESC(),agentData.get(i).getAGEN_CANUSENOCODE(),
                                    agentData.get(i).getAGEN_CANADDCOMMENTS(),agentData.get(i).getAGEN_TIMESTAMP(),
                                    agentData.get(i).getIS_DELETED(),agentData.get(i).getAGENT_TYPE()));

                            }

                        agentTableInfoList = db.getAllAgents();
                        Log.e("AGEN size: ", ""+agentTableInfoList.size());
                        for (AgentTableInfo cd : agentTableInfoList) {
                            String log = "AGEN_CODE: "+cd.getAGEN_CODE()+" ,AGEN_NAME1: " + cd.getAGEN_NAME1();
                            // Writing Contacts to log
                            Log.e("AGENT DATA: ", log);
                        }
                    }


                    if(syncResponse.getData().getCust_customers().size()>0){
                        List<CustomerTableInfo> customerTableInfo =new ArrayList<CustomerTableInfo>();
                        customerTableInfo.addAll(syncResponse.getData().getCust_customers());
                        db.deleteCustomer();
                        for(int i = 0; i < customerTableInfo.size(); i++) {

                            db.addCustomer(new CustomerTableInfo(customerTableInfo.get(i).getCUST_CODE(),
                                    customerTableInfo.get(i).getCUST_NAME1(),customerTableInfo.get(i).getCUST_NAME2(),
                                    customerTableInfo.get(i).getCUST_ADDRESS(),customerTableInfo.get(i).getCUST_ZIP(),
                                    customerTableInfo.get(i).getCUST_CITY(),customerTableInfo.get(i).getCUST_PROVINCE(),
                                    customerTableInfo.get(i).getCUST_COUNTRY(),customerTableInfo.get(i).getCUST_TEL(),
                                    customerTableInfo.get(i).getCUST_FAX(),customerTableInfo.get(i).getCUST_MOBILE(),
                                    customerTableInfo.get(i).getCUST_MAIL(),customerTableInfo.get(i).getCUST_CF(),
                                    customerTableInfo.get(i).getCUST_VATNUM(),customerTableInfo.get(i).getCUST_IBAN(),
                                    customerTableInfo.get(i).getVATT_ID(),customerTableInfo.get(i).getPAYM_ID(),
                                    customerTableInfo.get(i).getAGEN_CODE(),customerTableInfo.get(i).getCUST_PRICELIST(),
                                    customerTableInfo.get(i).getCUST_DISCOUNT(),customerTableInfo.get(i).getCUST_STATE(),
                                    customerTableInfo.get(i).getCUST_TIMESTAMP(),customerTableInfo.get(i).getIS_DELETED()));

                            CustomerTableInfoList = db.getAllCustomer();
                            Log.e("cuInfo size: ", ""+customerTableInfo.size());
                            for (CustomerTableInfo cd : customerTableInfo) {
                                String log = "BRANcuInfoId: "+cd.getVATT_ID()+" ,cuInfoCode: " + cd.getCUST_CODE();
                                // Writing Contacts to log
                                Log.e("cat DATA: ", log);
                            }
                        }
                    }



                    if(syncResponse.getData().getPric_pricelists().size()>0){
                        List<PriceListTableInfo> priceListTableInfo =new ArrayList<PriceListTableInfo>();
                        priceListTableInfo.addAll(syncResponse.getData().getPric_pricelists());
                        db.deletePrice();
                        for(int i = 0; i < priceListTableInfo.size(); i++) {

                            db.addPriceList(new PriceListTableInfo(priceListTableInfo.get(i).getPRIC_DESC0(),
                                    priceListTableInfo.get(i).getPRIC_DESC1(),priceListTableInfo.get(i).getPRIC_DESC2(),
                                    priceListTableInfo.get(i).getPRIC_DESC3(),priceListTableInfo.get(i).getPRIC_DESC4(),
                                    priceListTableInfo.get(i).getPRIC_DESC5(),priceListTableInfo.get(i).getPRIC_DESC5(),
                                    priceListTableInfo.get(i).getPRIC_DESC6(),priceListTableInfo.get(i).getPRIC_DESC7(),
                                    priceListTableInfo.get(i).getPRIC_DESC8(),priceListTableInfo.get(i).getPRIC_DESC9()));

                            priceListTableInfo = db.getAllPriceList();
                            Log.e("cat size: ", ""+brandsTableInfoList.size());
                            for (PriceListTableInfo cd : priceListTableInfo) {
                                String log = "BRAN_DESCRIPTION: "+cd.getPRIC_DESC0()+" ,BRAN_ID: " + cd.getPRIC_DESC1();
                                // Writing Contacts to log
                                Log.e("cat DATA: ", log);
                            }
                        }
                    }


                    if(syncResponse.getData().getPaym_payments().size()>0){
                        List<PaymentTableInfo> paymentTableInfo =new ArrayList<PaymentTableInfo>();
                        paymentTableInfo.addAll(syncResponse.getData().getPaym_payments());
                         db.deletePayment();
                        for(int i = 0; i < paymentTableInfo.size(); i++) {

                            db.addPayment(new PaymentTableInfo(paymentTableInfo.get(i).getPAYM_ID(),
                                    paymentTableInfo.get(i).getPAYM_CODE(),paymentTableInfo.get(i).getPAYM_DESCRIPTION(),
                                    paymentTableInfo.get(i).getPAYM_DISCOUNT(),paymentTableInfo.get(i).getPAYM_TIMESTAMP(),
                                    paymentTableInfo.get(i).getIS_DELETED()));

                        }

                        paymentTableInfo = db.getPaymentList();
                        Log.e("cat size: ", ""+paymentTableInfo.size());
                        for (PaymentTableInfo cd : paymentTableInfo) {
                            String log = "BRAN_DESCRIPTION: "+cd.getPAYM_DESCRIPTION()+" ,BRAN_ID: " + cd.getPAYM_DISCOUNT();
                            // Writing Contacts to log
                            Log.e("cat DATA: ", log);
                        }
                    }

                //Vat table
                    if(syncResponse.getData().getVatt_vat().size()>0){
                        List<VatTableInfo> vatTableInfo =new ArrayList<>();
                        vatTableInfo.addAll(syncResponse.getData().getVatt_vat());
                        Log.e("vat siz: ", ""+vatTableInfo.size());
                          db.deleteVat();
                        for(int i = 0; i < vatTableInfo.size(); i++) {
                            Log.e("vat siz: ", ""+vatTableInfo.size());

                            db.addVat(new VatTableInfo(vatTableInfo.get(i).getVATT_ID(),
                                    vatTableInfo.get(i).getVATT_CODE(),vatTableInfo.get(i).getVATT_DESCRIPTION(),
                                    vatTableInfo.get(i).getVATT_PERCENT(),vatTableInfo.get(i).getVATT_TIMESTAMP(),
                                    vatTableInfo.get(i).getIS_DELETED()));

                        }

                        vatTableInfo = db.getAllVats();
                        Log.e("vat siz: ", ""+vatTableInfo.size());
                        for (VatTableInfo cd : vatTableInfo) {
                            String log = "Vat id "+cd.getVATT_ID()+" ,vat code: " + cd.getVATT_CODE();
                            // Writing Contacts to log
                            Log.e("vat data ", log);
                        }
                    }


                    if(syncResponse.getData().getBran_brands().size()>0){
                        List<BrandsTableInfo> brandsTableInfo =new ArrayList<BrandsTableInfo>();
                        brandsTableInfo.addAll(syncResponse.getData().getBran_brands());
                        db.deleteBrands();
                        for(int i = 0; i < brandsTableInfo.size(); i++) {

                            db.addBrand(new BrandsTableInfo(brandsTableInfo.get(i).getBRAN_ID(),
                                    brandsTableInfo.get(i).getBRAN_DESCRIPTION(),brandsTableInfo.get(i).getBRAN_TIMESTAMP(),
                                    brandsTableInfo.get(i).getIS_DELETED()));

                            brandsTableInfoList = db.getAllBrands();
                            Log.e("cat size: ", ""+brandsTableInfoList.size());
                            for (BrandsTableInfo cd : brandsTableInfoList) {
                                String log = "BRAN_DESCRIPTION: "+cd.getBRAN_DESCRIPTION()+" ,BRAN_ID: " + cd.getBRAN_ID();
                                // Writing Contacts to log
                                Log.e("cat DATA: ", log);
                            }
                        }
                    }

                    if(syncResponse.getData().getSubc_subcategories().size()>0){
                        List<SubCatTableInfo> subCategoryData =new ArrayList<SubCatTableInfo>();
                        subCategoryData.addAll(syncResponse.getData().getSubc_subcategories());
                        db.deleteSubCatagory();
                        for(int i = 0; i < subCategoryData.size(); i++) {

                            db.addSubcategory(new SubCatTableInfo(subCategoryData.get(i).getSUBC_ID(),
                                    subCategoryData.get(i).getSUBC_DESCRIPTION(),subCategoryData.get(i).getCATE_ID(),
                                    subCategoryData.get(i).getSUBC_TIMESTAMP(),subCategoryData.get(i).getIS_DELETED()));

                            subcateGoryInfoList = db.getAllSubcategories();
                            Log.e("cat size: ", ""+subcateGoryInfoList.size());
                            for (SubCatTableInfo cd : subcateGoryInfoList) {
                                String log = "cat_des: "+cd.getCATE_ID()+" ,catId: " + cd.getSUBC_DESCRIPTION();
                                // Writing Contacts to log
                                Log.e("cat DATA: ", log);
                            }
                        }
                    }


                    if(syncResponse.getData().getCate_categories().size()>0){
                        List<CateGoryInfo> categoryData =new ArrayList<CateGoryInfo>();
                        categoryData.addAll(syncResponse.getData().getCate_categories());
                        db.deleteCatagory();
                        for(int i = 0; i < categoryData.size(); i++) {

                            db.addCategory(new CateGoryInfo(categoryData.get(i).getCATE_ID(),
                                    categoryData.get(i).getCATE_DESCRIPTION(),categoryData.get(i).getCATE_TIMESTAMP(),
                                    categoryData.get(i).getIS_DELETED()));

                            cateGoryInfoList = db.getAllCategories();
                            Log.e("cat size: ", ""+cateGoryInfoList.size());
                            for (CateGoryInfo cd : cateGoryInfoList) {
                                String log = "cat_des: "+cd.getCATE_DESCRIPTION()+" ,catId: " + cd.getCATE_ID();
                                // Writing Contacts to log
                                Log.e("cat DATA: ", log);
                            }
                        }
                    }

                    if(syncResponse.getData().getProd_products().size()>0){

                        List<ProductTableInfo> proDcData =new ArrayList<ProductTableInfo>();
                        proDcData.addAll(syncResponse.getData().getProd_products());
                        db.deleteProduct();
                        for(int i = 0; i < proDcData.size(); i++) {
                            db.addProduct(new ProductTableInfo(proDcData.get(i).getPROD_CODE(),
                                    proDcData.get(i).getPROD_DESCRIPTION(), proDcData.get(i).getCATE_ID(),
                                    proDcData.get(i).getSUBC_ID(), proDcData.get(i).getBRAN_ID(),
                                    proDcData.get(i).getSUPP_ID(), proDcData.get(i).getPROD_MU(),
                                    proDcData.get(i).getPROD_MIN_QT(), proDcData.get(i).getPROD_P0(),
                                    proDcData.get(i).getPROD_P1(),
                                    proDcData.get(i).getPROD_P2(), proDcData.get(i).getPROD_P3(),
                                    proDcData.get(i).getPROD_P4(), proDcData.get(i).getPROD_P5(),
                                    proDcData.get(i).getPROD_P6(),
                                    proDcData.get(i).getPROD_P7(), proDcData.get(i).getPROD_P8(),
                                    proDcData.get(i).getPROD_P9(), proDcData.get(i).getVATT_ID(),
                                    proDcData.get(i).getPROD_AVL_QTY(),
                                    proDcData.get(i).getPROD_IMAGE(), proDcData.get(i).getPROD_PDF(),
                                    proDcData.get(i).getPROD_TIMESTAMP(), proDcData.get(i).getPROD_AVL_TIMESTAMP(),
                                    proDcData.get(i).getIS_DELETED()));

                            ProductTableInfoList = db.getAllProducts();
                            Log.e("PROD size: ", ""+ProductTableInfoList.size());
                            for (ProductTableInfo pd : ProductTableInfoList) {
                               // new ImageLoadTask("http://www.ict-euro.com/demo/salage/uploads/"+pd.getPROD_IMAGE());
                            }

                        }
                        isdataSync = "true";
                    }

                    if(syncResponse.getData().getDoch_document_heads().size()>0){
                        List<DocumentTableInfo> docData =new ArrayList<DocumentTableInfo>();
                        docData.addAll(syncResponse.getData().getDoch_document_heads());
                        db.deleteDocument();

                        for(int i = 0; i < docData.size(); i++) {
                            Log.e("DOC DATA",""+docData.get(0).getCUST_NAME1());
                            db.addDocument(new DocumentTableInfo(docData.get(i).getDOCH_TERM_CODE(),
                                    docData.get(i).getDOCH_TERM_ID(),docData.get(i).getDOCH_TYPE(),
                                    docData.get(i).getDOCH_NUMBER(),docData.get(i).getDOCH_DATE(),
                                    docData.get(i).getAGEN_CODE(),
                                    docData.get(i).getDOCH_PRICELIST(),docData.get(i).getCUST_CODE(),
                                    docData.get(i).getCUST_NAME1(),docData.get(i).getCUST_NAME2(),
                                    docData.get(i).getCUST_ADDRESS(),docData.get(i).getCUST_ZIP(),
                                    docData.get(i).getCUST_CITY(),docData.get(i).getCUST_MAIL(),
                                    docData.get(i).getCUST_PROVINCE(),docData.get(i).getCUST_COUNTRY(),
                                    docData.get(i).getCUST_DISCOUNT(),docData.get(i).getSPECIAL_VATT(),
                                    docData.get(i).getDEST_ID(),
                                    docData.get(i).getDEST_NAME(),docData.get(i).getDEST_ADDRESS(),
                                    docData.get(i).getDEST_ZIP(),docData.get(i).getDEST_CITY(),
                                    docData.get(i).getDEST_PROVINCE(),docData.get(i).getDEST_COUNTRY(),
                                    docData.get(i).getVATT_ID(),docData.get(i).getPAYM_ID(),
                                    docData.get(i).getDOCH_NOTE(),docData.get(i).getDOCH_TAXABLE(),
                                    docData.get(i).getDOCH_VAT(),docData.get(i).getDOCH_TOTAL(),
                                    docData.get(i).getDOCH_SENT(),docData.get(i).getIS_DELETED(),
                                    docData.get(i).getDOCH_TIMESTAMP()));

                            documentTableInfoList = db.getAllDocumentss();
                            Log.e("DB DATA: ", ""+documentTableInfoList.size());
                            for (DocumentTableInfo cn : documentTableInfoList) {
                                String log = "Id: "+cn.getAGEN_CODE()+" ,Name: " + cn.getCUST_NAME1() + " ,Phone: " + cn.getDOCH_TERM_ID();
                                // Writing Contacts to log
                                Log.e("DB DATA: ", log);

                            }

                        }

                    }
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        } );

    }


    protected void sendDataServer(final String url) {

        if (!NetInfo.isOnline(con)) {
            AlertMessage.showMessage(con, "Alert",
                    "No Internet");
            return;
        }

        final BusyDialog busyNow = new BusyDialog(con, true, false);
        busyNow.show();

        final AsyncHttpClient client = new AsyncHttpClient();

        final RequestParams param = new RequestParams();

        try {

            param.put("json_data", jsonServer);

        } catch (final Exception e1) {
            e1.printStackTrace();
        }

        client.post(url, param, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (busyNow != null) {
                    busyNow.dismis();
                }

                Log.e("resposne ", ">>" + new String(responseBody));

                Gson g = new Gson();
                syncResponse = g.fromJson(new String(responseBody), SyncResponse.class);

                if(syncResponse!=null) {

                }
                }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        } );

    }

    public class ImageLoadTask extends AsyncTask<Void, Void, Bitmap> {

        private String url;
        private String path;

        public ImageLoadTask(String url) {
            this.url = url;
           // this.path = path;
        }

        @Override
        protected Bitmap doInBackground(Void... params) {
            try {
                URL urlConnection = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) urlConnection
                        .openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(input);
                return myBitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            //imageView.setImageBitmap(result);

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            result.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            path = MediaStore.Images.Media.insertImage(con.getContentResolver(), result, "Title", null);

            AppConstant.DownLoadFileList.add(new DownLoadFile(path));
            Log.e("DownLoadFileList",""+AppConstant.DownLoadFileList.size());
            // Picasso.with(con).load(path).into(imageView);
            // return Uri.parse(path);
        }

    }


//    class DownloadFile extends AsyncTask<String,Integer,Long> {
//        ProgressDialog mProgressDialog = new ProgressDialog(con);// Change Mainactivity.this with your activity name.
//        String strFolderName;
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            mProgressDialog.setMessage("Downloading");
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.setMax(100);
//            mProgressDialog.setCancelable(true);
//            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
//            mProgressDialog.show();
//        }
//        @Override
//        protected Long doInBackground(String... aurl) {
//            int count;
//            try {
//                URL url = new URL((String) aurl[0]);
//                URLConnection conexion = url.openConnection();
//                conexion.connect();
//                String targetFileName="Name"+".rar";//Change name and subname
//                int lenghtOfFile = conexion.getContentLength();
//                String PATH = Environment.getExternalStorageDirectory()+ "/"+"salageImage"+"/";
//                File folder = new File(PATH);
//                if(!folder.exists()){
//                    folder.mkdir();//If there is no folder it will be created.
//                }
//                InputStream input = new BufferedInputStream(url.openStream());
//                OutputStream output = new FileOutputStream(PATH+targetFileName);
//                byte data[] = new byte[1024];
//                long total = 0;
//                while ((count = input.read(data)) != -1) {
//                    total += count;
//                    publishProgress ((int)(total*100/lenghtOfFile));
//                    output.write(data, 0, count);
//                }
//                output.flush();
//                output.close();
//                input.close();
//            } catch (Exception e) {}
//            return null;
//        }
//        protected void onProgressUpdate(Integer... progress) {
//            mProgressDialog.setProgress(progress[0]);
//            if(mProgressDialog.getProgress()==mProgressDialog.getMax()){
//                mProgressDialog.dismiss();
//                //Toast.makeText(fa, "File Downloaded", Toast.LENGTH_SHORT).show();
//            }
//        }
//        protected void onPostExecute(String result) {
//        }
//    }


    private class GetXMLTask extends AsyncTask<String, Integer, List<RowItem>> {
        private Activity context;
        List<RowItem> rowItems;
        int noOfURLs;
        public GetXMLTask(Activity context) {
            this.context = context;
        }

        @Override
        protected List<RowItem> doInBackground(String... urls) {
            noOfURLs = urls.length;
            rowItems = new ArrayList<RowItem>();
            Bitmap map = null;
            for (String url : urls) {
                map = downloadImage(url);
                rowItems.add(new RowItem(map));
            }
            return rowItems;
        }

        private Bitmap downloadImage(String urlString) {

            int count = 0;
            Bitmap bitmap = null;

            URL url;
            InputStream inputStream = null;
            BufferedOutputStream outputStream = null;

            try {
                url = new URL(urlString);
                URLConnection connection = url.openConnection();
                int lenghtOfFile = connection.getContentLength();

                inputStream = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream dataStream = new ByteArrayOutputStream();

                outputStream = new BufferedOutputStream(dataStream);

                byte data[] = new byte[512];
                long total = 0;

                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    /*publishing progress update on UI thread.
                    Invokes onProgressUpdate()*/
                    publishProgress((int)((total*100)/lenghtOfFile));

                    // writing data to byte array stream
                    outputStream.write(data, 0, count);
                }
                outputStream.flush();

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inSampleSize = 1;

                byte[] bytes = dataStream.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,bmOptions);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                FileUtils.close(inputStream);
                FileUtils.close(outputStream);
            }
            return bitmap;
        }

        protected void onProgressUpdate(Integer... progress) {
            progressDialog.setProgress(progress[0]);
            if(rowItems != null) {
                progressDialog.setMessage("Loading " + (rowItems.size()+1) + "/" + noOfURLs);
            }
        }

        @Override
        protected void onPostExecute(List<RowItem> rowItems) {
            AppConstant.rowItem = rowItems;
//            listViewAdapter = new CustomListViewAdapter(context, rowItems);
//            listView.setAdapter(listViewAdapter);
            progressDialog.dismiss();
        }

    }


    private class DownloadFile extends AsyncTask<String, List<String>, List<String>>{

        @Override
        protected List<String>  doInBackground(String... strings) {
            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "testthreepdf");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try{
                pdfFile.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            FileDownloader.downloadFile(fileUrl, pdfFile);
            return null;
        }
    }

}
