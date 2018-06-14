package com.laisontech.mydouvoice.dbhelper;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

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

public class LikeButtonInfoDao implements IDBDao<LikeButtonInfo> {
    private Dao<LikeButtonInfo, Integer> mDao;

    public LikeButtonInfoDao(Context context) {
        DBHelper helper = DBHelper.getInstance(context);
        try {
            mDao = helper.getDao(LikeButtonInfo.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insert(LikeButtonInfo likeButtonInfo) {
        try {
            mDao.create(likeButtonInfo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrUpdate(LikeButtonInfo likeButtonInfo) {
        if (likeButtonInfo == null) return;
        try {
            List<LikeButtonInfo> query = mDao.queryBuilder()
                    .where()
                    .eq("url", likeButtonInfo.getUrl())
                    .query();
            if (query == null || query.size() < 1) {
                insert(likeButtonInfo);
            } else {
                LikeButtonInfo saveInfo = query.get(0);
                saveInfo.setSelect(likeButtonInfo.isSelect());
                saveInfo.setSaveDate(System.currentTimeMillis());
                mDao.update(saveInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveLikeButtonSelect(String url, boolean select) {
        try {
            List<LikeButtonInfo> query = mDao.queryBuilder()
                    .where()
                    .eq("url", url)
                    .query();
            if (query == null || query.size() < 1) {
                LikeButtonInfo likeButtonInfo = new LikeButtonInfo();
                likeButtonInfo.setSelect(select);
                likeButtonInfo.setSaveDate(System.currentTimeMillis());
                likeButtonInfo.setUrl(url);
                insert(likeButtonInfo);
            } else {
                LikeButtonInfo saveInfo = query.get(0);
                saveInfo.setSelect(select);
                saveInfo.setSaveDate(System.currentTimeMillis());
                mDao.update(saveInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void saveLikeButtonSelect(int videoId, boolean select) {
        try {
            List<LikeButtonInfo> query = mDao.queryBuilder()
                    .where()
                    .eq("videoId", videoId)
                    .query();
            if (query == null || query.size() < 1) {
                LikeButtonInfo likeButtonInfo = new LikeButtonInfo();
                likeButtonInfo.setSelect(select);
                likeButtonInfo.setSaveDate(System.currentTimeMillis());
                likeButtonInfo.setVideoId(videoId);
                insert(likeButtonInfo);
            } else {
                LikeButtonInfo saveInfo = query.get(0);
                saveInfo.setSelect(select);
                saveInfo.setSaveDate(System.currentTimeMillis());
                mDao.update(saveInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean getLikeButtonSelect(String url) {
        try {
            List<LikeButtonInfo> query = mDao.queryBuilder()
                    .where()
                    .eq("url", url)
                    .query();
            return !(query == null || query.size() < 1) && query.get(0).isSelect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getLikeButtonSelect(int videoId) {
        try {
            List<LikeButtonInfo> query = mDao.queryBuilder()
                    .where()
                    .eq("videoId", videoId)
                    .query();
            return !(query == null || query.size() < 1) && query.get(0).isSelect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateById(LikeButtonInfo likeButtonInfo, Integer id) {

    }

    @Override
    public void delete(LikeButtonInfo likeButtonInfo) {

    }

    @Override
    public void deleteList(List<LikeButtonInfo> list) {

    }

    @Override
    public void deleteListData(List<LikeButtonInfo> list) {

    }

    @Override
    public List<LikeButtonInfo> listAll() {
        return null;
    }

    @Override
    public List<LikeButtonInfo> listFuzzyAll(String tex) {
        return null;
    }
}
