package com.blitzkrieg.musictrivia_v2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import java.util.Timer;
import java.util.TimerTask;

//-----------------------SPLASH ACTIVITY (START)------------------------
public class SplashActivity extends Activity {

    MediaPlayer intro;
    int duration;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashlayout);

        intro =MediaPlayer.create(SplashActivity.this,R.raw.f);//Initialise the into
        intro.start();
        duration= intro.getDuration();
        TimerTask task=new TimerTask()
        {

            public void run()
            {

                finish();
                intro.stop();
                Intent mainIntent=new Intent().setClass(SplashActivity.this, MainMenu.class);//Transfer control to MainMenu after Task being finished
                startActivity(mainIntent);
            }

        };




        Timer timer=new Timer();
        timer.schedule(task,3*1000);//Method executed after the duration is over-------

    };
}
