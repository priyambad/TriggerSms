package com.priyambad.triggersms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.content.ContentResolver;
import android.net.Uri;
import android.database.Cursor;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    TriggerDB db;
    public static MainActivity inst;
    public static StringBuilder code=new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db=new TriggerDB(this,null,null,1);
        ImageView iv=(ImageView)findViewById(R.id.imageView2);
        iv.setImageResource(R.drawable.mainimg);

    }
    @Override
    protected void onStart(){

        super.onStart();
        inst=this;
    }
    @Override
    protected void onResume(){
        super.onResume();
    }
    public void changePass(View view){
        Intent i=new Intent(MainActivity.this,ChangePassword.class);
        startActivity(i);
        finish();
    }
    public void onAlert (View view){
        Intent i=new Intent(MainActivity.this,Details.class);
        i.putExtra("keyword","SPEAK");
        startActivity(i);
    }
    public void onLocate(View view){
        Intent i=new Intent(MainActivity.this,Details.class);
        i.putExtra("keyword","WHERE");
        startActivity(i);
    }


    /*
    public String getMsg(){
        String s="";
        ContentResolver contentResolver=getContentResolver();
        Cursor smsCursor=contentResolver.query(Uri.parse("content://sms/inbox"),null,null,null,null);
        int bodyIndex=smsCursor.getColumnIndex("body");
        if(bodyIndex<0 || !smsCursor.moveToFirst())
            return s;
        s+=smsCursor.getString(bodyIndex);
        smsCursor.close();
        return s;
    }
    public boolean check(){
        boolean val=true;
        if(code.length()==getMsg().trim().length()){
            for(int i=0;i<code.length();i++){
                if(!(code.charAt(i)==getMsg().charAt(i))){
                    val=false;
                    break;
                }
            }
        }
        else
            val=false;
        return val;
    }
    */
}
