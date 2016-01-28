package org.navigators.financial.data;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.navigators.financial.financialcenter.LoginScreen;

import java.util.HashMap;

/**
 * Created by rodolfo on 1/21/16.
 * All session related functions in one class to make them available in all activities.
 */
public class SessionManager {

    //Shared Preferences
    SharedPreferences sharedPreferences;

    //Editor for Shared Preferences
    Editor editor;

    //Context
    Context _context;

    //Shared Preferences mode
    int PRIVATE_MODE = 0;

    //Shared Preferences file name
    private static final String SHARED_PREFERENCE_NAME = "SessionData";

    //region All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    //User name
    public static final String KEY_USERNAME = "username";

    //Password
    private static final String KEY_PASSWORD = "password";

    //endregion

    //region Constructor
    public SessionManager(Context context){
        this._context = context;
        sharedPreferences = _context.getSharedPreferences(
                SHARED_PREFERENCE_NAME,
                PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }
    //endregion


    //region Methods

    /*
    * Create login session
    * */
    public void createLoginSession(String username, String password){
        //Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        //Storing username in Shared Preferences
        editor.putString(KEY_USERNAME, username);

        //Storing password in Shared Preferences
        editor.putString(KEY_PASSWORD, password);

        //commit changes
        editor.commit();
    }


    /*
    * Get stored session data
    * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();

        //username
        user.put(KEY_USERNAME, sharedPreferences.getString(KEY_USERNAME, null));

        //password
        user.put(KEY_PASSWORD, sharedPreferences.getString(KEY_PASSWORD, null));

        return user;
    }

    /*
    * Quick check for login
    * */
    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGIN, false);
    }


    /*
    * Check login method will check user login status
    * If false it will redirect user login screen
    * Else won't do anything
    * */
    public void chechLogin(){
        //Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent intent = new Intent(_context, LoginScreen.class);

            // Closing all the activities
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // All new flag to start new activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Starting login activity
            _context.startActivity(intent);
        }
    }


    /*
    * Clear session details
    * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Login Activity
        Intent intent = new Intent(_context, LoginScreen.class);

        // Closing all the Activities
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Starting Login Activity
        _context.startActivity(intent);
    }

    //endregion
}
