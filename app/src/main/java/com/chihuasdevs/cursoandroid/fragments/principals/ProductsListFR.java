package com.chihuasdevs.cursoandroid.fragments.principals;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.activities.menu.MenuDrawerManager;
import com.chihuasdevs.cursoandroid.fragments.utils.FragmentNavigationUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProductsListFR extends Fragment implements View.OnClickListener {


    //TAGS
    private final String TAG = ProductsListFR.class.getName();
    private View rootView;


    private Button addBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        MenuDrawerManager.INSTANCE.showBackButton(false);

        if (rootView != null){

            return rootView;
        }

        rootView = inflater.inflate(R.layout.fragment_products_list, container, false);

        addBtn = rootView.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view == addBtn){
            FragmentNavigationUtil.openProductFormFR(getActivity());
        }
    }
}
