package hagai.edu.servicesandnotifications;

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Hagai Zamir on 11-Jul-17.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        FirebaseInstanceId.getInstance();
        String token = FirebaseInstanceId.getInstance().getToken();

        //can't save to DB.... no user yet
        SharedPreferences prefs = getSharedPreferences("id", MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token" , token);
        //
        editor.commit();
    }
}
