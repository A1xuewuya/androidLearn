package cn.edu.neusoft.remembermedemo;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et_user,et_pass;
    private CheckBox cb_remember;
    private Button btn_login;
    private final static String SP_INFOS="test";
    private final static String USERNAME="uname";
    private final static String USERPASS="upass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_user=(EditText)findViewById(R.id.et_user);
        et_pass=(EditText)findViewById(R.id.et_pass);
        cb_remember=(CheckBox)findViewById(R.id.cb_remember);
        btn_login=(Button)findViewById(R.id.btn_login);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkIfRemember();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(cb_remember.isChecked())
        {
            this.rememberMe(et_user.getText().toString(), et_pass.getText().toString());
        }
    }
    public void checkIfRemember()
    {
        SharedPreferences sp=getSharedPreferences(SP_INFOS, MODE_PRIVATE);
        String username=sp.getString(USERNAME, null);
        String userpass=sp.getString(USERPASS, null);
        if(username!=null&&userpass!=null)
        {
            et_user.setText(username);
            et_pass.setText(userpass);
            cb_remember.setChecked(true);
        }
    }

    public void rememberMe(String uname,String upass)
    {
        SharedPreferences sp=getSharedPreferences(SP_INFOS, MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString(USERNAME, uname);
        editor.putString(USERPASS, upass);
        editor.commit();
    }
}
