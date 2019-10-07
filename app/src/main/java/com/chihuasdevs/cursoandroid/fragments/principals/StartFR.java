package com.chihuasdevs.cursoandroid.fragments.principals;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.activities.menu.MenuAC;
import com.chihuasdevs.cursoandroid.activities.menu.MenuDrawerManager;
import com.chihuasdevs.cursoandroid.fragments.utils.FragmentNavigationUtil;

import java.util.List;

import androidx.fragment.app.Fragment;

public class StartFR  extends Fragment  implements View.OnClickListener{



    //TAGS
    private final String TAG = StartFR.class.getName();

    private View rootView;

    private Button startBtn;


    private  MenuDrawerManager.AMenuDrawerManagerListener aMenuDrawerManagerListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        MenuDrawerManager.INSTANCE.showBackButton(false);

        if (rootView != null){

            return rootView;
        }

        rootView = inflater.inflate(R.layout.fragment_start, container, false);

        startBtn = rootView.findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);


        aMenuDrawerManagerListener = new MenuDrawerManager.AMenuDrawerManagerListener() {
            @Override
            public void menuDrawerManagerOnItemClicked(MenuItem menuItem) {
                if (menuItem.getTitleCondensed().toString().equalsIgnoreCase(getResources().getString(R.string.note_btn))){
                    Log.i(TAG, "noteBtn was clicked in StartFR");
                }
            }

            @Override
            public void menuDrawerManagerOnCreateOptionsMenu() {
                MenuDrawerManager.INSTANCE.showNoteItemBtn(true);
                MenuDrawerManager.INSTANCE.showDialogItemBtn(false);
            }
        };


        MenuDrawerManager.INSTANCE.addListener(aMenuDrawerManagerListener);


        return rootView;
    }

    @Override
    public void onDestroy() {
        MenuDrawerManager.INSTANCE.removeListener(aMenuDrawerManagerListener);
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        if (view == startBtn){
            Log.i(TAG, "startBtn was clicked");
            FragmentNavigationUtil.openSecondFR(getActivity());
        }

    }
}