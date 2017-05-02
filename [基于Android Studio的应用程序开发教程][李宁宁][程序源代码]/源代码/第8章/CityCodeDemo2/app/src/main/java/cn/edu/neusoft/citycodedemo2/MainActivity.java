package cn.edu.neusoft.citycodedemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private TextView tv;
    private Spinner sp;

    private List<CityCode> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.cityCode);
        sp=(Spinner)findViewById(R.id.cityList);

        ReadJsonTask mytask=new ReadJsonTask();
        mytask.execute();
    }

    class ReadJsonTask extends AsyncTask
    {
        @Override
        protected List<CityCode> doInBackground(Object[] params) {
            List<CityCode> result = new ArrayList<CityCode>();
            try{
                InputStream in = MainActivity.this.getResources().getAssets().open("citycode.json");
                int length = in.available(); //获取文件的字节数
                byte[]  buffer = new byte[length];//创建byte数组
                in.read(buffer);//将文件中的数据读到byte数组中
                String line=new String(buffer);
                result= convertToBean(line);
            }
            catch(Exception e){
            }
            return result;
        }
        public List<CityCode> convertToBean(String json)
        {
            List<CityCode> result=new ArrayList<CityCode>();
            try {
                JSONObject obj = new JSONObject(json);
                JSONArray jsons = obj.getJSONArray("城市代码");
                for (int n = 0; n < jsons.length(); n++) {
                    JSONObject sheng = jsons.getJSONObject(n);
                    JSONArray shi = sheng.getJSONArray("市");
                    for (int i = 0; i < shi.length(); i++) {
                        JSONObject rss = shi.optJSONObject(i);
                       CityCode m = new CityCode();
                        m.setCode(rss.getString("编码"));
                        m.setCity(rss.getString("市名"));
                      /*  Gson gson=new Gson();
                        CityCode m=gson.fromJson(rss.toString(),CityCode.class);
                        result.add(m);*/
                    }
                }
            }catch (JSONException e)
            {
            }
            return result;
        }

        @Override
        protected void onPostExecute(Object result) {
            super.onPostExecute(result);
            list=(List<CityCode>)result;
            String[] cities=new String[list.size()];
            for(int i=0;i<list.size();i++)
                cities[i]=list.get(i).getCity();
            ArrayAdapter adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_spinner_item,cities);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(adapter);

            AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                           long arg3) {
                    CityCode c = list.get(arg2);
                    String id = c.getCode();
                    tv.setText(id);
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            };
            sp.setOnItemSelectedListener(listener);
        }
    }
}

