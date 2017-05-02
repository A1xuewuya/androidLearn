package cn.edu.neusoft.fooddemo.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.adapter.AllCommentAdapter;
import cn.edu.neusoft.fooddemo.bean.Food;
import cn.edu.neusoft.fooddemo.bean.Order;
import cn.edu.neusoft.fooddemo.util.Contants;
import cn.edu.neusoft.fooddemo.util.ListViewHeightUtil;

public class FoodDetailFragment extends BaseFragment {
	private int food_id,shop_id;
	private String phonenum,shopname;
	private TextView tv_name,tv_price,tv_phone;
	private ImageView iv_pic;
	private ImageButton btn_call,btn_back;
	private Button btn_collect,btn_buy;
	private boolean collect_flag=false;
	private ListView list;
	private Food food;
	protected View init(LayoutInflater inflater, final ViewGroup container,
						Bundle savedInstanceState)
	{
		food_id=getArguments().getInt("food_id");
		phonenum=getArguments().getString("phonenum");
		shop_id=getArguments().getInt("shop_id");
		shopname=getArguments().getString("shopname");
		View view = inflater.inflate(R.layout.food_detail, container, false);
		list=(ListView)view.findViewById(R.id.list_comment);
		tv_name=(TextView)view.findViewById(R.id.food_name);
		tv_price=(TextView)view.findViewById(R.id.food_price);
		tv_phone=(TextView)view.findViewById(R.id.food_phone);
		iv_pic=(ImageView)view.findViewById(R.id.food_detail_image);
		btn_call=(ImageButton)view.findViewById(R.id.call);
		btn_back=(ImageButton)view.findViewById(R.id.food_back);
		btn_collect=(Button)view.findViewById(R.id.collect);
		btn_buy=(Button)view.findViewById(R.id.btn_buy);
		btn_call.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Uri uri = Uri.parse("tel:" + phonenum);
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				if (getActivity().checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
					getActivity().startActivity(intent);
					return;
				}
			}
		});
		btn_collect.setOnClickListener(new View.OnClickListener() {
										   @Override
										   public void onClick(View v) {
											   String url = Contants.BASEURL + "userCollectFood.do?user_id="+user_id+"&food_id="+food_id;
											   getJSONByVolley(url);
												   collect_flag=!collect_flag;
											   if(collect_flag)
												   btn_collect.setBackgroundResource(R.drawable.xihuanhou);
											   else
												   btn_collect.setBackgroundResource(R.drawable.xihuan);

										   }
									   });
		btn_buy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Bundle bundle = new Bundle();
				bundle.putInt("food_id", food_id);
				OrderFragment orderFragment = new OrderFragment();
				changeFrament(orderFragment, bundle, "orderFragment");
			}
		});
		btn_back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(0==getArguments().getInt("flag"))//返回菜单列表
				{
					Bundle bundle = new Bundle();
					bundle.putInt("shop_id", shop_id);
					bundle.putString("shopname", shopname);
					FoodListFragment foodListFragment = new FoodListFragment();
					changeFrament(foodListFragment, bundle, "foodListFragment");
				}else if(1==getArguments().getInt("flag"))//返回收藏
				{
					CollectFragment collectFragment = new CollectFragment();
					changeFrament(collectFragment, null, "collectFragment");
				}else//返回搜索
				{
					SearchFragment searchFragment=new SearchFragment();
					changeFrament(searchFragment,null,"searchFragment");
				}

			}
		});
		getJSONByVolley(Contants.BASEURL + "getFoodById.do?food_id=" + food_id);
		getJSONByVolley(Contants.BASEURL + "isCollected.do?flag=1&shop_food_id=" + food_id+"&user_id="+user_id);
		getJSONArrayByVolley(Contants.BASEURL + "getAllUserFoodOrder.do?food_id="+food_id);
		return view;
	}

	@Override
	protected void setJSONDataToView(String url,JSONObject data) {
		//读取菜单详细信息
		if(url.contains("getFoodById")) {
			Gson gson = new Gson();
			 Food food = gson.fromJson(data.toString(), Food.class);
			tv_name.setText(food.getFoodname());
			tv_price.setText(food.getPrice() + "元");
			tv_phone.setText(phonenum);
			if(food.getPic()!=null&&!"".equals(food.getPic())){
				loadImageByVolley(food.getPic(), iv_pic);
			}
		}
		else if(url.contains("userCollectFood"))//修改菜谱收藏状态
		{
			if(collect_flag)
				getToast("收藏成功！");
			else
				getToast("取消收藏！");
		}
		else//读取是否收藏
		{
			try {
				String collected = data.getString("collected");
				if("1".equals(collected)) {
					btn_collect.setBackgroundResource(R.drawable.xihuanhou);
					collect_flag=true;
				}
				else {
					btn_collect.setBackgroundResource(R.drawable.xihuan);
					collect_flag=false;
				}
			}catch (JSONException e)
			{

			}
		}
	}

	@Override
	protected void setJSONArrayToView(JSONArray data) {
		Gson gson=new Gson();
		List<Order> comments= gson.fromJson(data.toString(),new TypeToken<List<Order>>(){}.getType());
		AllCommentAdapter adapter=new AllCommentAdapter(this.getActivity(),comments);
		list.setAdapter(adapter);
    	ListViewHeightUtil.setListViewHeightBasedOnChildren(list);//动态设置高度
	}

}
