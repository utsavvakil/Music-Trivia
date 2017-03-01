package com.blitzkrieg.musictrivia_v2;

/**
 * Created by arpit on 8/31/13.
 */
//------------------------------------PAUSE MENU--------------------------
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class pauseMenu extends Activity {
    TextView txt;//FONT
    Typeface font,font2;
    Button b1,b2,b3;
    private static final String TAG=pauseMenu.class.getName();
    private static ArrayList<Activity> activities=new ArrayList<Activity>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.pausemenu);
        this.setFinishOnTouchOutside(false);
        txt=(TextView)findViewById(R.id.textView);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);
        b3=(Button)findViewById(R.id.button3);
        font=Typeface.createFromAsset(getAssets(), "Base 02.ttf");
        font2=Typeface.createFromAsset(getAssets(), "Colleged.ttf");
        txt.setTextSize(38);
        txt.setTypeface(font);
        b1.setTypeface(font2);
        b2.setTypeface(font2);
        b3.setTypeface(font2);

    }
//--------------------------------On click of buttons----------------------------
        public void restart(View v)
        {
            Intent myIntent = new Intent(pauseMenu.this,MainMenu.class);
            startActivity(myIntent);
        }

        public void quit(View view) {
            Intent myintent=new Intent(this,MainMenu.class);
            startActivity(myintent);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);

        }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        activities.remove(this);
    }



    public void finishDialog(View v) {
        pauseMenu.this.finish();
    }
    @Override
    public void onBackPressed() {
        pauseMenu.this.finish();

    };

}

