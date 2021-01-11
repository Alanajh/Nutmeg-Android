package com.aaenterprise.nutmeg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "login.db";
    public static final String TABLE_NAME = "loginUser";
    public static final String NICKS_TABLE = "NicksTests";
    public static final String TABLE_NAME_TEST = "test_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "username";
    public static final String COL_3 = "password";

    public static final String COL_ID_NICK = "id_nick";
    public static final String COL_TITLE_NICK = "title";
    public static final String COL_1_NICK = "questions";
    public static final String COL_2_NICK = "answers";
    public static final String COL_3_NICK = "option1";
    public static final String COL_4_NICK = "option2";
    public static final String COL_5_NICK = "option3";
    public static final String COL_6_NICK = "option4";
    public static final String COL_7_NICK = "answercheck";

    public static final Integer TEST_ID_COL = null;
    public static final String TEST_COL_1 = "table_name";
    public static final String TEST_COL_2 = "title";
    public static final Integer TEST_COL_3 = null;
    public static final String TEST_COL_4 = "subject";
    public static final String TEST_COL_5 = "genre";
    public static final String TEST_COL_6 = "tier";
    public static final Integer TEST_COL_7 = null;
    public static final Float TEST_COL_8 = null;
    public static final String TEST_COL_9 = "grade";
    public static final String TEST_COL_10 = "age";
    public static final String TEST_COL_11 = "endpoint";
    public static final String TEST_COL_12 = "documentation";
    public static final String TEST_COL_13 = "description";

    public static final String TBL_NAME = "test";
    public static final String KEY_ID = "ID";
    public static final String COL_SUBJECT = "SUBJECT";
    public static final String COL_TITLE = "TITLE";
    public static final String COL_GENRE = "GENRE";

    SQLiteDatabase dtbs = this.getReadableDatabase();
    public boolean connectt = false;
    public static String dbFilePath = null;
    public Integer count;
    public Integer questionCount = 0;

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
/**
        db.execSQL("CREATE TABLE loginUser (ID INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE " + NICKS_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, table_name TEXT" +
                ", title TEXT, type INTEGER, subject TEXT, genre TEXT, tier INTEGER, dewey INTEGER" +
                ", dewey_decimal FLOAT, grade TEXT, age TEXT, endpoint TEXT, documentation TEXT, description TEXT)");
        **/
        //if table exists, query
        //if table does not exist, then create it
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String user, String password) {
        dtbs = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long res = dtbs.insert("loginUser", null, contentValues);
        dtbs.close();
        return res;
    }

    public long addTest(String table_name, String title, Integer type, String subject, String genre,
                        Integer tier, Integer dewey, Float dewey_decimal, String grade, String age,
                        String endpoint, String documentation, String description) {
        dtbs = this.getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("table_name", table_name);
        cv.put("title", title);
        cv.put("type", type);
        cv.put("subject", subject);
        cv.put("genre", genre);
        cv.put("tier", tier);
        cv.put("dewey", dewey);
        cv.put("dewey_decimal", dewey_decimal);
        cv.put("grade", grade);
        cv.put("age", age);
        cv.put("endpoint", endpoint);
        cv.put("documentation", documentation);
        cv.put("description", description);
        long results = dtbs.insert("test_table", null, cv);
        dtbs.close();
        return results;

    }

    public String learn() {
        String dbName = getDatabaseName();
        SQLiteDatabase dbRead = this.getReadableDatabase();
        Cursor c = dbRead.rawQuery("SELECT username FROM loginUser", null);
        c.moveToFirst();
        return dbName;

    }

    public boolean checkUser(String username, String password) {
        String[] columns = {COL_1};
        dtbs = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = dtbs.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        dtbs.close();

        if (count > 0) {
            connectt = true;
            dbFilePath = dtbs.getPath();
            return true;
        } else {
            connectt = false;
            dbFilePath = "Unable to get the path to the database file.";
            return false;
        }
    }

    public boolean addData(String item1, String item2, String item3) {
        SQLiteDatabase dbs = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_SUBJECT, item1);
        cv.put(COL_TITLE, item2);
        cv.put(COL_GENRE, item3);

        long result = dbs.insert(TBL_NAME, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<String> getQ(){
        ArrayList<String> testQ = new ArrayList<>();
        SQLiteDatabase help = this.getReadableDatabase();
        String query = "SELECT * FROM testQuestions";
        Cursor cursor = help.rawQuery(query, null);

        if(cursor.moveToFirst()){
            testQ.add(cursor.getString(1));
            testQ.add(cursor.getString(2));
            testQ.add(cursor.getString(3));
            testQ.add(cursor.getString(4));
            testQ.add(cursor.getString(5));
            testQ.add(cursor.getString(6));
            testQ.add(cursor.getString(7));
            testQ.add(cursor.getString(8));
            testQ.add(cursor.getString(9));
            testQ.add(cursor.getString(10));
        }else{
            Toast.makeText(null, " Was not successful.", Toast.LENGTH_LONG).show();
        }

        return testQ;
    }

    public ArrayList<String> getA(){
        ArrayList<String> testAnswers = new ArrayList<>();
        ArrayList<Integer> testRandomsList = new ArrayList<>();
        ArrayList<String> testAnswers2 = new ArrayList<>();
        SQLiteDatabase help = this.getReadableDatabase();
        String query = "SELECT * FROM testAnswers";
        Cursor cursor = help.rawQuery(query, null);
        Integer a=0, b=0, c=0, d=0;
        Random random = new Random();

        Collections.shuffle(testRandomsList);
        for(int j = 0; j < 4; j++){
            testRandomsList.get(j).toString();
        }

        if(cursor.moveToFirst()){
            testAnswers.add(cursor.getString(a));
            testAnswers.add(cursor.getString(b));
            testAnswers.add(cursor.getString(c));
            testAnswers.add(cursor.getString(d));
        }else{
            Toast.makeText(null, " Was not successful.", Toast.LENGTH_LONG).show();
        }
        testAnswers2.add(testRandomsList.toString());
        return testAnswers;
    }

    public ArrayList<HashMap<String, String>> getAllTests(){
        ArrayList<HashMap<String, String>> testArrayList = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> testMap = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM sample_tests";
        SQLiteDatabase dd = this.getWritableDatabase();
        Cursor cursor = dd.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{

                testMap.put("genre", cursor.getString(0));
                testMap.put("title", cursor.getString(1));
                testMap.put("subject", cursor.getString(2));

                testArrayList.add(testMap);
            }while (cursor.moveToNext());
        }

        return testArrayList;
    }

    public ArrayList getAllTests4(){
        ArrayList testListing = new ArrayList();
        String selectQuery = "SELECT title FROM sample_tests ORDER BY title";
        SQLiteDatabase dd = this.getWritableDatabase();
        Cursor cursor = dd.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                String ttt = cursor.getString(cursor.getColumnIndex("title"));
                testListing.add(ttt);
            }while (cursor.moveToNext());
        }
        return testListing;
    }
    public String getPath() {
        return dtbs.getPath();
    }

        public ArrayList getQuestion() {

        ArrayList testQuestions = new ArrayList();
        String selectQuery = "SELECT * FROM testQuestions WHERE TestID = 1";
        SQLiteDatabase sqld = this.getReadableDatabase();
        Cursor cursor = sqld.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                String questions = cursor.getString(cursor.getColumnIndex("Q1"));
                testQuestions.add(questions);
            }while(cursor.moveToNext());
        }
        return testQuestions;
    }

}
