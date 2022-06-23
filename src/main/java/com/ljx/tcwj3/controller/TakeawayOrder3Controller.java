package com.ljx.tcwj3.controller;

import com.alibaba.fastjson.JSON;
import com.ljx.tcwj1.pojo.doo.TakeawayOrderDO;
import com.ljx.tcwj1.pojo.dto.TakeawayOrderChartDTO;
import com.ljx.tcwj1.service.TakeawayOrderService;
import com.ljx.tcwj2.websocket.TakeawayOrderWebSocket2Server;
import com.ljx.tcwjneln._02variables._05initVar._02Init_Str_JsonStr;
import com.ljx.tcwjneln._09util.maputil.MapGetter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ljx
 * @Description: 外卖订单管理
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
由于websocket服务端接口和普通后台接口写在同一个Controller中；注意以下对应的前台请求路径写法有区别，具体区别自行调试
接口路径写法1、
 @RestController("/takeawayOrder3")
 public class TakeawayOrderController{
 @GetMapping("/{userId}/queryByUserId")
 @ResponseBody
 }
 接口路径写法2、
 @RestController()
 public class TakeawayOrderController{
 @GetMapping("/takeawayOrder3/{userId}/queryByUserId")
 @ResponseBody
 }
  * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/8/10 0010 下午 5:08
 */
@RestController()
@RequestMapping("/takeawayOrder3")
public class TakeawayOrder3Controller {

    @Resource
    TakeawayOrderService takeawayOrderService;
    /**
     * webUrl测试
     * Description: 根据用户id，列表查询用户表
     * @Param userId     用户id
     * @Param params     前台页面查询条件
     * @return:
     * Author: ljx
     * Date: 2021/3/25 0025 下午 4:32
     */
    @GetMapping("/{userId}/queryByUserId")
    @ResponseBody
    public TakeawayOrderChartDTO selectTakeawayOrderChartDTOByUserId(@PathVariable String userId) {
        TakeawayOrderChartDTO takeawayOrderChartDTO =takeawayOrderService.dealTakeawayOrderChartDTOByUserId(userId);
        return takeawayOrderChartDTO;
    }
    /**
     * webUrll测试http://localhost:8090/addAndQueryByUserId
     * Description: 新增一条用户数据；并返回拼装的用户数据（根据用户id，列表查询用户表）
     *
     * @Param user:
     * @return:
     * Author: ljx
     * Date: 2021/3/24 0024 上午 10:14
     */
    @PostMapping("/addAndQueryByUserId")
    @ResponseBody
    public TakeawayOrderDO addAndQueryByUserId(TakeawayOrderDO takeawayOrderDO) {
        // 生成一个32位的随机字符串：36位UUID，去掉“-”
        takeawayOrderDO.setTakeawayOrderId(_02Init_Str_JsonStr.initUuidLen32());
        // 保存数据
        TakeawayOrderDO result = takeawayOrderService.addTakeawayOrderDO(takeawayOrderDO);
        // 拼装数据DTO通知前端
        TakeawayOrderChartDTO takeawayOrderChartDTO = takeawayOrderService.dealTakeawayOrderChartDTOByUserId(takeawayOrderDO.getUserId());
        // 通知前端，向前台发送消息
        TakeawayOrderWebSocket2Server.sendMessageToWebsocketJs(takeawayOrderDO.getUserId(), JSON.toJSONString(takeawayOrderChartDTO));
        return result;
    }
    /**
     * Title: selectTakeawayOrderChartDTOByParams
     * Description: 根据用户id、前台页面条件，列表查询用户表
     * @Param userId:
     * @Param params:
     * @return:
     * Author: ljx
     * Date: 2021/3/25 0025 下午 5:34
     */
    @GetMapping("/{userId}/queryByParams")
    @ResponseBody
    public TakeawayOrderChartDTO selectTakeawayOrderChartDTOByParams(@PathVariable String userId,@RequestParam Map<String,Object> params) {
        params.put("userId",userId);
        return takeawayOrderService.dealTakeawayOrderChartDTOByParams(params);
    }

    /**
     * webUrll测试http://localhost:8090/addAndQueryByUserId
     * Description: 新增一条用户数据；并返回拼装的用户数据（根据用户id、前台页面条件，列表查询用户表）
     *
     * @Param user:
     * @return: com.zxw.model.User
     * Author: ljx
     * Date: 2021/3/24 0024 上午 10:14
     */
    @PostMapping("/addAndQueryByParams")
    @ResponseBody
    public TakeawayOrderDO addAndQueryByParams(TakeawayOrderDO takeawayOrderDO) {
        // 生成一个32位的随机字符串：36位UUID，去掉“-”
        takeawayOrderDO.setTakeawayOrderId(_02Init_Str_JsonStr.initUuidLen32());
        // 保存数据
        TakeawayOrderDO result = takeawayOrderService.addTakeawayOrderDO(takeawayOrderDO);
        String userId=takeawayOrderDO.getUserId();
        String param1=takeawayOrderDO.getParam1();
        Map<String, Object> params=new HashMap<String, Object>(MapGetter.defaultInitialCapacity());
        params.put("userId",userId);
        params.put("param1",param1);
        // 拼装的用户数据（根据用户id、前台页面条件，列表查询用户表）
        TakeawayOrderChartDTO takeawayOrderChartDTO = takeawayOrderService.dealTakeawayOrderChartDTOByParams(params);
        // 通知前端，向前台发送消息
        TakeawayOrderWebSocket2Server.sendMessageToWebsocketJs(takeawayOrderDO.getUserId(), JSON.toJSONString(takeawayOrderChartDTO));
        return result;
    }
}
