package com.example.alarm_test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import androidx.annotation.RequiresApi;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AlarmActivity extends AppCompatActivity {

    private Button save;
    private TimePicker timePicker;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        timePicker=(TimePicker)findViewById(R.id.time_picker);
        save=(Button)findViewById(R.id.save);

        save.setOnClickListener(v->{
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            int hour=timePicker.getHour();
            int minute=timePicker.getMinute();
            calendar.set(Calendar.HOUR_OF_DAY,hour);
            calendar.set(Calendar.MINUTE,minute);
            System.out.println("hour = " + hour);
            System.out.println("minute = " + minute);

            setAlram(calendar);
        });
    }

    public void getAlram(Calendar calendar){

    }
    public void setAlram(Calendar calendar){
        AlarmManager alarmManager=(AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) { //알람이 없으면 실행
            Intent intent = new Intent(this, AlarmReceiver.class);
            PendingIntent alarmIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);


            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, alarmIntent);

            Toast.makeText(AlarmActivity.this,"알람이 저장되었습니다..",Toast.LENGTH_LONG).show();
        }
    }
}