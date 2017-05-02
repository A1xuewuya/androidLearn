package cn.edu.neusoft.loginadvdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements Button.OnClickListener{
    private Button loginButton;
    private EditText usernameET;
    private EditText passwordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(cn.edu.neusoft.loginadvdemo.R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(cn.edu.neusoft.loginadvdemo.R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(cn.edu.neusoft.loginadvdemo.R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        loginButton = (Button)findViewById(cn.edu.neusoft.loginadvdemo.R.id.login_button);
        usernameET = (EditText)findViewById(cn.edu.neusoft.loginadvdemo.R.id.username);
        passwordET = (EditText)findViewById(cn.edu.neusoft.loginadvdemo.R.id.password);
        loginButton.setOnClickListener(this);
        //loginButton.setOnClickListener(listener);
        /*loginButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "您输入的用户名是"+usernameET.getText()+",密码是"+passwordET.getText();
                Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
            }
        });*/
    }
 /*   Button.OnClickListener listener = new Button.OnClickListener(){//创建监听对象
        public void onClick(View v){
            String msg = "您输入的用户名是"+usernameET.getText()+",密码是"+passwordET.getText();
            Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
        }

    };*/
    public void onClick(View v){
        switch(v.getId()){
            case cn.edu.neusoft.loginadvdemo.R.id.login_button:
                String msg = "您输入的用户名是"+usernameET.getText()+",密码是"+passwordET.getText();
                Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
                break;
        }

    }
    /*public void onLoginClick(View v){
        String msg = "您输入的用户名是"+usernameET.getText()+",密码是"+passwordET.getText();
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(cn.edu.neusoft.loginadvdemo.R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == cn.edu.neusoft.loginadvdemo.R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
