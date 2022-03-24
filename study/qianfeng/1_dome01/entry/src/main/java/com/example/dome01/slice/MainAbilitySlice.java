package com.example.dome01.slice;

import com.example.dome01.MainAbility;
import com.example.dome01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.ability.MemoryInfo;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        // 在当前slice中渲染视图组件有两种方式
        // 1. 基于Java代码的渲染
        // 2. 基于xml文件渲染(default) -> setUIContent(xml文件引用)

        // 1. 获取id=btn1的按钮插件
        Button btn1 = (Button) this.findComponentById(ResourceTable.Id_btn1);
/*
        // 2. 设置按钮事件监听
        MainAbilitySlice _this = this;
        // a. 创建事件监听器
        Component.ClickedListener listener = new Component.ClickedListener() {
            @Override // 直接重载
            public void onClick(Component component) {
                // component就是表示监听的组件
                if (component == btn1){
//                    System.out.println("---------->>>AAA");
                    // 跳转到我们的slice2,
                    // 官方的跳转方式:
                    _this.present(new MainAbilitySlice2(), new Intent());

                }
            }
        };
        // b. 设置组件的事件监听
        btn1.setClickedListener(listener);
        */

        // 官方的跳转方式:
        // 这里的this就是AbilitySlice
        // 表示, 从this指代的当前的slice调转到其他的slice
//        this.present(new MainAbilitySlice2(), new Intent());
        // initent表示意图, 就是消息传递的载体
        // 但是在Listener中, 访问不到MainAbilitySlice, 所以我们需要提前获取

        // 我们不就是要点击按钮进行一个操作吗
        // 有请我们的lambda表达式
        /*btn1.setClickedListener(
                listener->{
                // 此时这里面的this就还是表示MainAbility
                    this.present(new MainAbilitySlice2(), new Intent());
                }
        );*/
        // 更简单:
        // 官方提示:
        btn1.setClickedListener(listener->this.present(new MainAbilitySlice2(), new Intent()));
/*
        // 实际上: 更好理解:
        //
        MainAbilitySlice _this = this;
        btn1.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        // 这里的参数是component
                        _this.present(new MainAbilitySlice2(), new Intent());


                    }
                }
        );
        // 等价于下面的:
        // 使用lambda表达式, 这个实现函数, 就相当于是在这个类里面实现的, 所以可以直接使用this
        btn1.setClickedListener(component -> {
            this.present(new MainAbilitySlice2(), new Intent());
        }); // 花括号里只有一句, 则可以省略

 */
        btn1.setClickedListener(component ->{
            // 在跳转之前的Slice将需要传递的数据设置到Intent对象中.
            Intent intent1 = new Intent();
            // 注意在setParm()的重载方式中, 不能能够传递对象(object), 所以json是一个不错的选择,
            intent1.setParam("productId", "101"); // 就是key: value的形式
            this.present(new MainAbilitySlice2(), intent1);
        });
    /*
    lambda 表达式的语法格式如下：

    (parameters) -> expression
    或
    (parameters) ->{ statements; }
    使用式接口:
        public interface ClickedListener {
            void onClick(Component var1);
        }

     */
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
