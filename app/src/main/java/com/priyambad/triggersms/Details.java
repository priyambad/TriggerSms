package com.priyambad.triggersms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.telephony.SmsManager;

public class Details extends AppCompatActivity {
    String key="";
    SmsManager smsManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        smsManager=SmsManager.getDefault();
        Intent i=getIntent();
        key+=i.getStringExtra("keyword").toString();
    }
    public void onDone(View view){
        EditText no=(EditText)findViewById(R.id.editText4);
        EditText co=(EditText)findViewById(R.id.editText5);
        String number=no.getText().toString();
        String msg=co.getText().toString();
        msg+=(" "+key);
        smsManager.sendTextMessage(number,null,msg,null,null);
        Intent i=new Intent(Details.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
