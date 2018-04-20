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
import com.example.administrator.youxuezhe.utils.MyConstant;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OrderManagementActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private RecyclerView orderListView;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;
    private List<Order> orders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);
        initView();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        orderListView.setLayoutManager(layoutManager);
        backButton.setOnClickListener(this);
        getOrders(MyUrlManager.MY_ORDER_URL);
        /**
         *
         */
//        Order order=new Order();
//        order.setPreleasetime("2017.3.5");
//        order.setPprice(40);
//        order.setPreleaseName("ma");
//        order.setPtime("2018.3.6");
//        order.setPid(20);
//        order.setPtitle("gggg");
//        order.setUserHeaderURL("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512404280056&di=c7b83c3f9d0c05cb52721e50acebff36&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20160915%2Ftooopen_sy_178926047887.jpg");
//        for (int i=0;i<20;i++) {
//            orderList.add(order);
//        }
        orderAdapter=new OrderAdapter(orderList,this);
        orderAdapter.notifyDataSetChanged();
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
        orderList=new ArrayList<>();
    }

    /**
     * 获取订单列表
     * @param url
     * @return
     */
    private void getOrders(final String url){
        RequestBody requestBody=new FormBody.Builder()
                .add("null","null")
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                if(isTimeOut(response.body().string())){
//                    HttUtil.refrashCookie();
//                    getOrders(url);
//                }
                orders= HandleJson.handleOrderResponse(response.body().string(), MyConstant.NO_PTIME);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        orderList.clear();
                        orderList.addAll(orders);
                    }
                });
            }
        });
    }

    /**
     * item监听事件
     */
    private void goToOrderDetail(){
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(OrderManagementActivity.this,OrderOrPublishActivity.class);
                intent.putExtra("from","OrderManagementActivity");
                intent.putExtra("pid",orderList.get(position).getPid());
                startActivity(intent);
            }
        });
    }

}
