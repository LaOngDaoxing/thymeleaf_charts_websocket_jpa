package com.ljx.tcwj1.service.impl;

import com.ljx.tcwj1.pojo.doo.TakeawayOrderDO;
import com.ljx.tcwj1.pojo.dto.TakeawayOrderChartDTO;
import com.ljx.tcwj1.repository.TakeawayOrderRepository;
import com.ljx.tcwj1.service.TakeawayOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.beans.Transient;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @author ljx
 * @Description:
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/8/10 0010 下午 5:18
 */
@Service
public class TakeawayOrderServiceImpl implements TakeawayOrderService {

    @Resource
    TakeawayOrderRepository takeawayOrderRepository;

    @Override
    public List<TakeawayOrderDO> selectUserList() {
        return takeawayOrderRepository.findAll();
    }

    @Transient
    @Override
    public TakeawayOrderDO addTakeawayOrderDO(TakeawayOrderDO takeawayOrderDO) {
        return takeawayOrderRepository.save(takeawayOrderDO);
    }

    @Override
    public List<Map<String, Object>> selectSexNumListByUserId(String userId) {
        return takeawayOrderRepository.selectSexNumListByUserId(userId);
    }
    @Override
    public TakeawayOrderChartDTO dealTakeawayOrderChartDTOByUserId(String userId) {
        TakeawayOrderChartDTO takeawayOrderChartDTO = new TakeawayOrderChartDTO();
        List<Map<String, Object>> mapList = selectSexNumListByUserId(userId);
        if (null != mapList && mapList.size() > 0) {
            mapList.forEach((Map<String, Object> map) -> {
                int switchI=Integer.valueOf(map.get("platformId").toString()) ;
                switch (switchI) {
                    case 1:
                        takeawayOrderChartDTO.setEleMe((long) map.get("platformIdNum"));
                        break;
                    case 2:
                        takeawayOrderChartDTO.setKouBei((long) map.get("platformIdNum"));
                        break;
                    default:
                        takeawayOrderChartDTO.setMeiTuan((long) map.get("platformIdNum"));
                        break;
                }
            });
        }
        return takeawayOrderChartDTO;
    }

    @Override
    public List<Map<String, Object>> selectSexNumListByParams(Map<String, Object> params) {
        String userId= (String)params.get("userId");
        String param1=(String)params.get("param1");
        List<Map<String, Object>> list=takeawayOrderRepository.selectSexNumListByParams(userId,param1);
        return list;
    }
    @Override
    public TakeawayOrderChartDTO dealTakeawayOrderChartDTOByParams(Map<String, Object> params) {
        TakeawayOrderChartDTO TakeawayOrderChartDTO = new TakeawayOrderChartDTO();
        List<Map<String, Object>> mapList = selectSexNumListByParams(params);
        if (null != mapList && mapList.size() > 0) {
            mapList.forEach((Map<String, Object> map) -> {
                int switchI=Integer.valueOf(map.get("platformId").toString()) ;
                switch (switchI) {
                    case 1:
                        TakeawayOrderChartDTO.setEleMe(((BigInteger)map.get("platformIdNum")).longValue());
                        break;
                    case 2:
                        TakeawayOrderChartDTO.setKouBei(((BigInteger)map.get("platformIdNum")).longValue());
                        break;
                    default:
                        TakeawayOrderChartDTO.setMeiTuan(((BigInteger)map.get("platformIdNum")).longValue());
                        break;
                }
            });
        }
        return TakeawayOrderChartDTO;
    }
}
