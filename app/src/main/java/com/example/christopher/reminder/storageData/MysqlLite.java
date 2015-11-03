package com.example.christopher.reminder.storageData;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.christopher.reminder.model.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christopher on 04/10/2015.
 */
public class MysqlLite extends SQLiteOpenHelper {
    private static final int version= 1;
    /*database name */
    private static final String db_name = "remind";
    /* table name*/
    private static final String table_event = "task";
    /* table column*/
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    /* create table  */
    private static final String create_table_task= "CREATE TABLE "+ table_event
            +"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_TITLE+" TEXT,"+KEY_CONTENT+" TEXT"+")";
    private static final String get_all_task = "SELECT  * FROM " + table_event;

    public MysqlLite(Context context) {
            super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_table_task);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_event );
        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,task.getTitle());
        values.put(KEY_CONTENT,task.getContent());
        db.insert(table_event, null, values);
        db.close();
    }

    public List<Task> getAllTasks() {
        List<Task> tasksList = new ArrayList<Task>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(get_all_task, null);
        if (cursor.moveToFirst()){
            do {
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
                task.setTitle(cursor.getString(cursor.getColumnIndex(KEY_TITLE)));
                task.setContent(cursor.getString(cursor.getColumnIndex(KEY_CONTENT)));

                tasksList.add(task);

            }while (cursor.moveToNext());
        }
        return tasksList;

    }
    /*public Task getTask(int id) {

    }


    public int updateTask(Task task) {}


    public void deleteTask(Task task) {}*/
}
