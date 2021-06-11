package com.bjpowernode.crm.workbench.service.impl;


import com.bjpowernode.crm.settings.dao.UserDao;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.utils.SqlSessionUtil;
import com.bjpowernode.crm.utils.UUIDUtil;
import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.dao.*;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.ClueActivityRelation;
import com.bjpowernode.crm.workbench.domain.ClueRemark;
import com.bjpowernode.crm.workbench.service.ClueService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClueServiceImpl implements ClueService {

    private ClueDao clueDao = SqlSessionUtil.getSqlSession().getMapper(ClueDao.class);
    private ClueActivityRelationDao clueActivityRelationDao = SqlSessionUtil.getSqlSession().getMapper(ClueActivityRelationDao.class);
    private ClueRemarkDao clueRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ClueRemarkDao.class);
    private UserDao userDao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

    private CustomerDao customerDao = SqlSessionUtil.getSqlSession().getMapper(CustomerDao.class);
    private CustomerRemarkDao customerRemarkDao = SqlSessionUtil.getSqlSession().getMapper(CustomerRemarkDao.class);

    private ContactsDao contactsDao = SqlSessionUtil.getSqlSession().getMapper(ContactsDao.class);
    private ContactsRemarkDao contactsRemarkDao = SqlSessionUtil.getSqlSession().getMapper(ContactsRemarkDao.class);


    public boolean save(Clue c) {

        boolean flag = true;

        int count = clueDao.save(c);

        if (count !=1){
            flag = false;
        }
        return flag;
    }

    public PaginationVO<Clue> pageList(Map<String, Object> map) {
        int total = clueDao.getTotalByCondition(map);

        //取得dataList
        List<Clue> dataList = clueDao.getActivityListByCondition(map);

        //创建一个vo对象，将total和dataList封装到vo中
        PaginationVO<Clue> vo = new PaginationVO<Clue>();
        vo.setTotal(total);
        vo.setDataList(dataList);

        //将vo返回
        return vo;
    }

    public Clue detail(String id) {

        Clue c = clueDao.detail(id);

        return c;
    }

    public boolean unbund(String id) {

        boolean flag = true;

        int count = clueActivityRelationDao.unbund(id);

        if (count!=1){
            flag = false;
        }

        return flag;
    }

    public boolean bund(String cid, String[] aids) {

        boolean flag = true;

        for (String aid : aids) {

            //取得每一个aid和cid做关联
            ClueActivityRelation car = new ClueActivityRelation();
            car.setId(UUIDUtil.getUUID());
            car.setActivityId(aid);
            car.setClueId(cid);

            //添加关联关系表中的记录
            int count = clueActivityRelationDao.bund(car);
            if (count!=1){
                flag = false;
            }

        }

        return flag;
    }

    public boolean convert() {

        boolean flag = true;

        return flag;
    }

    public boolean deleteClue(String[] ids) {
        boolean flag = true;

        //删除备注
        int count1 = clueRemarkDao.getCountByAids(ids);

        int count2 = clueRemarkDao.deleteByAids(ids);

        if (count1 != count2){
            flag = false;
        }

        //删除关联
        int count3 = clueActivityRelationDao.getCountByAids(ids);

        int count4 = clueActivityRelationDao.deleteByAids(ids);

        if (count3 != count4){
            flag = false;
        }

        //删除线索
        int count5 = clueDao.deleteClue(ids);

        if (count5 != 1){
            flag = false;
        }

        return flag;
    }

    public Map<String, Object> getUerListAndClue(String id) {

        List<User> uList = userDao.getUserList();

        Clue c = clueDao.getClueById(id);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("uList",uList);
        map.put("c",c);

        return map;
    }

    public boolean updateClue(Clue clue) {

        boolean flag = true;

        int count = clueDao.updateClue(clue);

        if (count != 1){
            flag = false;
        }

        return flag;
    }
}
