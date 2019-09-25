package com.chihuasdevs.cursoandroid.fragments.utils;

import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.activities.menu.MenuDrawerManager;
import com.chihuasdevs.cursoandroid.fragments.principals.SecondFR;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class FragmentNavigationUtil {


    public static void addFragment(FragmentActivity fragmentActivity, Fragment fragment, String tag) {

        //all fragments except Start has backbutton
        MenuDrawerManager.INSTANCE.showBackButton(true);


        FragmentManager manager = fragmentActivity.getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.addToBackStack(tag);
        ft.replace(R.id.content_frame, fragment, tag);
        ft.commitAllowingStateLoss();
    }


    public static void openSecondFR(FragmentActivity fragmentActivity){

        SecondFR secondFR = new SecondFR();

        //Bundle bundle  = new Bundle();
        //bundle.putSerializable("param", param);
        //scannedTrapDevicesFR.setArguments(bundle);

        FragmentNavigationUtil.addFragment(fragmentActivity,secondFR,"secondFR");
    }
}
