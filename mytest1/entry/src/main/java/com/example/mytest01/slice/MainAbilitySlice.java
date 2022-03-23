package com.example.mytest01.slice;

import com.example.mytest01.MyTask;
import com.example.mytest01.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.Revocable;
import ohos.app.dispatcher.task.TaskPriority;

public class MainAbilitySlice extends AbilitySlice implements Component.ClickedListener {
    Button but; // 设置为类的成员, 方便后面事件的使用
    Revocable job_1;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main); // 加载xml文件

        // 为按钮添加事件
        // 1. 找到按钮id:
         but = (Button) findComponentById(ResourceTable.Id_but1);

        // 2. 给按钮添加一个点击事件:
        // 如果我们没有添加点击事件, 那么鼠标点击按钮之后是没有任何反映的.
        // 只有给按钮添加了点击事件, 当点击按钮时, 就会执行相应的代码
//        but.setClickedListener(this);  // 因为上面已经实现了这个ClickedListener的接口, 所以这里直接使用this
        // 然后后面我们可以实现这个onClick()函数, 在里面放上点击该按钮后的执行的操作
        // 也就是, 当我们鼠标点击了btu这个按钮后, 就执行本类中的onClick() 方法

        // 使用匿名内部类, 直接实现onClick()方法
        but.setClickedListener(
                new Component.ClickedListener() {
                    @Override
                    public void onClick(Component component) {
                        if (component == but){ // 检测按钮
                            Intent i = new Intent(); // 意图对象
                            Operation op = new Intent.OperationBuilder()
                                    .withDeviceId("")
                                    .withBundleName("com.example.mytest01")
                                    .withAbilityName("con.example.mytest01.SecondAbility")
                                    .build(); // 聚合
                            i.setOperation(op); // 添加操作
                            startAbility(i); // 开启意图

                        }
                    }
                }
        );
        but.setClickedListener(this);

        // 开启我们的线程协调员
        TaskDispatcher globalTaskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT); // 返回全局县城协调器
        // 异步派发到别的线程上了.
         // 返回的是一个状态?
        job_1 = globalTaskDispatcher.asyncDispatch(new MyTask("任务1"));
        // Revocable是线程管理的,

        // 同步派发, 指的是主线程分配了一个协调员去执行这个任务, 并不是在主线程中执行
        // 但是主线程需要等待该线程执行完, 再继续执行.
        globalTaskDispatcher.syncDispatch(new MyTask("任务_2")); // 但是没有返回值
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }


    // 我们需要当执行
    @Override
    protected void onStop() {
        super.onStop();
        // 如果任务已经执行, 那么revoke就失效了
        // 如果任务已经在执行中或执行完成，则会返回取消失败。
        job_1.revoke(); //


    }

    // 实现这个方法
    @Override
    public void onClick(Component component) {
        // 点击btu后的所要执行的操作 -> 跳转页面
        if (component == but) { // 只有当点击了btu这个按钮后, 才进行跳转
            // 跳转到哪个页面中(意图)
            Intent i = new Intent(); // 意图对象
            // 包含了要跳转的页面信息: -> 寻找页面, 也是一个对象, 在Operation里面
            Operation operation = new Intent.OperationBuilder()
                    .withDeviceId("") // 要跳转到那个设备上, 如果传递一个没有内容的字符串, 则表示跳转本机
                    .withBundleName("com.example.mytest01") // 要跳转到那个应用上, 参数: 包名(可以从config中找)
                    .withAbilityName("com.example.mytest01.SecondAbility") // 要跳转的页面
                    .build(); // 表示上面的信息打包
            // 把打包之后的op设置到意图当中
            i.setOperation(operation);
            // 实现跳转页面, 也就是上述定义好的意图:
            startAbility(i); // 开启这个意图


        }
    }

}
