package com.bjpowernode.crm.workbench.dao;

import com.bjpowernode.crm.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

public interface ClueDao {

    int save(Clue c);

    int getTotalByCondition(Map<String, Object> map);

    List<Clue> getActivityListByCondition(Map<String, Object> map);

    Clue detail(String id);

    int deleteClue(String[] ids);


    Clue getClueById(String id);

    int updateClue(Clue clue);
}
