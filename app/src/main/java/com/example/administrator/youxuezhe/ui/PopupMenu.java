package com.example.administrator.youxuezhe.ui;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.youxuezhe.R;
import com.example.administrator.youxuezhe.activity.modular_about_play.ReleaseAboutPlayActivity;
import com.example.administrator.youxuezhe.activity.modular_user_login.LoginActivity;
import com.example.administrator.youxuezhe.cache.SaveUser;
import com.example.administrator.youxuezhe.utils.HttUtil;
import com.example.administrator.youxuezhe.utils.MyUtils;


public class PopupMenu {
    private static final String TAG = "PopupMenu";

    public static PopupMenu getInstance() {
        return MenuUtilHolder.INSTANCE;
    }

    private static class MenuUtilHolder {
        public static PopupMenu INSTANCE = new PopupMenu();
    }

    private View rootVew;
    private PopupWindow popupWindow;

    private RelativeLayout rlClick;
    private ImageView ivBtn;
    private Button teachOrProvideButton;
    private Button seekHelpButton;
    private Button aboutPlayButton;
    private Activity mActivity;
    private LinearLayout llTest1, llTest2, llTest3, llTest4, llTest5, llTest6, llTest7, llTest8;
    /**
     * 动画执行的 属性值数组
     */
    float animatorProperty[] = null;
    /**
     * 第一排图 距离屏幕底部的距离
     */
    int top = 0;
    /**
     * 第二排图 距离屏幕底部的距离
     */
    int bottom = 0;

    /**
     * 创建 popupWindow 内容
     *
     * @param context context
     */
    private void createView(final Context context) {
        rootVew = LayoutInflater.from(context).inflate(R.layout.popup_menu, null);
        popupWindow = new PopupWindow(rootVew,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        //设置为失去焦点 方便监听返回键的监听
        popupWindow.setFocusable(false);

        // 如果想要popupWindow 遮挡住状态栏可以加上这句代码
        //popupWindow.setClippingEnabled(false);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(false);

        if (animatorProperty == null) {
            top = dip2px(context, 310);
            bottom = dip2px(context, 210);
            animatorProperty = new float[]{bottom, 60, -30, -20 - 10, 0};
        }

        initLayout(context);
    }

    /**
     * dp转化为px
     *
     * @param context  context
     * @param dipValue dp value
     * @return 转换之后的px值
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * 初始化 view
     */
    private void initLayout(Context context) {
        rlClick = (RelativeLayout) rootVew.findViewById(R.id.pop_rl_click);
        ivBtn = (ImageView) rootVew.findViewById(R.id.pop_iv_img);
        teachOrProvideButton = (Button) rootVew.findViewById(R.id.button_teach_service);
        seekHelpButton = (Button) rootVew.findViewById(R.id.button_seek_help);
        aboutPlayButton= (Button) rootVew.findViewById(R.id.button_about_play);
        teachOrProvideButton.setOnClickListener(new MViewClick(3, context));
        seekHelpButton.setOnClickListener(new MViewClick(1, context));
        aboutPlayButton.setOnClickListener(new MViewClick(2, context));
        rlClick.setOnClickListener(new MViewClick(0,context));

    }

    /**
     * 点击事件
     */
    private class MViewClick implements View.OnClickListener {

        public int index;
        public Context context;

        public MViewClick(int index, Context context) {
            this.index = index;
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            SaveUser saveUser =new SaveUser(mActivity.getApplicationContext());
            boolean isLogin= saveUser.getState();
            String account=saveUser.getAccount();
            String password=saveUser.getPassword();
            Intent loginIntent=new Intent(context, LoginActivity.class);
            Intent intent2=new Intent(context, ReleaseAboutPlayActivity.class);
            switch (index){
                case 0:
                    rlClickAction();
                    break;
                //求助
                case 1:
                    break;
                //约玩
                case 2:
                    if (isLogin) {
                        HttUtil.reLogin(account,password);
                        mActivity.startActivity(intent2);
                        close();
                    }else {
                        MyUtils.showToast("请先登录");
                        mActivity.startActivity(loginIntent);
                    }
                    break;
                //教授知识，提供服务
                case 3:
                    break;
            }
        }
    }

    Toast toast = null;

    /**
     * 防止toast 多次被创建
     *
     * @param context context
     * @param str     str
     */
    private void showToast(Context context, String str) {
        if (toast == null) {
            toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    /**
     * 刚打开popupWindow 执行的动画
     */
    private void openPopupWindowAction() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 0f, 135f);
        objectAnimator.setDuration(200);
        objectAnimator.start();
        startAnimation(teachOrProvideButton, 500, animatorProperty);
        startAnimation(seekHelpButton, 430, animatorProperty);
        startAnimation(aboutPlayButton, 500, animatorProperty);
    }


    /**
     * 关闭 popupWindow执行的动画
     */
    public void rlClickAction() {
        if (ivBtn != null && rlClick != null) {

            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(ivBtn, "rotation", 135f, 0f);
            objectAnimator.setDuration(300);
            objectAnimator.start();

            closeAnimation(teachOrProvideButton, 300, top);
            closeAnimation(seekHelpButton, 200, top);
            closeAnimation(aboutPlayButton, 300, top);

            rlClick.postDelayed(new Runnable() {
                @Override
                public void run() {
                    close();
                }
            }, 300);

        }
    }


    /**
     * 弹起 popupWindow
     *
     * @param context context
     * @param parent  parent
     */
    public void show(Context context, View parent) {
        createView(context);
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
            openPopupWindowAction();
        }
    }
    public void show(Activity activity,View parent){
        createView((Context)activity);
        mActivity=activity;
        if (popupWindow != null && !popupWindow.isShowing()) {
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, 0, 0);
            openPopupWindowAction();
        }
    }

    /**
     * 关闭popupWindow
     */

    public void close() {
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
            mActivity=null;
        }
    }

    /**
     * @return popupWindow 是否显示了
     */
    public boolean isShowing() {
        if (popupWindow == null) {
            return false;
        } else {
            return popupWindow.isShowing();
        }
    }

    /**
     * 关闭 popupWindow 时的动画
     *
     * @param view     mView
     * @param duration 动画执行时长
     * @param next     平移量
     */
    private void closeAnimation(View view, int duration, int next) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", 0f, next);
        anim.setDuration(duration);
        anim.start();
    }

    /**
     * 启动动画
     *
     * @param view     view
     * @param duration 执行时长
     * @param distance 执行的轨迹数组
     */
    private void startAnimation(View view, int duration, float[] distance) {
        ObjectAnimator anim = ObjectAnimator.ofFloat(view, "translationY", distance);
        anim.setDuration(duration);
        anim.start();
    }


}
