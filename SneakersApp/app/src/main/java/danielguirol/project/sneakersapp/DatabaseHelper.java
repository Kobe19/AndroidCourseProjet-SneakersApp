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

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(id integer primary key autoincrement, name text, username text, email text, address text, phone integer, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists user");
    }

    //insertion in the table
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
        if(ins==-1){
            return false;
        }else {
            return true;
        }
    }

    public boolean checkuser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username =?", new String[]{username});
        if(cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }

    public boolean checkuandpass(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username =? and password =?", new String[]{username,password});
        if(cursor.getCount()>0){
            return true;
        }else{
            return false;
        }
    }

    public boolean takeuser(String username){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username =?", new String[]{username});
        if(cursor.getCount()>0){
            return false;
        }else{
            return true;
        }
    }
}
