package neusoft.edu.cn.manifestdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by zhang_fy on 2016/5/26.
 */
public class MyService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
