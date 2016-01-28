package org.navigators.financial.financialcenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import org.navigators.financial.data.AuthenticationTask;
import org.navigators.financial.data.SessionManager;
import org.navigators.financial.model.User;

import java.util.HashMap;


/**
 * Created by rodolfo on 1/19/16.
 */
public class SplashScreen extends Activity {


    String username;
    String password;
    String latitude;
    String longitude;
    String status;

    SessionManager sessionManager;
    User user;


    //to wait for specific time and once the timer is out we launched main activity.
    private static int SPLASH_TIME_OUT = 2000;


    //Calling Application class
    //final GlobalClass globalVariable = (GlobalClass) getApplicationContext();

    //get name and password in global/application context
    //username = globalVariable.getUsername();
    //password = globalVariable.getPassword();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        //Session Manager
        sessionManager = new SessionManager(getApplicationContext());

        //Login Status
        status = String.valueOf(sessionManager.isLoggedIn());
        HashMap<String, String> userHash;
        if(status == "1"){

            userHash = sessionManager.getUserDetails();
            username = userHash.get("username");
            password = userHash.get("password");
            latitude = "38.833882";
            longitude = "-104.821363";
            /*
            * Async Task to make http call
            * */
            new AuthenticationTask(username, password, latitude, longitude).execute();

        }
        else{

            // to wait for specific time and once the timer is out we launched main activity.
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    //This method will be executed once the timer is over
                    //Start the app main activity
                    Intent i = new Intent(SplashScreen.this, LoginScreen.class);
                    startActivity(i);

                    //close this activity
                    finish();
                }
            }, SPLASH_TIME_OUT);
        }

        /**
         * Showing splashscreen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */








    }



}
