package cn.edu.neusoft.weatherdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.util.LruCache;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private Spinner sp;

    private List<CityCode> list;

    private TextView tvWeatherCity,tvWeather,tvWeatherTemp;
    private ImageView img1,img2;
    private static final String IMGURL="http://m.weather.com.cn/img/";
    private static final String CITYINFOURL="http://www.weather.com.cn/data/cityinfo/";
    private String citycode="101010100";//城市代码
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);
        init();
        ReadJsonTask mytask=new ReadJsonTask();
        mytask.execute();
        getJSONByVolley(CITYINFOURL+citycode+".html");


    }

    public void init()
    {
        tvWeatherCity=(TextView)findViewById(R.id.weatherCity);
        tvWeather=(TextView)findViewById(R.id.weather);
        tvWeatherTemp=(TextView)findViewById(R.id.weatherTemp);
        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        sp=(Spinner)findViewById(R.id.cityList);
    }
    public void setWeatherData(Weather weather)
    {
        tvWeatherCity.setText(weather.getCity());
        tvWeather.setText(weather.getWeather());
        tvWeatherTemp.setText(weather.getTemp1()+"-"+ weather.getTemp2());
        loadImageByVolley(weather.getImg1(), img1);
        loadImageByVolley(weather.getImg2(), img2);
    }
    /**
     * 利用Volley异步加载图片
     *
     * 注意方法参数:
     * getImageListener(ImageView view, int defaultImageResId, int errorImageResId)
     * 第一个参数:显示图片的ImageView
     * 第二个参数:默认显示的图片资源
     * 第三个参数:加载错误时显示的图片资源
     */
    private void loadImageByVolley(String img,ImageView imgView){
        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                lruCache.put(key, value);
            }
            @Override
            public Bitmap getBitmap(String key) {
                return lruCache.get(key);
            }
        };
        ImageLoader imageLoader = new ImageLoader(requestQueue, imageCache);
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imgView, R.drawable.error,R.drawable.error);
        imageLoader.get(IMGURL + img, listener);
    }
    /**
     * 利用Volley获取JSON数据，并装入Weather对象中,最后设置到对应的控件上
     */
    private void getJSONByVolley(String JSONDataUrl) {
        final ProgressDialog progressDialog = ProgressDialog.show(this, "耐心等待", "...读取天气预报中...");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                JSONDataUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("===",response.toString());
                        try {
                            JSONObject weatherinfo=response.getJSONObject("weatherinfo");
                            Gson gson=new Gson();
                           Weather weather=gson.fromJson(weatherinfo.toString(),Weather.class);
                            setWeatherData(weather);
                            if (progressDialog.isShowing()&&progressDialog!=null) {
                                progressDialog.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    /**
     * 将JSON数据，转换成Weather对象
     */
/*    public Weather convertToBean(JSONObject json)
    {
           Weather w=new Weather();
        try {
            w.setCity(json.getString("city"));
            w.setCityid(json.getString("cityid"));
            w.setImg1(json.getString("img1"));
            w.setImg2(json.getString("img2"));
            w.setTemp1(json.getString("temp1"));
            w.setTemp2(json.getString("temp2"));
            w.setWeather(json.getString("weather"));
            w.setPtime(json.getString("ptime"));
            System.out.print(w);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return w;
    }*/

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
                        result.add(m);
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
                    citycode= c.getCode();
                    getJSONByVolley(CITYINFOURL + citycode + ".html");
                }
                @Override
                public void onNothingSelected(AdapterView<?> arg0) {

                }
            };
            sp.setOnItemSelectedListener(listener);
        }
    }
}
