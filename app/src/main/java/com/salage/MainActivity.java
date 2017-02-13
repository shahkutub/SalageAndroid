package com.salage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.icteuro.salage.R;
import com.salage.model.JsonInfo;
import com.salage.model.JsonStructure;

import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener{

    Context con;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    private NavigationView navigationView;
    TextView tvToolBarTitle;
    private LinearLayout sohokariBrindo,podobiBittik ,sohoKariCommissioner,upojelaOfficer,upoPorichalok,otiriktoMejistrate,
            otiriktJelaProsasok,jelaProsasok,kormokortaBrindo,jelaSomuho,bivagPorichiti,prochchod,linBivagPorichiti,jelas,kormokorta
            ,jelaprosokGon,otirikktoJelagon,otiriktoMejisGon,upoPorichalokSthanio,upjelNirbahigon,sohoKomiVumi,podVittik,sokrriGon;

    private ScrollView buttonView;
    ImageView menuSetting;
    Activity activity;
    private Handler handler;
    private boolean isTimerRunning;
    Runnable Update;
    Timer swipeTimer;
    private ViewPager launchViewpager;
    public static int[] imageRSC = {R.drawable.first,R.drawable.second};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.t1);

        con = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
        menuSetting = (ImageView)toolbar.findViewById(R.id.img_home);
        menuSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonView.setVisibility(View.GONE);
                setContentFragment(new RegistrationFragement(), false,"Active Work");
                //startActivity(new Intent(con,MyWb.class));
            }
        });
        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                return false;
            }
        });



        linBivagPorichiti = (LinearLayout)findViewById(R.id.linBivagPorichiti);
        jelas = (LinearLayout)findViewById(R.id.jelas);
        kormokorta = (LinearLayout)findViewById(R.id.kormokorta);
        jelaprosokGon = (LinearLayout)findViewById(R.id.jelaprosokGon);
        otirikktoJelagon = (LinearLayout)findViewById(R.id.otirikktoJelagon);
        otiriktoMejisGon = (LinearLayout)findViewById(R.id.otiriktoMejisGon);
        upoPorichalokSthanio = (LinearLayout)findViewById(R.id.upoPorichalokSthanio);
        upjelNirbahigon = (LinearLayout)findViewById(R.id.upjelNirbahigon);
        sohoKomiVumi = (LinearLayout)findViewById(R.id.sohoKomiVumi);
        podVittik = (LinearLayout)findViewById(R.id.podVittik);
        sokrriGon = (LinearLayout)findViewById(R.id.sokrriGon);

        linBivagPorichiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new BivagPorichitiFragement(), false,"Active Work");
            }
        });


        jelas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                //setContentFragment(new JelaSomuhoFragement(), false,"Active Work");
            }
        });


        kormokorta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
               // setContentFragment(new KromokortaBrindoFragement(), false,"Active Work");
            }
        });


        jelaprosokGon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new JelaProsasokFragement(), false,"Active Work");
            }
        });


        otirikktoJelagon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new OtiriktojelaFragement(), false,"Active Work");
            }
        });


        otiriktoMejisGon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new OtiriktoMejistrateFragement(), false,"Active Work");
            }
        });


        upoPorichalokSthanio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new Upoporichalokragement(), false,"Active Work");
            }
        });


        upjelNirbahigon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new UpojelaNirbahiFragement(), false,"Active Work");
            }
        });


        sohoKomiVumi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new SohokariKomissonerragement(), false,"Active Work");
            }
        });


        podVittik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new PodobiVittikFragement(), false,"Active Work");
            }
        });

        sokrriGon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new SohokariBrindoFragement(), false,"Active Work");
            }
        });


        prochchod = (LinearLayout)findViewById(R.id.prochchod);
        bivagPorichiti = (LinearLayout)findViewById(R.id.bivagPorichiti);
        jelaSomuho = (LinearLayout)findViewById(R.id.jelaSomuho);
        kormokortaBrindo = (LinearLayout)findViewById(R.id.kormokortaBrindo);
        jelaProsasok = (LinearLayout)findViewById(R.id.jelaProsasok);
        otiriktJelaProsasok = (LinearLayout)findViewById(R.id.otiriktJelaProsasok);
        otiriktoMejistrate = (LinearLayout)findViewById(R.id.otiriktoMejistrate);
        upoPorichalok = (LinearLayout)findViewById(R.id.upoPorichalok);
        upojelaOfficer = (LinearLayout)findViewById(R.id.upojelaOfficer);
        sohoKariCommissioner = (LinearLayout)findViewById(R.id.sohoKariCommissioner);
        podobiBittik = (LinearLayout)findViewById(R.id.podobiBittik);
        sohokariBrindo = (LinearLayout)findViewById(R.id.sohokariBrindo);
        buttonView = (ScrollView) findViewById(R.id.buttonView);




        sohokariBrindo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new SohokariBrindoFragement(), false,"Active Work");
            }
        });

        podobiBittik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new PodobiVittikFragement(), false,"Active Work");
            }
        });

        sohoKariCommissioner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new SohokariKomissonerragement(), false,"Active Work");
            }
        });

        upoPorichalok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new Upoporichalokragement(), false,"Active Work");
            }
        });

        otiriktoMejistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new OtiriktoMejistrateFragement(), false,"Active Work");
            }
        });

        otiriktJelaProsasok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new OtiriktojelaFragement(), false,"Active Work");
            }
        });

        jelaProsasok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new JelaProsasokFragement(), false,"Active Work");
            }
        });

        kormokortaBrindo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
               // setContentFragment(new KromokortaBrindoFragement(), false,"Active Work");
            }
        });

        jelaSomuho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
               // setContentFragment(new JelaSomuhoFragement(), false,"Active Work");
            }
        });


        bivagPorichiti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new BivagPorichitiFragement(), false,"Active Work");
            }
        });



        prochchod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.VISIBLE);
            }
        });
        upojelaOfficer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers();
                buttonView.setVisibility(View.GONE);
                setContentFragment(new UpojelaNirbahiFragement(), false,"Active Work");

            }
        });

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

        //setContentFragment(new MainFragment(), false,"Active Work");


        handler = new Handler();
        createSwipeTimer();
        isTimerRunning = true;
        activity =this;
//        TextView textViewDescription = (TextView)getView ().findViewById(R.id.textViewDescription);
//        textViewDescription.setText(Html.fromHtml(getString(R.string.text_cose_kimik)));
        launchViewpager = (ViewPager) findViewById(R.id.launchViewpager);
        launchViewpager.setAdapter(new Adapter(con, imageRSC, activity));
        launchViewpager.setCurrentItem(0);
        launchViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                    isTimerRunning = false;
                    swipeTimer.cancel();
                } else {
                    if (!isTimerRunning) {
                        createSwipeTimer();
                        isTimerRunning = true;
                    }
                }
            }
        });


        Update = new Runnable() {
            @Override
            public void run() {

                if (imageRSC != null && imageRSC.length > 0) {
                    int currentImg = launchViewpager.getCurrentItem();
                    currentImg++;
                    if (currentImg == imageRSC.length) {
                        currentImg = 0;
                    }
                    launchViewpager.setCurrentItem(currentImg, true);
                }

				/*
				 * if (currentPage == AllMenuImgInfo.getAllMenuImgInfo().size())
				 * { currentPage = 0; }
				 * MainViewPager.setCurrentItem(currentPage++, true);
				 */
            }
        };



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

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }

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

    private void createSwipeTimer() {
        swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);


    }
}
