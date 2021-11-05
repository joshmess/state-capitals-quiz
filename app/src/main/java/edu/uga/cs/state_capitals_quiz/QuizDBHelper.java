package edu.uga.cs.state_capitals_quiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class QuizDBHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG = "QuizDBHelper";

    private static final String DB_NAME = "quiz.db";
    private static final int DB_VERSION = 1;

    //Questions DB constants
    public static final String TABLE_QUESTIONS = "questions";
    public static final String QUESTIONS_COLUMN_ID = "_id";
    public static final String QUESTIONS_COLUMN_STATE = "state";
    public static final String QUESTIONS_COLUMN_CAPITAL = "capital";
    public static final String QUESTIONS_COLUMN_CITY1 = "city1";
    public static final String QUESTIONS_COLUMN_CITY2 = "city2";

    //Quizzes DB constants
    public static final String TABLE_QUIZZES = "questions";
    public static final String QUIZZES_COLUMN_ID = "_id";
    public static final String QUIZZES_COLUMN_DATE = "date";
    public static final String QUIZZES_COLUMN_Q1 = "q1";
    public static final String QUIZZES_COLUMN_Q2 = "q2";
    public static final String QUIZZES_COLUMN_Q3 = "q3";
    public static final String QUIZZES_COLUMN_Q4 = "q4";
    public static final String QUIZZES_COLUMN_Q5 = "q5";
    public static final String QUIZZES_COLUMN_Q6 = "q6";
    public static final String QUIZZES_COLUMN_SCORE = "score";

    // This is a reference to the only instance for the helper.
    private static QuizDBHelper helperInstance;

    //Create table constant for questions
    private static final String CREATE_QUESTIONS =
            "create table " + TABLE_QUESTIONS + " ("
                    + QUESTIONS_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUESTIONS_COLUMN_STATE + " TEXT, "
                    + QUESTIONS_COLUMN_CAPITAL + " TEXT, "
                    + QUESTIONS_COLUMN_CITY1 + " TEXT, "
                    + QUESTIONS_COLUMN_CITY2 + " TEXT"
                    + ")";
    //Create table constant for quizzes
    private static final String CREATE_QUIZZES =
            "create table " + TABLE_QUIZZES + " ("
                    + QUIZZES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + QUIZZES_COLUMN_DATE + " TEXT, "
                    + QUIZZES_COLUMN_Q1 + " TEXT, "
                    + QUIZZES_COLUMN_Q2 + " TEXT, "
                    + QUIZZES_COLUMN_Q3 + " TEXT, "
                    + QUIZZES_COLUMN_Q4 + " TEXT, "
                    + QUIZZES_COLUMN_Q5 + " TEXT, "
                    + QUIZZES_COLUMN_Q6 + " TEXT, "
                    + QUIZZES_COLUMN_SCORE + " INTEGER  "
                    + ")";

    //private constructor
    private QuizDBHelper( Context context ) {
        super( context, DB_NAME, null, DB_VERSION );
    }

    public static synchronized QuizDBHelper getInstance( Context context ) {
        // check if the instance already exists and if not, create the instance
        if( helperInstance == null ) {
            helperInstance = new QuizDBHelper( context.getApplicationContext() );
        }
        return helperInstance;
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_QUESTIONS );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUESTIONS + " created" );

        db.execSQL( CREATE_QUIZZES );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZZES + " created" );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( "drop table if exists " + TABLE_QUESTIONS );
        db.execSQL( "drop table if exists " + TABLE_QUIZZES );
        onCreate( db );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUESTIONS + " upgraded" );
        Log.d( DEBUG_TAG, "Table " + TABLE_QUIZZES + " upgraded" );
    }

}
