package com.example.administrator.youxuezhe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.adapter.NeedingAdapter;
import com.example.administrator.youxuezhe.bean.needing;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyConstant;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * needing 列表页面
 */
public class NeedingActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBackButton;
    private TextView mNeedingTitleText;
    private RecyclerView mNeedingList;
    private Intent intent;
    private List<needing> needingList;
    private NeedingAdapter needingAdapter;
    public static List<needing>needings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needing);
        initView();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mNeedingList.setLayoutManager(linearLayoutManager);
        mBackButton.setOnClickListener(this);
        setTitle();
        getNeeding(MyUrlManager.MY_NEEDING_INFO_URL);
        needingAdapter=new NeedingAdapter(needingList,this);
        mNeedingList.setAdapter(needingAdapter);
        goToDtail();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBackButton = (Button) findViewById(R.id.back_button);
        mBackButton.setOnClickListener(this);
        mNeedingTitleText = (TextView) findViewById(R.id.needing_title_text);
        mNeedingList = (RecyclerView) findViewById(R.id.needing_list);
        setTitle();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 设置标题
     */
    private void setTitle(){
        intent=getIntent();
        mNeedingTitleText.setText(intent.getStringExtra("from"));
    }
    private void getNeeding(String url){
        Intent intent=new Intent();
        String from=intent.getStringExtra("from");
        final RequestBody requestBody=new FormBody.Builder()
                .add("label",from)
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                needings= HandleJson.handleNeedingResponse(response.body().string(), MyConstant.NO_PTIME);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        needingList.clear();
                        needingList.addAll(needings);
                        needingAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    private void goToDtail(){
        needingAdapter.setOnItemClickListener(new NeedingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent=new Intent(NeedingActivity.this,NeedingDetailActivity.class);
                intent.putExtra("pid",needingList.get(position).getPid());
                intent.putExtra("from","needinglist");
                startActivity(intent);
            }
        });
        }
    }

