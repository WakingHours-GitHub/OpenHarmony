package com.example.huc_project.slice;

import com.example.huc_project.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.DataAbilityHelper;
import ohos.aafwk.ability.DataAbilityRemoteException;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.agp.render.Region;
import ohos.data.rdb.ValuesBucket;
import ohos.utils.net.Uri;

import java.util.OptionalInt;

public class MainAbility3_registerSlice extends AbilitySlice {
    private DataAbilityHelper dataAbilityHelper;
    Uri uri = Uri.parse("dataability:///com.example.huc_project.UserDataAbility/users");
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_ability3_register);

        // 初始化:
        dataAbilityHelper = DataAbilityHelper.creator(this);

        // 寻找组件:
        Button join_in_but = (Button) findComponentById(ResourceTable.Id_but_join_in);

        TextField register_username = (TextField) findComponentById(ResourceTable.Id_register_username);
        TextField register_password = (TextField) findComponentById(ResourceTable.Id_register_password);
        TextField register_re_password = (TextField) findComponentById(ResourceTable.Id_register_re_password);
        Text remind = (Text) findComponentById(ResourceTable.Id_remind);
        Text join_in_text = (Text) findComponentById(ResourceTable.Id_join_in_text);
        // 弹窗
/*        CommonDialog dialog = new CommonDialog(getContext());
        dialog.setTitleText("");
        dialog.setContentText("This is CommonDialog Content area.");
        dialog.setButton(IDialog.BUTTON3, "CONFIRM", (iDialog, i) -> iDialog.destroy());
        dialog.show();
*/
        join_in_but.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        remind.setText("");

                        if (register_username.getText().equals("") || register_password.getText().equals("") || register_re_password.getText().equals("") ) {
                            remind.setText("用户名或密码不能为空!!!");
                        }else if (!register_password.getText().equals(register_re_password.getText())) {
                            remind.setText("两次密码不一致");
                        }
                        /*else if () // 判断用户名是否在数据库中*/
                        else{ // 最后才是登录成功

                            // 将数据添加进数据库中
                            // 访问DataAbility完成数据的添加.
                            // 要通过DataAbilityHelper来访问
                            // 每个DataAbility都会在config.json有自己的uri, 我们需要在这里写入我们需要操作的DataAbility的uri
                            // 通过dataAbilityHelper的insert, 可以访问任何一个dataAbility的insert方法, 我们通过uri指定调用目标的DataAbility
                            // "dataability://设备id/com.example.huc_project.UserDataAbility/访问的子资源"
                            // 如果是跨设备的就需要指定设备id, 如果是本地设备, 就只需要指定///即可.
                            // 但是我们需要的是一个Uri对象, 而不是一个字符串, 所以我们需要解析一下, 使用API: Uri.parse

                            // 并且我们需要插入数据, 那么我们的数据需要添加在ValuesBucket中, 所以我们还需要添加一个ValueBucket
                            ValuesBucket valuesBucket = new ValuesBucket();
                            valuesBucket.putString("userName",register_username.getText());
                            valuesBucket.putString("userPwd",register_password.getText());

                            try {
                                // 因为这个操作本身是抛出异常的, 所以我们需要try catch来环绕
                                int i = dataAbilityHelper.insert(uri, valuesBucket); // 这里是通过uri调用的, 而不是通过方法.
//                                System.out.println("-->>>"+i); // 打印i, 即是否添加成功
                                if (i == 1){
                                    join_in_text.setText("成功加入锤子科技!!!");
                                }else{
                                    join_in_text.setText("添加失败");
                                }
                            } catch (DataAbilityRemoteException e) {
                                e.printStackTrace();
                            }


                            // 返回登录页面
                            // 使用intent(页间跳转)
//                            Intent intent1 = new Intent();

                        }


                    }
                }
        );
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
