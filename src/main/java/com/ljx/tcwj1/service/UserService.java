package com.ljx.tcwj1.service;

import com.ljx.tcwj1.pojo.dto.UserChartDTO;
import com.ljx.tcwj1.pojo.doo.UserDO;

import java.util.List;
import java.util.Map;
/**
 *
* @Description:
* @FR功能需求：
* @ImportJar:
* @ApiGrammer规则：
* @Remark:
* @AlibabaCodeStatuteScanError：
* @CodeBug解决:
* @Debug调试：
* @date
* @author  ljx
*
 */
public interface UserService {
    /**
     * 列表查询用户表
     * @return
     */
    List<UserDO> selectUserList();

    /**
     * 新增用户表一条数据
     * @param user
     * @return
     */
    UserDO addUser(UserDO user);

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
    UserChartDTO dealUserChartDtoByUserId(String userId);

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
    UserChartDTO dealUserChartDtoByParams(Map<String, Object> params);
}
