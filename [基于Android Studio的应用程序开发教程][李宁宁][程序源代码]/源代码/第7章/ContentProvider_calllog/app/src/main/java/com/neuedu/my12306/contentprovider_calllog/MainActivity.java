package com.neuedu.my12306.contentprovider_calllog;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    Button button_all, button_find, button_delete, button_add;
    ContentResolver resolver;
    String[] projection;

    final static Uri CALLLOGURI = CallLog.Calls.CONTENT_URI;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView1);
        button_all = (Button) findViewById(R.id.button1);
        button_find = (Button) findViewById(R.id.button2);
        button_delete = (Button) findViewById(R.id.button3);
        button_add = (Button) findViewById(R.id.button4);

        resolver = getContentResolver();
        projection = new String[] { CallLog.Calls.CACHED_NAME,
                CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.TYPE, CallLog.Calls.DURATION };

        button_all.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor cursor = resolver.query(CALLLOGURI, projection, null,	null, null);
                startManagingCursor(cursor);//将cursor交友activity管理，其生命周期便于activity同步，省去了手动管理

                //Cursor cursor2=managedQuery(CALLLOGURI, projection, null, null, null);
                textView.setText(convertToCalls(cursor));
            }
        });

        button_find.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Cursor cursor = resolver.query(CALLLOGURI, projection,
                        CallLog.Calls.CACHED_NAME + "=?", new String[]{"移动"}, null);
                textView.setText(convertToCalls(cursor));
            }
        });

        button_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int cnt=resolver.delete(CALLLOGURI, CallLog.Calls.CACHED_NAME + "=?", new String[]{"移动"});
                if (cnt>0) {
                    Toast.makeText(getApplicationContext(), "delete sucess", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ContentValues values=new ContentValues();
                values.put(CallLog.Calls.CACHED_NAME, "移动");
                values.put(CallLog.Calls.NUMBER, "10086");
                values.put(CallLog.Calls.DATE, System.currentTimeMillis());
                values.put(CallLog.Calls.TYPE, CallLog.Calls.INCOMING_TYPE);
                Uri uri=resolver.insert(CALLLOGURI, values);
                Toast.makeText(getApplicationContext(), uri.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    String convertToCalls(Cursor cursor) {
        String text = "";
        if (cursor != null) {
            int cnt = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < cnt; i++) {
                text += cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.CACHED_NAME)) + ";  ";
                text += cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.NUMBER)) + "; ";

                String timeStr = cursor.getString(cursor
                        .getColumnIndexOrThrow(CallLog.Calls.DATE));
                Long long1 = Long.valueOf(timeStr);
                Date date1 = new Date(long1);
                String time = dateFormat.format(date1);
                text += time + ";   ";

                int type = Integer.parseInt(cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.TYPE)));
                switch (type) {
                    case CallLog.Calls.INCOMING_TYPE:
                        text += "已接来电;  ";
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        text += "已拨打;  ";
                        break;

                    case CallLog.Calls.MISSED_TYPE:
                        text += "未处理;  ";
                        break;
                    default:
                        break;
                }

                text += cursor.getString(cursor
                        .getColumnIndex(CallLog.Calls.DURATION)) + "; ";

                text += "\n";
                cursor.moveToNext();
            }

        }
        return text;
    }

}
