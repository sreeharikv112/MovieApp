package com.dev.movieapp.ui.fragments;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.dev.movieapp.R;
import com.dev.movieapp.app.MovieApp;
import com.dev.movieapp.dipinject.components.InjectionSubComponent;
import com.dev.movieapp.ui.uiinterfaces.AlertCallBack;

/**
 * Base Fragment for all Fragments
 * Handles image loading
 * Showing Alert view
 * Handling alert call backs
 */

public abstract class BaseFrag extends Fragment implements AlertCallBack {

    private boolean mIsInjectionComponentUsed = false;
    protected AlertDialog mCallBackAlertDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected InjectionSubComponent getInjectionComponent() {

        if (mIsInjectionComponentUsed) {
            throw new IllegalStateException("should not use Injection more than once.");
        }
        mIsInjectionComponentUsed = true;
        return ((MovieApp) getActivity().getApplication())
                .getApplicationComponent()
                .newInjectionComponent();
    }

    protected void loadImage(String imagePath, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.imdb_icon)
                .error(R.mipmap.image_error)
                .priority(Priority.NORMAL);
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
    public void showMessage(String message, int positiveBtnText, int negativeBtnText) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
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

    @Override
    public void handleNegativeAlertCallBack() {
        mCallBackAlertDialog.dismiss();
    }

    @Override
    public void handlePositiveAlertCallBack() {
        mCallBackAlertDialog.dismiss();
    }
}
