package com.gaoxianglong.navigiondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private NavController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = Navigation.findNavController(this,R.id.nav_host); // 获取管理者对象
        NavigationUI.setupActionBarWithNavController(this, mController); // 使用导航控制器设置操作栏
    }

    /**
     * 重写onSupportNavigateUp()
     * 设置标题栏上的返回逻辑
     * @return 上一个导航
     */
    @Override
    public boolean onSupportNavigateUp() {
        return mController.navigateUp(); // 回到上一步导航页面
    }
}
