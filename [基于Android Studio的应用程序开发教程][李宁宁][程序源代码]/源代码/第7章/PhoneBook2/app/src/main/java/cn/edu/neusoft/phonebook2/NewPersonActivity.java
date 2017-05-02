package cn.edu.neusoft.phonebook2;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;

public class NewPersonActivity extends Activity {
	private EditText et_name, et_phone, et_address, et_email;
	private Button btn_ok, btn_back;

	private int flag = 0;
	private int id = 0;
	DbUtils db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_person);

		DbUtils.DaoConfig config = new DbUtils.DaoConfig(this);
		config.setDbName("my_contact"); //设置数据库名
		config.setDbVersion(1);  //设置数据库版本
		db = DbUtils.create(config);//db含有丰富的方法

		et_name = (EditText) findViewById(R.id.et_name);
		et_phone = (EditText) findViewById(R.id.et_phone);
		et_address = (EditText) findViewById(R.id.et_address);
		et_email = (EditText) findViewById(R.id.et_email);
		btn_ok = (Button) findViewById(R.id.button1);
		btn_back = (Button) findViewById(R.id.button2);

		if (getIntent().getIntExtra("id", 0) > 0)//判断是新增还是修改
		{
			id = getIntent().getIntExtra("id", 0);
			setPersonInfo(id);
			flag = 1;
		}
		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
				String name=et_name.getText().toString();
				String phone_num=et_phone.getText().toString();
				String address=et_address.getText().toString();
				String email=et_email.getText().toString();
				PeopleInfo p=new PeopleInfo(name,phone_num,address,email);
				if(flag==0)
					db.save(p);
				else
				{
					p.setID(id);
                    db.update(p);
				}
					Toast.makeText(NewPersonActivity.this, "操作成功！", Toast.LENGTH_LONG).show();
					Intent intent=new Intent(NewPersonActivity.this,MainActivity.class);
					startActivity(intent);
			} catch (DbException e) {

			}
			}
		});
		btn_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
				startActivity(new Intent(NewPersonActivity.this, MainActivity.class));
			}
		});
	}
//修改时，初始化数据
	private void setPersonInfo(int id) {
		try {
			PeopleInfo d = db.findById(PeopleInfo.class, id);
			et_name.setText(d.getName());
			et_phone.setText(d.getPhone_number());
			et_address.setText(d.getAddress());
			et_email.setText(d.getE_mail());
		} catch (DbException e) {

		}
	}
}
