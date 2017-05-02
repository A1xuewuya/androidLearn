package cn.edu.neusoft.activityintentdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1=(Button)findViewById(R.id.button1);//映射activity_main.xml中的id=button1按钮
        button2=(Button)findViewById(R.id.button2);//映射activity_main.xml中的id=button2按钮
        //为两个按钮增加点击事件监听
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(MainActivity.this,SubActivity1.class);
               // startActivity(intent1);
                startActivityForResult(intent1,1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /* Intent intent2=new Intent(Intent.ACTION_VIEW, Uri.parse("intentdemo://cn.edu.neusoft"));*/
               Intent intent2=new Intent();
                intent2.setAction(Intent.ACTION_VIEW);//设置Action响应方式
                intent2.setData(Uri.parse("intentdemo://cn.edu.neusoft"));//Data内容：scheme://host形式
                startActivity(intent2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK)
            {
                Bundle random=data.getExtras();
                Toast.makeText(MainActivity.this,random.getInt("random")+"",Toast.LENGTH_LONG).show();
            }
        }
    }
}
