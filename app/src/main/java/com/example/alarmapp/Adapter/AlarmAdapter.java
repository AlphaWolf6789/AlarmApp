package com.example.alarmapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmapp.Model.Alarm;
import com.example.alarmapp.R;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder> {

    private Context context;
    private List<Alarm> alarmList;

    public AlarmAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Alarm> list){
        this.alarmList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlarmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent, false);
        return new AlarmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmViewHolder holder, int position) {
        Alarm alarm = alarmList.get(position);
        if(alarm == null){
            return;
        }
        holder.imgAlarm.setImageResource(alarm.getAlarm_ImgID());
        holder.txtAlarmTime.setText(alarm.getAlarmTime());
        holder.txtAlarmTag.setText(alarm.getAlarmTags());
        holder.txtAlarmNote.setText(alarm.getAlarmNotes());
        holder.btnDayRepeat.setEnabled(true);
        holder.switchBtn.setChecked(true);
    }

    @Override
    public int getItemCount() {
        if(alarmList != null) {
            return alarmList.size();
        }
        return 0;
    }

    public class AlarmViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgAlarm;
        private TextView txtAlarmTime;
        private TextView txtAlarmTag;
        private TextView txtAlarmNote;
        private Button btnDayRepeat;
        private SwitchCompat switchBtn;

        public AlarmViewHolder(@NonNull View v) {
            super(v);
            imgAlarm = v.findViewById(R.id.img_setAlarm);
            txtAlarmTime = v.findViewById(R.id.txt_TimeAlarm_Fragment);
            txtAlarmNote = v.findViewById(R.id.txt_alarmNote_Fragment);
            txtAlarmTag = v.findViewById(R.id.txt_alarmTags_Fragment);
            btnDayRepeat = v.findViewById(R.id.btn_Mon_Fragment);
            switchBtn = v.findViewById(R.id.switchBtn_Fragment);
        }
    }
}
