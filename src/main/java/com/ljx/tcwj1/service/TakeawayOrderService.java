package com.ljx.tcwj1.service;

import com.ljx.tcwj1.pojo.doo.TakeawayOrderDO;
import com.ljx.tcwj1.pojo.dto.TakeawayOrderChartDTO;

import java.util.List;
import java.util.Map;

/**
 * @author ljx
 * @Description: TODO()
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/8/10 0010 下午 5:17
 */
public interface TakeawayOrderService {
    /**
     * 列表查询用户表
     * @return
     */
    List<TakeawayOrderDO> selectUserList();

    /**
     * 新增外卖订单表一条数据
     * @param takeawayOrderDO
     * @return
     */
    TakeawayOrderDO addTakeawayOrderDO(TakeawayOrderDO takeawayOrderDO);

    /**
     * 根据userId，列表查询用户表
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectSexNumListByUserId(String userId);

    /**
     * 根据userId，查询用户表数据；并处理数据
     * @param userId
     * @return
     */
    TakeawayOrderChartDTO dealTakeawayOrderChartDTOByUserId(String userId);

    /**
     * 根据查询条件，查询用户表数据
     * @param params
     * @return
     */
    List<Map<String, Object>> selectSexNumListByParams(Map<String, Object> params);

    /**
     * 根据g查询条件，查询用户表数据；并处理数据
     * @param params
     * @return
     */
    TakeawayOrderChartDTO dealTakeawayOrderChartDTOByParams(Map<String, Object> params);
}
