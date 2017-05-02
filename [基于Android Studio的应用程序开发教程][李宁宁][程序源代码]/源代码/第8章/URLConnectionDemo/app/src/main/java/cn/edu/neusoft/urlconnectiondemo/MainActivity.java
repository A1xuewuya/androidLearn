package cn.edu.neusoft.urlconnectiondemo;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img=(ImageView)findViewById(R.id.imageView);
        MyTask myTask=new MyTask();
        myTask.execute();
    }
    class MyTask extends AsyncTask<Void,Integer,Drawable> {
        @Override
        protected Drawable doInBackground(Void[] params) {
            Drawable drawable=null;
            try {
                //设置要读取的资源路径
                String url="http://pic1.workercn.cn/ufile/201112/20111202145248571.jpg";
                //1.实例化URL
                URL objURL = new URL(url);
                //2.建立与URL的连接
                URLConnection conn=objURL.openConnection();
                conn.connect();
                //3.获取返回的InputStream
                InputStream is=conn.getInputStream();
                //4将InputStream进行处理
                drawable=Drawable.createFromStream(is,null);
                //5.关闭连接
                is.close();
            }catch (MalformedURLException e)
            {e.printStackTrace();            }
            catch (IOException e2)
            {e2.printStackTrace();}
            return drawable;
        }
        @Override
        protected void onPostExecute(Drawable o) {
            super.onPostExecute(o);
            img.setImageDrawable((Drawable)o);
        }
    }
}
