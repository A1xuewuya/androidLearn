package cn.edu.neusoft.phonebook2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private MyAdapter adapter;
    private Button btn_add,btn_clear;
    DbUtils db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv=(ListView)findViewById(R.id.listView1);
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_clear=(Button)findViewById(R.id.btn_clear);

        DbUtils.DaoConfig config = new DbUtils.DaoConfig(this);
        config.setDbName("my_contact"); //设置数据库名
        config.setDbVersion(1);  //设置数据库版本
        db = DbUtils.create(config);//db含有丰富的方法

        try {
            db.createTableIfNotExist(PeopleInfo.class); //创建一个表PeopleInfo
            List<PeopleInfo> results= db.findAll(Selector.from(PeopleInfo.class));
            Log.e("====", results.toString()+"");
            MyAdapter myAdapter=new MyAdapter(this,results,db);
            lv.setAdapter(myAdapter);
        }catch (DbException e)
        {
            e.printStackTrace();
        }
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,NewPersonActivity.class);
                startActivity(intent);
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    db.deleteAll(PeopleInfo.class);
                    Toast.makeText(MainActivity.this,
                            "清空成功！", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
