package com.example.jxm.daiban.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.jxm.daiban.R;
import com.example.jxm.daiban.bean.VideoItemInfo;
import com.example.jxm.daiban.fragment.fragment1;
import com.example.jxm.daiban.util.ItemClickListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ItemClickListener{

    private TextView tab1,tab2,tab3,tab4;
    private FrameLayout container;
    private Toolbar toolbar;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        toolbar.setTitle("hello");
        setSupportActionBar(toolbar);

        Fragment fragment=new fragment1();
        replaceFragment(fragment);


    }


    void initView(){

        navigationView=findViewById(R.id.nav_view);
        tab1=findViewById(R.id.tab1);
        tab2=findViewById(R.id.tab2);
        tab3=findViewById(R.id.tab3);
        tab4=findViewById(R.id.tab4);

        toolbar=findViewById(R.id.toolbar);
        container=findViewById(R.id.container);
        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);
        tab4.setOnClickListener(this);
        View view= navigationView.getHeaderView(0);//获取headerView，再用headerView获取其中的各个空间
        Log.d("navigationView name", "initView: "+ navigationView.getHeaderView(0)+((TextView)view.findViewById(R.id.username)).toString());
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.nav_call:

                        break;

                    case R.id.nav_friends:

                        break;

                    case R.id.nav_location:

                        break;

                }

                return false;
            }
        });

    }

    @Override
    public void onClick(View v) {
        Fragment fragment=null;
        switch (v.getId()){
            case R.id.tab1:

                fragment=new fragment1();
                Bundle b=new Bundle();
                b.putString("url","www.baidu.com");
                fragment.setArguments(b);

                break;

            case R.id.tab2:

                break;

            case R.id.tab3:

                break;

            case R.id.tab4:

                break;

        }
     replaceFragment(fragment);

    }


    void replaceFragment(Fragment fragment){
        if (fragment!=null){
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction= fragmentManager.beginTransaction();

            transaction.replace(R.id.container,fragment);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTitleClick(VideoItemInfo info) {

        Intent intent=new Intent(this,PlayActivity.class);
        intent.putExtra("play_addr",info.getUrlPreview());

        startActivity(intent);

    }

    @Override
    public void onImageClick(VideoItemInfo info) {

    }
}
