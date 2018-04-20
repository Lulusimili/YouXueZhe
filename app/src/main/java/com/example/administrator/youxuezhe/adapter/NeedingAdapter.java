package com.example.administrator.youxuezhe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.bean.needing;

import java.util.List;

import static com.example.administrator.youxuezhe.MyApplication.getContext;


/**
 * 需求适配器
 */

public class NeedingAdapter extends RecyclerView.Adapter<NeedingAdapter.ViewHolder> {
    private List<needing> needings;
    private Context context;
    private OnItemClickListener onItemClickListener;
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView type_text;
        TextView publish_name_text;
        TextView price_text;
        ImageView publishIcon;
        ViewHolder(View view) {
            super(view);
            type_text = (TextView)view.findViewById(R.id.type_text);
            publish_name_text = (TextView)view.findViewById(R.id.publish_name_text);
            price_text = (TextView)view.findViewById(R.id.price_text);
            publishIcon=(ImageView) view.findViewById(R.id.publish_icon);
        }
    }
    public NeedingAdapter(List<needing> needings,Context context){
        this.needings=needings;
        this.context=context;
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener=listener;
    }
    public ViewHolder onCreateViewHolder(ViewGroup parent,int ViewType){
        View view= LayoutInflater.from(getContext())
                .inflate(R.layout.item_needing,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return  viewHolder;
    }
    @Override
    public void onBindViewHolder(final ViewHolder holder,int position){
        holder.type_text.setText(needings.get(position).getNeedName());
        holder.publish_name_text.setText(needings.get(position).getUserName());
        holder.price_text.setText(String.valueOf(needings.get(position).getPrice())+"元/小时");
        Log.d("调用","1111");
        Glide.with(getContext())
                .load(needings.get(position).getImgUrl())
                .placeholder(R.drawable.load_error)
                .into(holder.publishIcon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClickListener!=null){
                    int pos=holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView,pos);
                }
            }
        });

    }
    public int getItemCount(){
        Log.d("长度",String.valueOf(needings.size()));
        return needings.size();
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
