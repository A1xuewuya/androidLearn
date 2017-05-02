package neusoft.edu.cn.servicedemo_musicplayer_bind;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

import java.io.IOException;

public class MusicService extends Service {
	private MyBinder iBinder;
	private MediaPlayer musicPlayer;

	public class MyBinder extends Binder {
		MusicService getService() {
			return MusicService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this,"MusicService onCreate",Toast.LENGTH_SHORT).show();
	}


	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		iBinder = new MyBinder();
		Toast.makeText(this,"MusicService onBind",Toast.LENGTH_SHORT).show();
		return iBinder;
	}


	public void playMusic() throws IOException {// 播放音乐
		Toast.makeText(this,"MusicService playMusic",Toast.LENGTH_SHORT).show();
		musicPlayer = MediaPlayer.create(getApplicationContext(), R.raw.starry_sky);//初始化音乐播放器
		musicPlayer.setLooping(true);//设置循环播放
		musicPlayer.start();
	}


	public void stopMusic() {// 停止播放
		if (musicPlayer == null)
			return;
		if (musicPlayer.isPlaying()) {
			Toast.makeText(this,"MusicService stopMusic",Toast.LENGTH_SHORT).show();
			musicPlayer.stop();
		}
	}

	public void pauseMusic() {// 暂停播放
		if (musicPlayer == null)
			return;
		if (musicPlayer.isPlaying()) {
			Toast.makeText(this,"MusicService pauseMusic",Toast.LENGTH_SHORT).show();
			musicPlayer.pause();
		}
	}

	public void restartMusic() {// 恢复播放
		if (musicPlayer == null)
			return;
		if (!musicPlayer.isPlaying()) {
			Toast.makeText(this,"MusicService restartMusic",Toast.LENGTH_SHORT).show();
			musicPlayer.start();
		}
	}
}
