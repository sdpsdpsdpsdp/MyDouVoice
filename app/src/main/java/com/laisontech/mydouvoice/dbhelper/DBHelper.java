package com.laisontech.mydouvoice.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.laisontech.mydouvoice.utils.OperateSPUtils;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SDP on 2017/5/5.
 * 记录：所以在有新的版本时，则直接对数据进行移植即可，表单的增加，需要数据库版本进行更改，但是如果对某个表单增加了列，或者删除了列，则需要进行数据库升级。
 * 2018 5 8日：版本1 各种数据库信息
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {
    //数据库名称 从网络端下载的表计信息
    private static final String DB_NAME = "douyin.db";
    //版本号，从20开始，每次数据库增加列则加1即可
    private static final int DB_VERSION = 2;

    private Map<String, Dao> maps = new HashMap<>();
    //使用单利进行数据的访问
    private static DBHelper instance;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public static synchronized DBHelper getInstance(Context context) {
        if (instance == null) {
            synchronized (DBHelper.class) {
                if (instance == null) {
                    instance = new DBHelper(context);
                }
            }
        }
        return instance;
    }

    //获取
    @SuppressWarnings(value = {"unchecked"})
    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();
        if (maps.containsKey(className)) {
            dao = maps.get(className);
        }
        if (dao == null) {
            dao = (Dao) super.getDao(clazz);
            maps.put(className, dao);
        }
        return dao;
    }

    //onCreate只会创建一回
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        createOrUpdateDB(-1, "likeButtonInfo", LikeButtonInfo.class, sqLiteDatabase, connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.e(DBHelper.class.getName(), "DatabaseHelper onUpgrade() oldVersion:" + oldVersion + ",newVersion:" + newVersion);
        //旧的版本小于当前的版本，则将旧版本的数据迁移到新的版本里面,有字段增加的将false改为true即可。
        if (oldVersion < DB_VERSION) {
            createOrUpdateDB(newVersion, "likeButtonInfo", LikeButtonInfo.class, sqLiteDatabase, connectionSource);
        }
    }

    private void createOrUpdateDB(int newVersion, String keySave, Class<?> clz, SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            if (OperateSPUtils.getDaveDatabaseVersion(keySave) == 0) {
                TableUtils.createTable(connectionSource, clz);
                OperateSPUtils.saveDatabaseVersion(keySave, DB_VERSION);
            } else {
                //表单对应的数据库的版本不为0，并且保存的版本小于新版本，则进行数据迁移
                if (OperateSPUtils.getDaveDatabaseVersion(keySave) < newVersion) {
                    DatabaseUtil.upgradeTable(sqLiteDatabase, connectionSource, clz, DatabaseUtil.OperationType.ADD);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //关闭所有操作
    @Override
    public void close() {
        super.close();
        for (String key : maps.keySet()) {
            Dao dao = maps.get(key);
            dao = null;
        }
        instance = null;
    }
}
