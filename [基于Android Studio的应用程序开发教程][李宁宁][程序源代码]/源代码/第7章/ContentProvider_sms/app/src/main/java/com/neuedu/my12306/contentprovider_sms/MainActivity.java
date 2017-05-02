package com.neuedu.my12306.contentprovider_sms;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    TextView textView;
    Button button;
    ContentResolver resolver;
    String[] projection;

    final static Uri SMSURI = Uri.parse("content://sms/");
    SimpleDateFormat sfd1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);
        button=(Button)findViewById(R.id.button);

        resolver = getContentResolver();
        projection = new String[] { "address","date","body"};
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               // Cursor cursor = resolver.query(SMSURI, projection, " address in "+ "(10086, 13342291345)",	null, null);
                Cursor cursor = resolver.query(SMSURI, projection, " address =10086",	null, null);
                textView.setText(convertToSms(cursor));
            }
        });
    }

    String convertToSms(Cursor cursor) {
        String text = "";
        if (cursor != null) {
            int cnt = cursor.getCount();
            System.out.println("==>>>"+"cnt="+cnt);
            cursor.moveToFirst();
            for (int i = 0; i < cnt; i++) {
                text += cursor.getString(cursor
                        .getColumnIndex("address")) + "; ";

                Long long1=Long.valueOf(cursor.getString(cursor
                        .getColumnIndexOrThrow("date")));

                Date date1 = new Date(long1);
                String time = sfd1.format(date1);
                text += time + ";   ";

                text += cursor.getString(cursor
                        .getColumnIndex("body")) + "; ";
                System.out.println("==>>>"+"body="+cursor.getString(cursor
                        .getColumnIndex("body")));
                text += "\n";
                cursor.moveToNext();
            }

        }
        return text;
    }

}
