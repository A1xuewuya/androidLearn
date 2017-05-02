package neusoft.edu.cn.threaddemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_ok;
    TextView tv_result;
    private Thread luckThread;
    private Handler mHandler;
    private boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tv_result.setText(msg.obj.toString());
            }
        };

    }

    class LuckThread extends Thread{
        @Override
        public void run() {
            super.run();
            String[] names={"张彤","王月月","陈晓遇","孙兆玲","马千里"};
            while(flag){
                try {
                    Thread.sleep(1000);
                    Random rand =new Random();
                    int r=rand.nextInt(names.length);
                    String luckman=names[r];
                    Message msg=Message.obtain();
                    msg.obj=luckman;
                    mHandler.sendMessage(msg);
                    Log.e("====",luckman);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private  void init(){
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_ok= (Button) findViewById(R.id.btn_ok);
        tv_result = (TextView) findViewById(R.id.result);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (luckThread==null || !luckThread.isAlive()) {
                    flag = true;
                    luckThread = new LuckThread();
                    luckThread.start();
                    Log.e("===", "start");
                }
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (luckThread != null && luckThread.isAlive()) {
                    luckThread.interrupt();
                    luckThread = null;
                    flag = false;
                    Toast.makeText(getApplicationContext(), "大奖已经诞生了！", Toast.LENGTH_LONG).show();
                    Log.e("===", "end");
                }
            }
        });
    }

}
