package com.priyambad.triggersms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    TriggerDB db;
    EditText e1;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        db=new TriggerDB(this,null,null,1);
        t1=(TextView)findViewById(R.id.textView2);
        e1=(EditText)findViewById(R.id.editText3);
        t1.setText(db.getQuestion());
    }
    public void Validate(View view){
        String ans=e1.getText().toString();
        if(checkStr(ans,db.getAnswer())){
            Intent i=new Intent(ChangePassword.this,SetPassword.class);
            startActivity(i);
            finish();
        }
        else{
            e1.setText("");
            Toast.makeText(this,"Wrong Credentials",Toast.LENGTH_SHORT).show();
        }
    }
    public boolean checkStr(String code,String smsStr){
        boolean val=true;
        if(code.length()==smsStr.trim().length()){
            for(int i=0;i<code.length();i++){
                if(!(code.charAt(i)==smsStr.charAt(i))){
                    val=false;
                    break;
                }
            }
        }
        else
            val=false;
        return val;
    }
}
