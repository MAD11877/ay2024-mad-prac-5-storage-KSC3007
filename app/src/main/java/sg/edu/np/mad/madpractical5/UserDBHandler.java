package sg.edu.np.mad.madpractical5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.StringJoiner;

import android.database.sqlite.SQLiteException;
import android.util.Log;

public class UserDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "users.db";
    private static final String USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_FOLLOWED = "followed";

    public UserDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        Log.i("Database Operations", "Creating a table.");
        try {
            String CREATE_USERS_TABLE = "CREATE TABLE " + USERS + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + "TEXT," + COLUMN_DESCRIPTION + "TEXT,"
                    + COLUMN_FOLLOWED + "BOOLEAN" + ")";
            db.execSQL(CREATE_USERS_TABLE);
            ContentValues values = new ContentValues();
            db.insert(USERS, null, values);
            Log.i("Database Operations", "Table created successfully.");
            db.close();
        } catch (SQLiteException e) {
            Log.i("Database Operations", "Error creating table", e);
        }
    }

    public User getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + USERS, null);

        if (cursor.moveToFirst()) {
            user.setID(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setPassword(cursor.getString(2));
            cursor.close();
        }
        db.close();
        return user;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USERS);
        onCreate(db);
    }
}
