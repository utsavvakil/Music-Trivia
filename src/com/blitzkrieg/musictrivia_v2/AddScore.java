package com.blitzkrieg.musictrivia_v2;

/**
 * Created by arpit on 8/31/13.
 */
import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Created by utsavvakil on 06/09/13.
 */

public class AddScore extends Activity {
    DatabaseHandler db;
    EditText name;
    int count,count_score;
    Button addscore1;
    String pass_name;
    TextView txt;
    Typeface font;
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.addscore);
        this.setFinishOnTouchOutside(false);
        db = new DatabaseHandler(this);
        name=(EditText)findViewById(R.id.editTextName);
        addscore1=(Button)findViewById(R.id.btn_add);
        txt=(TextView)findViewById(R.id.textView);
        count=getIntent().getIntExtra("count",0);
        font=Typeface.createFromAsset(getAssets(), "Colleged.ttf");
        System.out.print("ADDITION"+pass_name);
        txt.setTypeface(font);
        txt.setTextSize(35);
         count_score =db.getContactsCount();
        addscore1.setTypeface(font);
        addscore1.setOnClickListener(myClickListener5);
    }
    View.OnClickListener myClickListener5 = new View.OnClickListener(){
        public void onClick(View v){
            pass_name=(name.getText()).toString();
            db.addContact(new Contact(pass_name, count));
             AddScore.this.finish();
        }

    };
}