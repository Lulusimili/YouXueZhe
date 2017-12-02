package com.example.administrator.youxuezhe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.bean.Commodity;

import java.util.List;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 商品信息适配器
 */

public class CommodityAdapter extends
        RecyclerView.Adapter<CommodityAdapter.ViewHolder> {
    private List<Commodity> commodities;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView publishIcon;
        TextView publishNameText;
        TextView publishTimeText;
        TextView priceText;
        TextView typeText;

        ViewHolder(View view){
            super(view);
            publishIcon=view.findViewById(R.id.publish_icon);
            priceText=view.findViewById(R.id.price_text);
            publishNameText=view.findViewById(R.id.publish_name_text);
            publishTimeText=view.findViewById(R.id.publish_time_text);
            typeText=view.findViewById(R.id.type_text);
        }
    }
    public CommodityAdapter(List<Commodity>commodities, Context context){
        this.commodities=commodities;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commodity,parent,false);
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return commodities.size();
    }
}
