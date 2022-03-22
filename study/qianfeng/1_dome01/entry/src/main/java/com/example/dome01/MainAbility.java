package com.example.dome01;

import com.example.dome01.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.agp.render.render3d.Task;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;

/*
MainAbility只是一个页面的容器
里面放的是我们通过前端语言所呈现的内容.

 */


public class MainAbility extends Ability {


    /*
    当系统首次创建当前PageAbility实例时, 自动调用onStart方法,
    也就是说对于一个page而言, onStart方法只会执行一次

    在激活(Active)之前, 加载页面视图.

     */
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(MainAbilitySlice.class.getName());
        System.out.println("-------->>>>onStart");


    }

    /*
    房前page进入到手机前台(Active状态), 则会触发onActive方法
    获取焦点时, 就会触发onActive方法
    就是载入手机的过程.
     */

    @Override
    protected void onActive() {
        super.onActive();
        System.out.println("-------->>>>onActive");

    }

    /*
    当前PageAbility失去焦点, 会触发onInaction方法.
    程序进入后台,只是失去焦点,的一种方式
     */
    @Override
    protected void onInactive() {
        super.onInactive();
        System.out.println("-------->>>>onInactive");


    }

    /*
    当前PageAbility切换到后台, 不可见时, 会触发onBackground执行

     */
    @Override
    protected void onBackground() {
        super.onBackground();
        System.out.println("-------->>>>onBackground");

    }

    /*
    当前PageAbility从后台切换到前台(页面没有被销毁, 使用home键, 将程序放到后台), 从不可见 -> 可见,会触发onForeground执行
     */
    @Override
    protected void onForeground(Intent intent) {
        super.onForeground(intent);
        System.out.println("-------->>>>onForeground");

    }
    /*
    当前PageAbility被销毁时, 触发onStop执行.
    只用清空后台, 或者后退(退出)程序, 就会执行我们的onStop方法.
     */
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("-------->>>>onStop");

    }
}








/*


        // 疯狂匿名实现Thread并且直接开启线程.
        new Thread( // .start直接开启
                new Runnable() { // 重载接口中的方法
                    @Override
                    public void run() {

                    }
                }
        ).start();


        鸿蒙的方式:
        // 获取全局任务分配程序
        TaskDispatcher taskDispatcher = getGlobalTaskDispatcher(TaskPriority.DEFAULT);
        taskDispatcher.asyncDispatch(
                new Runnable() {
                    @Override
                    public void run() {

                    }
                }
        );

 */

