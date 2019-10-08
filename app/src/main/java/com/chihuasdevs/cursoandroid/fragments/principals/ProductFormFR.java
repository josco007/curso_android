package com.chihuasdevs.cursoandroid.fragments.principals;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.models.Product;
import com.google.android.material.textfield.TextInputLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProductFormFR extends Fragment implements View.OnClickListener {


    //TAGS
    private final String TAG = ProductsListFR.class.getName();
    private View rootView;

    private TextInputLayout nameTil;
    private EditText nameTxt;

    private TextInputLayout barCodeTil;
    private EditText barCodeTxt;

    private TextInputLayout priceTil;
    private EditText priceTxt;

    private Button saveBtn;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        if (rootView != null){

            return rootView;
        }

        rootView = inflater.inflate(R.layout.fragment_product_form, container, false);

        nameTil = rootView.findViewById(R.id.nameTil);
        nameTxt = rootView.findViewById(R.id.nameTxt);

        barCodeTil = rootView.findViewById(R.id.barCodeTil);
        barCodeTxt = rootView.findViewById(R.id.barCodeTxt);

        priceTil = rootView.findViewById(R.id.priceTil);
        priceTxt = rootView.findViewById(R.id.priceTxt);

        saveBtn = rootView.findViewById(R.id.saveBtn);
        saveBtn.setOnClickListener(this);


        return rootView;
    }

    private boolean validateForm(){


        nameTil.setError(null);
        barCodeTil.setError(null);
        priceTil.setError(null);

        if (nameTxt.getText().length() == 0){
            nameTil.setError(getString(R.string.required));
            return false;
        }
        else if (barCodeTxt.getText().length() == 0){
            barCodeTil.setError(getString(R.string.required));
            return  false;
        }
        else if (priceTxt.getText().length() == 0){
            priceTil.setError(getString(R.string.required));
            return  false;
        }

        return true;
    }

    private Product getProductFromForm(){

        Product product = new Product();

        product.name = nameTxt.getText().toString();
        product.barCode = barCodeTxt.getText().toString();
        product.price = Double.parseDouble(priceTxt.getText().toString());

        return product;

    }

    private void save(){
        if (!validateForm()){
            return;
        }

        Product productToSave = getProductFromForm();

        Log.i(TAG, "Guardar producto con nombre: " + productToSave.name);
    }

    @Override
    public void onClick(View view) {
        if (view == saveBtn){
            save();
        }
    }
}