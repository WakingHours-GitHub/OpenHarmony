package com.example.demo01_taskdispatcher.utils;
import com.example.demo01_taskdispatcher.DBConfig;
import com.example.demo01_taskdispatcher.DataBase;
import com.example.demo01_taskdispatcher.Log;
import com.example.demo01_taskdispatcher.User;
//import com.sudojava.databaseorm.bean.User;
//import com.sudojava.databaseorm.db.DBConfig;
//import com.sudojava.databaseorm.db.UserStore;
import ohos.app.Context;
import ohos.data.orm.OrmContext;
import ohos.data.orm.OrmPredicates;
import ohos.data.rdb.RdbPredicates;
import ohos.data.rdb.ValuesBucket;
import ohos.data.resultset.ResultSet;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import java.util.ArrayList;
import java.util.List;

public class UserManger {
    private final static HiLogLabel HI_LOG_LABEL =
            new HiLogLabel(HiLog.LOG_APP,0x00201,"UserManager");
    private Context context;
    private DBConfig config;
    private OrmContext ormContext;
    public UserManger(Context context){
        config = new DBConfig(context);
        ormContext = config.getConnectionContext(DataBase.class);

    }


    public List<User> queryUser(String name){
        List<User> list = new ArrayList<>();
        OrmPredicates predicates =
                ormContext.where(User.class).equalTo("name",name);
        list = ormContext.query(predicates);
//        Log.info("UserManager:"+list);
//        HiLog.info(HI_LOG_LABEL,);
        return list;
//dbOrmContext.flush(context);
    }



    public User queryOne(Integer userId){
        OrmPredicates predicates =
                ormContext.where(User.class).equalTo("userId",userId);
        User user = new User();
        ResultSet resultSet = ormContext.query(predicates,new String[]
                {"userId","firstName","lastName","age","balance"});
        if (resultSet.goToNextRow()){
            user.setUserId(resultSet.getInt(resultSet.getColumnIndexForName("userId")));
            user.setAge(resultSet.getInt(resultSet.getColumnIndexForName("age")));
            user.setName(resultSet.getString(resultSet.getColumnIndexForName("Name")));

        }
        return user;
    }
    /**
     *
     * @param userId
     * @return
     */
    public boolean deleteUser(Integer userId){
        OrmPredicates predicates =
                ormContext.where(User.class).equalTo("userId",userId);
        int flag = ormContext.delete(predicates);
        HiLog.info(HI_LOG_LABEL,"UserManager:"+flag);
//        第七步：编写MainAbilitySlice类
        return flag>0?true:false;
    }
    /**
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user){
        boolean flag = ormContext.insert(user);
        HiLog.info(HI_LOG_LABEL,"UserManager:"+flag);
        return ormContext.flush();
    }
}

//public class UserManager {

//
//}
