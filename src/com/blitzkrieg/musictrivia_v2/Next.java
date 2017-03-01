package com.blitzkrieg.musictrivia_v2;

/**
 * Created by arpit on 8/31/13.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;



public class Next extends Activity{
    TextView txt,txt1,txt2,txt3,txt4;
    Typeface font,font2;
    DatabaseHandler db;
    Contact minc,cn;
    int count_score,rank;
    int assignSuccess;
    int count,min,c;
    Button b1,b2,b3;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.next);
        db = new DatabaseHandler(this);
        count = getIntent().getIntExtra("count", 0);
        c = getIntent().getIntExtra("c", 0);
         assignSuccess = getIntent().getIntExtra("assignSuccess",0);
        font2=Typeface.createFromAsset(getAssets(), "Colleged.ttf");
        b1=(Button)findViewById(R.id.viewbt);
        b2=(Button)findViewById(R.id.extbt);
        b3=(Button)findViewById(R.id.mainbt);
        b1.setTypeface(font2);
        b2.setTypeface(font2);
        b3.setTypeface(font2);
        font=Typeface.createFromAsset(getAssets(), "Base 02.ttf");

        txt=(TextView)findViewById(R.id.textView);

                txt.setTypeface(font);


        txt1=(TextView)findViewById(R.id.textView2);
        txt1.setText("your score : "+Integer.toString(count));

        txt1.setTypeface(font2);


        txt2=(TextView)findViewById(R.id.textView3);
        txt2.setText("questions attempted : "+Integer.toString((assignSuccess+1)/3));

        txt2.setTypeface(font2);


        txt3=(TextView)findViewById(R.id.textView4);
        txt3.setText("correct answers : "+Integer.toString(c));

        txt3.setTypeface(font2);
        txt4=(TextView)findViewById(R.id.textView5);
        rank=(db.getContactRank(count))+1;
        txt4.setText("Rank:"+rank);
        txt4.setTypeface(font2);
        count_score =db.getContactsCount();
        minc=db.getMin();
       min=minc.getScore();
        if(count_score>=10 && count>min){

               //db.deleteContact(min,minc);
                db.updateContact(minc);
                Intent myIntent = new Intent(Next.this,AddScore.class);
                myIntent.putExtra("count",count);
                startActivity(myIntent);

        }
        else if(count>0 && count_score<10){
            Intent myIntent = new Intent(Next.this,AddScore.class);
            myIntent.putExtra("count",count);
            startActivity(myIntent);
        }

        else{
              db.addContact1(new Contact("", count));
        }
    }
    public void mainmenu(View v) {
        Intent myIntent = new Intent(Next.this,MainMenu.class);
        startActivity(myIntent);
    }

    public void viewscore(View v) {
        Intent myIntent = new Intent(Next.this,display.class);
        startActivity(myIntent);
    }

    public void exit(View v) {
        Intent myintent=new Intent(this,MainMenu.class);
        startActivity(myintent);
        Next.this.finish();
    }
    @Override
    public void onBackPressed() {
        Intent myintent =new Intent(this,MainMenu.class);
        startActivity(myintent);
        Next.this.finish();

    };
}

