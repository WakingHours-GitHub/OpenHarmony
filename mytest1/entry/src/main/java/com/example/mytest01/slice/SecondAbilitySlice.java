package com.example.mytest01.slice;

import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Component;
import ohos.agp.components.DirectionalLayout;
import ohos.agp.components.TableLayout;
import ohos.agp.components.Text;
import ohos.agp.utils.Color;
import ohos.agp.utils.TextAlignment;

// 这是第二个子页面, 我们使用java代码的形式来去构造
public class SecondAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        // 因为使用java代码构造页面, 所以我们不使用xml文件了
//        super.setUIContent(ResourceTable.Layout_ability_second); // 于是我们将这句注释

        // 代码:
        // 1. 创建一个布局对象: 这里使用流式布局, 从上倒下
        DirectionalLayout dl = new DirectionalLayout(this); // 这个界面放在哪呢, 放在当前的这个子界面当中
        dl.setOrientation(Component.VERTICAL);

        // 2. 创建文本对象
        Text t = new Text(this);
        // 设置内容:
         t.setText("登录成功");
        // 设置布局
        t.setTextAlignment(TextAlignment.CENTER);
        // 设置文字大小:a
        t.setTextSize(80);
        // 设置文字颜色:
        t.setTextColor(Color.BLUE);
        // 3. 把文本对象添加到布局当中
        dl.addComponent(t);
        dl.setAlignment(TableLayout.Alignment.ALIGNMENT_START);
        // 4. 把布局添加到子界面当中
        super.setUIContent(dl);



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
