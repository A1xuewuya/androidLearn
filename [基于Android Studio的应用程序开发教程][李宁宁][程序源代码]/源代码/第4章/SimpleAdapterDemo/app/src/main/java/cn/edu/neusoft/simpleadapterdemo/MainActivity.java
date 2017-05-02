package cn.edu.neusoft.simpleadapterdemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView news_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        news_list = (ListView)findViewById(R.id.news_list);
        SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.listview_item,new String[]{"news_title","news_info","news_thumb"},
                new int[]{R.id.news_title,R.id.news_info,R.id.news_thumb});

        news_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private List<Map<String, Object>> getData() {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("news_title","毡帽系列");
        map.put("news_info","此系列服装有点cute，像不像小车夫。");
        map.put("news_thumb",R.drawable.i1);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("news_title","蜗牛系列");
        map.put("news_info","宝宝变成了小蜗牛，爬啊爬啊爬啊。");
        map.put("news_thumb",R.drawable.i2);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("news_title","小蜜蜂系列");
        map.put("news_info","小蜜蜂，嗡嗡嗡，飞到西，飞到东。");
        map.put("news_thumb",R.drawable.i3);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("news_title","毡帽系列");
        map.put("news_info","此系列服装有点cute，像不像小车夫。");
        map.put("news_thumb",R.drawable.i4);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("news_title","蜗牛系列");
        map.put("news_info","宝宝变成了小蜗牛，爬啊爬啊爬啊。");
        map.put("news_thumb",R.drawable.i5);
        list.add(map);
        map = new HashMap<String, Object>();
        map.put("news_title","小蜜蜂系列");
        map.put("news_info","小蜜蜂，嗡嗡嗡，飞到西，飞到东。");
        map.put("news_thumb",R.drawable.i6);
        list.add(map);
        return list;
    }
}
