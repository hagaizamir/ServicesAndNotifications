package hagai.edu.servicesandnotifications;

import android.util.Log;

import com.firebase.jobdispatcher.JobParameters;

/**
 * presents a job to be dispatched while the device is idle
 */

public class MyJobService extends com.firebase.jobdispatcher.JobService {
    private static final String TAG = "Ness";

    @Override
    public boolean onStartJob(final JobParameters job) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //fake some work:
                try {Thread.sleep(1000);} catch (InterruptedException ignored) {}
                showNotification();
                jobFinished(job, true);
            }
        });
        t.start();


        return true; //is there ongoing work?
    }

    //No UI!
    private void showNotification() {
        Log.d(TAG, "showNotification: ");
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        return false; // should this job be retired?
    }
}