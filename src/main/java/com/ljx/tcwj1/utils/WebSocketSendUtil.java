package com.ljx.tcwj1.utils;

import com.ljx.tcwjneln._02variables._07convertVar._05Convert_Str_Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 *
 * @Description: 发送 消息(用户信息)的 websocket服务器端 工具类
 * @FR功能需求：
 * @ImportJar:
 * @ApiGrammer规则：
        注解@ServerEndpoint 是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端。注解的值将被用于监听用户连接的终端访问URL地址。
 * @Remark:
 * @CodeBug解决:
 * @date 2021年3月24日 下午1:36:00
 * @author  ljx
 *
 */
public class WebSocketSendUtil {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketSendUtil.class);
    /**
     * 存储 websocket session等，以记录每个用户下多个终端【PC（不同浏览器登陆，产生的sessionid不同）、pad、phone】的连接
     */
    public static final Map<String, List<Session>> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();
    /*######################## 二、根据用户id，发送 消息(用户信息)的 websocket服务器端 工具方法########################*/
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
     * @Param key:
     * @Param message:
     * @return: void
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

    /**
     * 从session中，获取请求路径中携带的信息
     * @param session
     */
    public static void gainUrlParamFromSession(Session session){
        // {moduleCode=dkh,userId=54fdea80-f6a5-11eb-9810-f44d300627e6}
        Map<String, String> map = session.getPathParameters();
        // param1=paramVal123ss
        String str = session.getQueryString();
        // ws://192.168.21.21:8090/thymeleaf_charts_websocket_jpa/userWs1/dkh/54fdea80-f6a5-11eb-9810-f44d300627e6?param1=paramVal123ss
        String uri = session.getRequestURI().toString();
        // param1=paramVal123ss
        String paramsStr=uri.substring(uri.indexOf("?")+1);
        // {param1=paramVal123ss}
        Map<String, Object>  paramsMap= _05Convert_Str_Map.convertUrlParamsStrToMap(paramsStr);
    }
}
