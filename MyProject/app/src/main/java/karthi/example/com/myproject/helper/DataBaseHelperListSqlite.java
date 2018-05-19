package karthi.example.com.myproject.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import karthi.example.com.myproject.data.ListSqlight;

/**
 * Created by AshokKumar on 21/10/2017.
 */

public class DataBaseHelperListSqlite extends SQLiteOpenHelper{

    private static final String TAG = "DataBaseHelperListSqlit";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dnsqlite1";
    private static final String TABLE_NAME = "listsqlite1";
    private static final String ID = "id";
    private static final String TITLE = "title";

    private String CREATE_TABLE_NAME = "CREATE TABLE " + TABLE_NAME + "("
            + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TITLE + " TEXT" + ")";

    public DataBaseHelperListSqlite(Context context) {
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
    
    public void InsertListRow(String value){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, value);
        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();
    }
    
    public void DeleteListRow(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String whereClause = ID + "=?";
        String[] selectionArgs = {id};
        sqLiteDatabase.delete(TABLE_NAME, whereClause, selectionArgs);
        sqLiteDatabase.close();
    }

    public ArrayList<ListSqlight> Getall() {
        ArrayList<ListSqlight> sqlights = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        String[] columns = {ID, TITLE};

        Cursor cursor = sqLiteDatabase.query(TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst()) {
            do {
                ListSqlight listSqlight = new ListSqlight();
                listSqlight.setId(cursor.getString(cursor.getColumnIndex(ID)));
                listSqlight.setTitle(cursor.getString(cursor.getColumnIndex(TITLE)));
                sqlights.add(listSqlight);
            } while (cursor.moveToNext());
        }
        return sqlights;
    }
}
