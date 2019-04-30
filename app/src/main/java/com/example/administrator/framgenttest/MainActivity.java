package com.example.administrator.framgenttest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.ViewTreeObserver;

public class MainActivity extends FragmentActivity implements NavigationView.OnItemClickListener {

    private NavigationView nv;
    private Fragment coatFragment;
    private Fragment trousersFragment;
    private Fragment shoeFragment;
    private Fragment moreFragment;
    private String[] titles = { "外套", "裤子", "鞋子","更多" };
    private int[] selectedImage = { R.mipmap.member, R.mipmap.photo,
            R.mipmap.wallet,R.mipmap.more };
    private int[] unSelectedImage = { R.mipmap.member, R.mipmap.photo,
            R.mipmap.wallet,R.mipmap.more};
    private int mHeight;
    private boolean isGetHeight = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }


    private void initView() {
        // 获取屏幕宽度
        Display dm = getWindowManager().getDefaultDisplay();
        final int screenWidth = dm.getWidth();
        nv = findViewById(R.id.nv);
        // 初始化获取底部导航自身高度
        final ViewTreeObserver vt = nv.getViewTreeObserver();
        vt.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                if (isGetHeight) {
                    mHeight = nv.getMeasuredHeight();
                    nv.setLayout(titles, selectedImage, unSelectedImage, screenWidth, mHeight, MainActivity.this);
                    nv.setColorLing(0);
                    nv.setOnItemClickListener(MainActivity.this);
                    isGetHeight = false;
                }
                return true;
            }
        });
        showFragment(0);
    }


    @Override
    public void onItemClick(int position) {
        showFragment(position);
    }


    /**
     * 动态添加和显示fragment
     *
     * @param position
     */
    private void showFragment(int position) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        hideFragment(transaction);
        switch (position) {
            case 0:
                if (coatFragment == null) {
                    coatFragment = new CoatFragment();
                    transaction.add(R.id.fragment_content, coatFragment);
                } else {
                    transaction.show(coatFragment);
                }
                break;
            case 1:
                if (trousersFragment == null) {
                    trousersFragment = new TrousersFragment();
                    transaction.add(R.id.fragment_content, trousersFragment);
                } else {
                    transaction.show(trousersFragment);
                }
                break;
            case 2:
                if (shoeFragment == null) {
                    shoeFragment = new ShoeFragment();
                    transaction.add(R.id.fragment_content, shoeFragment);
                } else {
                    transaction.show(shoeFragment);
                }
                break;
            case 3:
                if(moreFragment==null){
                    moreFragment=new MoreFragment();
                    transaction.add(R.id.fragment_content, moreFragment);
                }else {
                    transaction.show(moreFragment);
                }
        }
        transaction.commit();
    }


    /**
     * 隐藏所有fragment
     *
     * @param transaction
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (coatFragment != null) {
            transaction.hide(coatFragment);
        }


        if (trousersFragment != null) {
            transaction.hide(trousersFragment);
        }


        if (shoeFragment != null) {
            transaction.hide(shoeFragment);
        }

        if (moreFragment != null) {
            transaction.hide(moreFragment);
        }
    }
}
