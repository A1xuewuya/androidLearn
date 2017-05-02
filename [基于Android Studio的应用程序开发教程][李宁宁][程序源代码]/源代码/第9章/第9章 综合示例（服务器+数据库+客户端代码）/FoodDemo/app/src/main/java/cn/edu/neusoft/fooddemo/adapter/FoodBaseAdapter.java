package cn.edu.neusoft.fooddemo.adapter;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import cn.edu.neusoft.fooddemo.FoodApplication;
import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.activity.MainActivity;

/**
 * Created by liningning on 2016/4/17.
 */
public class FoodBaseAdapter extends BaseAdapter{
    protected RequestQueue requestQueue = null;
    protected Context context;
    private FoodApplication app;
    public FoodBaseAdapter(Context context)
    {
        this.context=context;
        app=(FoodApplication)((MainActivity)context).getApplication();
        requestQueue=app.requestQueue;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
    public void loadImageByVolley(String imgurl,ImageView imgView){
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
        imageLoader.get(imgurl, listener);
    }
    public void changeFrament(Fragment fragment, Bundle bundle, String tag) {
        FragmentManager fgManager = ((MainActivity) context).getFragmentManager();
        for (int i = 0, count = fgManager.getBackStackEntryCount(); i < count; i++) {
            fgManager.popBackStack();
        }
        FragmentTransaction fg = fgManager.beginTransaction();
        fragment.setArguments(bundle);
        fg.add(R.id.fragmentRoot, fragment, tag);
        fg.addToBackStack(tag);
        fg.commit();
    }
    /**
     * 利用Volley获取String数据，格式为JSON形式
     */
    public void getJSONByVolley(String url) {
            final String flag = url;

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            setJSONDataToView(flag, response);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError arg0) {
                            getNetError();
                        }
                    });
            requestQueue.add(jsonObjectRequest);

    }
    public void getJSONByVolley(String url,JSONObject params) {
        final String flag=url;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        setJSONDataToView(flag,response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        arg0.printStackTrace();
                    }
                });
        requestQueue.add(jsonObjectRequest);
    }
    protected void setJSONDataToView(String url,JSONObject data) {
    }

    public void getToast(String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void getNetError() {
        getToast("亲~网络连接失败！");
    }
}
