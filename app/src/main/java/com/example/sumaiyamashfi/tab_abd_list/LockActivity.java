package com.example.sumaiyamashfi.tab_abd_list;

/**
 * Created by Sumaiya Mashfi on 2/14/2016.
 */
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;

public class LockActivity extends TabActivity {

    static SharedData S = new SharedData();

    static ArrayList<String> Locked_Apps = null;
    static ArrayList<String> Selected_Apps = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);

        Locked_Apps = new ArrayList<>();

        //Load the packages of locked apps
        SharedPreferences P = getSharedPreferences("com.example.saveyourtime",MODE_PRIVATE);
        String packs = "";
        packs = P.getString("LockedApps", "");

        String items[] = packs.split(";");
        int ii;
        for (ii = 0; ii < items.length; ii++)
            Locked_Apps.add(items[ii]);

        Selected_Apps = new ArrayList<>();
        //Copy the locked apps to selected apps



        final TabHost tabHost = getTabHost();
        final Intent I = new Intent(this, ListActivity_1.class);
        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("Installed")
                .setContent(I));

        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("Locked")
                        //.setContent(new Intent(this, ListActivity_1.class)));
                .setContent(I));

        // ArrayList<String> Packages = new ArrayList<>();
        //ArrayList<String> From_Time = new ArrayList<>();
        // ArrayList<String> To_Time = new ArrayList<>();

        /*
        Packages.add("com.facebook.katana");
        String From_Time=Date_Utility.Date_To_String(new Date(2016, 2, 8, 12, 47, 0));
        String To_Time = Date_Utility.Date_To_String(new Date(2016, 2, 8, 12, 50, 0));

        Intent I = new Intent(this, AppCheck.class);
        I.putStringArrayListExtra("Packages", Packages);
        I.putExtra("From_Date", From_Time);
        I.putExtra("To_Date", To_Time);
        startService(I);


        */
        Button lock_button = (Button) findViewById(R.id.lock_button);

        lock_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this,String.valueOf(S.Selected_Apps.size()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LockActivity.this , ListActivity_2.class);
                intent.putStringArrayListExtra("SelectedApps", Selected_Apps);
                startActivity(intent);
                //Toast.makeText(LockActivity.this, Integer.toString(Selected_Apps.size()), Toast.LENGTH_SHORT).show();
            }

        });

    }


}
