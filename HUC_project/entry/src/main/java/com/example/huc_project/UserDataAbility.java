package com.example.huc_project;

import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;
import ohos.data.DatabaseHelper;
import ohos.data.rdb.RdbOpenCallback;
import ohos.data.rdb.RdbStore;
import ohos.data.rdb.StoreConfig;
import ohos.data.resultset.ResultSet;
import ohos.data.rdb.ValuesBucket;
import ohos.data.dataability.DataAbilityPredicates;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.utils.net.Uri;
import ohos.utils.PacMap;

import java.io.FileDescriptor;

/*
用来操作数据库的class
1. 创建DataAbility, 并且在config.json中有说明和关联
2. 在创建好的DataAbility关联(关键, 创建)对应的数据表
3. 重写这些方法. 完成对表的CRUD操作.

使用DataAbility完成数据库访问实现: -> CRUD
    实例: 完成对用户数据的访问: (user_id, user_name, user_Pwd)
    1. 创建一个DataAbility. 保留我们的CRUD四个方法.
    2. 创建关系型数据 -> user表
    3. 重写数据库的操作方法. -> CRUD



 */
// User
public class UserDataAbility extends Ability {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(3, 0xD001100, "Demo");

    // rdbStore就表示数据库连接, 通过此对象就可以完成数据表中的CRUD操作.
    private RdbStore rdbStore;

    // StoreConfig对象,就是关联数据库文件配置
    private StoreConfig config = StoreConfig.newDefaultConfig("userStore"); // 传入一个数据库名称
    // RdbOpenCallback 使用rdbStore对象回调此RdbOpenCallback对象的onCreate()创建数据表.
    private RdbOpenCallback callback = new RdbOpenCallback() {
        // 回调函数
        @Override
        public void onCreate(RdbStore rdbStore) { // 创建数据表

            // 使用rdbStore对象执行SQL创建数据表
            rdbStore.executeSql("CREATE TABLE IF NOT EXISTS users" +
                    "(userId integer primary key autoincrement," + // id是主键.
                    "userName text not null unique," + // 用户名必须是唯一并且非空的
                    "userPwd text not null)"); // 密码是非空的
        }

        @Override
        public void onUpgrade(RdbStore rdbStore, int i, int i1) {

        }
    };


    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        HiLog.info(LABEL_LOG, "UserDataAbility onStart");


        // 初始化与数据库的连接
        DatabaseHelper helper = new DatabaseHelper(this);
        // 需要传入一个config数据, 所以我们在前面初始化,也就是需要连接到那张数据库, 并且还需要传入版本号
        // 然后连接到那张数据库, 那么我们还需要指定连接那张数据表, 也就是使用callback完成创建数据表..
        rdbStore = helper.getRdbStore(config, 1, callback); // 完成初始化


    }

    // 在下面的操作中, 我们可以直接使用rdbStore进行数据库操作.

    /**
     *
     * @param uri
     * @param columns 查询操作需要返回的字段, ["userName", "userPwd"]
     * @param predicates 封装查询条, 相当于ktMapper中, example对象
     * @return
     */
    @Override
    public ResultSet query(Uri uri, String[] columns, DataAbilityPredicates predicates) {

        return null;
    }

    /**
     * 重写insert, 实现用户信息的添加操作.
     * @param uri 表示DataAbility的访问路径, 在config.json中: "dataability:/[/这个地方是我们的设备id]/com.example.huc_project.UserDataAbility/那张表"
     *            uri就是代表别人的访问意图.
     *            // 如果你有两张表的话, 通过最后的表明可以来区分操作哪张表
     *            "dataability:///com.example.huc_project.UserDataAbility/user"
     *            "dataability:///com.example.huc_project.UserDataAbility/info"
     * @param value 传递添加数据的容器, 就表示我们需要操作的数据. 结构就是哪个数据添加到哪个字段.
     * @return
     */
    @Override
    public int insert(Uri uri, ValuesBucket value) {

        int status = -1;
        // ValuesBucket数据结构:
//        ValuesBucket value = new ValuesBucket();
//        value.putString("userName", "zhangsan");
        HiLog.info(LABEL_LOG, "UserDataAbility insert");
        String path = uri.getLastPath(); // 获取最后一个字段, 也就是表名
        // 然后我们需要那这个表名进行判断, 看操作那这个那张表?
        if ("users".equalsIgnoreCase(path)){ // users表
            status = (int) rdbStore.insert("users", value); //传入需要操作的表名和数据

        }

        return status;
    }

    @Override
    public int delete(Uri uri, DataAbilityPredicates predicates) {
        return 0;
    }

    @Override
    public int update(Uri uri, ValuesBucket value, DataAbilityPredicates predicates) {
        return 0;
    }

    // 下面的函数都没什么用处, 只针对fileDataAbility, 只是针对文件操作的方法
    @Override
    public FileDescriptor openFile(Uri uri, String mode) {
        return null;
    }

    @Override
    public String[] getFileTypes(Uri uri, String mimeTypeFilter) {
        return new String[0];
    }

    @Override
    public PacMap call(String method, String arg, PacMap extras) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }
}