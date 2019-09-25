package com.chihuasdevs.cursoandroid.activities.menu;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


public class MenuDrawerManager {


    private interface IMenuDrawerManagerListener{
        void menuDrawerManagerOnItemClicked();
        void menuDrawerManagerOnCreateOptionsMenu();
    }

    public static abstract class AMenuDrawerManagerListener implements IMenuDrawerManagerListener {
        @Override
        public void menuDrawerManagerOnItemClicked(){}

        @Override
        public void menuDrawerManagerOnCreateOptionsMenu() { }
    }

    private boolean mToolBarNavigationListenerIsRegistered = false;
    private MenuAC menuAC;
    private boolean backButtonEnabled;
    private boolean forceGoBack;
    private List<AMenuDrawerManagerListener> listeners = new ArrayList<>();
    public static final MenuDrawerManager INSTANCE = new MenuDrawerManager();


    private MenuDrawerManager(){

    }

    public void init(MenuAC menuAC){
        this.menuAC = menuAC;
    }

    public void addListener(AMenuDrawerManagerListener aMenuDrawerManagerListener){
        listeners.add(aMenuDrawerManagerListener);
    }

    public void removeListener(AMenuDrawerManagerListener aMenuDrawerManagerListener){
        listeners.remove(aMenuDrawerManagerListener);
    }

    protected void executeListenersForNoteItemClicked(){
        for(AMenuDrawerManagerListener aMenuDrawerManagerListener : listeners){
            aMenuDrawerManagerListener.menuDrawerManagerOnItemClicked();
        }
    }

    protected void executeListenersForOnCreateOptionsMenu(){
        for(AMenuDrawerManagerListener aMenuDrawerManagerListener : listeners){
            aMenuDrawerManagerListener.menuDrawerManagerOnCreateOptionsMenu();
        }
    }

    public void hideMainButton(boolean hide){
        menuAC.getSupportActionBar().setDisplayHomeAsUpEnabled(!hide);
        menuAC.getSupportActionBar().setDisplayShowHomeEnabled(!hide);

        backButtonEnabled = !hide;
    }

    public void showNoteItemBtn(boolean show){
        if (menuAC.noteItem == null){
            return;
        }
        menuAC.noteItem.setVisible(show);
    }

    public boolean isBackButtonEnabled() {
        return backButtonEnabled;
    }

    public boolean isForcingGoBack() {
        return forceGoBack;
    }

    public void goBack(){
        forceGoBack = true;
        menuAC.onBackPressed();
        forceGoBack = false;
    }

    public void showBackButton(boolean enable) {

        backButtonEnabled = enable;

        if(enable) {

            menuAC.mDrawerToggle.setDrawerIndicatorEnabled(false);

            menuAC.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            if(!mToolBarNavigationListenerIsRegistered) {
                menuAC.mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        menuAC.onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {

            menuAC.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            menuAC.mDrawerToggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            menuAC.mDrawerToggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }

    }

    public void goStartFR(){
        menuAC.selectItem(0);
    }

}