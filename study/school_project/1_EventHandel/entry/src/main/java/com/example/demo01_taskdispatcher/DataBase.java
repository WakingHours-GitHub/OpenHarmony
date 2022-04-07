package com.example.demo01_taskdispatcher;

import ohos.data.orm.OrmDatabase;
import ohos.data.orm.annotation.Database;


// 这里我们声明一个数据库
// 我们不需要实现getVersion和getHelper方法, 直接将类设为abstract
@Database(entities = {User.class}, version=1)
public abstract class DataBase extends OrmDatabase {
}
