package com.example.christopher.reminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.christopher.reminder.model.Task;
import com.example.christopher.reminder.storageData.MysqlLite;

public class EditActivity extends AppCompatActivity {
    private Button button;
    private EditText edtTitle, edtContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtTitle = (EditText) findViewById(R.id.input_title);
        edtContent = (EditText) findViewById(R.id.input_content);

        button =(Button) findViewById(R.id.addButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                String title = edtTitle.getText().toString();
                String content = edtContent.getText().toString();
                if (title.isEmpty()&&content.isEmpty()){
                    Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }else{
                    Task task = new Task(content,title);
                    MysqlLite lite = new MysqlLite(getApplicationContext());
                    lite.addTask(task);
                    Log.d("Task", String.valueOf(task));
                    edtTitle.setText("");
                    edtContent.setText("");
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    intent.putExtra("title",title);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }

}
