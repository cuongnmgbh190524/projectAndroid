package com.example.finala;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataExpence extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME_EXPENCE = "Expence.db";
    private static final int DATABASE_VERSION_EXPENCE = 1;
    private static final String TABLE_NAME_EXPENCE = "my_expence";
    private static final String COLUMN_ID_EXPENCE = "expence_id";
    private static final String COLUMN_EXPENCE = "expence_name";
    private static final String COLUMN_COST_EXPENCE = "expence_cost";
    private static final String COLUMN_DATE_START_EXPENCE ="expence_date_start";
    private static final String COLUMN_DATE_END_EXPENCE="expence_date_end";
    private static final String COLUMN_OVERVIEW_EXPENCE="expence_overview";
    public DataExpence(@Nullable Context context) {
        super(context, DATABASE_NAME_EXPENCE,null,DATABASE_VERSION_EXPENCE );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME_EXPENCE +
                " (" + COLUMN_ID_EXPENCE + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COLUMN_EXPENCE + " TEXT, " +
                COLUMN_COST_EXPENCE + "TEXT, " +
                COLUMN_DATE_START_EXPENCE + " TEXT, " +
                COLUMN_DATE_END_EXPENCE + " TEXT, " +
                COLUMN_OVERVIEW_EXPENCE + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_EXPENCE);
        onCreate(db);
    }
    void addExpence(String nameExpence, String costExpence, String DateStartExpence,String DateEndExpence, String OverviewExpence){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_EXPENCE, nameExpence);
        cv.put(COLUMN_COST_EXPENCE, costExpence);
        cv.put(COLUMN_DATE_START_EXPENCE, DateStartExpence);
        cv.put(COLUMN_DATE_END_EXPENCE, DateEndExpence);
        cv.put(COLUMN_OVERVIEW_EXPENCE, OverviewExpence);
        long result = db.insert(TABLE_NAME_EXPENCE , null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }
}
