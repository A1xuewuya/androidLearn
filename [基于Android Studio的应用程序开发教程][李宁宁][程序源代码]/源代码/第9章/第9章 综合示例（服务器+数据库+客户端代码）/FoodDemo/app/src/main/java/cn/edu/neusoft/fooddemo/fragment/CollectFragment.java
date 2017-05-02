package cn.edu.neusoft.fooddemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.adapter.CollectFoodAdapter;
import cn.edu.neusoft.fooddemo.adapter.CollectShopAdapter;
import cn.edu.neusoft.fooddemo.bean.Food;
import cn.edu.neusoft.fooddemo.bean.Shop;
import cn.edu.neusoft.fooddemo.util.Contants;

public class CollectFragment extends BaseFragment {
	private RadioButton ra_shop_bt, ra_food_bt;
	private ListView list;
	List<Food> foods;
	List<Shop> shops;
	private int flag = 0;

	protected View init(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.user_collection, container, false);
		list = (ListView) view.findViewById(R.id.collect_list);
		ra_shop_bt = (RadioButton) view.findViewById(R.id.ra_shop_bt);
		ra_food_bt = (RadioButton) view.findViewById(R.id.ra_food_bt);

		ra_shop_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ra_shop_bt.setBackgroundResource(R.color.colorMain);
				ra_food_bt.setBackgroundResource(R.color.gray);
				shops = new ArrayList<Shop>();
				flag = 0;
				String params = "?user_id=" + user_id + "&flag=" + flag;
				getJSONArrayByVolley(Contants.BASEURL + "getAllUserCollection.do" + params);
			}
		});
		ra_food_bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ra_food_bt.setBackgroundResource(R.color.colorMain);
				ra_shop_bt.setBackgroundResource(R.color.gray);
				flag = 1;
				String params = "?user_id=" + user_id + "&flag=" + flag;
				getJSONArrayByVolley(Contants.BASEURL + "getAllUserCollection.do" + params);
			}
		});
		//默认显示收藏店铺
		String params = "?user_id=" + user_id + "&flag=" + flag;
		getJSONArrayByVolley(Contants.BASEURL + "getAllUserCollection.do" + params);
		return view;
	}

	protected void setJSONArrayToView(JSONArray data) {
		Gson gson = new Gson();
		if (flag == 0) {
			shops = gson.fromJson(data.toString(), new TypeToken<List<Shop>>() {
			}.getType());
			CollectShopAdapter adapter = new CollectShopAdapter(this.getActivity(), shops, user_id);
			list.setAdapter(adapter);
		} else {
			foods = gson.fromJson(data.toString(), new TypeToken<List<Food>>() {
			}.getType());
			CollectFoodAdapter adapter = new CollectFoodAdapter(this.getActivity(), foods, user_id);
			list.setAdapter(adapter);
		}
	}
}