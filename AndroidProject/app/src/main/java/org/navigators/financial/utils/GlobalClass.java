package org.navigators.financial.utils;

import android.app.Application;

/**
 * Created by rodolfo on 1/20/16.
 */
public class GlobalClass extends Application {

    //region Fields
    private String username;
    private String password;
    private boolean status;

    //endregion;

    //region Properties
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    //endregion;

    //region Methods



    //endregion
}
