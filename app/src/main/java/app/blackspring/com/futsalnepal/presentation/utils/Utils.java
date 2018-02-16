package app.blackspring.com.futsalnepal.presentation.utils;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import app.blackspring.com.futsalnepal.R;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by utsavstha on 2/15/18.
 */

public class Utils {
    public static void showSnackBar(View rootView, String message) {
        Snackbar snackbar = Snackbar
                .make(rootView, message, Snackbar.LENGTH_LONG);

        snackbar.show();
    }

    public static void showSnackBar(View rootView, String message, String actionName, SnackBarListener snackBarListener) {
        Snackbar snackbar = Snackbar
                .make(rootView, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(actionName, view -> {
            snackbar.dismiss();
            snackBarListener.onClick();
        });


        snackbar.show();
    }

    public static void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public interface SnackBarListener {
        void onClick();
    }

    public static void toggleProgress(ViewSwitcher viewSwitcher, View mainView, View progressView) {
        if (viewSwitcher.getCurrentView() != mainView) {
            viewSwitcher.showPrevious();
        } else if (viewSwitcher.getCurrentView() != progressView) {
            viewSwitcher.showNext();
        }
    }

}
