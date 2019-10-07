package com.chihuasdevs.cursoandroid.statics

import android.content.Context
import android.view.View
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.chihuasdevs.cursoandroid.custom.MyCustomView

class OpenDialogsUtil {
    companion object {

        fun openMaterialDialog(context: Context, view: View): MaterialDialog {

            return MaterialDialog(context)
                    .cancelable(false)
                    .show {
                        customView(view = view)
                    }

        }

        fun openMyCustomViewDialog(context: Context,
                                   delegate:MyCustomView.MyCustomViewDelegate): MaterialDialog{

            var myCustomView:MyCustomView = MyCustomView(context);
            myCustomView.setDelegate(delegate)

            return MaterialDialog(context).
                    cancelable(false)
                    .show {
                        customView(view= myCustomView)
                    }
        }
    }
}