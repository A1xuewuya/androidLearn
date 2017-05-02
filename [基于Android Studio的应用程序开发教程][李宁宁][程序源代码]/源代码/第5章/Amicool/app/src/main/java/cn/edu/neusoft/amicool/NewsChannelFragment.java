package cn.edu.neusoft.amicool;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsChannelFragment extends Fragment {

    private String channelName;
    private TextView view;
    @Override
    public void setArguments(Bundle args) {
        channelName=args.getString("cname");
    }

    public NewsChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view==null){
            view=new TextView(super.getActivity());
            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            view.setGravity(Gravity.CENTER);
            view.setTextSize(30);
            view.setText(channelName);

        }
        ViewGroup parent=(ViewGroup)view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        return view;

    }

}
