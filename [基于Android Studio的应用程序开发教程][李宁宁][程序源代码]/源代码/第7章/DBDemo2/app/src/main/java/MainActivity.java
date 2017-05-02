import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Toast;

import cn.edu.neusoft.dbdemo2.R;

public class MainActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
		super.onCreate(savedInstanceState, persistentState);
		setContentView(R.layout.activity_main);

		int r=BaseDBUtil.copyDatabaseFile(this);
		Toast.makeText(this,r+"",Toast.LENGTH_LONG).show();

	}
}
