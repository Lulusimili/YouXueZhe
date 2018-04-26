package com.example.administrator.youxuezhe.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.bean.AboutPlayEntity;
import com.example.administrator.youxuezhe.utils.MyUrlManager;


import java.util.List;

import static com.example.administrator.youxuezhe.MyApplication.getContext;

/**
 * Created by Administrator on 2018/4/1 0001.
 *
 */

public class AboutPlayAdapter extends RecyclerView.Adapter<AboutPlayAdapter.ViewHolder> {
    private List<AboutPlayEntity> aboutPlayEntities;
    private Context context;
    private AboutPlayAdapter.OnItemClickListener onItemClickListener;
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView detail;

        ImageView img_AboutPlay;
        ViewHolder(View view) {
            super(view);
            title = (TextView)view.findViewById(R.id.title_about_play);
            detail= (TextView)view.findViewById(R.id.detail_about_play);
            img_AboutPlay=(ImageView) view.findViewById(R.id.image_about_play);
        }
    }
    public AboutPlayAdapter(List<AboutPlayEntity> aboutPlayEntities,Context context){
        this.aboutPlayEntities=aboutPlayEntities;
        this.context=context;
    }
    public void setOnItemClickListener(AboutPlayAdapter.OnItemClickListener listener){
        onItemClickListener=listener;
    }
    public AboutPlayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view= LayoutInflater.from(getContext())
                .inflate(R.layout.item_about_play,parent,false);
        AboutPlayAdapter.ViewHolder viewHolder=new AboutPlayAdapter.ViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(final AboutPlayAdapter.ViewHolder holder, int position){
        holder.title.setText(aboutPlayEntities.get(position).getTitle());
        holder.detail.setText(aboutPlayEntities.get(position).getContent());
        Glide.with(getContext())
                .load(MyUrlManager.BASE_URL+aboutPlayEntities.get(position).getPictureUrl())
                .placeholder(R.drawable.load_error)
                .into(holder.img_AboutPlay);
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
        return aboutPlayEntities.size();
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}
