package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.ClueRemark;

import java.util.List;

public interface ClueRemarkDao {

    int getCountByAids(String[] ids);

    int deleteByAids(String[] ids);

    List<ClueRemark> getRemarkListByClueId(String clueId);

    int saveRemark(ClueRemark clueRemark);

    int updateRemark(ClueRemark clueRemark);

    int deleteRemark(String id);
}
