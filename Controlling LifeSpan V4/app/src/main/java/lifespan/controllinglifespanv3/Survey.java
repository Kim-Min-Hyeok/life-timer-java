package lifespan.controllinglifespanv3;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.controllinglifespanv3.R;

import java.util.Calendar;
import java.util.Date;

public class Survey extends AppCompatActivity {

    String  sex, _age, cigarette, sleep, exercise, disease;
    int age, food;
    float _food;
    Calendar setDate;
    int whole_Life=83;
    Date today;
    long startDate;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        preferences = getSharedPreferences("Profile", Context.MODE_PRIVATE);
        editor = preferences.edit();

            today = Calendar.getInstance().getTime();
            setDate = Calendar.getInstance();
            setDate.setTime(today);
            setDate.add(Calendar.YEAR, whole_Life);
            Button button = (Button) findViewById(R.id.btn_done);
            EditText ed_name = (EditText) findViewById(R.id.sur_name);
            EditText ed_age = (EditText) findViewById(R.id.sur_age);
            EditText ed_height = (EditText) findViewById(R.id.sur_hei);
            EditText ed_weight = (EditText) findViewById(R.id.sur_wei);
            RatingBar rating_food = (RatingBar) findViewById(R.id.sur_foo);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(RadioGroup radioGroup, int i) {
                            RadioButton radioButton = (RadioButton) findViewById(i);
                            int id = radioGroup.getId();
                            switch (id) {
                                case R.id.sur_sex:
                                    sex = ((RadioButton) findViewById(i)).getText().toString();
                                    break;//성별
                                case R.id.sur_cig:
                                    cigarette = ((RadioButton) findViewById(i)).getText().toString();
                                    break;//담배
                                case R.id.sur_sle:
                                    sleep = ((RadioButton) findViewById(i)).getText().toString();
                                    break;//수면
                                case R.id.sur_exc:
                                    exercise = ((RadioButton) findViewById(i)).getText().toString();
                                    break;//운동
                                case R.id.sur_dis:
                                    disease = ((RadioButton) findViewById(i)).getText().toString();
                                    break;//질병
                            }
                        }
                    };


                    if (sex == "남자") {
                        setDate.add(Calendar.YEAR, -3);
                    } else setDate.add(Calendar.YEAR, 3);
                    ;
                    //성별-radio


                    _age = ed_age.getText().toString();
                    age = Integer.parseInt(_age);
                    setDate.add(Calendar.YEAR, -age);
                    //나이 intent


                    if (cigarette == "한값 이하") {
                        setDate.add(Calendar.YEAR, -3);
                    } else if (cigarette == "두갑 이상") {
                        setDate.add(Calendar.YEAR, -10);
                    } else if (cigarette == "한갑~두갑") {
                        setDate.add(Calendar.YEAR, -5);
                    }
                    //담배-radio

                    if (sleep == "6~8시간") {
                        setDate.add(Calendar.YEAR, 5);
                    } else setDate.add(Calendar.YEAR, -5);
                    //수면-radio

                    if (exercise == "3일 이하") {
                        setDate.add(Calendar.YEAR, 1);
                    } else if (exercise == "3~5일") {
                        setDate.add(Calendar.YEAR, 2);
                    } else if (exercise == "5일 이상") {
                        setDate.add(Calendar.YEAR, 4);
                    } else setDate.add(Calendar.YEAR, -2);
                    //운동-radio

                    _food = rating_food.getRating();
                    food = (int) (2 * _food);
                    setDate.add(Calendar.YEAR, -food);
                    //음식
                    if (disease == "아니요(No)") {
                        setDate.add(Calendar.YEAR, -5);
                    }
                    //질병-radio

                    Intent intent = new Intent(Survey.this, MainActivity.class);

                    //intent.putExtra("name", ed_name.getText().toString());
                    editor.putString("name", ed_name.getText().toString());

                    //intent.putExtra("sex", sex);
                    editor.putString("sex", sex);
                    //intent.putExtra("age", ed_age.getText().toString());
                    editor.putString("age", ed_age.getText().toString());
                    //intent.putExtra("height", ed_height.getText().toString());
                    editor.putString("height", ed_height.getText().toString());

                    //intent.putExtra("weight", ed_weight.getText().toString());
                    editor.putString("weight", ed_weight.getText().toString());
                    //intent.putExtra("cigarette", cigarette);
                    //intent.putExtra("sleep", sleep);
                    editor.putString("cigarette", cigarette);
                    editor.putString("sleep", sleep);
                    //intent.putExtra("exercise", exercise);
                    editor.putString("exercise",exercise);
                    //intent.putExtra("food", food);
                    editor.putString("food", Float.toString(_food));
                    //intent.putExtra("disease", disease);
                    editor.putString("disease", disease);
                    //intent.putExtra("setDate", (long) setDate.getTimeInMillis());
                    editor.putLong("setDate", (long) setDate.getTimeInMillis());
                    editor.commit();
                    intent.putExtra("survey_running", "true");
//광고
                    startActivity(intent);
                }
            });
    }
}