package com.statckback;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.statckback.fragment.Fragment1;
import com.statckback.fragment.Fragment2;
import com.statckback.fragment.Fragment3;
import com.statckback.fragment.Fragment4;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FragmentManager supportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        supportFragmentManager = getSupportFragmentManager();
    }

    private void initView() {
        Button bt_add1 = (Button) findViewById(R.id.bt_add1);
        Button bt_add2 = (Button) findViewById(R.id.bt_add2);
        Button bt_add3 = (Button) findViewById(R.id.bt_add3);
        Button bt_add4 = (Button) findViewById(R.id.bt_add4);
        Button bt_back = (Button) findViewById(R.id.bt_back);
        Button bt_backto1 = (Button) findViewById(R.id.bt_backto1);
        Button bt_backto2 = (Button) findViewById(R.id.bt_backto2);

        bt_add1.setOnClickListener(this);
        bt_add2.setOnClickListener(this);
        bt_add3.setOnClickListener(this);
        bt_add4.setOnClickListener(this);
        bt_back.setOnClickListener(this);
        bt_backto1.setOnClickListener(this);
        bt_backto2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_add1:
                addFragment(new Fragment1(), "fragment1");

                break;
            case R.id.bt_add2:
                addFragment(new Fragment2(), "fragment2");

                break;
            case R.id.bt_add3:
                addFragment(new Fragment3(), "fragment3");

                break;
            case R.id.bt_add4:
                addFragment(new Fragment4(), "fragment4");

                break;
            case R.id.bt_back:
                // 退一步
                supportFragmentManager.popBackStack();
                break;
            case R.id.bt_backto2:
                // arg0 要回退到的Fragmenttag
                // arg1 0 代表直接推到2
                supportFragmentManager.popBackStack("fragment2", 0);
                break;
            case R.id.bt_backto1:
                //回退到2,顺便将2回退
                supportFragmentManager.popBackStack("fragment2",FragmentManager.POP_BACK_STACK_INCLUSIVE);
                break;
            default:
                break;
        }
    }

    public void addFragment(Fragment fragment, String tag) {
        FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
        beginTransaction.add(R.id.framelayout, fragment, tag);
        // 添加到回退栈,并设置标记
        beginTransaction.addToBackStack(tag);
        beginTransaction.commit();
    }
}
