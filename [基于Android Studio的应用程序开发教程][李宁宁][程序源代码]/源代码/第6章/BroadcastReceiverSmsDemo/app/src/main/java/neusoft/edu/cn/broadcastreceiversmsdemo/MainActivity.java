package neusoft.edu.cn.broadcastreceiversmsdemo;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private EditText messageText, numberText;
    private Button button;
    public static TextView textView;// 被其他类引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        messageText = (EditText) findViewById(R.id.edit_message);
        button = (Button) findViewById(R.id.button_send);
        numberText = (EditText) findViewById(R.id.edit_number);
        textView = (TextView) findViewById(R.id.text_show);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String number = numberText.getText().toString();
                String message = messageText.getText().toString();
                SmsManager smsManager = SmsManager.getDefault();// 获得默认的消息管理器

                // 实例化一个pendingIntent的对象，该对象的功能位广播
                PendingIntent mpi = PendingIntent.getBroadcast(
                        MainActivity.this, 0, new Intent(), 0);
                smsManager.sendTextMessage(number, null, message, mpi, null);
            }

        });
    }

}
