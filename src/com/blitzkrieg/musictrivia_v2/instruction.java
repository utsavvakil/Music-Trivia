package com.blitzkrieg.musictrivia_v2;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.ViewFlipper;


/**
 * Created by arpit on 8/31/13.
 */
public class instruction extends Activity {
    private ViewFlipper viewFlipper;
    private float lastX;
    Typeface font;
    TextView txt1,txt2,txt3,txt4;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.instruction);
        viewFlipper = (ViewFlipper) findViewById(R.id.view_flipper);
        txt1=(TextView)findViewById(R.id.TextView1);
        txt2=(TextView)findViewById(R.id.TextView2);
        txt3=(TextView)findViewById(R.id.TextView3);
        txt4=(TextView)findViewById(R.id.TextView4);
        font=Typeface.createFromAsset(getAssets(), "HelvLightRegular.ttf");
        txt1.setText("This game has '30' questions  which you need to answer in '60' seconds");
        txt4.setText("Every correct answer increases score by '10' and reduces timer by '2' seconds");
        txt3.setText("For answering the correct answers consecutively you get a Bonous score!");
        txt2.setText("Enjoy the Game!.....");
        txt1.setTextSize(25);
        txt2.setTextSize(25);
        txt3.setTextSize(25);
        txt4.setTextSize(25);

    }



    // Method to handle touch event like left to right swap and right to left swap
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen to swap
            case MotionEvent.ACTION_DOWN:
            {
                lastX = touchevent.getX();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                float currentX = touchevent.getX();

                // if left to right swipe on screen
                if (lastX < currentX)
                {
                    // If no more View/Child to flip
                    if (viewFlipper.getDisplayedChild() == 0)
                        break;

                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Left and current Screen will go OUT from Right
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    // Show the next Screen
                    viewFlipper.showNext();
                }

                // if right to left swipe on screen
                if (lastX > currentX)
                {
                    if (viewFlipper.getDisplayedChild() == 1)
                        break;
                    // set the required Animation type to ViewFlipper
                    // The Next screen will come in form Right and current Screen will go OUT from Left
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    // Show The Previous Screen
                    viewFlipper.showPrevious();
                }
                break;
            }
        }
        return false;
    }

}
