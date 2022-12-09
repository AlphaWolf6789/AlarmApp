package com.example.alarmapp.Service;

import static com.example.alarmapp.AlarmReceiver.VIBRATE;
import static com.example.alarmapp.AlarmReceiver.VOLUME;
import static com.example.alarmapp.App.CHANNEL_ID;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.alarmapp.Activities.RingActivity;
import com.example.alarmapp.R;

public class AlarmService extends Service {
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;

    @Override
    public void onCreate() {
        super.onCreate();

        mediaPlayer = MediaPlayer.create(this, R.raw.wakemeup);
        mediaPlayer.setLooping(true);
//        mediaPlayer.setV


        if (Build.VERSION.SDK_INT >= 26) {


            Intent notificationIntent = new Intent(this, RingActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("Ring Ring .. Ring Ring")
                    .setSmallIcon(R.drawable.ic_alarm)
                    .setContentIntent(pendingIntent)
                    .build();

            startForeground(1, notification);
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


//        Intent notificationIntent = new Intent(this, RingActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
//
//        String alarmTitle = String.format("%s Alarm", intent.getStringExtra(NOTE));
//
//        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setContentTitle(alarmTitle)
//                .setContentText("Ring Ring .. Ring Ring")
//                .setSmallIcon(R.drawable.ic_alarm)
//                .setContentIntent(pendingIntent)
//                .build();
//
//        mediaPlayer.start();

        if(intent==null){
            Toast.makeText(this, "Intent null", Toast.LENGTH_SHORT).show();
            Log.e("Loi o day","null intent");
        }


        float volume = (float) intent.getIntExtra(VOLUME, 10);
        boolean isVibrate = intent.getBooleanExtra(VIBRATE, false);
        float volumeRing = (float) (Math.log(100 - volume) / Math.log(100));
        mediaPlayer.setVolume(volumeRing, volumeRing);
        mediaPlayer.start();
        Toast.makeText(this, "volume " + volume, Toast.LENGTH_SHORT).show();
        Log.i("vibrate", "onStartCommand: " + volume);
        if (isVibrate) {
            vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {0, 2000, 500};
            vibrator.vibrate(pattern, 0);

        }
//
//        startForeground(1, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mediaPlayer.stop();
        vibrator.cancel();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
