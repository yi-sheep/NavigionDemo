package com.gaoxianglong.navigiondemo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 在这里面初始化控件最稳当避免空安全 因为这里是在碎片加载完后才调用的方法
        Button button = getView()  // 先获取到当前碎片的根布局文件
                .findViewById(R.id.home_button);  // 再找到控件
        button.setOnClickListener(v -> {
            NavController controller = Navigation.findNavController(v); // 获取一个导航管理者
            controller.navigate(R.id.action_homeFragment_to_detailFragment); // 设置路线 这个id是my_nav.xml中的action标签的id就路线控件
        });
        // 上面这一大串还有简便的编写方法 在Detail碎片中就是要简便的
    }
}
