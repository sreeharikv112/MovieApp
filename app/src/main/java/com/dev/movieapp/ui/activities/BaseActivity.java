package com.dev.movieapp.ui.activities;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.dev.movieapp.R;
import com.dev.movieapp.app.MovieApp;
import com.dev.movieapp.dipinject.components.InjectionSubComponent;
import com.dev.movieapp.dipinject.modules.NetworkModule;
import com.dev.movieapp.ui.uiinterfaces.AlertCallBack;

import java.io.File;


/**
 * Base Activity class
 * Manages Dagger component
 * Initiates File cache for OkHttp
 * Handles image loading
 */
public abstract class BaseActivity extends AppCompatActivity implements AlertCallBack {

    private boolean mIsInjectionComponentUsed = false;

    protected AlertDialog mCallBackAlertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected InjectionSubComponent getInjectionComponent(){

        if (mIsInjectionComponentUsed) {
            throw new IllegalStateException("should not use Injection more than once.");
        }
        mIsInjectionComponentUsed=true;
        return ((MovieApp)getApplication())
                .getApplicationComponent()
                .newInjectionComponent();
    }

    public abstract void renderView();

    public abstract void init();

    protected void loadImage(String imagePath, ImageView imageView){
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.imdb_icon)
                .error(R.mipmap.image_error)
                .priority(Priority.NORMAL)
                ;
        RequestBuilder<Drawable> requestBuilder = Glide.with(this)
                .load(imagePath);
                requestBuilder.apply(requestOptions)
                        .into(imageView);
    }
    /**
     * Show alert message with call back
     *
     * @param message         message to show in alert
     * @param positiveBtnText positive button text
     * @param negativeBtnText negative button text
     */
    protected void showMessage(String message, int positiveBtnText, int negativeBtnText) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setMessage(message);

        dialogBuilder.setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                handlePositiveAlertCallBack();
            }
        });
        dialogBuilder.setNegativeButton(negativeBtnText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                handleNegativeAlertCallBack();
            }
        });
        mCallBackAlertDialog = dialogBuilder.create();
        mCallBackAlertDialog.setCancelable(false);
        mCallBackAlertDialog.show();
    }

    public void handleNegativeAlertCallBack() {
        mCallBackAlertDialog.dismiss();
    }

    public void handlePositiveAlertCallBack() {
        mCallBackAlertDialog.dismiss();
    }

}
