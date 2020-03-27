package com.example.trelli.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbAdapter{
    static final String KEY_ID      = "_id";
    static final String KEY_JUDUL   = "judul";
    static final String KEY_DATE    = "tgl";
    static final String KEY_CATATAN = "catatan";
    static final String TAG         = "DbHelper";
    static final String DB_NAME     = "DbTask";
    static final String DB_TABLE    = "task";
    static final int    DB_VERSION  = 1;
    static final String DB_CREATE   = "create table "
            + DB_TABLE + "("
            + KEY_ID + " integer primary key autoincrement, "
            + KEY_JUDUL + " text not null, "
            + KEY_DATE + " text not null, "
            + KEY_CATATAN + " text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DbAdapter(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(DB_CREATE);
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to"
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS task");
            onCreate(db);
        }
    }

    /**
     * Open database
     */
    public DbAdapter open(){
        db = DBHelper.getWritableDatabase();
        return this;
    }

    /**
     * Close database
     */
    public void close(){
        DBHelper.close();
    }
    /**
     * Insert Task
     */

    public long insertTask(String judulTask, String tanggalTask, String catatanTask){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_JUDUL, judulTask);
        initialValues.put(KEY_DATE, tanggalTask);
        initialValues.put(KEY_CATATAN, catatanTask);
        return db.insert(DB_TABLE, null, initialValues);
    }
    public long insertTask(String Id, String judulTask, String tanggalTask, String catatanTask){
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_ID, Id);
        initialValues.put(KEY_JUDUL, judulTask);
        initialValues.put(KEY_DATE, tanggalTask);
        initialValues.put(KEY_CATATAN, catatanTask);
        return db.insert(DB_TABLE, null, initialValues);
    }

    /**
     * Retrieve data.
     * @return
     */
    public Cursor getAllTask(){
        return db.query(DB_TABLE, new String[] {KEY_ID, KEY_JUDUL, KEY_DATE, KEY_CATATAN}, null, null, null, null, null);
    }

    /**
     * Update Task
      */

    public boolean updateTask(long Id, String judul, String tanggal, String catatan){
        ContentValues args = new ContentValues();
        args.put(KEY_JUDUL, judul);
        args.put(KEY_DATE, tanggal);
        args.put(KEY_CATATAN, catatan);
        return db.update(DB_TABLE, args, KEY_ID + "=" + Id, null) > 0;
    }

    /**
     * Delete Task
      */

    public boolean deleteContact(long Id){
        return db.delete(DB_TABLE, KEY_ID + "=" + Id, null) > 0;
    }
}
