package karthi.example.com.myproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.data.User;

/**
 * Created by AshokKumar on 19/10/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper{

    private static final String TAG = "DataBaseHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myproject";
    private static final String TABLE_NAME = "mytable";
    private static final String ID = "id";
    private static final String IMAGE = "image";
    private static final String NAME = "name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";

    private static String CREATE_TABLE_NAME = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + IMAGE + " TEXT,"
            + NAME + " TEXT,"
            + EMAIL + " TEXT,"
            + PASSWORD + " TEXT" + ")";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS" + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void Insert(User user){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE, user.getImage());
        values.put(NAME, user.getName());
        values.put(EMAIL, user.getEmail());
        values.put(PASSWORD, user.getPassword());
        long s = sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();
    }

    public boolean CheckUser(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {ID};

        String selection = EMAIL + "=?" + " AND " + PASSWORD + "=?" ;

        String[] selectionArgs = {email,password};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int CursorCount = cursor.getCount();
        cursor.close();

        if (CursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean CheckUser(String email){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {ID};

        String selection = EMAIL + "=?" ;

        String[] selectionArgs = {email};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        int CursorCount = cursor.getCount();
        cursor.close();

        if (CursorCount > 0){
            return true;
        }
        return false;
    }

    public User getValue(String email) {
//        List<User> users = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {ID, IMAGE, NAME, EMAIL, PASSWORD};

        String selection = EMAIL + "=?";

        String[] selectionArgs = {email};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.moveToFirst()) ;
            User user = new User();
            user.setName(cursor.getString(cursor.getColumnIndex(NAME)));
            user.setImage(cursor.getBlob(cursor.getColumnIndex(IMAGE)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(EMAIL)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(PASSWORD)));
//            users.add(user);

        return user;
    }

}
