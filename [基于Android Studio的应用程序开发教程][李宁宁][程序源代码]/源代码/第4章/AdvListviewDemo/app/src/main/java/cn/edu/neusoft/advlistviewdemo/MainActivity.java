package cn.edu.neusoft.advlistviewdemo;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.neusoft.advlistviewdemo.R;

public class MainActivity extends AppCompatActivity {
    private ListView news_list;
    private List<Map<String, Object>> mData;
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
        mData = getData();
        MyAdapter adapter = new MyAdapter(this);
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
    public final class ViewHolder{
                public ImageView news_thumb;
                public TextView news_title;
                public TextView news_info;
                public ImageButton news_btn;
            }
    public class MyAdapter extends BaseAdapter {
         
                private LayoutInflater mInflater;
                 
                 
                public MyAdapter(Context context){
                        this.mInflater = LayoutInflater.from(context);
                    }
                @Override
                public int getCount() {
                        // TODO Auto-generated method stub
                        return mData.size();
                    }
         
                @Override
                public Object getItem(int arg0) {
                        // TODO Auto-generated method stub
                        return null;
                    }
         
                @Override
                public long getItemId(int arg0) {
                        // TODO Auto-generated method stub
                        return 0;
                    }
         
                @Override
                public View getView(final int position, View convertView, ViewGroup parent) {
                         
                        ViewHolder holder = null;
                        if (convertView == null) {
                                 
                                holder=new ViewHolder(); 
                                 
                                convertView = mInflater.inflate(R.layout.listview_item, null);
                                holder.news_thumb = (ImageView)convertView.findViewById(R.id.news_thumb);
                                holder.news_title = (TextView)convertView.findViewById(R.id.news_title);
                                holder.news_info = (TextView)convertView.findViewById(R.id.news_info);
                                holder.news_btn = (ImageButton)convertView.findViewById(R.id.news_btn);
                                convertView.setTag(holder);
                                 
                            }else {
                                 
                                holder = (ViewHolder)convertView.getTag();
                            }
                         
                         
                        holder.news_thumb.setBackgroundResource((Integer)mData.get(position).get("news_thumb"));
                        holder.news_title.setText((String) mData.get(position).get("news_title"));
                        holder.news_info.setText((String)mData.get(position).get("news_info"));
                        holder.news_btn.setTag(position);
                        holder.news_btn.setOnClickListener(new View.OnClickListener() {
                                 
                                @Override
                                public void onClick(View v) {
                                    showInfo(position);
                                    }
                            });
                        return convertView;
                    }
                 
            }
    public void showInfo(int position){


        new AlertDialog.Builder(this)
                .setTitle(mData.get(position).get("news_title").toString())
                .setMessage(mData.get(position).get("news_info").toString())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
