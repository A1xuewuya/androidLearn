package cn.edu.neusoft.fooddemo.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.bean.Food;
import cn.edu.neusoft.fooddemo.bean.Shop;
import cn.edu.neusoft.fooddemo.fragment.FoodDetailFragment;
import cn.edu.neusoft.fooddemo.util.Contants;

public class CollectFoodAdapter extends FoodBaseAdapter{
	private List<Food> listItems;
	private LayoutInflater inflater;
	private int user_id,food_id;
	private Food food;
	private Shop shop;


	public CollectFoodAdapter(Context context, List<Food> data,int user_id) {
	   super(context);
		this.inflater = LayoutInflater.from(context);
		this.listItems=data;
		this.user_id=user_id;
	}
	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder;
		final  int p=position;

		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.collect_food_item, null);
			holder.food_name= (TextView) convertView.findViewById(R.id.food_name);
			holder.btn_uncollect = (Button) convertView.findViewById(R.id.btn_uncollect);
			holder.btn_enter = (Button) convertView.findViewById(R.id.btn_enter);
			holder.food_image = (ImageView) convertView.findViewById(R.id.food_image);
			holder.food_price = (TextView) convertView.findViewById(R.id.food_price);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();}
		holder.food_name.setText(listItems.get(position).getFoodname());
		holder.food_price.setText(String.valueOf(listItems.get(position).getPrice()) +"元");
		holder.food_image.setTag(listItems.get(position).getPic());
		// 预设一个图片
		holder.food_image.setImageResource(R.drawable.error);
		// 通过 tag 来防止图片错位
		if (holder.food_image.getTag() != null && holder.food_image.getTag().equals(listItems.get(position).getPic())&&!listItems.get(position).getPic().equals("")) {
			loadImageByVolley(holder.food_image.getTag().toString(), holder.food_image);
		}

		holder.btn_uncollect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				food = listItems.get(p);
				Log.e("========",food.toString());
				getJSONByVolley(Contants.BASEURL + "userCollectFood.do?food_id=" + food.getFood_id() + "&user_id=" + user_id);
			}
		});
		holder.btn_enter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				food = listItems.get(p);
				Log.e("========",food.toString());
				getJSONByVolley(Contants.BASEURL + "getShopById.do?shop_id=" + food.getShop_id());
			}
		});
		return convertView;
	}
	class Holder {
		TextView food_name,food_price;
		ImageView food_image;
		Button btn_uncollect,btn_enter;
	}

	@Override
	protected void setJSONDataToView(String url, JSONObject data) {
		if(url.contains("userCollectFood")) {
			try {
				String collected = data.getString("success");
				if ("1".equals(collected)) {
					getToast("取消成功");
				} else {
					getToast("取消失败");
				}
				listItems.remove(food);
				notifyDataSetChanged();
			} catch (JSONException e) {

			}
		}else
		{
			Gson gson=new Gson();
			shop=gson.fromJson(data.toString(),Shop.class);
			Bundle bundle = new Bundle();
			bundle.putInt("food_id", food_id);
			bundle.putString("phonenum", shop.getPhonenum());
			bundle.putInt("flag", 1);//标记返回
			FoodDetailFragment foodDetailFragment = new FoodDetailFragment();
			changeFrament(foodDetailFragment, bundle, "foodDetailFragment");
		}

	}

}
