package com.neuedu.my12306.sqlitedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView  textshow;
    Button btn_add,btn_deleteall,btn_showall,btn_clear,btn_query_byName,btn_delete_byName,btn_update_byName;
    EditText edit_name,edit_number,edit_address,edit_mail;

    DBAdapter adpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textshow=(TextView)findViewById(R.id.text_show);
        btn_add=(Button)findViewById(R.id.btn_add);
        btn_deleteall=(Button)findViewById(R.id.btn_deleteAll);
        btn_showall=(Button)findViewById(R.id.btn_showAll);
        btn_clear=(Button)findViewById(R.id.btn_clear);
        btn_query_byName=(Button)findViewById(R.id.btn_qurey);
        btn_delete_byName=(Button)findViewById(R.id.btn_del);
        btn_update_byName=(Button)findViewById(R.id.btn_update);
        edit_name=(EditText)findViewById(R.id.edit_name);
        edit_number=(EditText)findViewById(R.id.edit_number);
        edit_address=(EditText)findViewById(R.id.edit_address);
        edit_mail=(EditText)findViewById(R.id.edit_mail);

        adpter=new DBAdapter(this);
        adpter.openDB();

        btn_add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String name=edit_name.getText().toString().trim();
                String number=edit_number.getText().toString().trim();
                String address=edit_address.getText().toString().trim();
                String mail=edit_mail.getText().toString().trim();
                if (name.equals("")) {
                    textshow.setText("姓名不能为空！");
                    return;
                }
                PeopleInfo people=new PeopleInfo(name,number,address,mail);
                adpter.Insert(people);
                textshow.setText("数据添加成功"+"\n");
            }

        });

        btn_deleteall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                adpter.deleteAll();
                textshow.setText("数据全部删除成功");
            }
        });

        btn_showall.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                PeopleInfo[] peoples=adpter.qurryAll();
                if (peoples==null) {
                    textshow.setText("数据库为空");
                    return;
                }
                else {
                    String result="";
                    System.out.println("-->>"+"数据库记录一共有"+peoples.length+"条");
                    for (int i = 0; i < peoples.length; i++) {
                        result+=peoples[i].showInfo();
                    }
                    textshow.setText(result);
                }
            }
        });

        btn_clear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                edit_name.setText("");
                edit_number.setText("");
                edit_address.setText("");
                edit_mail.setText("");
            }
        });

        btn_query_byName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String input=edit_name.getText().toString();
                if(input.isEmpty()){
                    textshow.setText("姓名不能为空");
                    return;
                }
                PeopleInfo[] peoples=adpter.queryByName(input);

                if (peoples==null) {
                    textshow.setText("对不起，没有对应信息");
                    return;
                }
                else {
                    String result="";
                    result=peoples[0].showInfo();
                    textshow.setText(result);
                }
            }
        });

        btn_delete_byName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String name=edit_name.getText().toString().trim();
                long  rlt=adpter.deleteByName(name);
                System.out.println("-->>删除时，rlt=" + rlt);
                if (rlt==0) {
                    textshow.setText("查无此数据，删除不成功！");
                } else {
                    textshow.setText("删除成功！");
                }
            }
        });

        btn_update_byName.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String name=edit_name.getText().toString().trim();
                String number=edit_number.getText().toString().trim();
                String mail=edit_mail.getText().toString().trim();
                String address=edit_address.getText().toString().trim();
                if (name.equals("")) {
                    textshow.setText("姓名不能为空！");
                    return;
                }

                PeopleInfo people=new PeopleInfo(name,number,mail,address);
                long rlt=adpter.updateByName(people,name);
                if (rlt==0) {
                    textshow.setText("数据更新不成功");
                } else {
                    textshow.setText("数据更新成功,  "+people.showInfo());
                }
            }
        });

    }
}
