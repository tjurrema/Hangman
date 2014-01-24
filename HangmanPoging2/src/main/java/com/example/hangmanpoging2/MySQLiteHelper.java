package com.example.hangmanpoging2;

/**
 * Created by Tessa on 21-1-14.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;

public class MySQLiteHelper extends SQLiteOpenHelper {

    // Books table name
    private static final String TABLE_SCORES = "scores";

    // Books Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_SCORES = "scores";
    private static final String[] COLUMNS = {KEY_ID,KEY_SCORES};

    // Database Version
    private static final int DATABASE_VERSION = 4;
    // Database Name
    private static final String DATABASE_NAME = "ScoresDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create score table
        String CREATE_SCORES_TABLE = "CREATE TABLE scores ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "scores INTEGER)";

        // create scores table
        db.execSQL(CREATE_SCORES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older scores table if existed
        db.execSQL("DROP TABLE IF EXISTS scores");

        // create fresh scores table
        this.onCreate(db);
    }

    // to add scores in database
    public void addScores(Scores scores){

        //for logging
        Log.d("addScores", scores.toString());

        // get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();

        // create ContentValues to add key "column"/value
        ContentValues values = new ContentValues();
        values.put(KEY_SCORES, scores.getScores()); // get score from user

        // insert
        db.insert(TABLE_SCORES, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        // close
        db.close();
    }

    //get top Ten Scores
    public List<Scores> getTopTenScores() {
        List<Scores> topTenScores = new LinkedList<Scores>();

        // build the query
        String query = "SELECT  * FROM " + TABLE_SCORES + " ORDER BY " + KEY_SCORES + " LIMIT 10";
                //+ "ORDERED BY" + KEY_SCORES;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // go over each row, build scores and add it to list
        Scores scores = null;
        if (cursor.moveToFirst()) {
            do {
                scores = new Scores();
                scores.setId(Integer.parseInt(cursor.getString(0)));
                scores.setScores(Integer.parseInt(cursor.getString(1)));

                // Add scores to list
                topTenScores.add(scores);
             } while (cursor.moveToNext());
         }

        Log.d("getAllScores()", topTenScores.toString());

        // return the top ten scores
        return topTenScores;
    }

}