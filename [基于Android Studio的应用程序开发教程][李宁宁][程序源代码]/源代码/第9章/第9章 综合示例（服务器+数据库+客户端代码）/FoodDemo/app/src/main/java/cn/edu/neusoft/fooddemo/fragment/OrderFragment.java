package cn.edu.neusoft.fooddemo.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.bean.Food;
import cn.edu.neusoft.fooddemo.bean.Shop;
import cn.edu.neusoft.fooddemo.bean.User;
import cn.edu.neusoft.fooddemo.util.Contants;

public class OrderFragment extends BaseFragment {
	private int food_id;
	private TextView tv_foodname,tv_foodprice,tv_username,tv_useraddress,tv_result;
	private ImageView iv_foodpic;
	private Button btn_ok,btn_cancel;
	private EditText et_num;
	private Food food;
	private User user;
	private double price_sum;
	private String stime;
	private Spinner sp_stime;
	protected View init(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.order, container, false);
		tv_foodname=(TextView)view.findViewById(R.id.food_name);
		tv_foodprice=(TextView)view.findViewById(R.id.food_price);
		tv_username=(TextView)view.findViewById(R.id.username);
		tv_useraddress=(TextView)view.findViewById(R.id.useraddress);
		tv_result=(TextView)view.findViewById(R.id.result);
		et_num=(EditText)view.findViewById(R.id.num);
		iv_foodpic=(ImageView)view.findViewById(R.id.food_pic);
		btn_ok=(Button)view.findViewById(R.id.btn_ok);
		btn_cancel=(Button)view.findViewById(R.id.btn_cancel);
		sp_stime=(Spinner)view.findViewById(R.id.sp_stime);
        setSpinner(sp_stime);

		btn_ok.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					int num=Integer.parseInt(et_num.getText().toString());
					String param="user_id="+user_id+"&food_id="+food_id+"&num="+num+"&sum="+price_sum+"&suggesttime="+stime;
					getJSONByVolley(Contants.BASEURL + "insertOrder.do?"+param);
			}
		});

		et_num.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if(count>0)
				{
					String num=et_num.getText().toString();
					int inum=Integer.parseInt(num);
					price_sum=food.getPrice()*inum;
					tv_result.setText("总计："+price_sum+"元");
				}
			}
			@Override
			public void afterTextChanged(Editable s) {
			}
		});

		food_id=getArguments().getInt("food_id");
		getJSONByVolley(Contants.BASEURL + "getFoodById.do?food_id=" + food_id);
		getJSONByVolley(Contants.BASEURL + "getUserById.do?user_id=" + user_id);
		return view;
	}

	@Override
	protected void setJSONDataToView(String url, JSONObject data) {
		Gson gson=new Gson();
		if(url.contains("getFoodById")) {
           food=gson.fromJson(data.toString(),Food.class);
			tv_foodname.setText("菜名："+food.getFoodname());
			tv_foodprice.setText("价钱："+food.getPrice()+"元");
			if(food.getPic()!=null&&!"".equals(food.getPic())){
				loadImageByVolley(food.getPic(), iv_foodpic);
			}
			String num=et_num.getText().toString();
			int inum=Integer.parseInt(num);
			price_sum=food.getPrice()*inum;
			tv_result.setText("总计："+price_sum+"元");
		}else if(url.contains("getUserById")){
			user=gson.fromJson(data.toString(),User.class);
			tv_username.setText("用户姓名："+user.getUsername());
			tv_useraddress.setText("配送地址："+user.getAddress());
		}else
		{
			try {
				if (1==data.getInt("success"))
					getToast("下单成功");
				else
					getToast("下单失败");
			}catch (JSONException e)
			{
			}
		}
	}
	public void setSpinner(Spinner sp)
	{
		// 建立数据源
		String[] mItems = getResources().getStringArray(R.array.suggesttime);
// 建立Adapter并且绑定数据源
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, mItems);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//绑定 Adapter到控件
		sp .setAdapter(adapter);
		sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
									   int pos, long id) {

				String[] stimes = getResources().getStringArray(R.array.suggesttime);
				stime=stimes[pos];
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Another interface callback
			}
		});
	}
}
