package com.example.sumaiyamashfi.tab_abd_list;

import android.app.Service;
import android.content.Intent;

import java.util.TimerTask;

/**
 * Created by Sumaiya Mashfi on 2/5/2016.
 */
public class MyTimerTask extends TimerTask {

        Intent I;
        Service S;

        public MyTimerTask(Intent C, Service C_S) {
            I = C;
                S = C_S;
        }

        @Override
        public void run() {
            // You can do anything you want with param

        }

}
