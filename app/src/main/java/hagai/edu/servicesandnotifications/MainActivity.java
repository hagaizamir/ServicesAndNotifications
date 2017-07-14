package hagai.edu.servicesandnotifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        //AlarmManager -> stop using it
        //API 21 and up -> Job Scheduler


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        SharedPreferences prefs = getSharedPreferences("id", MODE_PRIVATE);

        String token = prefs.getString("token", "");
        Toast.makeText(this, token, Toast.LENGTH_SHORT).show();
        Log.d("Ness", token);


        //dispatcher dispatch and cancel jobs:
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this/*context*/));



        int start = (int)java.util.concurrent.TimeUnit.HOURS.toSeconds(2);
        int end = (int) TimeUnit.HOURS.toSeconds(3);

        //Criteria for running the service
        Job job = dispatcher.newJobBuilder().
                setTag("my job tag").
                setService(MyJobService.class).
                setLifetime(Lifetime.UNTIL_NEXT_BOOT).
                setRecurring(false).
                setTrigger(Trigger.executionWindow(start, end)).
                build();
        //tag identifies the job -> cancel

        dispatcher.mustSchedule(job);
    }
}
