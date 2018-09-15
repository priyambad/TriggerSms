package com.priyambad.triggersms;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
/**
 * Created by priyambad on 16-Mar-17.
 */
public class Receiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE="pdus";
    String smsStr="";
    TriggerDB db;
    StringBuilder code=new StringBuilder();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context,Intent intent){
        Bundle intentExtras = intent.getExtras();
        db=new TriggerDB(context,null,null,1);
        code.setLength(0);
        code.append(db.getPassword()+" SPEAK");
        if(intentExtras!=null){
            Object[] sms=(Object[]) intentExtras.get(SMS_BUNDLE);
            for(int i=0;i<sms.length;i++){
                String format=intentExtras.getString("format");
                SmsMessage smsMessage =SmsMessage.createFromPdu((byte[]) sms[i], format);
                String smsbody=smsMessage.getMessageBody();
                smsStr+=smsbody;
            }
        }
        if(check()){
            Intent i=new Intent(context,Alarm.class);
            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
    public boolean check(){
        boolean val=true;
        if(code.length()==smsStr.trim().length()){
            for(int i=0;i<code.length();i++){
                if(!(code.toString().charAt(i)==smsStr.charAt(i))){
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
