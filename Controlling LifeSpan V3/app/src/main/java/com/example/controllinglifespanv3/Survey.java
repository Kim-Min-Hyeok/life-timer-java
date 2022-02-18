package com.example.controllinglifespanv3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.controllinglifespanv3.MainActivity;
import com.example.controllinglifespanv3.R;

public class Survey extends AppCompatActivity {

    public static final int done = 1001;
    String sex,cigarette,sleep,exercise,disease;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Button button = (Button) findViewById(R.id.btn_done);
        EditText ed_name = (EditText) findViewById(R.id.sur_name);
        EditText ed_age = (EditText) findViewById(R.id.sur_age);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Survey.this, MainActivity.class);

                RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {
                        RadioButton radioButton = (RadioButton) findViewById(i);
                        int id = radioGroup.getId();
                        switch (id) {
                            case R.id.sur_sex:
                                sex = ((RadioButton) findViewById(i)).getText().toString();
                                break;
                            case R.id.sur_cig:
                                cigarette = ((RadioButton) findViewById(i)).getText().toString();
                                break;
                            case R.id.sur_sle:
                                sleep = ((RadioButton) findViewById(i)).getText().toString();
                                break;
                            case R.id.sur_exc:
                                exercise = ((RadioButton) findViewById(i)).getText().toString();
                                break;
                            case R.id.sur_dis:
                                disease = ((RadioButton) findViewById(i)).getText().toString();
                        }
                    }
                };

                intent.putExtra("name", ed_name.getText().toString());
                intent.putExtra("sex", sex);
                intent.putExtra("age", ed_age.getText().toString());
                intent.putExtra("cigarette", cigarette);
                intent.putExtra("sleep", sleep);
                intent.putExtra("exercise", exercise);

                intent.putExtra("disease", disease);

                startActivity(intent);
            }
        });


    }
}