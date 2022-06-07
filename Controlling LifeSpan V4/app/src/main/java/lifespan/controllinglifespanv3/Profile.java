package lifespan.controllinglifespanv3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controllinglifespanv3.R;

public class Profile extends AppCompatActivity {
    String name, sex, age, height, weight, cigarette, sleep, exercise, food, disease;
    TextView info_1, info_2, info_3, info_4, info_5, info_6, info_7, info_8, info_9;
    SharedPreferences preferences;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        info_1= (TextView)findViewById(R.id.info_1);
        info_2= (TextView)findViewById(R.id.info_2);
        info_3= (TextView)findViewById(R.id.info_3);
        info_4= (TextView)findViewById(R.id.info_4);
        info_5= (TextView)findViewById(R.id.info_5);
        info_6= (TextView)findViewById(R.id.info_6);
        info_7= (TextView)findViewById(R.id.info_7);
        info_8= (TextView)findViewById(R.id.info_8);
        info_9= (TextView)findViewById(R.id.info_9);

        preferences = getSharedPreferences("Profile", MODE_PRIVATE);

        name=preferences.getString("name", "홍길동");
        sex=preferences.getString("sex", "남");
        age=preferences.getString("age", "20");
        height=preferences.getString("height", "180cm");
        weight=preferences.getString("weight", "75kg");
        cigarette=preferences.getString("cigarette", "비흡연자");
        sleep=preferences.getString("sleep", "6~8시간");
        exercise=preferences.getString("exercise", "3~5일");
        food=preferences.getString("food", "3.5");
        disease=preferences.getString("disease", "아니요(No)");

        info_1.setText("1.이름: " + name);
        info_2.setText("2.성별: "+ sex);
        info_3.setText("3.나이: "+ age+"세");
        info_4.setText("4.키/몸무게: "+ height+"/"+ weight);
        info_5.setText("5.흡연유무: "+ cigarette);
        info_6.setText("6.수면시간: "+sleep);
        info_7.setText("7.운동주기: "+exercise);
        info_8.setText("8.섭취음식 자극정도(0.0~5.0): "+food);
        info_9.setText("9.유전적질병 유무: "+disease);

        ImageButton btn_delete = (ImageButton) findViewById(R.id.btn_delete);

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(Profile.this, Survey.class);
                startActivity(intent);
            }
        });

    }
}