package com.example.notificationnewversion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText editTitle,editDescription;
    public Button btnAdd;
    public  String title, descriptionText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTitle = findViewById(R.id.editTitle);
        editDescription = findViewById(R.id.editDescription);



        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel();
            }
        });




    }


        private void createNotificationChannel() {

            title = (editTitle.getText().toString());
            descriptionText = (editDescription.getText().toString());
            String id ="my_cahennel_id_01";
            NotificationManager manager =(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = manager.getNotificationChannel(id);
            if (channel==null){
                channel=new NotificationChannel(id,"Channel Title",NotificationManager.IMPORTANCE_HIGH);

                channel.setDescription("[Channel description]");
                channel.enableVibration(true);
                channel.setVibrationPattern(new long[]{10,100,20,34});
                channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                manager.createNotificationChannel(channel);
            }
            }
            Intent notificationIntent = new Intent(this,NotificationActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
            stackBuilder.addNextIntentWithParentStack(notificationIntent);
            PendingIntent contentIntent =
                    stackBuilder.getPendingIntent(0,
                            PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,id)
                    .setSmallIcon(R.drawable.notification)
                    .setStyle(new NotificationCompat.BigPictureStyle()
                            .setBigContentTitle(title))
                    .setContentText(descriptionText)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setVibrate(new long[]{10,100,20,34})
                    .setAutoCancel(false);
            builder.setContentIntent(contentIntent);
            NotificationManagerCompat n = NotificationManagerCompat.from(getApplicationContext());
            n.notify(1,builder.build());


            }
    }



