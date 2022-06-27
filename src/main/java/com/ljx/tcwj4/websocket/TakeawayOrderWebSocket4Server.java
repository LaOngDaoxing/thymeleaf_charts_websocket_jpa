package com.ljx.tcwj4.websocket;

import com.alibaba.fastjson.JSON;
import com.ljx.tcwj1.pojo.dto.UserChartDTO;
import com.ljx.tcwjneln._09util.constantutil.ConstantUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
* @Description: 后台服务器的websocket服务器端|ServerEndpoint
* @FR功能需求：
* @ImportJar:
* @ApiGrammer规则：
    注解@ServerEndpoint 是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务端。注解的值将被用于监听用户连接的终端访问URL地址。
* @Remark:
* @CodeBug解决:
* @date 2021年3月24日 下午1:36:00
* @author  ljx
*
 */
@ServerEndpoint("/userws/{userId}")
@Component
public class TakeawayOrderWebSocket4Server {
    private static final Logger logger = LoggerFactory.getLogger(TakeawayOrderWebSocket4Server.class);
    /**
     * 存储 websocket session等，以记录每个用户下多个终端【PC（不同浏览器登陆，产生的sessionid不同）、pad、phone】的连接
     */
    public static final Map<String, List<Session>> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();
    /*######################## 一、根据用户id，接收 消息(用户信息)的 websocket服务端 ########################*/
    /**
     * 当前台用户终端【浏览器】页面，使用js WebSocket；与服务器建立连接并完成握手后，前台会回调ws.onopen；后台调用@OnOpen注解的方法。
     * @param userId
     * @param session
     */
    @OnOpen
    public void openSession(@PathParam("userId") String userId, Session session) {
        // ##-------- 获取请求路径中携带的信息
        // {userId=dkh}
        Map<String, String> map = session.getPathParameters();
        // emailWsParam=1
        String str = session.getQueryString();
        // /userws/dkh?emailWsParam=1
        String uri = session.getRequestURI().toString();
        List<Session> list = ONLINE_USER_SESSIONS.get(userId);
        // 如果该用户当前是第一次连接/没有在别的终端登录
        if (null == list) {
            list = new ArrayList<>();
        }
        // 如果该用户当前不是第一次连接/已经在别的终端登录
        if (!list.contains(session)) {
            list.add(session);
        }
        ONLINE_USER_SESSIONS.put(userId, list);
    }

    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String message) {
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
            UserChartDTO userChartDTO =new UserChartDTO();
            sendMessageToWebsocketJs(userId, JSON.toJSONString(userChartDTO));
        }
    }

    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session) {
        List<Session> list = ONLINE_USER_SESSIONS.get(userId);
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
    /*######################## 二、根据用户id，发送 消息(用户信息)的 websocket服务端 工具方法########################*/
    /**
     * @Author Zhouxw
     * @Date 2020/09/21 13:19
     * @Description 向前台用户终端【浏览器】页面，发送 消息(用户信息)
     * @Param [session, message]
     * @Return void
     */
    public static void sendMessageToWebsocketJs(Session session, String message) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(message);
        } catch (IOException e) {
            logger.error("sendMessage IOException ", e);
        }
    }
    /**
     * Description: 这个根据业务情况详细设计
     * @CodeSteps： 根据用户id，向前台用户终端【浏览器】页面，发送 消息(用户信息)
     * @Param key:      userId
     * @Param message:  消息(用户信息)
     * @return: void
     * Author: ljx
     * Date: 2021/3/24 0024 下午 3:35
     */
    public static void sendMessageToWebsocketJs(String key, String message) {
        List<Session> list = ONLINE_USER_SESSIONS.get(key);
        // 给用户的所有终端发送数据消息
        list.stream().forEach(se -> {
            if(se.isOpen()){
                sendMessageToWebsocketJs(se, message);
            }
        });
    }
}