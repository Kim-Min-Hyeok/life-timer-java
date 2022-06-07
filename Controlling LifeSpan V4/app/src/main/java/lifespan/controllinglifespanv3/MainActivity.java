package lifespan.controllinglifespanv3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.controllinglifespanv3.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    String name, sex, age_, height, weight, cigarette, sleep, exercise, disease;
    int food;
    float _food;
    //int age;
    long remaining_Life, year, month, day, hour, minute, second;
    TextView text_name;
    TextView text_y, text_mo, text_d, text_h, text_mi, text_s, menu_Name, menu_Info;
    Handler handler = new Handler();
    private DrawerLayout drawerLayout;
    private View drawerView;
    Calendar curDate;
    long setDate, diffDate;
    SharedPreferences preferences;
    private long getDate;
    private AdView mAdView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));

                ImageButton profileImage = (ImageButton)findViewById(R.id.menu_img);
                profileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        ActivityCompat.finishAffinity(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //광고(앱ID)설정
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });



        //배너광고
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        preferences = getSharedPreferences("Profile", MODE_PRIVATE);
        getDate = preferences.getLong("setDate", 0);
        if(getDate == 0) {
            Intent intent_Activity = new Intent(MainActivity.this, Survey.class);
            startActivity(intent_Activity);
        }else {

            final ImageButton menu = (ImageButton) findViewById(R.id.btn_menu);
            drawerLayout = (DrawerLayout) findViewById(R.id.la_main);
            drawerView = (View) findViewById(R.id.drawer);
            //navigation메뉴 넘어가는 버튼

            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawerLayout.openDrawer(drawerView);
                    ;
                }
            });

            ImageButton profileImage = (ImageButton) findViewById(R.id.menu_img);
            profileImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent imageIntent = new Intent();
                    imageIntent.setType("image/*");
                    imageIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(imageIntent, "Select Picture"), 1);
                }
            });
            //프로필 사진 > 갤러리에서 선택 후 변경

            Button profile = (Button) findViewById(R.id.menu_profile);
            profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent_profile = new Intent(MainActivity.this, Profile.class);
                    startActivity(intent_profile);
                }
            });

            //Intent intent = getIntent();
            setDate = preferences.getLong("setDate", 0);
            name = preferences.getString("name", "홍길동");
            //sex = intent.getStringExtra("sex");
            //age_ = intent.getStringExtra("age");
            height = preferences.getString("height", "180cm");
            weight = preferences.getString("weight", "74kg");
            //cigarette = intent.getStringExtra("cigarette");
            //sleep = intent.getStringExtra("sleep");
            //exercise = intent.getStringExtra("exercise");
            //disease = intent.getStringExtra("disease");
            //_food = intent.getFloatExtra("food", 0);
            //food = (int)(2*_food);

            text_name = (TextView) findViewById(R.id.tx_name);
            text_name.setText(name + "님은");

            menu_Name = (TextView) findViewById(R.id.menu_name);
            menu_Name.setText(name);

            menu_Info = (TextView) findViewById(R.id.menu_info);
            menu_Info.setText(height + "cm/" + weight + "kg");

            Date today = Calendar.getInstance().getTime();
            curDate = Calendar.getInstance();
            curDate.setTime(today);

            diffDate = setDate - curDate.getTimeInMillis();

            setTimer();

            text_y = (TextView) findViewById(R.id.tx_y);
            text_mo = (TextView) findViewById(R.id.tx_mo);
            text_d = (TextView) findViewById(R.id.tx_d);
            text_h = (TextView) findViewById(R.id.tx_h);
            text_mi = (TextView) findViewById(R.id.tx_mi);
            text_s = (TextView) findViewById(R.id.tx_s);

            text_y.setText(Long.toString(year) + "년");
            text_mo.setText(Long.toString(month) + "개월");
            text_d.setText(Long.toString(day) + "일");
            text_h.setText(Long.toString(hour) + "시간");
            text_mi.setText(Long.toString(minute) + "분");
            text_s.setText(Long.toString(second) + "초");

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
            timer.schedule(task, 1000, 1000);

        }
    }

    public void setTimer(){
        long rem;
        long div_min = 60;
        long div_hour = div_min * 60;
        long div_day = div_hour * 24;
        long div_mo = div_day * 31;
        long div_y = div_day * 365;

        remaining_Life = diffDate/1000;

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