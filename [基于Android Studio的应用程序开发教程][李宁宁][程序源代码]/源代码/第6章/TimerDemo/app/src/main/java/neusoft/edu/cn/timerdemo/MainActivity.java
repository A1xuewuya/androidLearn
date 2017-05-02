package neusoft.edu.cn.timerdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button button;
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what>0) {
                textView.setText(" "+msg.what);
             }
            else {
                //在handler里可以更改UI组件
                textView.setText("开始点火");
                timer.cancel();
                timer=null;
                System.out.println("==>>before" + "timer=" + timer);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        button=(Button)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer=new Timer();
                timerTask=new TimerTask() {
                    int i=10;//倒计时数目
                    @Override
                    public void run() {
                        Message message=Message.obtain();
                        message.what=i;
                        i--;
                        handler.sendMessage(message);
                    }
                };

                timer.schedule(timerTask,1000,1000);
            }
        });
    }
}
