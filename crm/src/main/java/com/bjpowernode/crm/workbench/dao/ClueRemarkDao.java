package com.bjpowernode.crm.workbench.dao;

public interface ClueRemarkDao {

    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);
}
