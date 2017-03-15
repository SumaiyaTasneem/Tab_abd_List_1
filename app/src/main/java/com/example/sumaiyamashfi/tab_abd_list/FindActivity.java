package com.example.sumaiyamashfi.tab_abd_list;

/**
 * Created by Sumaiya Mashfi on 2/16/2016.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FindActivity extends Activity {

    MyReceiver mAppReceiver = new MyReceiver();

    final Context context = this;

    Button ok;
    EditText meditText;
    static String sms = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);



        meditText = (EditText) findViewById(R.id.editText);
        ok=(Button) findViewById(R.id.okButton);

        ok.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      sms = meditText.getText().toString();
                                      Toast toast = new Toast(getApplicationContext());
                                      toast.makeText(FindActivity.this, sms, Toast.LENGTH_SHORT).show();

                                  }
                              }

        );

        registerReceiver(mAppReceiver, new IntentFilter("MyReceiver"));
        Intent intent = new Intent("MyReceiver");
        intent.putExtra("sms",sms);
        sendBroadcast(intent);


    }


    public void onDestroy() {

        unregisterReceiver(mAppReceiver);
        super.onDestroy();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
