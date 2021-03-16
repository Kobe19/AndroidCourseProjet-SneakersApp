package danielguirol.project.sneakersapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, "sneakerdb", null, 1);
    }

    /* ************************************************************************************************************************************************************************************************************
     *                       CREATE TABLE METHOD                                                                                                                                                                  *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(id integer primary key autoincrement, name text, username text, email text, address text, phone integer, password text)");
    }

    /* ************************************************************************************************************************************************************************************************************
     *                       UPDATING IF WHETHER THERE IS A NEW VERSION                                                                                                                                           *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists user");
    }

    /* ************************************************************************************************************************************************************************************************************
     *                       INSERTION OF THE DATA IN THE TABLE                                                                                                                         *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    public boolean insert(String name, String username, String email, String address, String phone, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("username",username);
        cv.put("email",email);
        cv.put("address",address);
        cv.put("phone",phone);
        cv.put("password",password);
        long ins = db.insert("user", null,cv);
        return ins != -1;
    }

    /* ************************************************************************************************************************************************************************************************************
     *                       CHECKING IF THE USERNAME IS CORRECT                                                                                                                          *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    public boolean checkuser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username =?", new String[]{username});
        return cursor.getCount() <= 0;
    }

    /* ************************************************************************************************************************************************************************************************************
     *                       CHECKING IF THE USERNAME & PASSWORD ARE CORRECT                                                                                                                         *                                                                                                                                                                                                           *
     * ************************************************************************************************************************************************************************************************************/
    public boolean checkuandpass(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username =? and password =?", new String[]{username,password});
        return cursor.getCount() > 0;
    }
}
