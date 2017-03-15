package com.example.sumaiyamashfi.tab_abd_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button find_phone = (Button)findViewById(R.id.button_find);
        find_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, FindActivity.class);

                startActivity(intent);
            }

        });

        Button time_lock = (Button)findViewById(R.id.button_time);
        time_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this, LockActivity.class);

                startActivity(intent);
            }

        });


    }

}
