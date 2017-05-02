package cn.edu.neusoft.servicedemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static Handler handler = new Handler();
    private static TextView labelView = null;
    private static int randomDouble ;
    private static int randomDouble1 ;
    private static int randomDouble2 ;

    public static void UpdateGUI(int refreshDouble,int refreshDouble1,int refreshDouble2){
        randomDouble = refreshDouble;
        randomDouble1 = refreshDouble1;
        randomDouble2 = refreshDouble2;
        handler.post(RefreshLable);
    }

    private static Runnable RefreshLable = new Runnable(){
        @Override
        public void run() {
            labelView.setText(String.valueOf("13110200"+randomDouble+randomDouble1+randomDouble2) );
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        labelView = (TextView)findViewById(R.id.label);
        Button startButton = (Button)findViewById(R.id.start);
        Button stopButton = (Button)findViewById(R.id.stop);

        final Intent serviceIntent = new Intent(this, RandomService.class);

        startButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                startService(serviceIntent);
            }
        });

        stopButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                stopService(serviceIntent);
            }
        });

    }

}
