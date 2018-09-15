package com.priyambad.triggersms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.content.Intent;

public class SetPassword extends AppCompatActivity {

    EditText e1;
    EditText e2;
    Spinner sp;
    TriggerDB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);
        e1=(EditText)findViewById(R.id.editText);
        e2=(EditText)findViewById(R.id.editText2);
        sp=(Spinner)findViewById(R.id.spinner);
        db=new TriggerDB(this,null,null,1);

    }
    public void setPass(View view){
        String pass=e1.getText().toString();
        String que=String.valueOf(sp.getSelectedItem());
        String ans=e2.getText().toString();
        db.addToDB(pass,que,ans);
        Intent i=new Intent(SetPassword.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
