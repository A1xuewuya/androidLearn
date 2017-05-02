package neusoft.edu.cn.servicedemo_lifecycle;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	int cnt;

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		MainActivity.text=MainActivity.text+"Service onBind"+"\n";
		MainActivity.textView.setText(MainActivity.text);
		return null;
	}
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		cnt=0;
		MainActivity.text=MainActivity.text+"Service onCreate():"+"cnt="+cnt+"\n";
		MainActivity.textView.setText(MainActivity.text);
	}
	

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		cnt++;
		MainActivity.text=MainActivity.text+ "Service onStart():"+"cnt="+cnt+"\n";
		MainActivity.textView.setText(MainActivity.text);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		cnt=0;
		MainActivity.text=MainActivity.text+ "Service onDestroy()"+"\n";
		MainActivity.textView.setText(MainActivity.text);
	}

}
