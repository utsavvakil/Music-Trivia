package com.blitzkrieg.musictrivia_v2;

/**
 * Created by arpit on 8/31/13.
 */
//-----------------------------------------MAIN ACTIVITY -------------------------------

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MusicTrivia extends Activity {
    MediaPlayer mp= new MediaPlayer();//object variable to play the song
    String[][] store = new String [10][1000];//String for storing paths
    Button button1,button2,button3;//declaration of buttons
    TextView question;   //text views
     Typeface font;         //fonts
    Cursor cursor;
    
    TimerTask task,task3;
    Timer task2;

    Chronometer mChronometer;
    //----------------------------------------Integer variables------------------------
    int k,r, count=0,c=0 ;
    int totalSongCount=0;
    int songcount=0;
    int count_true=0;
    int assignSuccess=0;
    int res=0;
    long total;
    int songs[]=new int[1000];
    int duration,position=0;
    int mpPausePosition =0;
    long timeWhenStopped = 0;
    long timestopped = 0;
    TextView txtan,displayscore;

    // Animation
    Animation animFadein,animSlide,animblinkless;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_new);
        mChronometer = (Chronometer)findViewById(R.id.chronometer);
        displayscore=(TextView)findViewById((R.id.displayscore));
        displayscore.setText("" + count);
        txtan=(TextView)findViewById(R.id.slide);
        Timer task2 = new Timer();

//my code

         task3=new TimerTask() {
            @Override

            public void run() {

                System.out.println("OKAAAAAAA");
                //----------------------------------------------------------------------
                //now it becomes red only when chronometer's timer reaches 50 seconds
                //---------------------------------------------------------------------
                if( timestopped*-1>=50*1000){
                    runOnUiThread(new Runnable() {
                        public void run() {
                    System.out.print("BASE"+( SystemClock.elapsedRealtime() - mChronometer.getBase()));
                    mChronometer.setVisibility(View.VISIBLE);
                    mChronometer.setTextColor(Color.parseColor("#e60f0f"));
                    mChronometer.startAnimation(animFadein);
                        }});

            }
            }
        };

        task2.schedule(task3,1000,1000);
        animFadein = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        animSlide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slideup);
        animblinkless=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blinkless);




        // start the animation

        //------------------------------------------------------Initialization for buttons-------------------
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);

        question=(TextView)findViewById(R.id.textView);
        font=Typeface.createFromAsset(getAssets(), "Base 02.ttf");//--------CHANGING THE FONT-----------------

        question.setTypeface(font);
