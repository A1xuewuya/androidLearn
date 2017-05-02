package cn.edu.neusoft.fooddemo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.adapter.ShopAdapter;
import cn.edu.neusoft.fooddemo.bean.Shop;
import cn.edu.neusoft.fooddemo.util.Contants;

public class ShopListFragment extends BaseFragment {
	private ListView list;
	List<Shop> shops;
	protected View init(LayoutInflater inflater, ViewGroup container,
						Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.shop_list, container, false);
		list=(ListView)view.findViewById(R.id.listView1);
		shops=new ArrayList<Shop>();
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
									long arg3) {
				int shop_id = shops.get(arg2).getShop_id();
				String shopname = shops.get(arg2).getShopname();
				Bundle bundle = new Bundle();
				bundle.putInt("shop_id", shop_id);
				bundle.putString("shopname", shopname);
				bundle.putInt("flag", 0);//标记返回

				FoodListFragment foodListFragment = new FoodListFragment();
				changeFrament(foodListFragment, bundle, "foodListFragment");
			}
		});
		String url = Contants.BASEURL+"getAllShops.do";
		getJSONArrayByVolley(url);
		return view;
	}
	protected void setJSONArrayToView(JSONArray data) {
		Gson gson=new Gson();
		shops= gson.fromJson(data.toString(),new TypeToken<List<Shop>>(){}.getType());
		ShopAdapter adapter = new ShopAdapter(this.getActivity(), shops);
		list.setAdapter(adapter);
	}
}
