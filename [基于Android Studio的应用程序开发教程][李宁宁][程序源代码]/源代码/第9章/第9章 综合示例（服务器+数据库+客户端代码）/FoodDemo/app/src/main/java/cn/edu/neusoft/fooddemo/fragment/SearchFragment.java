package cn.edu.neusoft.fooddemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.adapter.FoodAdapter;
import cn.edu.neusoft.fooddemo.bean.Food;
import cn.edu.neusoft.fooddemo.bean.Shop;
import cn.edu.neusoft.fooddemo.util.Contants;

public class SearchFragment extends BaseFragment {

	private ListView list;
	List<Food> foods;
	private Button btn_search;
	private EditText et_search;
	private int food_id;

	Shop shop;
	protected View init(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.search_list, container, false);
		list=(ListView)view.findViewById(R.id.foodlist);
		btn_search=(Button)view.findViewById(R.id.search_btn);
		et_search=(EditText)view.findViewById(R.id.search_edit);
		foods=new ArrayList<Food>();

		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				int shop_id = foods.get(arg2).getShop_id();
				food_id=foods.get(arg2).getFood_id();
				//获取当前店铺信息
				getJSONByVolley(Contants.BASEURL + "getShopById.do?shop_id=" + shop_id);
			}
		});
		btn_search.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				try {
					String result = et_search.getText().toString();
					String url = Contants.BASEURL + "getFoodBySearch.do?search=" + URLEncoder.encode(result, "UTF-8");
					getJSONArrayByVolley(url);
				}catch (UnsupportedEncodingException e)
				{
					e.printStackTrace();
				}
			}
		});

		return view;
	}
	protected void setJSONArrayToView(JSONArray data) {
		Gson gson=new Gson();
		foods= gson.fromJson(data.toString(),new TypeToken<List<Food>>(){}.getType());
		FoodAdapter adapter=new FoodAdapter(this.getActivity(), foods);
		list.setAdapter(adapter);
	}
	@Override
	protected void setJSONDataToView(String url,JSONObject data) {
			Gson gson=new Gson();
			shop=gson.fromJson(data.toString(), Shop.class);
		Bundle bundle = new Bundle();
		bundle.putInt("food_id", food_id);
		bundle.putString("phonenum", shop.getPhonenum());
		bundle.putInt("shop_id", shop.getShop_id());
		bundle.putString("shopname", shop.getShopname());
		bundle.putInt("flag", 2);//标记返回
		FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
		changeFrament(foodDetailFragment, bundle, "foodDetailFragment");
	}

}
