package neusoft.edu.cn.broadcastreceiverselfdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by zhang_fy on 2016/5/23.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String luckman=intent.getStringExtra("name");
        MainActivity.tv_result.setText(luckman);
    }
}
