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

//-------------------------------MAIN MENU---------------
public class MainMenu extends Activity {
    TextView txt;//Text to change the font
    Typeface font,font2;
    Button b1,b2,b3,b4,b5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainmenu);
        txt=(TextView)findViewById(R.id.title);
        b1=(Button)findViewById(R.id.play);
        b2=(Button)findViewById(R.id.inst);
        b3=(Button)findViewById(R.id.highscr);
        b4=(Button)findViewById(R.id.abt);
        b5=(Button)findViewById(R.id.exit);
        font=Typeface.createFromAsset(getAssets(), "Base 02.ttf");
        font2=Typeface.createFromAsset(getAssets(), "Colleged.ttf");
        txt.setTypeface(font);
        b1.setTypeface(font2);
        b2.setTypeface(font2);
        b3.setTypeface(font2);
        b4.setTypeface(font2);
        b5.setTypeface(font2);
     }

        //On button click play
        public void play(View v){
            Intent myIntent=new Intent(MainMenu.this,MusicTrivia.class);//Shift to Music trivia
            startActivity(myIntent);
        }
    public void inst(View v){
        Intent myIntent=new Intent(MainMenu.this,instruction.class);//Shift to Instruction
        startActivity(myIntent);
    }
    public void about(View v){
        Intent myIntent=new Intent(MainMenu.this,about.class);//Shift to About
        startActivity(myIntent);
    }
    public void highscore(View v){
        Intent myIntent=new Intent(MainMenu.this,display.class);//Shift to High score
        startActivity(myIntent);
    }
    public void exitgame(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }
    //ON back press
    @Override
    public void onBackPressed() {
        Intent myIntent = new Intent(MainMenu.this,pauseMenu.class);//Shift to pause menu
        startActivity(myIntent);
    }
}
