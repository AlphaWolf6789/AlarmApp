package com.example.alarmapp.Model;

public class Alarm {
    private int alarm_ImgID;
    private String alarmTime;
    private String alarmDayRepeat;
    private String alarmTags;
    private String alarmNotes;
    private String alarmRingTone;
    private int alarmVolume;
    private boolean alarmVibrate;

    public Alarm() {
    }

    public Alarm(int alarm_ImgID, String alarmTime, String alarmDayRepeat, String alarmTags,
                 String alarmNotes, String alarmRingTone, int alarmVolume, boolean alarmVibrate) {
        this.alarm_ImgID = alarm_ImgID;
        this.alarmTime = alarmTime;
        this.alarmDayRepeat = alarmDayRepeat;
        this.alarmTags = alarmTags;
        this.alarmNotes = alarmNotes;
        this.alarmRingTone = alarmRingTone;
        this.alarmVolume = alarmVolume;
        this.alarmVibrate = alarmVibrate;
    }

    public int getAlarm_ImgID() {
        return alarm_ImgID;
    }

    public void setAlarm_ImgID(int alarm_ImgID) {
        this.alarm_ImgID = alarm_ImgID;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmDayRepeat() {
        return alarmDayRepeat;
    }

    public void setAlarmDayRepeat(String alarmDayRepeat) {
        this.alarmDayRepeat = alarmDayRepeat;
    }

    public String getAlarmTags() {
        return alarmTags;
    }

    public void setAlarmTags(String alarmTags) {
        this.alarmTags = alarmTags;
    }

    public String getAlarmNotes() {
        return alarmNotes;
    }

    public void setAlarmNotes(String alarmNotes) {
        this.alarmNotes = alarmNotes;
    }

    public String getAlarmRingTone() {
        return alarmRingTone;
    }

    public void setAlarmRingTone(String alarmRingTone) {
        this.alarmRingTone = alarmRingTone;
    }

    public int getAlarmVolume() {
        return alarmVolume;
    }

    public void setAlarmVolume(int alarmVolume) {
        this.alarmVolume = alarmVolume;
    }

    public boolean isAlarmVibrate() {
        return alarmVibrate;
    }

    public void setAlarmVibrate(boolean alarmVibrate) {
        this.alarmVibrate = alarmVibrate;
    }
}
