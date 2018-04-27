package cn.leancloud.chatkitapplication;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.avos.avoscloud.im.v2.AVIMChatRoom;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMConversation;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;
import com.avos.avoscloud.im.v2.callback.AVIMConversationCreatedCallback;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;

import cn.leancloud.chatkit.LCChatKit;
import cn.leancloud.chatkit.LCChatKitUser;
import cn.leancloud.chatkit.activity.LCIMConversationActivity;
import cn.leancloud.chatkit.activity.LCIMConversationFragment;
import cn.leancloud.chatkit.activity.LCIMConversationListFragment;
import cn.leancloud.chatkit.utils.LCIMConstants;

public class MainActivity extends AppCompatActivity {
  private static Logger logger = Logger.getLogger(MainActivity.class.getSimpleName());

  private Toolbar toolbar;
  private ViewPager viewPager;
  private TabLayout tabLayout;

  /**
   * 上一次点击 back 键的时间
   * 用于双击退出的判断
   */
  private static long lastBackTime = 0;

  /**
   * 当双击 back 键在此间隔内是直接触发 onBackPressed
   */
  private final int BACK_INTERVAL = 1000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
//    viewPager = (ViewPager) findViewById(R.id.pager);
//    tabLayout = (TabLayout) findViewById(R.id.tablayout);
    setTitle(R.string.app_name);
    setSupportActionBar(toolbar);
//    initTabLayout();
    checkselfPermission();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_square, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int menuId = item.getItemId();
    if (menuId == R.id.menu_square_members) {
      gotoSquareConversation();
    } else if (menuId == R.id.menu_quit) {
      LCChatKit.getInstance().close(new AVIMClientCallback() {
        @Override
        public void done(AVIMClient avimClient, AVIMException e) {
          if (null!= e) {
            e.printStackTrace();
          } else {
            MainActivity.this.finish();
          }
        }
      });
    }
    return super.onOptionsItemSelected(item);
  }

  private void initTabLayout() {
//    FragmentManager fmConversation=getSupportFragmentManager();
//    FragmentTransaction fragmentTransaction=fmConversation.beginTransaction();
//    Fragment LCIMConFragment=new LCIMConversationFragment();
//    fragmentTransaction.add(R.id.conversation,LCIMConFragment);
//    fragmentTransaction.commit();


    String[] tabList = new String[]{"会话", "联系人"};
    final Fragment[] fragmentList = new Fragment[] {new LCIMConversationListFragment(),
      new ContactFragment()};

    tabLayout.setTabMode(TabLayout.MODE_FIXED);
    for (int i = 0; i < tabList.length; i++) {
      tabLayout.addTab(tabLayout.newTab().setText(tabList[i]));
    }

    TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(),
      Arrays.asList(fragmentList), Arrays.asList(tabList));
    viewPager.setAdapter(adapter);
    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override
      public void onPageSelected(int position) {
        if (0 == position) {
//          EventBus.getDefault().post(new ConversationFragmentUpdateEvent());
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {
      }
    });
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.setTabsFromPagerAdapter(adapter);
  }

//  public void onBackPressed() {
//    long currentTime = System.currentTimeMillis();
//    if (currentTime - lastBackTime < BACK_INTERVAL) {
//      super.onBackPressed();
//    } else {
//      Toast.makeText(this, "双击 back 退出", Toast.LENGTH_SHORT).show();
//    }
//    lastBackTime = currentTime;
//  }

  public class TabFragmentAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> mFragments;
    private List<String> mTitles;

    public TabFragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
      super(fm);
      mFragments = fragments;
      mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
      return mFragments.get(position);
    }

    @Override
    public int getCount() {
      return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mTitles.get(position);
    }
  }

  private void gotoSquareConversation() {
    List<LCChatKitUser> userList = CustomUserProvider.getInstance().getAllUsers();
    List<String> idList = new ArrayList<>();
    for (LCChatKitUser user : userList) {
      idList.add(user.getUserId());
    }
    LCChatKit.getInstance().getClient().createChatRoom(
      idList, getString(R.string.square), null, true, new AVIMConversationCreatedCallback() {
        @Override
        public void done(AVIMConversation avimConversation, AVIMException e) {
          if (avimConversation instanceof AVIMChatRoom) {
            Intent intent = new Intent(MainActivity.this, LCIMConversationActivity.class);
            intent.putExtra(LCIMConstants.CONVERSATION_ID, avimConversation.getConversationId());
            startActivity(intent);
          } else {
            logger.log(Level.WARNING, "createChatRoom is wrong!");
          }
        }
      });
  }
  public void checkselfPermission(){
    if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
      ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }
    if(ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
      ActivityCompat.requestPermissions(MainActivity.this,new String[]{android.Manifest.permission.RECORD_AUDIO},1);
    }
  }
}
