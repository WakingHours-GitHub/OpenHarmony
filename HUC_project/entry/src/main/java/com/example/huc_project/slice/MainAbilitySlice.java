package com.example.huc_project.slice;

import com.example.huc_project.ResourceTable;
import com.example.huc_project.UserDataAbility;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.data.rdb.RdbPredicates;
import ohos.data.resultset.ResultSet;
import ohos.org.xml.sax.EntityResolver;
import ohos.utils.net.Uri;

import javax.lang.model.util.ElementScanner6;
import java.lang.annotation.Target;
import java.nio.file.FileAlreadyExistsException;

public class MainAbilitySlice extends AbilitySlice {
    private DataAbilityHelper dataAbilityHelper;


    @Override // 初始化页面
    public void onStart(Intent intent) { // intent用于信息传递.
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // 初始化DataAbility
        dataAbilityHelper = DataAbilityHelper.creator(this);

        // 寻找组件
        Button login = (Button) findComponentById(ResourceTable.Id_but_login);
        Button register = (Button) findComponentById(ResourceTable.Id_but_register);
        Button forget_but = (Button) findComponentById(ResourceTable.Id_forget_but);
        Text status = (Text) findComponentById(ResourceTable.Id_text_status);
        TextField tf_username = (TextField) findComponentById(ResourceTable.Id_tf_username);
        TextField tf_password = (TextField) findComponentById(ResourceTable.Id_tf_password);

        // 登录按钮点击
        login.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        if (tf_username.getText().equals("") || tf_password.getText().equals("")) {
                            status.setText("请输入用户名或密码");
                            // 返回
                        } else { // 不为空的情况

                            boolean is_user = false;
//                        status.setText("请输入用户名");
                            // 执行查询操作：
                            Uri uri = Uri.parse("dataability:///com.example.huc_project.UserDataAbility/users"); // 从字符串转换到URI object
                            String[] columns = new String[]{"userName", "userPwd"}; // 查询列表
                            DataAbilityPredicates dataAbilityPredicates = new DataAbilityPredicates();

                            try {
                                ResultSet rs = dataAbilityHelper.query(uri, columns, dataAbilityPredicates);
                                int user_count = rs.getRowCount();

                                if (user_count > 0) { // 只有
                                    rs.goToFirstRow();
                                    do {
                                        String userName = rs.getString(rs.getColumnIndexForName("userName"));
                                        String userPwd = rs.getString(rs.getColumnIndexForName("userPwd"));
                                        if (userName.equals(tf_username.getText()) && userPwd.equals(tf_password.getText())) { // 如果均正确
                                            is_user = true;
                                        }
//                                    System.out.println(userName + userPwd);
                                    } while (rs.goToNextRow()); // 循环, 查找
                                }

                            } catch (DataAbilityRemoteException e) {
                                e.printStackTrace();
                            }


                            if (is_user) { // 判断是否在数据库中
                                // 页面导航, 可以实现不同设备间的页面的一个切换
                                // 添加意图
                                Intent intent1 = new Intent(); // 意图对象
                                // 定义操作, 与操作系统操作的接口
                                Operation op = new Intent.OperationBuilder()
                                        .withDeviceId("") // 设置设备信息.
                                        .withBundleName("com.example.huc_project")
                                        .withAbilityName("com.example.huc_project.MainAbility2_login")
                                        .build(); // 聚合
                                intent1.setOperation(op); // 添加操作

                                startAbility(intent1); // 开启意图
                            } else { // 如果, 无用户, 则需要注册
                                status.setText("无该用户, 请注册");
                            }


                        }
                    }
                }
        );

        // 注册按钮点击
        register.setClickedListener( // 注册页面
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        // 跳转到注册页面
                        Intent intent1 = new Intent();
                        Operation op = new Intent.OperationBuilder()
                                .withDeviceId("").withBundleName("com.example.huc_project")
                                .withAbilityName("com.example.huc_project.MainAbility3_register")
                                .build();
                        intent1.setOperation(op);
                        startAbility(intent1);

                    }
                }
        );

        // 忘记密码
        // 页面间导航, 到从Slice到Slice
        forget_but.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        // 跳转到forget_password_slice页面
                        present(new forget_password_slice(), new Intent());

                    }
                }
        );
    }

    @Override
    public void onActive() { // UI线程, 不断的调用他, 不断的响应, 响应页面操作
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) { // 返回前台. 然后再执行onActive, 处于激活状态
        super.onForeground(intent);
    }

    // 如果需要获取消息, 就需要重写onAbilityResult()方法
}
