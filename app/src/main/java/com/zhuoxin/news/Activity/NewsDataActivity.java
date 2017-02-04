package com.zhuoxin.news.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.zhuoxin.news.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDataActivity extends AppCompatActivity {

    @BindView(R.id.wb_view)
    WebView wbView;
    @BindView(R.id.iv_view)
    ImageView ivView;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsdata);
        ButterKnife.bind(this);
        initSystemUI();
        Bundle bundel = getIntent().getBundleExtra("Bundle");
        String url = bundel.getString("url");
        String image = bundel.getString("image");
         title=bundel.getString("title");
        //设置 WebView加载新闻网页
        wbView.getSettings().setJavaScriptEnabled(true);
        wbView.loadUrl(url);
        this.setTitle(title);
        //加载图片
        Picasso.with(this)
                .load(image)
                .into(ivView);
    }

    private void initSystemUI() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
