package com.example.alarmapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;

import com.example.alarmapp.R;
import com.example.alarmapp.Service.AlarmService;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class RingActivity extends AppCompatActivity {

    @BindView(R.id.btn_dismiss) Button dismiss;
    @BindView(R.id.btn_snooze) Button snooze;
    @BindView(R.id.time_Alert) TextView timeAlarm;
    private String time, note;
    private Calendar calendar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ring2);
//        btn_dismiss =  findViewById(R.id.btn_dismiss);
//        btn_snooze = findViewById(R.id.btn_snooze);
//        timeAlarm = findViewById(R.id.time_Alert);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");

        time = dateFormat.format(calendar.getInstance().getTime());
        timeAlarm.setText(time);

//        ButterKnife.bind(this);


        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
                getApplicationContext().stopService(intentService);
                finish();
            }
        });

//        snooze.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(System.currentTimeMillis());
//                calendar.add(Calendar.MINUTE, 10);
//
//                Alarm alarm = new Alarm();
//
//                alarm.schedule(getApplicationContext());
//
//                Intent intentService = new Intent(getApplicationContext(), AlarmService.class);
//                getApplicationContext().stopService(intentService);
//                finish();
//            }
//        });
//
//        animateClock();
    }

//    private void animateClock() {
//        ObjectAnimator rotateAnimation = ObjectAnimator.ofFloat(clock, "rotation", 0f, 20f, 0f, -20f, 0f);
//        rotateAnimation.setRepeatCount(ValueAnimator.INFINITE);
//        rotateAnimation.setDuration(800);
//        rotateAnimation.start();
//    }
}