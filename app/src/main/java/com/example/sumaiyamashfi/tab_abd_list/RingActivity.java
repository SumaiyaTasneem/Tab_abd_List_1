package com.example.sumaiyamashfi.tab_abd_list;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

/**
 * Created by Aney on 08-Jan-16.
 */
public class RingActivity extends Activity {

    final Context context = this;
    MediaPlayer mPlayer = new MediaPlayer();
    //Camera camera;
    Camera camera= Camera.open();
    Camera.Parameters p = camera.getParameters();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        //Log.d("abcdefgh", "Ringing...");

        Bundle extras = getIntent().getExtras();
        String num = extras.getString("num");
        String msg = extras.getString("msg");

        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, "I AM At Reciver\nsenderNum: "+num+", message: " + msg, duration);
        toast.show();



        final AudioManager mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int origionalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
       mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);



        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        camera.setParameters(p);
        camera.startPreview();




         /*
        SmsManager smsManager = SmsManager.getDefault();
        if(IsRingerSilent() || IsVibrate())
        {

        }
        else
        {
        */
        if(IsRingerSilent() || IsVibrate()) {

            AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);
            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);


            audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
            origionalVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);


            mPlayer.setLooping(true);
            mPlayer.start();


        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

        // Setting Dialog Title
        alertDialogBuilder.setTitle("Device Ringing");

        // Setting Dialog Message
        alertDialogBuilder.setMessage("Sender : "+num+"\n"+"Message : "+msg);

        alertDialogBuilder.setNegativeButton("Dialog Close", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                if (mPlayer != null) {
                  //  mPlayer.setLooping(false);
                    mPlayer.stop();
                }

                p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(p);
                camera.stopPreview();

            dialog.cancel();
            finish();
        }
    });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        //show dialog
        alertDialog.show();

    }

    private boolean IsVibrate()
    {
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        if(audioManager.getRingerMode()==AudioManager.RINGER_MODE_VIBRATE )
        {
           mPlayer = MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean IsRingerSilent()
    {
        AudioManager audioManager = (AudioManager) getBaseContext().getSystemService(Context.AUDIO_SERVICE);

        if(audioManager.getRingerMode()==AudioManager.RINGER_MODE_SILENT )
        {
            mPlayer = MediaPlayer.create(getApplicationContext(), Settings.System.DEFAULT_RINGTONE_URI);
            return true;
        }
        else
        {
            return false;
        }
    }

   /* public boolean onKeyDown(int keycode, KeyEvent ke)
    {
        if(keycode==KeyEvent.KEYCODE_BACK)
        {
            if(mPlayer.isPlaying())
            {
                mPlayer.setLooping(false);
                mPlayer.stop();
            }
            finish();
        }
        return true;
    }*/

}
