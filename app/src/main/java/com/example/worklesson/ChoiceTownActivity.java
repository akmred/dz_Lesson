package com.example.worklesson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ChoiceTownActivity extends AppCompatActivity {
    static final String DATA_KEY_TOWN =  "DATA_KEY_TOWN";
    static final String DATA_KEY_SING_DATA =  "DATA_KEY_SING_DATA";
    EditText editTown;
    Button BackButtonCustomizeActivity;
    String sing;

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
        editTown.setText(nameTown);

    }

    private void setOnListener() {
        BackButtonCustomizeActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentContainer = new Intent();
                intentContainer.putExtra(DATA_KEY_SING_DATA, sing);
                intentContainer.putExtra(DATA_KEY_TOWN, editTown.getText().toString());
                setResult(Activity.RESULT_OK, intentContainer);
                finish();
            }
        });
    }

    private void initVariables() {
        editTown = findViewById(R.id.editTown);
        BackButtonCustomizeActivity = findViewById(R.id.idButtonBack2);
    }


}
