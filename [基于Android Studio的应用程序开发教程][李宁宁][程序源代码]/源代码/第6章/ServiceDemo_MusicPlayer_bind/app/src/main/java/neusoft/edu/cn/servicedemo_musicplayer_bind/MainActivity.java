package neusoft.edu.cn.servicedemo_musicplayer_bind;

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

public class MainActivity extends Activity implements OnClickListener{

	private MusicService musicService;
	private Button btnStart, btnStop, btnRestart, btnPause;


	// 在bindService时会启动
	private ServiceConnection mServiceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			musicService = ((MusicService.MyBinder) service).getService();
		}

		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			musicService=null;
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// 启动服务
		Intent intent = new Intent(this,MusicService.class);
		bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);

		initView();
	}

	private void initView() {
		btnStart = (Button) findViewById(R.id.start);
		btnStart.setOnClickListener(this);

		btnPause = (Button) findViewById(R.id.pause);
		btnPause.setOnClickListener(this);

		btnStop = (Button) findViewById(R.id.stop);
		btnStop.setOnClickListener(this);

		btnRestart = (Button) findViewById(R.id.restart);
		btnRestart.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.start:
				try {
					musicService.playMusic();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case R.id.pause:
				musicService.pauseMusic();	break;

			case R.id.restart:
				musicService.restartMusic();	break;

			case R.id.stop:
				musicService.stopMusic();	break;
		}
	}

}
