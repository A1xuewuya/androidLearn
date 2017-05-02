package neusoft.edu.cn.servicedemo_lifecycle_bind;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button button_bind,button_unbind,button_clr;
	public static TextView textView;
	public static String text="";
	private MyService myService;
	private boolean isBind=false;//标识服务的绑定状态：=1：绑定；=0：未绑定

	// 在bindService时会启动
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			myService = ((MyService.MyBinder) service).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			myService=null;
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button_bind=(Button)findViewById(R.id.button_bind);
		button_unbind=(Button)findViewById(R.id.button_unbind);
		button_clr=(Button)findViewById(R.id.button_clr);
		textView=(TextView)findViewById(R.id.textView);

		button_bind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent serviceIntent=new Intent(MainActivity.this,MyService.class);
				bindService(serviceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);
				isBind=true;
			}
		});

		button_unbind.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(isBind){
					unbindService(mServiceConnection);
					isBind=false;
				}
			}
		});

		button_clr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				textView.setText("");
				text="";
			}
		});

	}



}