//---------------------------------------SETTING TRUE FOR MARQUEE------------------------
        button1.setSelected(true);
        button2.setSelected(true);
        button3.setSelected(true);
        //-------------------------Calling function for the first time----------------
        ListAllSongs();
        random_song();
    }


    //-------------------------------------------- Fetching media files--------------------------------------------
    private String[] STAR = { "*" };
    public void ListAllSongs()  {

        Uri allsongsuri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";
        int i=0;
        if (isSdPresent()) {
            cursor = managedQuery(allsongsuri, STAR, selection, null, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        String songname = cursor
                                .getString(cursor
                                        .getColumnIndex(MediaStore.Audio.Media.TITLE));
                        store[1][i]=songname;

                        String fullpath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DATA));

                        store[0][i]=fullpath;
                        i++;
                        position++;
                        totalSongCount++;
                    } while (cursor.moveToNext() );
                }
            }
        }
    }
    //--------------------------------------------check if the sd is present----------------------------
    public static boolean isSdPresent()
    {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }


    public void animate_blink(){
        //--------------------------------------
        //same change as task 3 over here
        //-------------------------------------
      if( timestopped*-1>=50*1000){
          System.out.print("BASE"+( SystemClock.elapsedRealtime() - mChronometer.getBase()));
          mChronometer.setVisibility(View.VISIBLE);
          mChronometer.setTextColor(Color.parseColor("#e60f0f"));
          mChronometer.startAnimation(animFadein);

      }
    }
    public void animate_slide(){
        txtan.setTextColor(Color.parseColor("#1196d8"));
        txtan.startAnimation(animSlide);
    }
    public void animate_blink_less(){
        displayscore.setTextColor(Color.parseColor("#1196d8"));
        displayscore.startAnimation(animblinkless);

    }

    //----------------------------------------Randomizing the song----------------------------------------
    public void random_song(){
        //Generation random song
        Random randomGenerator = new Random();
        int randomInt[]=new int [5];
        for(int i=0;i<3;i++)
        {
            do {
                randomInt[i]= randomGenerator.nextInt(position);

            }while(checkRepitition(randomInt[i]));
            assignSuccess++;
            songs[songcount++]=randomInt[i];
        }
        r=randomGenerator.nextInt(3);
        k=randomInt[r];
        //---------------------------------playing the song---------------------
        try {
            mp.reset();
            mp.setDataSource(store[0][k]);
            mp.prepare();
            mp.start();
            duration=mp.getDuration();
            mp.seekTo(duration/(k+2));
        } catch (Exception e) {

        }
        //----------------------------------Set the text according to the song generated------------------------------
        if(((assignSuccess+1)/3)>30){
            mp.stop();
            Intent myIntent = new Intent(MusicTrivia.this,Next.class);
            myIntent.putExtra("count",count);
            myIntent.putExtra("c",c);
            myIntent.putExtra("assignSuccess",assignSuccess);
            startActivity(myIntent);
        }
       else{ button1.setText(store[1][randomInt[0]]);
        button2.setText(store[1][randomInt[1]]);
        button3.setText(store[1][randomInt[2]]);

        question.setText("question\n "+((assignSuccess+1)/3));


        question.setTypeface(font);
        }
    }
    //------------------------------------------------CHECK REPITITION----------------------------------
    public boolean checkRepitition(int n)
    {
        boolean flag=false;
        if(songcount==totalSongCount)
            songcount=0;
        for(int i=0;i<songcount;i++)
        {
            if(n==songs[i])
            {
                flag=true;
                break;
            }
        }
        return flag;
    }


    //----------------------------------------------On click of Buttons-------------------------------------------------
