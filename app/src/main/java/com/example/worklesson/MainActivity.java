package com.example.worklesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    Switch blackTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blackTheme = findViewById(R.id.idBlackTheme);
        if (blackTheme != null){
            blackTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        setTheme(R.style.Theme_AppCompat_DayNight);
                    }
                }
            });
        }
    }

    public void onClickCustomization(View view) {
        setContentView(R.layout.activity_customization);
    }


    public void onClickBackMainActivity(View view) {
        setContentView(R.layout.activity_main);
    }

    public void onClickBackCustomizeActivity(View view) {
        setContentView(R.layout.activity_customization);
    }

    public void onClickEditTown(View view) {
        setContentView(R.layout.avtivity_choice_town);
    }
}
