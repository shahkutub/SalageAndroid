package com.salage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import com.icteuro.salage.R;
import com.salage.Utils.AppConstant;
import com.salage.Utils.PersistentUser;
import com.salage.model.DatabaseHelper;
import com.salage.model.DocumentTableInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/4/2017.
 */

public class MainActivityDemo extends AppCompatActivity implements OnFragmentInteractionListener{

    Context con;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    TextView tvToolBarTitle;
    private LinearLayout linNav,linConfig,linClientMenu,linProductMenu,linDocumentMenu,linSincroMenu,
            linConfigMain,linClientMain,linProductMain,linDocumentMain,linSincroMain;
    Button btnSave;
    EditText etUrl,etAgent,etPassword,etCode;
    ImageView  img_home;
    private ScrollView buttonView;
   Activity activity;
    private DatabaseHelper db;
    private List<DocumentTableInfo> documentTableInfoList  = new ArrayList<>();
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
                onBackPressed();
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
        insertContact();
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

        btnSave = (Button)findViewById(R.id.btnSave);

        linConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                //buttonView.setVisibility(View.GONE);
                if(!PersistentUser.isLogged(con)){
                    startActivity(new Intent(con, LoginActivity.class));
                }
                //setContentFragment(new LoginCofigFragement(), true,"Active Work");
            }
        });

        linNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                drawerLayout.closeDrawers();
//               // buttonView.setVisibility(View.GONE);
//                setContentFragment(new NewsFragement(), false,"Active Work");
            }
        });
        linClientMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new ClientFragement(), false,"Active Work");
            }
        });

        linProductMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new ProductMainFragement(), false,"Active Work");
            }
        });

        linDocumentMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new DocumentMainFragement(), false,"Active Work");
            }
        });


        linConfigMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!PersistentUser.isLogged(con)){
                    startActivity(new Intent(con, LoginActivity.class));
                }
               // setContentFragment(new LoginCofigFragement(), false,"Active Work");
            }
        });

        linClientMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new ClientFragement(), false,"Active Work");
            }
        });

        linProductMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new ProductMainFragement(), false,"Active Work");
            }
        });

        linDocumentMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new DocumentMainFragement(), false,"Active Work");
            }
        });


    }


    private void insertContact() {

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

        if(PersistentUser.isLogged(con)){
            //Toast.makeText(con,"clicked", Toast.LENGTH_SHORT).show();
            buttonView.setVisibility(View.VISIBLE);
            linClientMain.setVisibility(View.VISIBLE);
            linProductMain.setVisibility(View.VISIBLE);
            linDocumentMain.setVisibility(View.VISIBLE);
            linSincroMain.setVisibility(View.VISIBLE);

            linClientMenu.setVisibility(View.VISIBLE);
            linProductMenu.setVisibility(View.VISIBLE);
            linDocumentMenu.setVisibility(View.VISIBLE);
            linSincroMenu.setVisibility(View.VISIBLE);



        }else {
            //linProductMain.setVisibility(View.VISIBLE);
        }
    }
}
