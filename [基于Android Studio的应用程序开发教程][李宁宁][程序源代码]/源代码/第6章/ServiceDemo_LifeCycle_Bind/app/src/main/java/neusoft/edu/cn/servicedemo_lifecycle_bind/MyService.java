package neusoft.edu.cn.servicedemo_lifecycle_bind;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {
	int cnt;
	private MyBinder iBinder;

	public class MyBinder extends Binder {
		MyService getService() {
			return MyService.this;
		}
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		cnt++;
		MainActivity.text=MainActivity.text+"Service onBind:"+"cnt="+cnt+"\n";
		MainActivity.textView.setText(MainActivity.text);
		iBinder = new MyBinder();
		return iBinder;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		MainActivity.text=MainActivity.text+"Service onUnbind:"+"cnt="+cnt+"\n";
		MainActivity.textView.setText(MainActivity.text);
		return super.onUnbind(intent);
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
