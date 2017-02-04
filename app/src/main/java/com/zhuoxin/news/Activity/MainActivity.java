package com.zhuoxin.news.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zhuoxin.news.Frament.CollectFrament;
import com.zhuoxin.news.Frament.ImageFrament;
import com.zhuoxin.news.Frament.ScrollingFragment;
import com.zhuoxin.news.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化系统的UI界面
        initSystemUI();
        //显示新闻Fragment
        showScrollingFragment();
    }
    private void showScrollingFragment(){
        //创建fragment
        ScrollingFragment newsfragment=new ScrollingFragment();
        //通过 FragmentManager来展示
        FragmentManager fragmentManager=getSupportFragmentManager();
        //替换操作
        fragmentManager.beginTransaction().
                replace(R.id.frame,newsfragment).commit();
    }
    private void showImageFragment(){
        //创建fragment
        ImageFrament imagefragment=new ImageFrament();
        //通过 FragmentManager来展示
        FragmentManager fragmentManager=getSupportFragmentManager();
        //替换操作
        fragmentManager.beginTransaction().
                replace(R.id.frame,imagefragment).commit();
    }
    private void showCollectFragment(){
        //创建fragment
        CollectFrament collectfragment=new CollectFrament();
        //通过 FragmentManager来展示
        FragmentManager fragmentManager=getSupportFragmentManager();
        //替换操作
        fragmentManager.beginTransaction().
                replace(R.id.frame,collectfragment).commit();
    }
    //初始化系统的UI界面
    private void initSystemUI() {
        //工具栏
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //把toolbar作为ActionBar显示出来
        setSupportActionBar(toolbar);
        //浮动按钮
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //抽屉布局，主页面内容+侧边栏（导航菜单）内容
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //创建抽屉的开关按钮
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //创建抽屉的开关侦听
        drawer.setDrawerListener(toggle);
        //同步开关按钮的状态
        toggle.syncState();
        //导航菜单，侧边栏
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //设置导航菜单中的单击事件
        navigationView.setNavigationItemSelectedListener(this);
    }
    //返回按钮的设置
    @Override
    public void onBackPressed() {
        //获取抽屉布局
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //判断是否打开
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            //如果打开，则关闭抽屉
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //如果关闭，则调用父类方法，也可直接用finsh（）；关闭，效果一样
            super.onBackPressed();
        }
    }
    //创建ActionBar设置菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //填充菜单到ActionBar上面，如果ActionBar显示则他也显示
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    //设置菜单的单击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * 设置导航菜单
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_News) {
            // Handle the camera action
            showScrollingFragment();
            Toast.makeText(this, "新闻", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_Image) {
            showImageFragment();
            Toast.makeText(this, "图片", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.nav_collect) {
            showCollectFragment();
            Toast.makeText(this, "收藏 ", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        //整个Activity的抽屉布局
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //关闭导航菜单（侧边栏）
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
