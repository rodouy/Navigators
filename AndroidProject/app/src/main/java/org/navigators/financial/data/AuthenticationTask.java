package org.navigators.financial.data;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.navigators.financial.financialcenter.R;
import org.navigators.financial.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;

/**
 * Created by rodolfo on 1/20/16.
 */
public class AuthenticationTask extends AsyncTask<Void, Void, User> {

    private final String LOG_TAG = AuthenticationTask.class.getSimpleName();

    String username;
    String password;
    String latitude;
    String longitude;

    //Create the constructor so we can pass the parameters
    public AuthenticationTask(String username, String password,
                              String latitude, String longitude){
        this.username = username;
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public User getUserDataFromJson(String authenticationJsonString,
                                    String username, String password)
        throws JSONException {

        User userReturn = null;
        //These are the names of the JSON objects that need to be extracted
        //example
        //{"status":"1","group":"Workday","entity":"MIL","lastlogin":"1\/15\/2016 9:21:24 AM","admin":"0","adminapps":[]}
        final String TAG_STATUS = "status";
        final String TAG_GROUP = "group";
        final String TAG_ENTITY = "entity";
        final String TAG_LASTLOGIN = "lastlogin";
        final String TAG_LASTPWDCHG = "lastpwdchg";
        final String TAG_ADMIN = "admin";

        JSONObject responseJson = new JSONObject(authenticationJsonString);
        String statusJson = responseJson.getString(TAG_STATUS);
        String groupJson;
        String entityJson;
        String lastloginJson;
        String lastpwdchgJson;
        String adminJson;

        if(statusJson == "0")
        {
            String errornum = responseJson.getString("errornum");
            String errormsg = responseJson.getString("errormsg");
            return userReturn;
        }
        else
        {
            groupJson = responseJson.getString(TAG_GROUP);
            entityJson = responseJson.getString(TAG_ENTITY);
            lastloginJson = responseJson.getString(TAG_LASTLOGIN);
            lastpwdchgJson = responseJson.getString(TAG_LASTPWDCHG);
            adminJson = responseJson.getString(TAG_ADMIN);
        }

        userReturn = new User(username, password);

        userReturn.setStatus(Boolean.valueOf(statusJson));
        userReturn.setGroup(groupJson);
        userReturn.setEntity(entityJson);
        userReturn.setLastlogin(Date.valueOf(lastloginJson));
        userReturn.setLastpwdchg(Date.valueOf(lastpwdchgJson));
        userReturn.setAdmin(Boolean.valueOf(adminJson));


        return userReturn;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //before making authentication call

    }

    @Override
    protected User doInBackground(Void... params) {

        if(this.username.length() == 0 || this.password.length() == 0){
            return null;
        }

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String authenticationJsonString = null;

        /* "appkey": "A161E24E56E9434CA4EC62535E18EB0F",
         * "username": "111112",
         * "password": "urk887",
         * "latitude": "38.833882",
         * "longitude": "-104.821363"
         * */
        String format = "json";
        String appkey = String.valueOf(R.string.app_key);
        String username = this.username;
        String password = this.password;
        String latitude = this.latitude;
        String longitude = this.longitude;

        try {
            //construct the URL
            final String AUTHENTICATION_BASE_URL = String.valueOf(R.string.authentication_url);
            final String APPKEY_PARAM = "appkey";
            final String USERNAME_PARAM = "username";
            final String PASSWORD_PARAM = "password";
            final String LATITUDE_PARAM = "latitude";
            final String LONGITUDE_PARAM = "longitude";

            Uri builtUri = Uri.parse(AUTHENTICATION_BASE_URL).buildUpon()
                    .appendQueryParameter(APPKEY_PARAM, appkey)
                    .appendQueryParameter(USERNAME_PARAM, username)
                    .appendQueryParameter(PASSWORD_PARAM, password)
                    .appendQueryParameter(LATITUDE_PARAM, latitude)
                    .appendQueryParameter(LONGITUDE_PARAM, longitude)
                    .build();
            URL url = new URL(builtUri.toString());

            Log.v(LOG_TAG, "Built URI " + builtUri.toString());

            //create the request and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if(inputStream == null){
                //nothing to do
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line = reader.readLine()) != null){
                //since it's JSON adding a newLine isn't necessary (it won't affect parsing)
                //but it does make debugging a *lot* easier if you print out the completed
                //buffer for debugging
                buffer.append(line + "\n");
            }

            if(buffer.length() == 0){
                //Stream was empty. no point in parsing
                return null;
            }

            authenticationJsonString = buffer.toString();

            Log.v(LOG_TAG, "Authentication JSON String " + authenticationJsonString);

        } catch (IOException e){
            Log.e(LOG_TAG, "Error", e);
            return null;
        } finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader != null){
                try {
                    reader.close();
                }catch (final IOException e){
                    Log.e(LOG_TAG, "Error closing stream",e);
                }
            }
        }

        try{
            return getUserDataFromJson(authenticationJsonString, username, password);
        } catch (JSONException e){
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }

        return null;
    }
}
