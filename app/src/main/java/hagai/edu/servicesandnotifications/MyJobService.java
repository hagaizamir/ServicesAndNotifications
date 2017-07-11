package hagai.edu.servicesandnotifications;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

/**
 * Created by Hagai Zamir on 11-Jul-17.
 */

public class MyJobService extends JobService {

    private static final String TAG = "NESS";

    @Override
    public boolean onStartJob(JobParameters job) {
        //
        ShowNotification ();
        return false;//is there ongoing progress
    }

    private void ShowNotification() {
        Log.d(TAG, "showNotification");
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false;//should this job be retired?
    }
}
