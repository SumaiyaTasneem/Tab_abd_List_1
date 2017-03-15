package com.example.sumaiyamashfi.tab_abd_list;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;


public class AppCheck extends Service {

    private final int UPDATE_INTERVAL = 100;
    private Timer timer = new Timer();
    public static boolean ChatHead = false;
    public static ArrayList<String> Packages = new ArrayList<>();
    public ArrayList<String> From_Dates = new ArrayList<>();
    public ArrayList<String> To_Dates = new ArrayList<>();

    public AppCheck() {

    }

    private final Handler toastHandler = new Handler() {


        @Override
        public void handleMessage(final Message msg) {
            final Bundle Data = msg.getData();

            try {

                Intent intent = new Intent(AppCheck.this, Overlap.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            } catch (Exception e) {
                Log.e("KUET_CSE_AppLock", "Calling Error");
            }
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startid) {
        Toast.makeText(this, "Started!", Toast.LENGTH_LONG).show();
        try {
            ArrayList<String> Selected_Apps = intent.getStringArrayListExtra("Packages");
            String From_Date = intent.getStringExtra("From_Date");
            String To_Date = intent.getStringExtra("To_Date");
            int i;
            for (i = 0; i < Selected_Apps.size(); i++) {
                Log.i("info", Selected_Apps.get(i));
                Packages.add(Selected_Apps.get(i));
                From_Dates.add(From_Date);
                To_Dates.add(To_Date);
            }

        }
        catch(Exception e)
        {
            Log.e("KUET_CSE_AppLock", "On Start Error");
        }
        timer.scheduleAtFixedRate(new MyTimerTask(intent, this) {

            @Override
            public void run() {


                Date currentTime = new Date();


                    Context C = getApplicationContext();
                    ActivityManager activityManager = (ActivityManager) C.getSystemService(Context.ACTIVITY_SERVICE);
                    List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
                    if (appProcesses == null) {

                    }


                    boolean Running = false;
                    int k;

                    try {


                        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
                            Log.d("ProcessRunning", appProcess.processName);
                            for (k = 0; k < Packages.size(); k++) {


                                Date F_T = Date_Utility.String_To_Date(From_Dates.get(k));
                                Date T_T = Date_Utility.String_To_Date(To_Dates.get(k));

                                boolean LockIt = false;
                                if (currentTime.getHours() > F_T.getHours() && currentTime.getHours() < T_T.getHours())
                                {
                                    LockIt = true;
                                }
                                else if (F_T.getHours() == currentTime.getHours() && T_T.getHours() == currentTime.getHours())
                                {
                                    if (currentTime.getMinutes() >= F_T.getMinutes() && currentTime.getMinutes() <= T_T.getMinutes()) {
                                        LockIt = true;
                                    }
                                }

                                if (LockIt == true) {
                                    if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND && appProcess.processName.equals(Packages.get(k))) {


                                        if (ChatHead == false) {
                                            ChatHead = true;

                                            Message msg = new Message();
                                            Bundle b = new Bundle();
                                            b.putString("package", appProcess.processName);
                                            msg.setData(b);

                                            toastHandler.sendMessage(msg);
                                        }
                                        break;


                                    }
                                }

                            }

                        }

                    } catch (Exception e) {
                        Log.e("KUET_CSE_AppLock", "Lock Error : ");
                    }

            }

        }, 0, UPDATE_INTERVAL);
        return START_STICKY;
    }






  public void onDestroy() {
      super.onDestroy();
      if (timer != null) {
          timer.cancel();
      }
  }


    private void stopService() {
        if (timer != null) timer.cancel();
    }
 @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