//---------------------Option 1--------------------
        public void opt1(View v)
        {   String s=button1.getText().toString();
            if(s== store[1][k]){
                //Correct
                ++c;
                ++count_true;
              System.out.println("count is"+count_true);
                if(count_true>2)
                    count=count+((count_true-2)*10);
                else
                    count=count+10;
                displayscore.setText("Score:\n " + count);
                //Toast.makeText(getApplicationContext(), "Correct! +10 pts ", Toast.LENGTH_SHORT).show();
                task.cancel();
                timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
                mChronometer.stop();
                animate_blink();
                animate_slide();
                animate_blink_less();
               // displayscore.setTextColor(Color.parseColor("#FFFFFF"));
                timestopped=(timeWhenStopped+2000);
                System.out.println("Correct ans "+timestopped);
                if(timestopped<0)
                    mChronometer.setBase(SystemClock.elapsedRealtime() + (timeWhenStopped+2000));
                else
                {
                    timeWhenStopped=0;
                    timestopped=0;
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
                mChronometer.start();

                timer_restart();
                random_song();
            }
            else if(s!= store[1][k]) {
                //Wrong
                displayscore.setText("Score:\n " + count);
                count_true=0;
                animate_blink();
                //Toast.makeText(getApplicationContext(), "Wrong! 0 pts ", Toast.LENGTH_SHORT).show();
                random_song();
            }
        }
    //---------------------Option 2--------------------
        public void opt2(View v)
        {   String s=button2.getText().toString();
            if(s== store[1][k]){
                //Correct
                ++c;
                ++count_true;
                if(count_true>2)
                    count=count+((count_true-2)*10);
                else
                    count=count+10;
                displayscore.setText("Score:\n " + count);
                //Toast.makeText(getApplicationContext(), "Correct! +10 pts ", Toast.LENGTH_SHORT).show();
                task.cancel();
                timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
                mChronometer.stop();
                animate_blink();
                animate_slide();
                animate_blink_less();
                timestopped=(timeWhenStopped+2000);
                System.out.println("TimewhenStopped "+timeWhenStopped);
                System.out.println("Correct ans "+timestopped);
                if(timestopped<0)
                    mChronometer.setBase(SystemClock.elapsedRealtime() + (timeWhenStopped+2000));
                else
                {
                    timeWhenStopped=0;
                    timestopped=0;
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }


                mChronometer.start();
                timer_restart();
                random_song();
            }
            else if (s!= store[1][k]) {
                // Wrong
                displayscore.setText("Score:\n " + count);
                count_true=0;
                animate_blink();
                //Toast.makeText(getApplicationContext(), "Wrong! 0 pts ", Toast.LENGTH_SHORT).show();
                random_song();
            }
        }
//---------------------Option 3--------------------
        public void opt3(View v)
        {
            String s=button3.getText().toString();
            if(s== store[1][k]){
                //Correct
                ++c;
                ++count_true;
                if(count_true>2)
                    count=count+((count_true-2)*10);
                else
                    count=count+10;
                displayscore.setText("Score:\n " + count);
                //Toast.makeText(getApplicationContext(), "Correct! +10 pts ", Toast.LENGTH_SHORT).show();
                task.cancel();
                timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
                mChronometer.stop();
                animate_blink();
                animate_slide();
                animate_blink_less();
                timestopped=(timeWhenStopped+2000);
                System.out.println("Correct ans "+timestopped);
                if(timestopped<0)
                    mChronometer.setBase(SystemClock.elapsedRealtime() + (timeWhenStopped+2000));
                else
                {
                    timeWhenStopped=0;
                    timestopped=0;
                    mChronometer.setBase(SystemClock.elapsedRealtime());
                }
                mChronometer.start();
                timer_restart();
                random_song();
            }
            else if (s!= store[1][k]){
                // Wrong
                displayscore.setText("Score:\n " + count);
                count_true=0;
                animate_blink();
              //  Toast.makeText(getApplicationContext(), "Wrong! 0 pts ", Toast.LENGTH_SHORT).show();
                random_song();
            }
        }
//------------------------------------On clicking pause button--------------------
    public void startDialog(View v) {
        Intent intent = new Intent(MusicTrivia.this, pauseMenu.class);
        startActivity(intent);
    }
//---------------------------------Time for the next activity---------------------------------
    public void timer_restart()
    { task=new TimerTask()
        { public void run()
            {   System.out.println("invoked the timer restart");
                mp.stop();
                Intent myIntent = new Intent(MusicTrivia.this,Next.class);
                myIntent.putExtra("count",count);
                myIntent.putExtra("c",c);
                myIntent.putExtra("assignSuccess",assignSuccess);
                startActivity(myIntent);
                finish();
            }
        };
        total=(60*1000);
        Timer timer=new Timer();
        if((total+timestopped)>=0)
        {
            timer.schedule(task, (total + timestopped));
        }
        else
        {
            timer.schedule(task,total+((timestopped)));
        }
    }
    //-------------------------On resuming the activity---------------------
    @Override
    protected void onResume() {
        super.onResume();
        timer_restart();
        mChronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
        mChronometer.start();
        res++;
        if(res==1)
            mpPausePosition=(mp.getDuration()/2);
        mp.seekTo(mpPausePosition);
        mp.start();
            }
    //----------------------------On pausing the activity-------------------
    @Override
    protected  void onPause() {
        super.onPause();
        task.cancel();
        timeWhenStopped = mChronometer.getBase() - SystemClock.elapsedRealtime();
        mChronometer.stop();
        timestopped=timeWhenStopped;
        mp.pause();
        mpPausePosition = mp.getCurrentPosition();

   }
   //------------------------On stopping th activity-------------------
    @Override
    protected void onStop()
    {
        super.onStop();
    }
    //-----------------------On distroying the activity---------------
       @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mp.release();

    }
    //--------------------------On Back press-----------------------------
    @Override
    public void onBackPressed() {
        mp.stop();
        Intent myIntent = new Intent(MusicTrivia.this,pauseMenu.class);
        startActivity(myIntent);

    };
}
