package cn.edu.neusoft.amicool.subfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class SubFragment2 extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		//T.showShort(getActivity(), "SubFragment2==onCreateView");
		TextView tv = new TextView(getActivity());
		tv.setTextSize(25);
		tv.setBackgroundColor(Color.parseColor("#FFA07A"));
		tv.setText("同事");
		tv.setGravity(Gravity.CENTER);
		return tv;
	}
}
