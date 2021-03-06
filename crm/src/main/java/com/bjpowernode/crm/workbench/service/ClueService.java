package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.vo.PaginationVO;
import com.bjpowernode.crm.workbench.domain.Clue;
import com.bjpowernode.crm.workbench.domain.ClueRemark;
import com.bjpowernode.crm.workbench.domain.Tran;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ClueService {
    boolean save(Clue c);

    PaginationVO<Clue> pageList(Map<String, Object> map);

    Clue detail(String id);

    boolean unbund(String id);

    boolean bund(String cid, String[] aids);

    boolean deleteClue(String[] ids);

    Map<String, Object> getUerListAndClue(String id);

    boolean updateClue(Clue clue);

    List<ClueRemark> getRemarkListByClueId(String clueId);

    boolean saveRemark(ClueRemark clueRemark);

    boolean updateRemark(ClueRemark clueRemark);

    boolean deleteRemark(String id);

    boolean convert(String clueId, Tran t, String createBy);
}
