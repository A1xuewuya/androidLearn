package cn.edu.neusoft.phonebook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

class MyOpenHelper extends SQLiteOpenHelper{
	public static final String TABLE_NAME="personInfo";
	public static final String ID="ID";
	public static final String NAME="Name";
	public static final String PHONE_NUMBER="Phone_number";
	public static final String ADDRESS="Address";
	public static final String EMAIL="E_mail";


	public MyOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("drop table if exists "+TABLE_NAME);
		db.execSQL("create table if not exists "+TABLE_NAME+" ("+
	ID+" integer primary key autoincrement,"+NAME+" varchar,"+PHONE_NUMBER+" varchar,"+ADDRESS+" varchar,"+EMAIL+" varchar)");
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}
