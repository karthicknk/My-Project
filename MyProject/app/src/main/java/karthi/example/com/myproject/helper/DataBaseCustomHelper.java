package karthi.example.com.myproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.data.CustomData;

/**
 * Created by AshokKumar on 21/10/2017.
 */

public class DataBaseCustomHelper extends SQLiteOpenHelper{

    private static final String TAG = "DataBaseCustomHelper";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "custom1";
    private static final String TABLE_NAME = "ustomtable1";
    private static final String ID = "id";
    private static final String IMAGE = "image";
    private static final String TITLE = "title";
    private static final String RATING = "rating";
    private static final String YEAR = "year";

    private String CREATE_TABLE_NAME = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + IMAGE + " TEXT,"
            + TITLE + " TEXT,"
            + RATING + " TEXT,"
            + YEAR + " TEXT" + ")";

    public DataBaseCustomHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_NAME);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void DeleteCustom(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("DELETE FROM " + TABLE_NAME);
        sqLiteDatabase.close();
    }

    public void InsertCustom(CustomData data){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(IMAGE, data.getImage());
        values.put(TITLE, data.getTitle());
        values.put(RATING, data.getRating());
        values.put(YEAR, data.getYear());
        long a = sqLiteDatabase.insert(TABLE_NAME,null,values);
//        Log.e(TAG, "InsertCustom: " + a );
        sqLiteDatabase.close();
    }

    public List<CustomData> GelAllValues(){
        List<CustomData> datas = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {ID,IMAGE,TITLE,RATING,YEAR};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()){
            do {
                CustomData customData = new CustomData();
                customData.setId(cursor.getString(cursor.getColumnIndex(ID)));
                customData.setImage(cursor.getString(cursor.getColumnIndex(IMAGE)));
                customData.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                customData.setRating(cursor.getDouble(cursor.getColumnIndex(RATING)));
                customData.setYear(cursor.getInt(cursor.getColumnIndex(YEAR)));
                datas.add(customData);
            }while (cursor.moveToNext());
        }
        return datas;
    }
}
