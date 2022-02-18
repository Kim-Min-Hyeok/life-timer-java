package com.example.controllinglifespanv3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.controllinglifespanv3.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    String name, sex, age_, cigarette, sleep, exercise, disease;
    long whole_Life=83; //년 단위 (+ remaining_Life도 년 단위)
    long age=20;
    long remaining_Life, year, month, day, hour, minute, second;
    long rem;
    long div_min = 60;
    long div_hour = div_min * 60;
    long div_day = div_hour * 24;
    long div_mo = div_day * 31;
    long div_y = div_day * 365;
    TextView text_name;
    TextView text_y, text_mo, text_d, text_h, text_mi, text_s;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        sex = intent.getStringExtra("sex");
        age_ = intent.getStringExtra("age");
        cigarette = intent.getStringExtra("cigarette");
        sleep = intent.getStringExtra("sleep");
        exercise = intent.getStringExtra("exercise");
        disease = intent.getStringExtra("disease");

        if(sex=="남자"){
            whole_Life = 80;
        }else whole_Life = 86;
        //성별 intent
        age = Long.parseLong(age_);
        //나이 intent
        if(cigarette=="한값 이하"){
            whole_Life = whole_Life - 3;
        }else if(cigarette=="두갑 이상"){
            whole_Life = whole_Life -10;
        }else whole_Life = whole_Life -5;
        //담배 intent
        if(sleep=="6~8시간"){
            whole_Life = whole_Life +5;
        }else whole_Life = whole_Life -5;
        //수면 intent
        if(exercise=="3일 이하"){
            whole_Life = whole_Life +1;
        }else if(exercise=="3~5일"){
            whole_Life = whole_Life +2;
        }else if(exercise=="5일 이상"){
            whole_Life = whole_Life +4;
        }else whole_Life = whole_Life -2;
        //운동 intent
        if(disease=="아니요(No)"){
            whole_Life = whole_Life -5;
        }



        text_name = (TextView)findViewById(R.id.tx_name);

        text_name.setText(name+"님은");

        text_y = (TextView)findViewById(R.id.tx_y);
        text_mo = (TextView)findViewById(R.id.tx_mo);
        text_d = (TextView)findViewById(R.id.tx_d);
        text_h = (TextView)findViewById(R.id.tx_h);
        text_mi = (TextView)findViewById(R.id.tx_mi);
        text_s = (TextView)findViewById(R.id.tx_s);

        setTimer();

        text_y.setText(Long.toString(year)+"년");
        text_mo.setText(Long.toString(month)+"개월");
        text_d.setText(Long.toString(day)+"일");
        text_h.setText(Long.toString(hour)+"시간");
        text_mi.setText(Long.toString(minute)+"분");
        text_s.setText(Long.toString(second)+"초");

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                updateTimer();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        text_y.setText(Long.toString(year) + "년");
                        text_mo.setText(Long.toString(month) + "개월");
                        text_d.setText(Long.toString(day) + "일");
                        text_h.setText(Long.toString(hour) + "시간");
                        text_mi.setText(Long.toString(minute) + "분");
                        text_s.setText(Long.toString(second) + "초");

                    }
                });

            }
        };
        timer.schedule(task,1000,1000);


    }

    public void setTimer(){
        remaining_Life = (whole_Life - age)*365*24*60*60;

        year = remaining_Life / div_y;
        rem = remaining_Life % div_y;

        month = rem / div_mo;
        rem = rem % div_mo;

        day = rem / div_day;
        rem = rem % div_day;

        hour = rem / div_hour;
        rem = rem % div_hour;

        minute = rem / div_min;
        second = rem % div_min;
    }

    public void updateTimer(){
        if (second != 0) {
            --second;
        } else if (minute != 0) {
            second = 60;
            --second;
            --minute;
        } else if (hour != 0) {
            second = 60;
            minute = 60;
            --second;
            --minute;
            --hour;
        } else if (day != 0) {
            second = 60;
            minute = 60;
            hour = 24;
            --second;
            --minute;
            --hour;
            --day;
        } else if (month != 0) {
            second = 60;
            minute = 60;
            hour = 24;
            day = 31;
            --second;
            --minute;
            --hour;
            --day;
            --month;
        } else if (year != 0) {
            second = 60;
            minute = 60;
            hour = 24;
            day = 31;
            month = 12;
            --second;
            --minute;
            --hour;
            --day;
            --month;
            --year;
        }

    }
}