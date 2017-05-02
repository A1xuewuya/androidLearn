package cn.edu.neusoft.volleydemo;

import android.app.Activity;
import android.os.Bundle;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {

    private TextView mNetworkJsonData;
    private ImageView mImageView;
    private NetworkImageView mNetworkImageView;
    private RequestQueue requestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        init();
    }

    private void init(){
        mNetworkJsonData=(TextView)findViewById(R.id.netJsonData);
        mImageView=(ImageView) findViewById(R.id.imageView);
        mNetworkImageView=(NetworkImageView)findViewById(R.id.networkImageView);
        getJSONByVolley();
        loadImageByVolley();
        showImageByNetworkImageView();
    }

    /**
     * 利用Volley获取JSON数据
     */
    private void getJSONByVolley() {
        String JSONDataUrl = "http://www.weather.com.cn/data/cityinfo/101010100.html";
        JsonRequest jsonObjectRequest = new JsonObjectRequest(
                JSONDataUrl,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        mNetworkJsonData.setText(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError arg0) {
                        mNetworkJsonData.setText("sorry,Error");
                    }
                });
        requestQueue.add(jsonObjectRequest);
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
    private void loadImageByVolley(){
        String imageUrl="http://pic32.nipic.com/20130829/12906030_124355855000_2.png";
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        // 使用最大可用内存值的1/8作为缓存的大小。
        int cacheSize = maxMemory / 8;
        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                // 重写此方法来衡量每张图片的大小，默认返回图片数量。
                return bitmap.getByteCount() / 1024;
            }
        };
       // final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
        ImageCache imageCache = new ImageCache() {
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
        ImageListener listener = ImageLoader.getImageListener(mImageView, R.drawable.error,R.drawable.error);
        imageLoader.get(imageUrl, listener);
    }

    /**
     * 利用NetworkImageView显示网络图片
     */
    private void showImageByNetworkImageView(){
        String imageUrl="http://img2.imgtn.bdimg.com/it/u=99623527,4144288843&fm=21&gp=0.jpg";
        final LruCache<String, Bitmap> lruCache = new LruCache<String, Bitmap>(20);
        ImageCache imageCache = new ImageCache() {
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
        mNetworkImageView.setImageUrl(imageUrl,imageLoader);
    }
}
