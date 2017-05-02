package cn.edu.neusoft.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    NotificationManager mNotificationManager;
    NotificationCompat.Builder mNotificationBuilder;
    Button btn_start,btn_cancle;
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;

        btn_start = (Button)findViewById(R.id.startBtn);
        btn_cancle = (Button)findViewById(R.id.cancelBtn);

        //第一步：获取状态通知栏管理：
        //注意：NotificationManager 是一个系统Service
        mNotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        //第二步：实例化通知栏构造器NotificationCompat.Builder：并对Builder进行配置：
        mNotificationBuilder=new NotificationCompat.Builder(context);
        Intent notificationIntent=new Intent(context, MainActivity.class);
        PendingIntent contentIntent=PendingIntent.getActivity(context, 0, notificationIntent, 0);
        mNotificationBuilder.setContentTitle("通知栏标题")
                .setContentText("通知栏内容：")
                .setContentIntent(contentIntent)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setTicker("通知来了")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.gps);

        //第三步：发送通知请求
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.notify(1,mNotificationBuilder.build());//第一个参数为ID
            }
        });

        // 取消通知
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.cancel(1);

            }
        });
    }
}
