package cn.edu.neusoft.activityintentdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class SubActivity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        Button btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random rand =new Random(25);
                int r=rand.nextInt(100);
                Bundle bundle=new Bundle();
                bundle.putInt("random",r);
                Intent intent3=new Intent();
                intent3.putExtras(bundle);
                setResult(RESULT_OK, intent3);
                finish();
            }
        });
    }
}
