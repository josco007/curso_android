package com.chihuasdevs.cursoandroid.activities.manager;

import android.app.Activity;
import android.content.Intent;

import com.chihuasdevs.cursoandroid.activities.menu.MenuAC;

public class ActivitiesManager {


    public static void openMenuAC(Activity activity ){
        Intent intent = new Intent(activity, MenuAC.class);
        activity.startActivity(intent);
    }
}