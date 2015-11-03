package com.example.christopher.reminder.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.christopher.reminder.R;
import com.example.christopher.reminder.model.Task;

import java.util.List;

/**
 * Created by Christopher on 04/10/2015.
 */
public class MyAdapter extends ArrayAdapter {
    protected LayoutInflater inflater;
    private TextView title;
    private TextView content;

    public MyAdapter(Context context, List<Task> tasks) {

        super(context, R.layout.customlist,tasks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = (Task) getItem(position);
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.customlist,null);
        title = (TextView)convertView.findViewById(R.id.txttitle);
        content = (TextView)convertView.findViewById(R.id.txtcontent);
        title.setText(task.getTitle());
        content.setText(task.getContent());

        return convertView;
    }
}
