package com.example.worklesson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowId;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch blackTheme;
    public static final boolean isDebug = true;
    public final String TAG = "MyApp";
    private Button buttonStartCustomization;
    static final String DATA_KEY_Town1 = "Data_key_town1";
    static final String DATA_KEY_Town2 = "Data_key_town2";
    static final String DATA_KEY_PRESSURE = "Data_key_pressure";
    static final String DATA_KEY_WINDSPEED = "Data_key_windspeed";
    TextView nameTown1View, nameTown2View, windSpeed1Name, windSpeed1Value,
                windSpeed2Name, windSpeed2Value, pressure1Name, pressure1Value,
                pressure2Name, pressure2Value;
    LinearLayout windSpeed1Lineal, pressuer1Linear,
                    windSpeed2Lineal, pressuer2Linear;
    private int activity_customization_request_cod = 1234;
    Boolean isCheckedWindSpeed, isCheckedPressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariables();
        setOnStartActivityCustomizationButton();
        setVisibleCheckedBoxes(false, false);

        String instanceState;

        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }

        Toast.makeText(getApplicationContext(), instanceState + " - onCreate()",
                Toast.LENGTH_SHORT).show();
        MyLogger("onCreate");

        if (blackTheme != null) {
            blackTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        setTheme(R.style.Theme_AppCompat_DayNight);
                    }
                }
            });
        }
    }

    private void setOnStartActivityCustomizationButton() {
        buttonStartCustomization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CustomizationActivity.class);
                intent.putExtra(DATA_KEY_Town1,  nameTown1View.getText().toString());
                intent.putExtra(DATA_KEY_Town2, nameTown2View.getText().toString());
                intent.putExtra(DATA_KEY_WINDSPEED, isCheckedWindSpeed);
                intent.putExtra(DATA_KEY_PRESSURE,  isCheckedPressure);
                startActivityForResult(intent, activity_customization_request_cod);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && requestCode == activity_customization_request_cod){
            String text = data.getStringExtra(CustomizationActivity.DATA_KEY_TOWN1_CUSTOMIZATION);
            nameTown1View.setText(text);
            text = data.getStringExtra(CustomizationActivity.DATA_KEY_TOWN2_CUSTOMIZATION);
            nameTown2View.setText(text);
            Boolean isCheckedPressure = data.getBooleanExtra(
                    CustomizationActivity.DATA_KEY_PRESSURE, false);
            Boolean isCheckedWindSpeed = data.getBooleanExtra(
                    CustomizationActivity.DATA_KEY_WINDSPEED, false);

            setVisibleCheckedBoxes(isCheckedPressure, isCheckedWindSpeed);
        }
    }

    private void setVisibleCheckedBoxes(Boolean isCheckedPressure, Boolean isCheckedWindSpeed) {
        int isChecked = (isCheckedWindSpeed) ? View.VISIBLE : View.GONE;
//        windSpeed1Name.setVisibility(isChecked);
//        windSpeed1Value.setVisibility(isChecked);
//        windSpeed2Name.setVisibility(isChecked);
//        windSpeed2Value.setVisibility(isChecked);
        // todo wind speed
        windSpeed1Lineal.setVisibility(isChecked);
        windSpeed2Lineal.setVisibility(isChecked);

        isChecked = (isCheckedPressure) ? View.VISIBLE : View.GONE;
//        pressure1Name.setVisibility(isChecked);
//        pressure1Value.setVisibility(isChecked);
//        pressure2Name.setVisibility(isChecked);
//        pressure2Value.setVisibility(isChecked);

        pressuer1Linear.setVisibility(isChecked);
        pressuer2Linear.setVisibility(isChecked);

        this.isCheckedPressure = isCheckedPressure;
        this.isCheckedWindSpeed = isCheckedWindSpeed;
    }

    // Инициализация переменных
    private void initVariables() {
        blackTheme = findViewById(R.id.idBlackTheme);
        buttonStartCustomization = findViewById(R.id.idButtonStartCustomization);
        nameTown1View = findViewById(R.id.idTown1);
        nameTown2View = findViewById(R.id.idTown2);

        windSpeed1Lineal = findViewById(R.id.idWindSpeed1Linear);
        pressuer1Linear = findViewById(R.id.idPressure1Linear);
        windSpeed2Lineal = findViewById(R.id.idWindSpeed2Linear);
        pressuer2Linear = findViewById(R.id.idPressure2Linear);

//        windSpeed1Name = findViewById(R.id.idNameWindSpeed1);
//        windSpeed1Value = findViewById(R.id.idWindSpeed1);
//        windSpeed2Name = findViewById(R.id.idNameWindSpeed2);
//        windSpeed2Value = findViewById(R.id.idWindSpeed2);

//        pressure1Name = findViewById(R.id.idNamePressure1);
//        pressure1Value = findViewById(R.id.idPressure1);
//        pressure2Name = findViewById(R.id.idNamePressure2);
//        pressure2Value = findViewById(R.id.idPressure2);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart()", Toast.LENGTH_SHORT).show();
        MyLogger("onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "Повторный запуск!! - onRestoreInstanceState()", Toast.LENGTH_SHORT).show();
        MyLogger("onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume()", Toast.LENGTH_SHORT).show();
        MyLogger("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause()", Toast.LENGTH_SHORT).show();
        MyLogger("onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
        MyLogger("onSaveInstanceState");

        initVariables();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop()", Toast.LENGTH_SHORT).show();
        MyLogger("onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(getApplicationContext(), "onRestart()", Toast.LENGTH_SHORT).show();
        MyLogger("onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy()", Toast.LENGTH_SHORT).show();
        MyLogger("onDestroy");
    }

    public void onClickCustomization(View view) {
        setContentView(R.layout.activity_customization);
    }

    public void MyLogger(String statement) {
        if (isDebug) {
            Log.v(TAG, statement);
        }
    }

}

