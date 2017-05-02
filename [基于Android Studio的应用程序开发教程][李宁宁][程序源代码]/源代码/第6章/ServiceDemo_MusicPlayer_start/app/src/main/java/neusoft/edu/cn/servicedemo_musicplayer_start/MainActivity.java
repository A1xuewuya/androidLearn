package neusoft.edu.cn.servicedemo_musicplayer_start;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        Button btnStart = (Button)findViewById(R.id.startMusic);
        Button btnStop = (Button)findViewById(R.id.stopMusic);

        OnClickListener listener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(MainActivity.this,MusicService.class);
                switch(v.getId()){
                    case R.id.startMusic:
                        startService(intent);
                        break;

                    case R.id.stopMusic:
                        stopService(intent);
                       break;

                }
            }
        };

        btnStart.setOnClickListener(listener);
        btnStop.setOnClickListener(listener);

    }

}
