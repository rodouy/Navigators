package org.navigators.financial.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by rodolfo on 1/21/16.
 */
public class AlertDialogManager {
    /**
     * Function to display simple Alert Dialog
     * @param context - application context
     * @param title - alert dialog title
     * @param message - alert message
     * */
    public void showAlertDialog(Context context,
                                String title,
                                String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Setting Dialog Title
        builder.setTitle(title);
        //Setting Dialog Message
        builder.setMessage(message);

        builder.setCancelable(false);

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }



}
