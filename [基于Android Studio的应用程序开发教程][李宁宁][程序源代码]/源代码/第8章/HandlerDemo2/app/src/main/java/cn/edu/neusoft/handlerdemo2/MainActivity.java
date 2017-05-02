package cn.edu.neusoft.handlerdemo2;

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
    TextView tv_result;
    Handler mhandler;
    Runnable runnable;
    Button btn_start, btn_ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mhandler=new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tv_result.setText(msg.obj.toString());
            }
        };
        runnable=new Runnable(){
            @Override
            public void run() {
                String[] names={"张彤","王月月","陈晓遇","孙兆玲","马千里"};
                Random rand =new Random();
                int r=rand.nextInt(names.length);
                String lunckman=names[r];
                Message msg=new Message();
                msg.obj=lunckman;
                mhandler.sendMessage(msg);
                mhandler.postDelayed(this, 1000);
            }
        };
    }
    private  void init()
    {
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_ok= (Button) findViewById(R.id.btn_ok);
        tv_result = (TextView) findViewById(R.id.result);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mhandler.postDelayed(runnable, 1000);// 打开定时器，执行操作
            }
        });
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mhandler.removeCallbacks(runnable);
                Toast.makeText(getApplicationContext(), "大奖已经诞生了！", Toast.LENGTH_LONG).show();
            }
        });
    }
}
