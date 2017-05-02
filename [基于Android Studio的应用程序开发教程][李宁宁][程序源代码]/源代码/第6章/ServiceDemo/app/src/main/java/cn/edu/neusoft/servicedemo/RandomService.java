package cn.edu.neusoft.servicedemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

public class RandomService extends Service{

	private Thread luckThread;

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "幸运大抽奖开始",
				Toast.LENGTH_LONG).show();
		luckThread = new Thread(null,backgroudWork,"luckThread");
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		Toast.makeText(this, "抽奖进行中",
				Toast.LENGTH_SHORT).show();
		if (!luckThread.isAlive()){
			luckThread.start();
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "恭喜你中奖了",
				Toast.LENGTH_SHORT).show();
		luckThread.interrupt();
	}


	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private Runnable backgroudWork = new Runnable(){
		@Override
		public void run() {
			try {
				while(!Thread.interrupted()){
					int randomDouble = (int) Math.round(Math.random()*2+1);
					int randomDouble1 = (int) Math.round(Math.random()*2);
					int randomDouble2 = (int) Math.round(Math.random()*9);
					MainActivity.UpdateGUI(randomDouble,randomDouble1,randomDouble2);
					Thread.sleep(600);
					if (randomDouble1==randomDouble2&&randomDouble1==0){
						luckThread.interrupt();
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	};


}
