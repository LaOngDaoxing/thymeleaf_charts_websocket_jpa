package com.ljx.tcwj1.controller;

import com.alibaba.fastjson.JSON;
import com.ljx.tcwj1.pojo.doo.TakeawayOrderDO;
import com.ljx.tcwj1.pojo.dto.TakeawayOrderChartDTO;
import com.ljx.tcwj1.service.TakeawayOrderService;
import com.ljx.tcwj1.utils.SessionUtil;
import com.ljx.tcwj1.websocket.TakeawayOrderWebSocket1Server;
import com.ljx.tcwjneln._02variables._05initVar._02Init_Str_JsonStr;
import com.ljx.tcwjneln._09util.constantutil.ConstantUtil;
import com.ljx.tcwjneln._09util.maputil.MapGetter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljx
 * @Description: 外卖订单管理
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
    由于websocket服务端接口和普通后台接口写在同一个Controller中；注意以下对应的前台请求路径写法有区别，具体区别自行调试
    接口路径写法1、
         @RestController("/takeawayOrder1")
         public class TakeawayOrderController{
             @GetMapping("/{userId}/queryByUserId")
             @ResponseBody
         }
    接口路径写法2、
         @RestController()
         public class TakeawayOrderController{
             @GetMapping("/takeawayOrder1/{userId}/queryByUserId")
             @ResponseBody
         }
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/8/10 0010 下午 5:08
 */
@ServerEndpoint("/takeawayOrderWs1/{groupCode}/{userId}")
@RestController()
public class TakeawayOrder1Controller {

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
    @GetMapping("/takeawayOrder1/{userId}/queryByUserId")
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
    @PostMapping("/takeawayOrder1/addAndQueryByUserId")
    @ResponseBody
    public TakeawayOrderDO addAndQueryByUserId(TakeawayOrderDO takeawayOrderDO) {
        // 生成一个32位的随机字符串：36位UUID，去掉“-”
        takeawayOrderDO.setTakeawayOrderId(_02Init_Str_JsonStr.initUuidLen32());
        // 保存数据
        TakeawayOrderDO result = takeawayOrderService.addTakeawayOrderDO(takeawayOrderDO);
        // 拼装数据DTO通知前端
        TakeawayOrderChartDTO takeawayOrderChartDTO = takeawayOrderService.dealTakeawayOrderChartDTOByUserId(takeawayOrderDO.getUserId());
        // 通知前端，向前台发送消息
        TakeawayOrderWebSocket1Server.sendMessageToWebsocketJs(takeawayOrderDO.getUserId(), JSON.toJSONString(takeawayOrderChartDTO));
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
    @GetMapping("/takeawayOrder1/{userId}/queryByParams")
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
    @PostMapping("/takeawayOrder1/addAndQueryByParams")
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
        TakeawayOrderWebSocket1Server.sendMessageToWebsocketJs(takeawayOrderDO.getUserId(), JSON.toJSONString(takeawayOrderChartDTO));
        return result;
    }
    /*######################## 一、根据用户id，接收 消息(用户信息)的 websocket服务端 ########################*/
    /**
     * 当前台用户终端【浏览器】页面，使用js WebSocket；与服务器建立连接并完成握手后，前台会回调ws.onopen；后台调用@OnOpen注解的方法。
     * @param groupCode dkh
     * @param userId    54fdea80-f6a5-11eb-9810-f44d300627e6
     * @param session
     */
    @OnOpen
    public void openSession(@PathParam("groupCode") String groupCode, @PathParam("userId") String userId, Session session) {
        // ##-------- 从session中，获取请求路径中携带的信息
        SessionUtil.gainUrlParamFromSession(session);
        List<Session> list = TakeawayOrderWebSocket1Server.ONLINE_USER_SESSIONS.get(userId);
        // 如果该用户当前是第一次连接/没有在别的终端登录
        if (null == list) {
            list = new ArrayList<>();
        }
        // 如果该用户当前不是第一次连接/已经在别的终端登录
        if (!list.contains(session)) {
            list.add(session);
        }
        TakeawayOrderWebSocket1Server.ONLINE_USER_SESSIONS.put(userId, list);
    }

    /**
     * 接收客户端发送的消息，并向客户端发送消息
     * @param groupCode dkh
     * @param userId    54fdea80-f6a5-11eb-9810-f44d300627e6
     * @param message   websocketJs_{"provinceMsg":"河北省","cityMsg":"石家庄市","emailMsg":1}
     * @param session
     */
    @OnMessage
    public void onMessage(@PathParam("groupCode") String groupCode,@PathParam("userId") String userId, String message, Session session) {
        // ##-------- 从session中，获取请求路径中携带的信息
        SessionUtil.gainUrlParamFromSession(session);
        // 前台用户终端【浏览器】页面，ws.send发送的消息（或心跳信息）
        if(message.startsWith(ConstantUtil.TO_WEBSOCKET_OF_CLIENT_TYPE1)){
            System.out.println(userId + "前台用户终端【浏览器】页面，ws.send发送的消息（或心跳信息）：" + message);
            // --接收并处理消息

            // --通知前端，向前台发送消息

        }
        // 后台服务器的websocket客户端|ClientEndpoint|Java服务器，UserWebSocketClient.java发送的消息（或心跳信息）
        else if(message.startsWith(ConstantUtil.TO_WEBSOCKET_OF_CLIENT_TYPE2)){
            // --接收并处理消息
            // --通知前端，向前台发送消息
            // 从session中，获取当前登录用户id
            userId="";
            // 根据条件，列表查询用户表
            TakeawayOrderChartDTO takeawayOrderChartDTO =new TakeawayOrderChartDTO();
            TakeawayOrderWebSocket1Server.sendMessageToWebsocketJs(userId, JSON.toJSONString(takeawayOrderChartDTO));
        }
    }

    /**
     *
     * @param groupCode dkh
     * @param userId    54fdea80-f6a5-11eb-9810-f44d300627e6
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("groupCode") String groupCode,@PathParam("userId") String userId, Session session) {
        // ##-------- 从session中，获取请求路径中携带的信息
        SessionUtil.gainUrlParamFromSession(session);
        List<Session> list = TakeawayOrderWebSocket1Server.ONLINE_USER_SESSIONS.get(userId);
        // 移除该用户的websocket session记录
        list.remove(session);
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Throwable msg " + throwable.getMessage());
    }
}
