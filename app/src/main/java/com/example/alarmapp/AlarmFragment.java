package com.example.alarmapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alarmapp.Adapter.AlarmAdapter;
import com.example.alarmapp.Model.Alarm;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class AlarmFragment extends Fragment {

    private FloatingActionButton btn_add;
    private RecyclerView recyclerView;
    private AlarmAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        btn_add = view.findViewById(R.id.btn_addAlarm);
        recyclerView = view.findViewById(R.id.rec_Alarm);
        adapter = new AlarmAdapter(getActivity());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter.setData(getListAlarm());
        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SetAlarmActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    private List<Alarm> getListAlarm() {
        List<Alarm> list= new ArrayList<>();
        list.add(new Alarm(R.drawable.img_day, "08 : 00", "Mon", "Work",
                "You have to work now!!!", "Default", 50, true));
        list.add(new Alarm(R.drawable.img_night, "20 : 00", "Mon", "Study",
                "You have to study now!!!", "Default", 50, true));
        list.add(new Alarm(R.drawable.img_day, "05 : 00", "Mon", "Exercise",
                "Wake up and do exercise!!!", "Default", 50, true));
        list.add(new Alarm(R.drawable.img_night, "23 : 30", "Mon", "Sleep",
                "It's time to go to bed!!!", "Default", 50, true));
        list.add(new Alarm(R.drawable.img_day, "08 : 00", "Mon", "Work",
                "You have to work now!!!", "Default", 50, true));
        list.add(new Alarm(R.drawable.img_night, "20 : 00", "Mon", "Study",
                "You have to study now!!!", "Default", 50, true));
        list.add(new Alarm(R.drawable.img_day, "05 : 00", "Mon", "Exercise",
                "Wake up and do exercise!!!", "Default", 50, true));
        list.add(new Alarm(R.drawable.img_night, "23 : 30", "Mon", "Sleep",
                "It's time to go to bed!!!", "Default", 50, true));
        return list;
    }

}