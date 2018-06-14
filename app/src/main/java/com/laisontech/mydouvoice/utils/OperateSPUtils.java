package com.laisontech.mydouvoice.utils;

import com.laisontech.mydouvoice.App;

/**
 * ..................................................................
 * .         The Buddha said: I guarantee you have no bug!          .
 * .                                                                .
 * .                            _ooOoo_                             .
 * .                           o8888888o                            .
 * .                           88" . "88                            .
 * .                           (| -_- |)                            .
 * .                            O\ = /O                             .
 * .                        ____/`---'\____                         .
 * .                      .   ' \\| |// `.                          .
 * .                       / \\||| : |||// \                        .
 * .                     / _||||| -:- |||||- \                      .
 * .                       | | \\\ - /// | |                        .
 * .                     | \_| ''\---/'' | |                        .
 * .                      \ .-\__ `-` ___/-. /                      .
 * .                   ___`. .' /--.--\ `. . __                     .
 * .                ."" '< `.___\_<|>_/___.' >'"".                  .
 * .               | | : `- \`.;`\ _ /`;.`/ - ` : | |               .
 * .                 \ \ `-. \_ __\ /__ _/ .-` / /                  .
 * .         ======`-.____`-.___\_____/___.-`____.-'======          .
 * .                            `=---='                             .
 * ..................................................................
 * Created by SDP on 2018/6/14.
 */

public class OperateSPUtils {
    /**
     * 当数据库中需要增加表单时，对此时的数据库的版本号进行保存
     */
    public static void saveDatabaseVersion(String databaseTableName, int version) {
        App.getApp().getSp().edit().putInt(databaseTableName, version).apply();
    }

    /**
     * 获取增加新的数据库表单时的版本号
     */
    public static int getDaveDatabaseVersion(String databaseTableName) {
        return App.getApp().getSp().getInt(databaseTableName, 0);
    }
}
