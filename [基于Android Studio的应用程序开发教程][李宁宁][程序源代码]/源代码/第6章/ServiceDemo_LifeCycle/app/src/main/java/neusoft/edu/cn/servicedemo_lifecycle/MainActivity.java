package neusoft.edu.cn.servicedemo_lifecycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button button_start,button_stop,button_clr;
	public static TextView textView;
	public static String text="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button_start=(Button)findViewById(R.id.button_start);
		button_stop=(Button)findViewById(R.id.button_stop);
		button_clr=(Button)findViewById(R.id.button_clr);
		textView=(TextView)findViewById(R.id.textView);

		button_start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent serviceIntent=new Intent(getApplicationContext(), MyService.class);
				startService(serviceIntent);
			}
		});

		button_stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent serviceIntent=new Intent(getApplicationContext(), MyService.class);
				stopService(serviceIntent);
			}
		});

		button_clr.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				textView.setText("");
			}
		});

	}

	
}
