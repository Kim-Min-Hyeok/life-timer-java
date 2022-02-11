package com.example.controllinglifespanv3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.controllinglifespanv3.MainActivity;
import com.example.controllinglifespanv3.R;

public class Survey extends AppCompatActivity {

    public static final int done = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);

        Button button = (Button) findViewById(R.id.btn_done);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(intent, done);
            }
        });


    }
}