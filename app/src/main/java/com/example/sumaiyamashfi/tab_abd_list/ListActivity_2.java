package com.example.sumaiyamashfi.tab_abd_list;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Sumaiya Mashfi on 1/14/2016.
 */
public class ListActivity_2  extends Activity{

    //final DatePicker dp = (DatePicker) findViewById(R.id.datepicker);

    int lock=0;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_2);
        final TimePicker tp1 = (TimePicker) findViewById(R.id.timepicker1);
        final TimePicker tp2 = (TimePicker) findViewById(R.id.timepicker2);
        //Intent I = getIntent();
        //final ArrayList<String> Selected_Apps = I.getStringArrayListExtra("SelectedApps");
        tp1.setIs24HourView(true);
        tp2.setIs24HourView(true);
        //packageManager = getPackageManager();

        //new LoadApplications().execute();
        Button Set = (Button)findViewById(R.id.setalarm);
        Set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Date currentTime = new Date();
                Date startTime = new Date(currentTime.getYear(), currentTime.getMonth(), currentTime.getDay(), tp1.getCurrentHour() , tp1.getCurrentMinute(), 0);
                Date endTime = new Date(currentTime.getYear(), currentTime.getMonth(), currentTime.getDay(), tp2.getCurrentHour() , tp2.getCurrentMinute(), 0);
                startTime.setHours(tp1.getCurrentHour());
                startTime.setMinutes(tp1.getCurrentMinute());
                String s_time = Date_Utility.Date_To_String(startTime);
                String e_time = Date_Utility.Date_To_String(endTime);
                Intent I = new Intent(ListActivity_2.this, AppCheck.class);


                Toast.makeText(ListActivity_2.this, "Locked!", Toast.LENGTH_SHORT).show();

/*
                try {
                    stopService(I);
                    I = null;
                }
                catch(Exception e)
                {

                }
*/
  //              I = new Intent(ListActivity_2.this, AppCheck.class);
                //Transfer the apps from selected to locked
                LockActivity.Locked_Apps.clear();
                int j;
                for (j = 0; j < LockActivity.Selected_Apps.size(); j++)
                {
                    LockActivity.Locked_Apps.add(LockActivity.Selected_Apps.get(j));
                }

                SharedPreferences P = getSharedPreferences("com.example.saveyourtime", MODE_PRIVATE);
                SharedPreferences.Editor E = P.edit();
                String packs = "";
                if (LockActivity.Locked_Apps.size() > 1)
                {
                    for (j = 0; j < LockActivity.Locked_Apps.size() - 1; j++ )
                    {
                        packs = packs + LockActivity.Locked_Apps.get(j) + ";";
                    }
                    packs = packs + LockActivity.Locked_Apps.get(LockActivity.Locked_Apps.size() - 1);
                }
                else if (LockActivity.Locked_Apps.size() == 1)
                {
                    packs = LockActivity.Locked_Apps.get(0);
                }

                E.putString("LockedApps", packs);
                E.commit();

                I.putStringArrayListExtra("Packages", LockActivity.Locked_Apps);
                I.putExtra("From_Date", s_time);
                I.putExtra("To_Date", e_time);


                startService(I);

                //Toast.makeText(ListActivity_2.this, s_time, Toast.LENGTH_SHORT).show();
                /*
                Date SS_Time = Date_Utility.String_To_Date(s_time);
                //Toast.makeText(ListActivity_2.this, Date_Utility.Date_To_String(SS_Time), Toast.LENGTH_SHORT).show();
                Date EE_Time = Date_Utility.String_To_Date(e_time);
                Date CE_Time =  new Date();


                if (CE_Time.getHours() > SS_Time.getHours() && CE_Time.getHours() < EE_Time.getHours())
                {
                    Toast.makeText(ListActivity_2.this, "True", Toast.LENGTH_SHORT).show();
                }
                else if (CE_Time.getHours() == SS_Time.getHours() && CE_Time.getHours() == EE_Time.getHours())
                {
                    if (CE_Time.getMinutes() >= SS_Time.getMinutes() && CE_Time.getMinutes() <= EE_Time.getMinutes())
                    {
                        Toast.makeText(ListActivity_2.this, "True", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(ListActivity_2.this, "False", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(ListActivity_2.this, "False", Toast.LENGTH_SHORT).show();
                    */
            }
        });

    }

        //int end_time = Integer.parseInt(endTime);
/*
        Intent I = new Intent(this, AppCheck.class);

        I.putExtra("startTime",startTime);
        I.putExtra("endTime",endTime);
        startService(I);
*/



        //Toast.makeText(ListActivity_2.this,"time" +time,Toast.LENGTH_LONG).show();
        // String input_date = dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth();
        // Date date = new Date (input_date);
        // long diff = date.getTime();


        //Toast.makeText(ListActivity_2.this, "start  " + startTime + "end time " + endTime + "Current time" + Current_time, Toast.LENGTH_LONG).show();
        //Intent I = new Intent(this, AppCheck.class);
        //I.putExtra("lock",lock);

        //startService(I);


        //      Toast.makeText(ListActivity_2.this, "Time from Dtae " + diff , Toast.LENGTH_LONG).show();


        //   finish();   // If you want to continue on that TimeDateActivity
        // If you want to go to new activity that code you can also write here



}





