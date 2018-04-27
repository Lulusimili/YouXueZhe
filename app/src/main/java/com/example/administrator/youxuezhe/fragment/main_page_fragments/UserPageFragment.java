package com.example.administrator.youxuezhe.fragment.main_page_fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.MyFriendListActivity;
import com.example.administrator.youxuezhe.activity.MyPublishActivity;
import com.example.administrator.youxuezhe.activity.OrderManagementActivity;
import com.example.administrator.youxuezhe.activity.modular_message.PrivateLetterListActivity;
import com.example.administrator.youxuezhe.ui.CustomRatingBar;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkitapplication.MainActivity;

import static cn.leancloud.chatkitapplication.CustomUserProvider.partUsers;
import static com.example.administrator.youxuezhe.activity.modular_user_login.LoginActivity.account;

/**
 * 个人信息页
 */

public class UserPageFragment extends Fragment implements View.OnClickListener{
    private TextView textName;
    private TextView textSchool;
    private TextView textGrade;
    private ImageView imgUserHeader;
    private Button buttonUserPayOrderManage;
    private Button buttonUserPublish;
    private Button buttonPrivateLetter;
    private Button buttonUserReciveOrderManage;
    private Button buttonSetUp;
    private Button buttonfriend;
    private CardView perseoninfoCardView;

    private CustomRatingBar customRatingBar;

    public static UserPageFragment newInstance() {
        Bundle args = new Bundle();
        UserPageFragment fragment = new UserPageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_user_page, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        buttonUserPayOrderManage.setOnClickListener(this);
        buttonUserPublish.setOnClickListener(this);
        buttonPrivateLetter.setOnClickListener(this);
        buttonfriend.setOnClickListener(this);
        getUsersMessage();
        customRatingBar.setMark(4.5f);
    }
    @Override
    public void onClick (View view){
        Intent intent;
        switch (view.getId()) {
            case R.id.cardView_commodity:
                break;
            case R.id.button_pay_order_manage:
                intent = new Intent(getContext(), OrderManagementActivity.class);
                startActivity(intent);
                break;
            case R.id.private_letter_button:
                LCChatKit.getInstance().open(account, new AVIMClientCallback() {
                    @Override
                    public void done(AVIMClient avimClient, AVIMException e) {
                        if (null == e) {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getContext(), e.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.button_user_publish:
                intent = new Intent(getContext(), MyPublishActivity.class);
                startActivity(intent);
                break;
            case R.id.button_receivables_order_manage:
                //收款订单列表；
                break;
            case R.id.button_set_up:
                //设置
                break;
            case R.id.my_friend:
                startActivity(new Intent(getContext(), MyFriendListActivity.class));
        }
    }

    /**
     * 初始化
     * @param view
     */
    private void initView(View view){
        imgUserHeader=(ImageView) view.findViewById(R.id.img_user_header);
        textName=(TextView)view.findViewById(R.id.text_name);
        textSchool=(TextView)view.findViewById(R.id.text_school);
        textSchool=(TextView) view.findViewById(R.id.text_user_grade);
        buttonUserPayOrderManage=(Button)view.findViewById(R.id.button_pay_order_manage);
        buttonUserPublish=(Button)view.findViewById(R.id.button_user_publish);
        buttonPrivateLetter=(Button)view.findViewById(R.id.private_letter_button);
        buttonUserReciveOrderManage=(Button)view.findViewById(R.id.button_receivables_order_manage);
        buttonSetUp=(Button)view.findViewById(R.id.button_set_up);
        customRatingBar=(CustomRatingBar)view.findViewById(R.id.rating_bar);
        buttonfriend=(Button)view.findViewById(R.id.my_friend);
        perseoninfoCardView=(CardView)view.findViewById(R.id.cardView_commodity);
    }

    /**
     * 获取个人信息
     */
    private void getUsersMessage(){

    }

    /**
     * 获取个人信息测试
     */
//    private void test(){
//        nameText.setText("name:码云");
//        schoolText.setText("school:UESTC");
//        gradeText.setText("grade 大二");
//        introduceText.setText("个人介绍：爱学习，爱写代码");
//        Glide.with(getContext())
//                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512313202316&di=8f7622b442d4e6584b2149b5efadcc1c&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20160915%2Ftooopen_sy_178926047887.jpg")
//                .into(myHeaderImage);
//    }

}
