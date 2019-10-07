package com.chihuasdevs.cursoandroid.fragments.principals;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.activities.menu.MenuAC;
import com.chihuasdevs.cursoandroid.activities.menu.MenuDrawerManager;
import com.chihuasdevs.cursoandroid.custom.MyCustomView;
import com.chihuasdevs.cursoandroid.statics.OpenDialogsUtil;

import androidx.fragment.app.Fragment;

public class SecondFR  extends Fragment implements MyCustomView.MyCustomViewDelegate, View.OnClickListener {


    //TAGS
    private final String TAG = SecondFR.class.getName();

    private View rootView;

    private Button showDialogBtn;
    private MyCustomView myCustomView;

    private MaterialDialog dialogMyCustomView2;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        setupMenuItems();

        if (rootView != null){

            return rootView;
        }

        rootView = inflater.inflate(R.layout.fragment_second, container, false);

        showDialogBtn = rootView.findViewById(R.id.showDialogBtn);
        showDialogBtn.setOnClickListener(this);

        myCustomView = rootView.findViewById(R.id.myCustomView);
        myCustomView.setDelegate(this);


        MenuDrawerManager.INSTANCE.addListener(new MenuDrawerManager.AMenuDrawerManagerListener() {

            @Override
            public void menuDrawerManagerOnItemClicked(MenuItem menuItem) {
                if (menuItem.getTitleCondensed().toString().equalsIgnoreCase(getResources().getString(R.string.dialog))){
                    showDialog();
                }
            }

            @Override
            public void menuDrawerManagerOnCreateOptionsMenu() {
                setupMenuItems();
            }
        });


        return rootView;
    }

    private void setupMenuItems(){
        MenuDrawerManager.INSTANCE.showDialogItemBtn(true);
        MenuDrawerManager.INSTANCE.showNoteItemBtn(false);
    }

    private void showDialog(){
        dialogMyCustomView2 = OpenDialogsUtil.Companion.openMyCustomViewDialog(getActivity(),
                new MyCustomView.MyCustomViewDelegate() {
                    @Override
                    public void myCustomViewOnOptionClicked(MyCustomView myCustomView, MyCustomView.Options option) {
                        switch (option){
                            case Negative:
                                Log.i(TAG,"Negative was clicked in customview 2");
                                break;
                            case Positive:
                                Log.i(TAG,"Positive was clicked in customview 2" + myCustomView.getTextFromMyText());
                                break;
                        }
                        dialogMyCustomView2.hide();
                    }
                });
    }

    @Override
    public void myCustomViewOnOptionClicked(MyCustomView myCustomView, MyCustomView.Options option) {
        if (myCustomView == this.myCustomView){
            switch (option){
                case Negative:
                    Log.i(TAG,"Negative was clicked in customview 1");
                    break;
                case Positive:
                    Log.i(TAG,"Positive was clicked in customview 1" + this.myCustomView.getTextFromMyText());
                    break;
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == showDialogBtn){
            showDialog();

        }
    }
}