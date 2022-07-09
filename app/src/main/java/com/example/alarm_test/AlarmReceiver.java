package com.example.alarm_test;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private Context context;
    private String channelId="alarm_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;


        Intent busRouteIntent = new Intent(context, AlarmActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntentWithParentStack(busRouteIntent);
        PendingIntent busRoutePendingIntent =
                stackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

        final NotificationCompat.Builder notificationBuilder=new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.mipmap.ic_launcher).setDefaults(Notification.DEFAULT_ALL)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setAutoCancel(true)
                .setContentTitle("알람")
                .setContentText("울림")
                .setContentIntent(busRoutePendingIntent);


        final NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel(channelId,"Channel human readable title",NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        int id=(int)System.currentTimeMillis();
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);

        if(alert == null){
            // alert is null, using backup
            alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            // I can't see this ever being null (as always have a default notification)
            // but just incase
            if(alert == null) {
                // alert backup is null, using 2nd backup
                alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            }
        }
        else{
            Ringtone r = RingtoneManager.getRingtone(context.getApplicationContext(), alert);
            r.play();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    r.stop();
                }
            },3000);
        }
        notificationManager.notify(id,notificationBuilder.build());
    }
}