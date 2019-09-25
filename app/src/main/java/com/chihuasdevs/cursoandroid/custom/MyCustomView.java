package com.chihuasdevs.cursoandroid.custom;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.chihuasdevs.cursoandroid.R;

import androidx.annotation.Nullable;

public class MyCustomView extends RelativeLayout implements View.OnClickListener {


    public enum Options{
        Positive,
        Negative
    }

    public interface MyCustomViewDelegate{
        void myCustomViewOnOptionClicked(MyCustomView myCustomView, Options option);
    }

    private MyCustomViewDelegate delegate;

    private EditText myTextTxt;
    private Button negativeBtn;
    private Button positiveBtn;


    public MyCustomView(Context context) {
        super(context);
        init(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context pContex){
        View.inflate(pContex, R.layout.custom_my_custom_view, this);//archivo xml

        myTextTxt = findViewById(R.id.myTextTxt);
        negativeBtn = findViewById(R.id.negativeBtn);
        negativeBtn.setOnClickListener(this);
        positiveBtn = findViewById(R.id.positiveBtn);
        positiveBtn.setOnClickListener(this);

    }

    public void setDelegate(MyCustomViewDelegate delegate) {
        this.delegate = delegate;
    }

    public String getTextFromMyText(){
        return myTextTxt.getText().toString();
    }

    @Override
    public void onClick(View view) {
        if (view == negativeBtn){
            if (delegate != null){
                delegate.myCustomViewOnOptionClicked(this,Options.Negative);
            }
        }
        else if (view == positiveBtn){
            if (delegate != null){
                delegate.myCustomViewOnOptionClicked(this,Options.Positive);
            }
        }
    }
}