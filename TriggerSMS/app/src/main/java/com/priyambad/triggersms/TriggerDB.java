package com.priyambad.triggersms;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.Context;
import android.content.ContentValues;
import android.widget.Toast;
import android.util.Log;
/**
 * Created by priyambad on 18-Mar-17.
 */
public class TriggerDB extends SQLiteOpenHelper {
    private static final String DB_NAME="Trigger_Database";
    private static final int DB_VER=1;

    public static final String table_pass="passwords";
    public static final String col_id_p="id";
    public static final String col_pass="password";

    public static final String table_que="Ques_Ans";
    public static final String col_id_q="id";
    public static final String col_q="question";
    public static final String col_a="answer";

    Context con;

    public TriggerDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VER);
        con=context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query1="CREATE TABLE "+table_pass+" ( "+col_id_p+" INTEGER PRIMARY KEY AUTOINCREMENT, "+col_pass+" TEXT "+");";
        String query2="CREATE TABLE "+table_que+" ( "+col_id_q+" INTEGER PRIMARY KEY AUTOINCREMENT, "+col_q+" TEXT, "+col_a+" TEXT "+");";
        db.execSQL(query1);
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_pass);
        db.execSQL("DROP TABLE IF EXISTS " + table_que);
        onCreate(db);
    }
    public void addToDB(String a,String b,String c){
        ContentValues value1=new ContentValues();
        ContentValues value2=new ContentValues();
        Log.d("TriggerDB","getwritable calling");
        SQLiteDatabase db=getWritableDatabase();
        Log.d("TriggerDB","getwritable called");
        value1.put(col_pass,a);
        value2.put(col_q,b);
        value2.put(col_a,c);
        int i=(int)db.insert(table_pass, null, value1);
        int j=(int)db.insert(table_que,null,value2);
        db.close();
        if(i != -1)
            Toast.makeText(con, "Password Added To Database " + i, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(con, "Something wrong ", Toast.LENGTH_SHORT).show();
        if(j != -1)
            Toast.makeText(con, "Q & A Added To Database " + i, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(con, "Something wrong ", Toast.LENGTH_SHORT).show();
    }
    public String getPassword(){
        String s="";
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+table_pass+" WHERE 1";
        Cursor c= db.rawQuery(query,null);
        if(c.moveToLast())
            s += c.getString(c.getColumnIndex("password"));
        else
            s+="No data in Password";
        db.close();
        c.close();
        return s;
    }
    public String getQuestion(){
        String s="";
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+table_que+" WHERE 1";
        Cursor c= db.rawQuery(query,null);
        if(c.moveToLast())
            s += c.getString(c.getColumnIndex("question"));
        else
            s+="No data in Question.";
        db.close();
        c.close();
        return s;
    }
    public String getAnswer(){
        String s="";
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+table_que+" WHERE 1";
        Cursor c= db.rawQuery(query,null);
        if(c.moveToLast())
            s += c.getString(c.getColumnIndex("answer"));
        else
            s+="No data In Question.";
        db.close();
        c.close();
        return s;
    }
}
