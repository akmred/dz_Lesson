package com.example.worklesson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static android.widget.AdapterView.*;

public class ChoiceTownActivity extends AppCompatActivity implements Postman {
    static final String DATA_KEY_TOWN =  "DATA_KEY_TOWN";
    static final String DATA_KEY_SING_DATA =  "DATA_KEY_SING_DATA";
    EditText editTown;
    Button BackButtonCustomizeActivity;
    String sing;
    ListView listView;
    String data_choice_name_city;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_choice_town);
        initVariables();
        setTextOnCustomizationActivity();
        setOnListener();
    }

    private void setTextOnCustomizationActivity(){
        Intent intent = getIntent();
        sing = intent.getStringExtra(CustomizationActivity.DATA_KEY_CLICK_BUTTON_EDIT_TOWN);
        String nameTown = intent.getStringExtra(CustomizationActivity.DATA_KEY_EDIT_TOWN);

    }

    private void setOnListener() {
        BackButtonCustomizeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContainer = new Intent();
                intentContainer.putExtra(DATA_KEY_SING_DATA, sing);
                intentContainer.putExtra(DATA_KEY_TOWN, data_choice_name_city);
                setResult(Activity.RESULT_OK, intentContainer);
                finish();
            }
        });

    }

    private void initVariables() {
        listView = findViewById(R.id.cities_list_view);
        BackButtonCustomizeActivity = findViewById(R.id.idButtonBack2);
    }

    @Override
    public void fragmentMail(String name_city) {
        data_choice_name_city = name_city;
    }
}
