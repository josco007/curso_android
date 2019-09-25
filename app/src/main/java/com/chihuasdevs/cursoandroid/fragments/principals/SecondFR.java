package com.chihuasdevs.cursoandroid.fragments.principals;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.activities.menu.MenuAC;
import com.chihuasdevs.cursoandroid.custom.MyCustomView;

import androidx.fragment.app.Fragment;

public class SecondFR  extends Fragment implements MyCustomView.MyCustomViewDelegate {


    //TAGS
    private final String TAG = SecondFR.class.getName();

    private View rootView;

    private Button button1;
    private MyCustomView myCustomView;
    private MyCustomView myCustomView2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        if (rootView != null){

            return rootView;
        }

        rootView = inflater.inflate(R.layout.fragment_second, container, false);

        button1 = rootView.findViewById(R.id.button1);

        myCustomView = rootView.findViewById(R.id.myCustomView);
        myCustomView.setDelegate(this);

        myCustomView2 = rootView.findViewById(R.id.myCustomView2);
        myCustomView2.setDelegate(this);


        return rootView;
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
        else if (myCustomView == myCustomView2){
            switch (option){
                case Negative:
                    Log.i(TAG,"Negative was clicked in customview 2");
                    break;
                case Positive:
                    Log.i(TAG,"Positive was clicked in customview 2" + myCustomView.getTextFromMyText());
                    break;
            }
        }
    }
}