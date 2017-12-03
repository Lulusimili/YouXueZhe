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

import java.util.List;

import static com.example.administrator.youxuezhe.MyApplication.getContext;


public class OrderManagementActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private RecyclerView orderListView;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_management);
        initView();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        orderListView.setLayoutManager(layoutManager);
        backButton.setOnClickListener(this);
        //orderList=getOrders(MyUrlManager.MY_ORDER_URL);
        orderAdapter=new OrderAdapter(orderList,this);
        //orderListView.setAdapter(orderAdapter);
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
     * 获取订单列表
     * @param url
     * @return
     */
    private List<Order> getOrders(String url){
        List<Order> orders;
        orders= HandleJson.handleOrederResponse(HandleJson.getJSon(url));
        return orders;
    }

    /**
     * item监听事件
     */
    private void goToOrderDetail(){
        orderAdapter.setOnItemClickListener(new OrderAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=getIntent();
                intent.getIntExtra("pid",orderList.get(position).getPid());
                startActivity(intent);
            }
        });
    }

}
