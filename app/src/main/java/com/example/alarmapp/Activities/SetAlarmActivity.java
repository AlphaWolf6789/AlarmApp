package com.example.alarmapp.Activities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.example.alarmapp.Data.AlarmDatabase;
import com.example.alarmapp.Fragment.AlarmFragment;
import com.example.alarmapp.Model.Alarm;
import com.example.alarmapp.R;

import java.util.Calendar;
import java.util.Locale;

public class SetAlarmActivity extends AppCompatActivity {

    int hour, minute, img, position;
    private TextView txt_setAlarm;
    private ImageView img_Alarm;
    private Spinner spn_AlarmTag;
    private EditText edt_AlarmNote;
    private SeekBar alarm_Volume;
    private SwitchCompat is_Vibrate;
    private static final String TIME_FORMAT_24 = "HH:mm";
    private Button btn_Save, btn_Cancle;
    private ToggleButton btn_Mon, btn_Tue, btn_Wed, btn_Thu, btn_Fri, btn_Sat, btn_Sun;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar calendar;
    private Alarm alarm;
    MediaPlayer mediaPlayer;

    @SuppressLint("WrongViewCast")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);

        btn_Mon = findViewById(R.id.btn_Mon);
        btn_Tue = findViewById(R.id.btn_Tue);
        btn_Wed = findViewById(R.id.btn_Wed);
        btn_Thu = findViewById(R.id.btn_Thu);
        btn_Fri = findViewById(R.id.btn_Fri);
        btn_Sat = findViewById(R.id.btn_Sat);
        btn_Sun = findViewById(R.id.btn_Sun);
        btn_Save = findViewById(R.id.btn_Save);
        btn_Cancle = findViewById(R.id.btn_cancle);
        txt_setAlarm = findViewById(R.id.txt_setAlarm);
        spn_AlarmTag = findViewById(R.id.spn_setAlarm);
        edt_AlarmNote = findViewById(R.id.edt_alarmNote);
        alarm_Volume = findViewById(R.id.seekbar);
        is_Vibrate = findViewById(R.id.is_Vibrate);
        calendar = Calendar.getInstance();
        img_Alarm = findViewById(R.id.img_setAlarm);
        receiveData();
        txt_setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpTimePicker(view);
            }
        });

        btn_Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataToMainActivity();
            }
        });

        btn_Cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Spinner spinner = findViewById(R.id.spn_setAlarm);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.alarm_types, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        mediaPlayer = MediaPlayer.create(SetAlarmActivity.this, R.raw.wakemeup);
        alarm_Volume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                float volume = (float) (Math.log(100 - i) / Math.log(100));
                mediaPlayer.setVolume(volume, volume);
                mediaPlayer.start();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                mediaPlayer.stop();
                mediaPlayer.start();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.reset();
            }
        });


    }

    private void receiveData() {
        position = getIntent().getIntExtra("position", -1);
        alarm = (Alarm) getIntent().getExtras().get("alarm_object");
        String sHour = String.format(Locale.getDefault(), "%02d", alarm.getHour());
        String sMinute = String.format(Locale.getDefault(), "%02d", alarm.getMinute());
        String time = sHour + ":" + sMinute;
        txt_setAlarm.setText(time);
        img_Alarm.setImageResource(alarm.getAlarm_ImgID());
        btn_Mon.setChecked(alarm.isMonday());
        btn_Tue.setChecked(alarm.isTuesday());
        btn_Wed.setChecked(alarm.isWednesday());
        btn_Thu.setChecked(alarm.isThursday());
        btn_Fri.setChecked(alarm.isFriday());
        btn_Sat.setChecked(alarm.isSaturday());
        btn_Sun.setChecked(alarm.isSunday());
        is_Vibrate.setChecked(alarm.isVibrate());
        edt_AlarmNote.setText(alarm.getNote());
    }

    private void sendDataToMainActivity() {
        String time = txt_setAlarm.getText().toString();
        String tag = spn_AlarmTag.getSelectedItem().toString();
        String note = edt_AlarmNote.getText().toString();
        String ringTone = "";
        int volume = alarm_Volume.getProgress();
        boolean vibrate = is_Vibrate.isChecked();
//        Toast.makeText(SetAlarmActivity.this, "volume "+ volume, Toast.LENGTH_SHORT).show();
        boolean recurring;
        if (btn_Mon.isChecked() || btn_Tue.isChecked() || btn_Wed.isChecked() || btn_Thu.isChecked()
                || btn_Fri.isChecked() || btn_Sat.isChecked() || btn_Sun.isChecked()) {
            recurring = true;
        } else {
            recurring = false;
        }
        Alarm alarm_result = new Alarm(alarm.getAlarmId(), getImageAlarm(), hour, minute, volume, true, recurring,
                vibrate, btn_Mon.isChecked(), btn_Tue.isChecked(), btn_Wed.isChecked(), btn_Thu.isChecked()
                , btn_Fri.isChecked(), btn_Sat.isChecked(), btn_Sun.isChecked(), tag, note, ringTone, System.currentTimeMillis());

        alarm.setAlarm_ImgID(getImageAlarm());
        alarm.setHour(hour);
        alarm.setMinute(minute);
        alarm.setVolume(volume);
        alarm.setStarted(true);
        alarm.setRecurring(recurring);
        alarm.setVibrate(vibrate);
        alarm.setMonday(btn_Mon.isChecked());
        alarm.setTuesday(btn_Tue.isChecked());
        alarm.setWednesday(btn_Wed.isChecked());
        alarm.setThursday(btn_Thu.isChecked());
        alarm.setFriday(btn_Fri.isChecked());
        alarm.setSaturday(btn_Sat.isChecked());
        alarm.setSunday(btn_Sun.isChecked());
        alarm.setTag(tag);
        alarm.setNote(note);
        alarm.setRingtone(ringTone);
        alarm.setCreated(System.currentTimeMillis());

        AlarmDatabase.getInstance(this).alarmDao().update(alarm);

        alarm_result.schedule(SetAlarmActivity.this);
        Intent intentResult = new Intent(SetAlarmActivity.this, AlarmFragment.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("alarm_object_fr",alarm);
//        intent1.putExtra("position_update", position);
//        intent1.putExtras(bundle);
        setResult(AlarmFragment.RESULT_CODE, intentResult);
        finish();
    }

    private void popUpTimePicker(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int Selectedhour, int Selectedminute) {
                hour = Selectedhour;
                minute = Selectedminute;
                img_Alarm.setImageResource(getImageAlarm());
                txt_setAlarm.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, hour, minute, true);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private int getImageAlarm() {
        if (hour >= 18 || (hour >= 0 && hour < 6)) {
            return R.drawable.img_night;
        } else {
            return R.drawable.img_day;
        }
    }

}