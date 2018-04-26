package com.example.administrator.youxuezhe.ui.label_selector;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.youxuezhe.R;
import java.util.List;


public class LabelAdapter extends BaseAdapter {
    private List<String> labels;
    private Context context;

    public LabelAdapter(Context context,List<String> labels){
        this.context=context;
        this.labels=labels;
    }

    @Override
    public int getCount() {
        return labels.size();
    }

    @Override
    public Object getItem(int position) {
        return labels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View v;
        if(convertView == null)
        {
            v = LayoutInflater.from(context).inflate(R.layout.item_label, null);
            holder = new ViewHolder();
            holder.labelName= (TextView)v.findViewById(R.id.tv_label_item);
            holder.imageView = (ImageView)v.findViewById(R.id.ic_label_item);
            v.setTag(holder);
        } else
        {
            v = convertView;
            holder = (ViewHolder)v.getTag();
        }
        holder.imageView.setImageResource(R.drawable.ic_label_item);
        holder.labelName.setText(labels.get(position));
        return v;
    }
    class ViewHolder{
        ImageView imageView;
        TextView labelName;

    }
}
