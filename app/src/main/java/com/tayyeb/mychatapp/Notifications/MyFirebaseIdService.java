package com.tayyeb.mychatapp.Notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.tayyeb.mychatapp.MessageActivity;

import androidx.core.app.NotificationCompat;

public class MyFirebaseIdService extends FirebaseMessagingService {

     public void onNewToken(String s){
         super.onNewToken(s);
         Log.d("NEW_TOKEN", s);

         FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

         String refreshToken=FirebaseInstanceId.getInstance().getToken();

         if(firebaseUser!=null){
             updateToken(refreshToken);
         }
     }

    public void updateToken(String refreshToken){
        FirebaseUser firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Tokens");
        Token token=new Token(refreshToken);
        reference.child(firebaseUser.getUid()).setValue(token);
    }



}
