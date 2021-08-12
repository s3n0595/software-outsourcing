package com.cykj.service.impl;

import com.cykj.bean.TradeWork;
import com.cykj.bean.Works;
import com.cykj.mapper.PortalMapper;
import com.cykj.service.PortalService;
import com.cykj.vo.SuccessfulCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author guoquansen
 * @date 2021/8/12 10:47 上午
 */
@Service
public class PortalServiceImpl implements PortalService {

    @Autowired
    private PortalMapper portalMapper;


    @Override
    public List<TradeWork> queryAllSuccessCase() {
        return portalMapper.queryAllSuccessCase();
    }

    @Override
    public int showCase(int tradeWorksId, int showCase) {
        return portalMapper.showCase(tradeWorksId, showCase);
    }

    @Override
    public List<TradeWork> queryIsShow(int showCase) {
        return portalMapper.queryIsShow(showCase);
    }

    @Override
    public int deleteCase(int tradeWorksId) {
        return portalMapper.deleteCase(tradeWorksId);
    }

    @Override
    public List<Works> queryWorksIdByTitle(String worksTitle) {
        return portalMapper.queryWorksIdByTitle(worksTitle);
    }

    @Override
    public TradeWork querySearchById(int worksId) {
        return portalMapper.querySearchById(worksId);
    }

}
