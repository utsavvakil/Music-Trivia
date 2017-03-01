package com.blitzkrieg.musictrivia_v2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by arpit on 8/31/13.
 */
public class about extends Activity {
    TextView txt;
    Typeface font;
    ImageButton img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.about);
        txt=(TextView)findViewById(R.id.about);
        font=Typeface.createFromAsset(getAssets(), "HelvLightRegular.ttf");
        //--------CHANGING THE FONT-----------------
        txt.setTypeface(font);
        txt.setText("About Us\n\nMusic Trivia\nVersion 1.0.0\n\nLead Developers\nArpit Sabherwal\nUtsav Vakil\n\nMade By:\nBlitzkrieg Studios");

        img = (ImageButton)findViewById(R.id.imageButton);
         //set img's image to the fb like image


    }
    public void like(View v){
        Intent open = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/androidblitzkrieg"));
        startActivity(open);
    }
}
