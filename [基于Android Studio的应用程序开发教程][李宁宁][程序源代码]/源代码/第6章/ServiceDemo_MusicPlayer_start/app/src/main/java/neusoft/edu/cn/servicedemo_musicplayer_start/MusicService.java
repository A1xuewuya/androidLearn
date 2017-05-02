package neusoft.edu.cn.servicedemo_musicplayer_start;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MusicService extends Service {

	private MediaPlayer mPlayer;

	@Override
	public void onCreate() {
		Toast.makeText(this, "MusicSevice onCreate()", Toast.LENGTH_SHORT).show();

		mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.starry_sky);
		if (mPlayer == null) {
			Log.e("MusicService", "------null");
		}

		mPlayer.setLooping(true); // 设置可以重复播放
		super.onCreate();
	}
	
	@Override       
	public void onStart(Intent intent, int startId) {         
		Toast.makeText(this, "MusicSevice onStart()", Toast.LENGTH_SHORT).show();
		mPlayer.start();
		super.onStart(intent, startId);
	}

	@Override 
	public void onDestroy() {       
		Toast.makeText(this, "MusicSevice onDestroy()", Toast.LENGTH_SHORT).show();
        mPlayer.stop();
        super.onDestroy();       
    } 
	

	@Override
	public IBinder onBind(Intent intent) {

		Toast.makeText(this, "MusicSevice onBind()", Toast.LENGTH_SHORT).show();
        return null;

    }


	@Override
	public boolean onUnbind(Intent intent) {

        Toast.makeText(this, "MusicSevice onUnbind()", Toast.LENGTH_SHORT).show();

        return super.onUnbind(intent);
   }



}
