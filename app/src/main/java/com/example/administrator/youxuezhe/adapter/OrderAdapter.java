package com.example.administrator.youxuezhe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.bean.CommodityOrderInfo;
import com.example.administrator.youxuezhe.bean.Order;
import com.example.administrator.youxuezhe.utils.MyUtils;

import java.util.List;

import static com.example.administrator.youxuezhe.MyApplication.getContext;

/**
 * Created by Administrator on 2017/12/2 0002.
 * 订单适配器
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private List<Order> orders;
    private Context context;
    private OnItemClickListener onItemClickListener;
    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView publishIcon;
        TextView publishNameText;
        TextView publishTimeText;
        TextView priceText;
        TextView typeText;
        ViewHolder(View view){
            super(view);
            publishIcon=(ImageView) view.findViewById(R.id.publish_icon);
            priceText=(TextView)view.findViewById(R.id.price_text);
            publishNameText=(TextView)view.findViewById(R.id.publish_name_text);
            publishTimeText=(TextView)view.findViewById(R.id.publish_time_text);
            typeText=(TextView)view.findViewById(R.id.type_text);
        }
    }
    public OrderAdapter(List<Order>orders, Context context){
        this.orders=orders;
        this.context=context;
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=listener;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_commodity,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.priceText.setText((String.valueOf(orders.get(position).getPprice()))+"元/小时");
        holder.publishNameText.setText(orders.get(position).getPreleaseName());
        holder.publishTimeText.setText(MyUtils.getDateToString(orders.get(position).getPreleasetime()));
        holder.typeText.setText(orders.get(position).getPtitle());
        Log.d("调用","123456");
        Glide.with(getContext())
                .load(orders.get(position).getUserHeaderURL())
                .into(holder.publishIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d("长度",String.valueOf(orders.size()));
        return  orders.size();

    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
    }
}
