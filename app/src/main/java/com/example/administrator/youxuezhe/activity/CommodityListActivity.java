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
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
        //getCommodity(MyUrlManager.MY_COMMODITY_LIST_URL);
        //***********************************
        Order order=new Order();
        order.setPreleasetime("2017.3.5");
        order.setPprice(40);
        order.setPreleaseName("ma");
        order.setPtime("2018.3.6");
        order.setPid(20);
        order.setPtitle("gggg");
        order.setUserHeaderURL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512404280056&di=c7b83c3f9d0c05cb52721e50acebff36&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20160915%2Ftooopen_sy_178926047887.jpg");
       for (int i=0;i<20;i++) {
           commodityList.add(order);
       }

        //********************************
        adapter=new OrderAdapter(commodityList,this);
        commodityListView.setAdapter(adapter);
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
        commodityList=new ArrayList<>();

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
    private void getCommodity(String url){
        Intent intent=getIntent();
        String from =intent.getStringExtra("from");
        JSONObject jsonFrom=new JSONObject();
        try {
            jsonFrom.put("label", from);
        }catch (JSONException e){
            e.printStackTrace();
        }
        String content=String.valueOf(jsonFrom);
        HttUtil.postOkhttp(url, content, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  commodityList=HandleJson.handleOrderResponse(response.body().string());
            }
        });
    }

    /**
     * 点击进入详情页
     */
    private void goToDetail(){
        adapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(CommodityListActivity.this,MyPublishDetailActivity.class);
                intent.putExtra("pid",commodityList.get(position).getPid());
                startActivity(intent);
            }
        });
    }

}
