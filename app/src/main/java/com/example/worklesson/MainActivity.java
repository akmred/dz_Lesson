package com.example.worklesson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch blackTheme;
    CheckBox pressure, windSpeed;
    public static final boolean isDebug = true;
    public final String TAG = "MyApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            boolean noDataPressure = savedInstanceState.getBoolean("noDataPressure");
            ;
            // если данные есть, то поолучим
            if (noDataPressure)
                setContentView(R.layout.activity_main);
            else setContentView(R.layout.activity_customization);
        }else   setContentView(R.layout.activity_main);
        initVariables();

        String instanceState;

        if (savedInstanceState == null) {
            instanceState = "Первый запуск!";
        } else {
            instanceState = "Повторный запуск!";
        }

        if (blackTheme != null) {
            final MainBlackTheme blackThemeClass = MainBlackTheme.getBlackTheme();
            blackTheme.setChecked(blackThemeClass.getIsBlackTheme());
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

    // Инициализация переменных
    private void initVariables() {
        blackTheme = findViewById(R.id.idBlackTheme);
        pressure =  findViewById(R.id.pressure);
        windSpeed =  findViewById(R.id.windSpeed);
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

        boolean noDataPressure = saveInstanceState.getBoolean("noDataPressure");;
        // если данные есть, то поолучим
       if (!noDataPressure) {
           boolean isPressure = saveInstanceState.getBoolean("pressure");
           boolean isWindSpeed = saveInstanceState.getBoolean("windSpeed");
           pressure.setChecked(isPressure);
           windSpeed.setChecked(isWindSpeed);
       }
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
        if (pressure != null){
            saveInstanceState.putBoolean("pressure", pressure.isChecked());
            saveInstanceState.putBoolean("windSpeed", windSpeed.isChecked());
            saveInstanceState.putBoolean("noDataPressure", false);
        }
        else saveInstanceState.putBoolean("noDataPressure", true);
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

    public void onClickBackMainActivity(View view) {
        setContentView(R.layout.activity_main);
    }

    public void onClickBackCustomizeActivity(View view) {
        setContentView(R.layout.activity_customization);
    }

    public void onClickEditTown(View view) {
        setContentView(R.layout.avtivity_choice_town);
    }

    public void MyLogger(String statement) {
        if (isDebug) {
            Log.v(TAG, statement);
        }
    }

}

