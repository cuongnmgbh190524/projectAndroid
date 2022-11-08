package com.example.finala;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import org.jetbrains.annotations.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Trip.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_trip";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TRIP = "trip_name";
    private static final String COLUMN_DEPARTURE ="trip_departure";
    private static final String COLUMN_DESTINATION="trip_destination";
    private static final String COLUMN_DATE="trip_date";
    private static final String COLUMN_RISK="trip_risk";
    private static final String COLUMN_DESCRIPTION="trip_description";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                COLUMN_TRIP + " TEXT, " +
                COLUMN_DEPARTURE + " TEXT, " +
                COLUMN_DESTINATION + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_RISK + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTrip(String name, String departure, String destination,String date, String risk, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TRIP, name);
        cv.put(COLUMN_DEPARTURE, departure);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_RISK, risk);
        cv.put(COLUMN_DESCRIPTION, description);
        long result = db.insert(TABLE_NAME , null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor readAllData(){
        String query = "SELECT * FROM my_trip";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id,String name, String departure, String destination, String date, String risk, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TRIP,name);
        cv.put(COLUMN_DEPARTURE, departure);
        cv.put(COLUMN_DESTINATION, destination);
        cv.put(COLUMN_DATE,date);
        cv.put(COLUMN_RISK,risk);
        cv.put(COLUMN_DESCRIPTION, description);

        long result = db.update(TABLE_NAME, cv, "id=?",new String[]{row_id});
        if (result ==-1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Successfully Update", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?",new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "Fail to Delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully Delete.", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
