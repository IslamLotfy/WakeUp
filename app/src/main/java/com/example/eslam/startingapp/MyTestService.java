package com.example.eslam.startingapp;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.text.format.Time;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by islam on 01/02/17.
 */

public class MyTestService extends IntentService {

    private final FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private authinticator auth;
    private List<WakeUp> wakeUps;
    private Time t;

    public MyTestService() {
        super("MytestService");
        database = FirebaseDatabase.getInstance();
        auth = new authinticator();
        databaseReference = database.getReference(auth.getUserId());
        wakeUps = new LinkedList<WakeUp>();
        t = new Time();
        t.setToNow();
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        getDatafromFirebse();

    }

    public void getDatafromFirebse() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int cnt = 0;
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    WakeUp wakeUp = singleSnapshot.getValue(WakeUp.class);
                    //System.out.println(wakeUp.getWakeUptitle());
                    if (wakeUp.getDay() == t.monthDay && wakeUp.getHour() == t.hour) {
                        wakeUps.add(wakeUp);
                        System.out.println(wakeUp.getWakeUptitle());
                        cnt++;
                    }
                }
                if (wakeUps.size() > 0) {
                    createNotification("you have " + wakeUps.size() + " WakeUps in the next hour !");
                    System.out.println("notification shouled bs createsed");
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void createNotification(String messageBody) {
        Intent intent = new Intent(this, WakeUpsNotificationed.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("wakeups", (Serializable) wakeUps);
        PendingIntent resultIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder mNotificationBuilder = (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_notifications_active_white_48dp)
                .setContentTitle("WakeUp")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(notificationSoundURI)
                .setContentIntent(resultIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000});

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, mNotificationBuilder.build());
    }
}
