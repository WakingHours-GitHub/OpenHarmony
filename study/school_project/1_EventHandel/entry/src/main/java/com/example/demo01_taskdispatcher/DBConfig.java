package com.example.demo01_taskdispatcher;


import ohos.app.Context;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmMigration;
import ohos.data.rdb.RdbOpenCallback;
import ohos.data.rdb.RdbStore;
import ohos.data.rdb.StoreConfig;
import ohos.data.resultset.ResultSetHook;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

/**
 *  配置数据库的访问类
 */

public class DBConfig {
    private final static HiLogLabel HI_LOG_LABEL =
            new HiLogLabel(HiLog.LOG_APP,0x00201,"MainAbilitySlice");
    private String database_name="mydb.db";
    private String database_name_alias="mydb";
    private DatabaseHelper helper;
    private OrmContext ormContext;
    private StoreConfig config;
    private RdbStore store;
    public DBConfig(Context context) {
        helper = new DatabaseHelper(context);
    }
    /**
     *
     * @param ObjectStore
     * @return
     */
    public OrmContext getConnectionContext(Class ObjectStore){
        this.ormContext =
                helper.getOrmContext(database_name_alias,database_name,ObjectStore,new
                        OrmMigration[0]);
//        第六步：创建一个User对应的数据库管理类
        return this.ormContext;
    }
    /**
     * 创建建表SQL语句
     * @param sql
     * @return
     */
    public RdbStore getConnectionStoreWithSql(final String sql){
        this.config = StoreConfig.newDefaultConfig(this.database_name);
        RdbOpenCallback callback = new RdbOpenCallback() {
            @Override
            public void onCreate(RdbStore rdbStore) {
                rdbStore.executeSql(sql);
            }
            @Override
            public void onUpgrade(RdbStore rdbStore, int i, int i1) {
            }
        };
        this.store = helper.getRdbStore(this.config,1,callback,
                (ResultSetHook)null);
        return this.store;
    }
}
