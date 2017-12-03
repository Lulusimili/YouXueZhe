package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.OrderAdapter;
import com.example.administrator.youxuezhe.bean.Order;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import java.util.List;

/**
 * 商品列表页面
 */
public class CommodityListActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private TextView commodityTitle;
    private RecyclerView commodityListView;
    private List<Order> commodityList;
    private OrderAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);
        initView();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        commodityListView.setLayoutManager(layoutManager);
        backButton.setOnClickListener(this);
        setPageTitle();
        commodityList=getCommodity(MyUrlManager.MY_COMMODITY_LIST_URL);
        adapter=new OrderAdapter(commodityList,this);
        goToDetail();
    }

    @Override
    public void onClick(View view) {
      finish();
    }

    /**
     * 初始化
     */
    private void initView(){
        backButton=(Button)findViewById(R.id.back_button);
        commodityTitle=(TextView)findViewById(R.id.commodity_title_text);
        commodityListView=(RecyclerView)findViewById(R.id.commodity_list);

    }
     private void setPageTitle(){
         Intent intent=getIntent();
         String from=intent.getStringExtra("from");
         switch (from){
             case "music":
                 commodityTitle.setText("音乐");
                 break;
             default:
                 break;
         }

    }

    /**
     * 获取商品列表
     * @param url
     * @return
     */
    private List<Order> getCommodity(String url){
        List<Order> commodityList=null;
        commodityList= HandleJson.handleOrederResponse(HandleJson.getJSon(url));
        return commodityList;
    }

    /**
     * 点击进入详情页
     */
    private void goToDetail(){
        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(CommodityListActivity.this,OrderDetailActivity.class);
                intent.putExtra("pid",commodityList.get(position).getPid());
            }
        });
    }

}
