package cn.edu.neusoft.logindemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private EditText zhanghao;
    private EditText mima;
    private Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login=(Button)findViewById(R.id.btn_login);
        zhanghao=(EditText)findViewById(R.id.login_name);
        mima=(EditText)findViewById(R.id.login_pass);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = zhanghao.getText().toString();
                String password = mima.getText().toString();
                MyTask myTask=new MyTask();
                myTask.execute(username,password);
            }
        });
    }
    class MyTask extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String[] params) {
            String param = "username=" + params[0] + "&password=" + params[1];
            return this.sendPost("http://10.0.2.2/LoginTest/login", param);

        }
        public String sendPost(String url, String params) {
            String result="";
            try {
                URL realurl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection) realurl.openConnection();
                conn.setConnectTimeout(6000); // 设置超时时间
                conn.setRequestMethod("POST");
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(params);
                out.flush();
                out.close();
                InputStream in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result=line;
                }
            } catch (MalformedURLException eio) {
                eio.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
        }
    }


}
