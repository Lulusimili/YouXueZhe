package com.example.administrator.youxuezhe.fragment.main_page_fragments.page_about_play;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.modular_about_play.AboutPlayDetailActivity;
import com.example.administrator.youxuezhe.adapter.AboutPlayAdapter;
import com.example.administrator.youxuezhe.bean.AboutPlayEntity;
import com.example.administrator.youxuezhe.utils.HandleJson;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUrlManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompreFragment extends Fragment {
    private RecyclerView rv_compre;
    private List<AboutPlayEntity> aboutPlayEntities;
    private List<AboutPlayEntity> aboutPlayEntitiesFromServer;
    private AboutPlayAdapter aboutPlayAdapter;
    private Intent intent;
    private Intent intentToDetail;
    private Bundle bundle;

    public static CompreFragment getInstance(){
        Bundle bundle=new Bundle();
        CompreFragment fragment=new CompreFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        aboutPlayAdapter=new AboutPlayAdapter(aboutPlayEntities,getContext());
//        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
//        rv_compre.setLayoutManager(staggeredGridLayoutManager);
//        rv_compre.setAdapter(aboutPlayAdapter);
//        getAboutPlayList(MyUrlManager.SHOW_ALL_ACTIVITY_URL);
//        init(inflater.inflate(R.layout.fragment_compre, container, false));
        return inflater.inflate(R.layout.fragment_compre, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        aboutPlayAdapter=new AboutPlayAdapter(aboutPlayEntities,getContext());
        StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        rv_compre.setLayoutManager(staggeredGridLayoutManager);
        rv_compre.setAdapter(aboutPlayAdapter);
        getAboutPlayList(MyUrlManager.SHOW_ALL_ACTIVITY_URL);
        goToDetail();
    }

    private void init(View view){
        rv_compre=(RecyclerView) view.findViewById(R.id.rv_compre);
        aboutPlayEntitiesFromServer=new ArrayList<>();
        aboutPlayEntities=new ArrayList<>();
    }

    private void goToDetail(){
        bundle=new Bundle();
        intentToDetail=new Intent(getContext(), AboutPlayDetailActivity.class);
        aboutPlayAdapter.setOnItemClickListener(new AboutPlayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                bundle.putSerializable("aboutPlayEntity",aboutPlayEntities.get(position));
                intentToDetail.putExtras(bundle);
                startActivity(intentToDetail);
            }
        });
    }

        /**
     * 从后台获取约玩列表
     */
    private void getAboutPlayList(String url){
        final RequestBody requestBody=new FormBody.Builder()
                .add("label","旅游")
                .build();
        HttUtil.postOkHttp(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               aboutPlayEntitiesFromServer= HandleJson.handleAboutPlayResponse(response.body().string());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        aboutPlayEntities.clear();
                        aboutPlayEntities.addAll(aboutPlayEntitiesFromServer);
                        aboutPlayAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}
