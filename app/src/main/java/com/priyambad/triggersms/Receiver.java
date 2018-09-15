package com.priyambad.triggersms;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.telephony.SmsManager;
/**
 * Created by priyambad on 16-Mar-17.
 */
public class Receiver extends BroadcastReceiver {
    public static final String SMS_BUNDLE="pdus";
    String smsStr="";
    TriggerDB db;
    StringBuilder number=new StringBuilder();
    StringBuilder code=new StringBuilder();
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onReceive(Context context,Intent intent){
        Bundle intentExtras = intent.getExtras();
        db=new TriggerDB(context,null,null,1);
        code.setLength(0);
        code.append(db.getPassword());
        if(intentExtras!=null){
            Object[] sms=(Object[]) intentExtras.get(SMS_BUNDLE);
            for(int i=0;i<sms.length;i++){
                String format=intentExtras.getString("format");
                SmsMessage smsMessage =SmsMessage.createFromPdu((byte[]) sms[i], format);
                String smsbody=smsMessage.getMessageBody();
                smsStr+=smsbody;
                number.append(smsMessage.getOriginatingAddress());
            }
        }
        if(StrEqual(code.toString(),smsStr.substring(0,5))){
            if(StrEqual(smsStr.substring(6,11),"SPEAK")){
                Intent i=new Intent(context,Alarm.class);
                i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
            else{
                if(StrEqual(smsStr.substring(6,11),"WHERE")){
                    GPSTracker gps=new GPSTracker(context);
                    if(gps.canGetLocation){
                        double longitude=gps.getLongitude();
                        double latitude=gps.getLatitude();
                        SmsManager smsManager=SmsManager.getDefault();
                        smsManager.sendTextMessage(number.toString(),null,smsStr.substring(0,5)+" LOCAT"+" "+latitude+" "+longitude,null,null);
                        number.setLength(0);
                    }
                }
                else{
                    if(StrEqual(smsStr.substring(6,11),"LOCAT")){
                        Intent i=new Intent(context,MapsActivity.class);
                        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }
                }
            }
        }
    }
    public boolean StrEqual(String s1,String s2){
        boolean cond=true;
        for(int i=0;i<s1.length();i++){
            if(!(s1.charAt(i)==s2.charAt(i))){
                cond=false;
                break;
            }
        }
        return cond;
    }
}
