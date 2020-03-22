package com.example.worklesson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CustomizationActivity extends AppCompatActivity {
    TextView nameTown1View, nameTown2View;
    Button backButton, editTown1Button, editTown2Button;
    CheckBox windSpeed, pressure;
    static final String DATA_KEY_WINDSPEED = "DATA_KEY_WINDSPEED";
    static final String DATA_KEY_PRESSURE = "DATA_KEY_PRESSURE";
    static final String DATA_KEY_TOWN1_CUSTOMIZATION = "DATA_KEY_TOWN1_CUSTOMIZATION";
    static final String DATA_KEY_TOWN2_CUSTOMIZATION = "DATA_KEY_TOWN2_CUSTOMIZATION";
    static final String DATA_KEY_CLICK_BUTTON_EDIT_TOWN = "DATA_KEY_CLICK_BUTTON_EDIT_TOWN";
    static final String DATA_KEY_EDIT_TOWN = "DATA_KEY_EDIT_TOWN";
    private int activity_choice_town_request_cod = 1112;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customization);
        initVariable();
        setTextOnMainActivity();
        setOnListener();
        OnStartActivityChoiceTown();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null && requestCode == activity_choice_town_request_cod){
            String text = data.getStringExtra(ChoiceTownActivity.DATA_KEY_TOWN);
            String text_sing_click = data.getStringExtra(ChoiceTownActivity.DATA_KEY_SING_DATA);
            if (text_sing_click.equals("town1")){ nameTown1View.setText(text); }
            else if(text_sing_click.equals("town2")) {nameTown2View.setText(text);}
        }
    }

    private void OnStartActivityChoiceTown() {
        editTown1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        CustomizationActivity.this, ChoiceTownActivity.class);
                intent.putExtra(DATA_KEY_CLICK_BUTTON_EDIT_TOWN,"town1");
                intent.putExtra(DATA_KEY_EDIT_TOWN, nameTown1View.getText());
                startActivityForResult(intent, activity_choice_town_request_cod);
            }
        });

        editTown2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        CustomizationActivity.this, ChoiceTownActivity.class);
                intent.putExtra(DATA_KEY_CLICK_BUTTON_EDIT_TOWN,"town2");
                intent.putExtra(DATA_KEY_EDIT_TOWN, nameTown2View.getText());
                startActivityForResult(intent, activity_choice_town_request_cod);
            }
        });

    }

    private void setOnListener() {
        // Устаналиваем слушатель на кнопку возврата
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContainer = new Intent();
                intentContainer.putExtra(DATA_KEY_WINDSPEED, windSpeed.isChecked());
                intentContainer.putExtra(DATA_KEY_PRESSURE, pressure.isChecked());
                intentContainer.putExtra(DATA_KEY_TOWN1_CUSTOMIZATION,
                            nameTown1View.getText().toString());
                intentContainer.putExtra(DATA_KEY_TOWN2_CUSTOMIZATION,
                            nameTown2View.getText().toString());
                setResult(Activity.RESULT_OK, intentContainer);
                finish();
            }
        });
    }

    private void setTextOnMainActivity() {
        String text;
        Intent intent = getIntent();
        boolean isChecked;

        text = intent.getStringExtra(MainActivity.DATA_KEY_Town1);
        nameTown1View.setText(text);
        text = intent.getStringExtra(MainActivity.DATA_KEY_Town2);
        nameTown2View.setText(text);
        isChecked = intent.getBooleanExtra(MainActivity.DATA_KEY_WINDSPEED, false);
        windSpeed.setChecked(isChecked);
        isChecked = intent.getBooleanExtra(MainActivity.DATA_KEY_PRESSURE, false);
        pressure.setChecked(isChecked);
    }

    private void initVariable() {
        nameTown1View = findViewById(R.id.idTown1Custom);
        nameTown2View = findViewById(R.id.idTown2_Custom);
        backButton = findViewById(R.id.idButtonBack);
        windSpeed = findViewById(R.id.windSpeed);
        pressure = findViewById(R.id.pressure);
        editTown1Button = findViewById(R.id.buttonEditTown1);
        editTown2Button = findViewById(R.id.buttonEditTown2);
    }

}
