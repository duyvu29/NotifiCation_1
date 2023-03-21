package com.example.notification_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    private static final String CHANNEL_ID = "Channel";
    private static final String CONTEXT  = "AAAAAaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaAAAAAAAAAAAaaaaaaaaaaaaaaaaaaaaaa";
    private static final String CONTEXT_2 = " bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        createNotificationChannel();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               setNotifyction();
            }
        });

    }

    void  setNotifyction(){
        Notification notification = null;
        // bitmap dùng về hình ảnh truyền data và gán data hình ảnh
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.laptop);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setContentTitle(CONTEXT)
                    .setContentText(CONTEXT_2)
                    /** bigText giúp show full text trong Notifycation**/
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(CONTEXT))
                    .setLargeIcon(bitmap)
                    /**BigPicture how full image trong notify**/

                    /** lưu ý khi sử dụng BigPictureStyle ( bitmap) nên sử dụng image.png nó sẽ không nhận hình ảnh file XML **/
                    .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap))
                    .setSmallIcon(R.drawable.ic_android_black_24dp)
                    .build();
        }

        NotificationManager notificationManager =( NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager!= null){
            notificationManager.notify(getNotifyId(), notification);
        }
    }

    private int getNotifyId() {
        return (int) new Date().getTime();
    }

    /** lưu ý khi muốn Notification hiển thị nhiều thông báo thi chú ý đoạn code dòng 71 và code 77**/

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null){
                notificationManager.createNotificationChannel(channel);
            }

        }
    }


    }

