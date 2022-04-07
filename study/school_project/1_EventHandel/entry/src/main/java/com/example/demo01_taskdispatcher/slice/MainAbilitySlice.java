package com.example.demo01_taskdispatcher.slice;

import com.example.demo01_taskdispatcher.Log;
import com.example.demo01_taskdispatcher.ResourceTable;
import com.example.demo01_taskdispatcher.User;
import com.example.demo01_taskdispatcher.utils.UserManger;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.TextField;
import ohos.agp.components.TextFilter;
import ohos.data.rdb.ValuesBucket;
import ohos.utils.net.Uri;

import java.util.List;

public class MainAbilitySlice extends AbilitySlice {
    // 获取helper

    private static final String base_uri = "dataability:///com.example.demo01_taskdispatcher.User/user";
    private UserManger manger;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        manger = new UserManger(MainAbilitySlice.this);
        // 获取helper
        DataAbilityHelper helper = DataAbilityHelper.create(this);

        TextField name = (TextField) findComponentById(ResourceTable.Id_name);
        TextField age = (TextField) findComponentById(ResourceTable.Id_age);
        TextField query = (TextField) findComponentById(ResourceTable.Id_query);

        Button button_connect = (Button) findComponentById(ResourceTable.Id_button_connect);
        Button button_query = (Button) findComponentById(ResourceTable.Id_button_query);

        // 点击按钮, 就链接数据库:

        button_connect.setClickedListener(component -> {
            if ("".equals(name.getText()) || "".equals(age.getText())){
                Log.debug("不能为空");
                return; // 直接结束
            }
            // 连接数据库:
            Log.info("连接数据库");
            User user = new User();
            user.setName(name.getText());
            user.setAge(Integer.parseInt(age.getText()));
            boolean flag = manger.insertUser(user);
            if (flag) {
                Log.info("插入成功");
            }else{
                Log.info("插入失败");
            }


        });

        // 查询按钮
        button_query.setClickedListener(component -> {
            List<User> list = manger.queryUser(query.getText());
            if (list.isEmpty()){
                Log.info("没有查询到任何用户");
            }else {
                Log.info("" + list);
            }

        });


    }


    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
