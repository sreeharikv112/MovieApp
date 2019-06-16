package com.dev.movieapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.dev.movieapp.ui.fragments.detailfrag.ResultDetailFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * Application Utility
 */

public class AppUtils {

    public Context mContext;

    public AppUtils(Context context) {
        mContext = context;
    }

    /**
     * Network connectivity validation
     *
     * @return
     */
    public boolean isNetworkConnected() {
        boolean status = false;
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
            status = true;
        }
        return status;
    }

    /**
     * Returns formatted date
     *
     * @param inputDate input data
     * @return
     */
    public String getFormattedDate(String inputDate) {
        //2018-06-06
        String date = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date newDate = format.parse(inputDate);

            format = new SimpleDateFormat("dd MMM yyyy");
            date = format.format(newDate);
        } catch (ParseException e) {
            Log.e(ResultDetailFragment.class.getSimpleName(), "Date ParseException = " + e.toString());
        }
        return date;
    }
}
