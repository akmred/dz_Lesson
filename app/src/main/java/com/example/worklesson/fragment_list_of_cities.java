package com.example.worklesson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.Objects;

public class fragment_list_of_cities extends Fragment {
    private ListView listView;
    private TextView emptyTextView;
    Activity activity;
    private int currentPosition = 0;    // Текущая позиция (выбранный город)

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            activity = (Activity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_of_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVariable(view);
        initList();

    }

    private void initVariable(View view) {
        listView = view.findViewById(R.id.cities_list_view);
        emptyTextView = view.findViewById(R.id.cities_list_empty_view);
    }

    private void initList() {
        // Для того, чтобы показать список, надо задействовать адаптер.
        // Такая конструкция работает для списков, например ListActivity.
        // Здесь создаем из ресурсов список городов (из массива)
        ArrayAdapter adapter =
                ArrayAdapter.createFromResource(Objects.requireNonNull(getActivity()), R.array.cities,
                        android.R.layout.simple_list_item_activated_1);
        listView.setAdapter(adapter);

        listView.setEmptyView(emptyTextView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                listView.setItemChecked(currentPosition, true);

                // Получаем соат с именем города
                CoatContainer coat = getCoatContainer();

                try {
                    ((Postman) activity).fragmentMail(coat.cityName);
                }catch (ClassCastException ignored){}

            }
        });

    }

    private CoatContainer getCoatContainer() {
        String[] cities = getResources().getStringArray(R.array.cities);
        CoatContainer container = new CoatContainer();
        container.position = currentPosition;
        container.cityName = cities[currentPosition];
        return container;
    }


}
