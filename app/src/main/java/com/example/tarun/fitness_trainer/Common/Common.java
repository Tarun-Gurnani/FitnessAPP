package com.example.tarun.fitness_trainer.Common;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.tarun.fitness_trainer.Model.User;


public class Common {

    public static User currentUser;

    public static final String USER_KEY = "User";

    public static final String PWD_KEY = "Password";

    public static boolean isConnectedInternet( Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService ( Context.CONNECTIVITY_SERVICE);
        if(connectivityManager != null)
        {
            NetworkInfo[] info = connectivityManager.getAllNetworkInfo ();
            if(info != null)
            {
                for(int i=0;i<info.length;i++)
                {
                    if(info[i].getState () == NetworkInfo.State.CONNECTED)
                        return true;
                }
            }
        }
        return false;
    }


}