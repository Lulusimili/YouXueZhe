package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.OrderAdapter;
import com.example.administrator.youxuezhe.bean.Order;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.MyApplication.getContext;

/**
 * 我的发布页面
 */
public class MyPublishActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private RecyclerView orderListView;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_publish);
        initView();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        orderListView.setLayoutManager(layoutManager);
        backButton.setOnClickListener(this);
        getMyPublishOrder(MyUrlManager.MY_PUBLISH_ORDER_URL);
        orderAdapter=new OrderAdapter(orderList,this);
        orderListView.setAdapter(orderAdapter);
        goToOrderDetail();
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
        orderListView=(RecyclerView)findViewById(R.id.my_order_list);
    }

    /**
     * 获取我发布的内容
     * @param url
     * @return
     */
    private void getMyPublishOrder(String url){

//        List<Order> orders=null;
//        orders= HandleJson.handleOrederResponse(HandleJson.getJSon(url));
//        return orders;
        HttUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                List<Order> responseOrders=HandleJson.handleOrderResponse(response.body().string());
                orderList=responseOrders;
            }
        });
    }

    /**
     * 跳转到详情页
     */
    private void goToOrderDetail(){
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(getContext(),MyPublishDetailActivity.class);
                intent.getIntExtra("pid",orderList.get(position).getPid());
                startActivity(intent);
            }
        });
    }
}
