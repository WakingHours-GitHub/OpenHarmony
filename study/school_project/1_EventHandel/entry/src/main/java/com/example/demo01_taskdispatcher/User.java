package com.example.demo01_taskdispatcher;


// 创建数据表:

import ohos.data.orm.OrmObject;
import ohos.data.orm.annotation.Entity;
import ohos.data.orm.annotation.Index;
import ohos.data.orm.annotation.PrimaryKey;


@Entity(
        tableName = "user", // 表名
        indices = {@Index(value = {"userId"}, name = "userId_index", unique = true)} // 设置索引, 唯一.
)
public class User extends OrmObject {
    // 设置属性:
    // set automation primary key:
    @PrimaryKey(autoGenerate = true)
    // 字段:
    private Integer userId; // primary key is class type
    private String name;
    private int age;


    // 设置成为: java beans, 生成get和set方法.
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
