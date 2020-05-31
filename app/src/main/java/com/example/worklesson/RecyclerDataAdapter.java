package com.example.worklesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.ViewHolder> {
    private String[] data;
    private Context context;
    private String nameForDayWeather;

    public RecyclerDataAdapter(String [] data, String nameForDayWeather){
        this.data = data;
        this.nameForDayWeather = nameForDayWeather;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        if (nameForDayWeather.equals("list1ForDaysWeather")) {
            view = LayoutInflater.from(context).inflate(R.layout.list1_item_layout,
                    parent, false);
        }
        else {
            view = LayoutInflater.from(context).inflate(R.layout.list2_item_layout,
                    parent, false);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setText(holder, position);

    }

    private void setText(@NonNull ViewHolder holder, final int position){
        holder.listItemTextView.setText(data[position] + "     +" + position);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0:data.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView listItemTextView;
        View itemView;
        ViewHolder(@NonNull View itemView){
            super(itemView);
            this.itemView = itemView;
            initView(itemView);
        }

        private void initView(View itemView){
            if (nameForDayWeather.equals("list1ForDaysWeather")) {
                listItemTextView = itemView.findViewById(R.id.list1ForDaysWeather);
            }
            else {
                listItemTextView = itemView.findViewById(R.id.list2ForDaysWeather);

            }
        }
    }
}
