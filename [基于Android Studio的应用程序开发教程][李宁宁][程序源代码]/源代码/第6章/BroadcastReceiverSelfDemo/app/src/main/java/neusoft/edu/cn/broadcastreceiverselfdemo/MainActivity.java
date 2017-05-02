package neusoft.edu.cn.broadcastreceiverselfdemo;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btn_start, btn_ok;
    static TextView tv_result;
    private Thread luckThread;
    private Handler mHandler;
    private boolean flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    class LuckThead extends Thread{
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
                    String action="neusoft.edu.cn.broadcastreceiverselfdemo";
                    Intent intent=new Intent(action);
                    intent.putExtra("name",luckman);
                    sendBroadcast(intent);//发送广播消息
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
                if (luckThread==null || !luckThread.isAlive()){
                    flag=true;
                    luckThread=new LuckThead();
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
