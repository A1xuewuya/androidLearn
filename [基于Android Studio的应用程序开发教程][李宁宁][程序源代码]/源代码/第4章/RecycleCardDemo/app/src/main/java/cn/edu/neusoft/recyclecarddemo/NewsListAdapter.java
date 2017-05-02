package cn.edu.neusoft.recyclecarddemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by zjs on 2016/3/28.
 */
public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<Map<String,Object>> mDataList;
    private LayoutInflater mLayoutInflater;
    public NewsListAdapter(Context mContext, List<Map<String,Object>> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = mLayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Map<String,Object> entity = mDataList.get(position);

        if (null == entity)
            return;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.news_title.setText(entity.get("news_title").toString());
        viewHolder.news_info.setText(entity.get("news_info").toString());
        viewHolder.news_thumb.setImageResource(Integer.parseInt(entity.get("news_thumb").toString()));
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView news_title;
        ImageView news_thumb;
        TextView news_info;
       // TextView news_info;

        public ViewHolder(View itemView) {
            super(itemView);
            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_thumb = (ImageView) itemView.findViewById(R.id.news_thumb);
            news_info = (TextView) itemView.findViewById(R.id.news_info);
            itemView.findViewById(R.id.news_container).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //
                }
            });
        }
    }


}
