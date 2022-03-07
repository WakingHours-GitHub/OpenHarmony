package com.example.study.slice;

import com.example.study.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;

import java.nio.channels.FileLockInterruptionException;
import java.security.PublicKey;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    Text text1 = null;

    @Override
    public void onStart(Intent intent) { // 开始onStart.
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);


        // 我们再这里编写我们的代码
        // 1. 找到按钮:
        /*
        完整写法:
        this.findComponentById(ResourceTable.Id_but1);
        this表示本类的对象, 在这里也就是子界面对象
        所以这句话的意思: 就是在子界面中通过id找到对应的组件.
        this可以省略. 所以就变成了下面这种: 实际上是调用了父类的该方法
        返回的是一个组件对象, 是所有组件的父类, 所以需要向下转型
        而ResourceTable是一个资源列表, 再build中, 有一个ResourceTable,中有一个对应的id.
        是static类型, 所以可以通过类名.属性, 得到其地址
         */

        Button but1 = (Button) findComponentById(ResourceTable.Id_but1); // 找到组, 并且强转成为BUT
        text1 = (Text) findComponentById(ResourceTable.Id_text1);

        // 2. 给按钮绑定一个单击事件
        // 此时点击按钮, 就会去执行实现类中的onClick方法
        /*
        点击按钮, 就会执行()中, 类实现的代码
         */
//        but1.setClickedListener(new MyListener()); // 传入一个实现类

        // 使用当前类作为实现类
        // 给but1绑定了单击事件, 当事件被触发之后, 就会执行本类中的onClick方法
        // 这种方式的好处, 是点击按钮后,需要操作其他组件对象, 我们可以使用这个方式
        but1.setClickedListener(this);

        // 匿名内部类方式实现:
        // 弊端: 代码只能使用一次
        but1.setClickedListener(
                new Component.ClickedListener() { // 使用匿名内部类的方式去实现
                    @Override
                    public void onClick(Component component) {
                        Button but = (Button) component;
                        but.setText("被点了-定义本类为实现类");
                        text1.setText("按钮被点击了");
                    }
                }
        );

        // 继续简化: 方法引用:
        // 不用override
        // 前提, 这个方法必须跟接口定义好的相同
        but1.setClickedListener(this::onClick);

        // 开始编写双击按钮的事件:
        // 1. 找到组件
        // 因为我们要修改Text2, 所以这里需要先找到该组件
        Text text2 = (Text) findComponentById(ResourceTable.Id_text2);
        Button but2 = (Button) findComponentById(ResourceTable.Id_but2);
        but2.setDoubleClickedListener(
                new Component.DoubleClickedListener() {
                    @Override
                    public void onDoubleClick(Component component) {
                        // 其中component表示点击组件的对象, component就是点击谁, 谁就传入进来表示谁的对象
                        text2.setText("双击按钮被双击了");
                    }
                }
        );


        // 开始编写长按按钮的事件:
        // 1. 寻找组件
        Text text3 = (Text) findComponentById(ResourceTable.Id_text3);
        Button but3 = (Button) findComponentById(ResourceTable.Id_but3);
        // 2. (绑定)设置长按相应事件
        but3.setLongClickedListener(
                new Component.LongClickedListener() {
                    @Override
                    public void onLongClicked(Component component) {
                        // 修改文本框的内容, 提示我们长按了
                        text3.setText("长按了!!!");
                    }
                }
        );


    }

    @Override
    public void onActive() { // 当点击到这个页面, 时就会被激活.
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) { // 回到前台
        super.onForeground(intent);
    }

    //    @Override
    public void onClick(Component component) {
        // 解释:
        // Component就是组件的意思, 就是所有组件的一个父类
        // 参数: 被点击的组件对象
        // 我们需要向下转型, 使其成为子类对象, 才能使用子类的方法
        Button but = (Button) component;
        but.setText("被点了-定义本类为实现类");
        text1.setText("按钮被点击了");
    }
}

class MyListener implements Component.ClickedListener {

    @Override
    public void onClick(Component component) {
        // 解释:
        // Component就是组件的意思, 就是所有组件的一个父类
        // 参数: 被点击的组件对象
        // 我们需要向下转型, 使其成为子类对象, 才能使用子类的方法
        Button but = (Button) component;
        but.setText("被点了");
    }
}
