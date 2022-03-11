package com.example.huc_project.slice;

import com.example.huc_project.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.agp.render.Region;

import java.util.OptionalInt;

public class MainAbility3_registerSlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main_ability3_register);

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
                        } /*else if () // 判断用户名是否在数据库中*/
                        else{ // 最后才是登录成功
                            join_in_text.setText("成功加入锤子科技!!!");
                            // 将数据添加进数据库中

                            // 跳转回登录页面
                            // 使用intent
                            Intent intent1 = new Intent();

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
