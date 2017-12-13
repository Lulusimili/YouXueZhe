package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.OrderAdapter;
import com.example.administrator.youxuezhe.bean.MyRequestBody;
import com.example.administrator.youxuezhe.bean.Order;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyConstant;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.example.administrator.youxuezhe.utils.HandleJson.handleOrderResponse;

/**
 * 商品列表页面
 */
public class CommodityListActivity extends AppCompatActivity implements View.OnClickListener{
    private Button backButton;
    private TextView commodityTitle;
    private RecyclerView commodityListView;
    private List<Order> commodityList;
    private OrderAdapter adapter;
    public static List<Order> commodities;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commodity_list);
        initView();
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        commodityListView.setLayoutManager(layoutManager);
        backButton.setOnClickListener(this);
        setPageTitle();
        getCommodity(MyUrlManager.MY_COMMODITY_LIST_URL);
        adapter = new OrderAdapter(commodityList, this);
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
        //commodities=new ArrayList<>();

    }
     private void setPageTitle(){
         Intent intent=getIntent();
         String from=intent.getStringExtra("from");
         switch (from){
             case "音乐":
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
    private void getCommodity(final String url){

        Intent intent=getIntent();
        String from =intent.getStringExtra("from");
        RequestBody requestBody=new FormBody.Builder()
                .add("label",from)
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, final Response response) throws IOException {

               // response.networkResponse();
                commodities= HandleJson.handleOrderResponse(response.body().string(), MyConstant.NO_PTIME);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        commodityList.clear();
                        commodityList.addAll(commodities);
                        adapter.notifyDataSetChanged();
                    }
                });
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
                Intent intent=new Intent(CommodityListActivity.this,OrderOrPublishActivity.class);
                intent.putExtra("pid",commodityList.get(position).getPid());
                intent.putExtra("from","CommodityListActivity");
                startActivity(intent);
            }
        });
    }

}
