package cn.edu.neusoft.fooddemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.neusoft.fooddemo.R;
import cn.edu.neusoft.fooddemo.bean.Food;

public class FoodAdapter extends FoodBaseAdapter{
	private List<Food> listItems;
	private LayoutInflater inflater;

public FoodAdapter(Context context, List<Food> data) {
	    super(context);
		this.inflater = LayoutInflater.from(context);
		this.listItems=data;
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
		if (convertView == null) {
			holder = new Holder();
			convertView = inflater.inflate(R.layout.food_item, null);
			holder.food_name= (TextView) convertView.findViewById(R.id.food_name);
			holder.food_image = (ImageView) convertView.findViewById(R.id.food_image);
			holder.food_price = (TextView) convertView.findViewById(R.id.food_price);
			holder.food_intro= (TextView) convertView.findViewById(R.id.food_intro);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();}
		holder.food_name.setText(listItems.get(position).getFoodname());
		holder.food_intro.setText(listItems.get(position).getIntro());
		holder.food_price.setText(String.valueOf(listItems.get(position).getPrice()) +"元");
// 给 ImageView 设置一个 tag
		holder.food_image.setTag(listItems.get(position).getPic());
		// 预设一个图片
		holder.food_image.setImageResource(R.drawable.error);
		// 通过 tag 来防止图片错位
		if (holder.food_image.getTag() != null && holder.food_image.getTag().equals(listItems.get(position).getPic())&&!listItems.get(position).getPic().equals("")) {
			loadImageByVolley(holder.food_image.getTag().toString(), holder.food_image);
		}
		return convertView;
	}
	class Holder {
		TextView food_name,food_price,food_intro;
		ImageView food_image;
	}



}
