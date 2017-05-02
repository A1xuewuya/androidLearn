package cn.edu.neusoft.fooddemo;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by liningning on 2016/4/16.
 */
public class FoodApplication extends Application{
    public RequestQueue requestQueue = null;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
    }
}
