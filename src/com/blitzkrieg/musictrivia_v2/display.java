package com.blitzkrieg.musictrivia_v2;

/**
 * Created by arpit on 8/31/13.
 */
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;


public class display extends Activity {
    String log,name,score;
    DatabaseHandler db;
    int i=0,n;
    Typeface font;
    public static final String[] titles = new String[50] ;
    public static final String[] descriptions = new String[50];
    TextView txt;
    List<RowItem> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.score);
        System.out.println("VIEW ON");
        db = new DatabaseHandler(this);
        ListView listView1=(ListView)findViewById(R.id.listView);
        txt=(TextView)findViewById(R.id.textView);
        font=Typeface.createFromAsset(getAssets(), "Colleged.ttf");
        txt.setTypeface(font);
        txt.setTextSize(30);
          List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            log = "Id: "+cn.getID()+" ,Name: " + cn.getName() + " ,Score: " + cn.getScore();
            name=""+cn.getName();
            score=""+cn.getScore();
            // Writing Contacts to log
            titles[i]=name;
            descriptions[i]=score;
                        Log.d("Name: ", log);
            i++;
        }
        n=db.getContactsCount();

        rowItems = new ArrayList<RowItem>();
        for (int i = 0; i<n; i++) {
            RowItem item = new RowItem( titles[i],descriptions[i]);
            rowItems.add(item);
        }
        CustomBaseAdapter adapter = new CustomBaseAdapter(this, rowItems);
        listView1.setAdapter(adapter);


        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        db.close();
        super.onPause();
    }

}

