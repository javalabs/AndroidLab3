package com.github.javalabs.androidlab3;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int notifyID = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View v) {
        switch (v.getId()) {
            case R.id.btn_alert: {
                AlertDialog.Builder b = new AlertDialog.Builder(this);
                //b.setTitle("Заголовок");
                View view = (RelativeLayout) getLayoutInflater().inflate(R.layout.alert_layout, null);
                b.setView(view);
                ((TextView) view.findViewById(R.id.textView)).setText("Тут текст сообщения (AlertDialog)");
                b.show();
                this.notifyID = 1;
                break;
            }
            case R.id.btn_notification: {
                Context context = getApplicationContext();
                NotificationCompat.Builder b = new NotificationCompat.Builder(context);
                RemoteViews r = new RemoteViews(this.getPackageName(), R.layout.alert_layout);
                r.setTextViewText(R.id.textView, "Проверка уведомления (Notification) id=" + String.valueOf(notifyID));
                b.setContent(r);
                b.setSmallIcon(R.mipmap.ic_launcher); // 02-14 19:51:14.802 400-1703/system_process E/NotificationService: Not posting notification with icon==0: Notification(pri=0 contentView=com.github.javalabs.androidlab3/0x7f04001a vibrate=null sound=null defaults=0x0 flags=0x0 kind=[null])
                b.setTicker("Текст, который появится в строке состояния");
                NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                nm.notify(notifyID, b.build());

                notifyID++;
                break;
            }
        }
    }
    private void Notify(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());

        builder.setSmallIcon(R.mipmap.image)
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker("Текст, который появится в строке!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Напоминание")
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Уведомление"); // Текст уведомления
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(12, notification);
    }

}
