package com.chihuasdevs.cursoandroid.activities.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.fragments.principals.StartFR;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;


public class MenuAC extends AppCompatActivity implements DrawerLayout.DrawerListener{


    //TAGS
    private final String TAG = MenuAC.class.getName();

    //things for side menu
    private Toolbar toolbar;
    protected DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    protected ActionBarDrawerToggle mDrawerToggle;
    private String[] mTitles;

    //items in the toolbar
    protected MenuItem noteItem;


    //fragments
    private StartFR startFR;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        startFR = new StartFR();

        initSlideMenu(savedInstanceState);

        MenuDrawerManager.INSTANCE.init(this);
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if (manager.getBackStackEntryCount() == 0){

            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);

            return;
        }

        if (!MenuDrawerManager.INSTANCE.isBackButtonEnabled() && !MenuDrawerManager.INSTANCE.isForcingGoBack()){
            return;
        }


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        noteItem = menu.findItem(R.id.noteItem);

        MenuDrawerManager.INSTANCE.executeListenersForOnCreateOptionsMenu();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Log.i(TAG, "selected item id "+ item.getItemId());
        if (item.getTitleCondensed().toString().equalsIgnoreCase(getResources().getString(R.string.note_btn))){
            MenuDrawerManager.INSTANCE.executeListenersForNoteItemClicked();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {

    }

    @Override
    public void onDrawerClosed(View drawerView) {

    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }




    private String[] getMenuTitles(){
        mTitles = getResources().getStringArray(R.array.menu_options);



        List<Integer> indicesToRemove = new ArrayList<>();
        List<String> list = new ArrayList<String>(Arrays.asList(mTitles));


        Collections.sort(indicesToRemove, Collections.reverseOrder());
        for (int i : indicesToRemove) {
            list.remove(i);
        }

        mTitles = list.toArray(new String[0]);

        return mTitles;
    }

    //slideMenu
    private void initSlideMenu( Bundle savedInstanceState){

        //please don´t forget add the xml

        //mTitle = mDrawerTitle = getTitle();
        mTitles = getMenuTitles();


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(this);
        //disable side touches
        //mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);

        mDrawerList =  findViewById(R.id.left_drawer);


        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        //getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                toolbar,
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                // getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        if (savedInstanceState == null) {
            selectItem(-1);
        }
    }

    protected void selectItem(int position) {

        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        switch (position){
            case -1: {
                // update the main content by replacing fragments
                openStartFR();
                break;
            }
            case 0:{
                openStartFR();
                break;
            }
            default:{ //signOut
                //ActivitiesManager.openLoginAC(this);
                //SessionManager.INSTANCE.setSession(null);
                this.finish();
                break;
            }
        }

        if (position != -1){
            // update selected product and title, then close the drawer
            //mDrawerList.setItemChecked(position, true);
            //setTitle(mPlanetTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        }


    }

    private void openStartFR(){
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, startFR).commit();
    }

}