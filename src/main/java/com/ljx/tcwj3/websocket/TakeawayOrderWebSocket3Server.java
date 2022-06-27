package com.ljx.tcwj3.websocket;
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
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @Description: 后台服务器的websocket服务器端|ServerEndpoint
 * @FR功能需求：
 * @ImportJar:
 * @Reference：
        参考链接：Sring MVC 模式下使用websocket——https://www.jianshu.com/p/3398d0230e5f
 * @ApiGrammer规则：
        注解@ServerEndpoint 是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务端。注解的值将被用于监听用户连接的终端访问URL地址。
 * @Remark:
 * @CodeBug解决:
        如果期望ById和ByParams页面在新增后展示返回数据不同，可以设置将session的key=userId，改为session的key=groupCode
 * @date 2021年3月24日 下午1:36:00
 * @author  ljx
 *
 */
@ServerEndpoint("/takeawayOrderWs3/{groupCode}/{userId}")
@Component
public class TakeawayOrderWebSocket3Server {

    private static final Logger logger = LoggerFactory.getLogger(TakeawayOrderWebSocket3Server.class);
    /**
     * 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
     */
    private static int onlineCount = 0;
    /**
     * 存储 websocket session等，以记录每个用户下多个终端【PC（不同浏览器登陆，产生的sessionid不同）、pad、phone】的连接
     */
    private static Map<String, Set<TakeawayOrderWebSocket3Server>> ONLINE_SESSIONS_TOWS3_MAP = new ConcurrentHashMap<>();
    /**
     * 连接特征userId
     */
    private String userId;
    /**
     * 存储 websocket session；表示与某个用户的连接会话，通过它给用户终端发送数据
     */
    private Session session;
    /**
     * 消息（或心跳信息）
     */
    private String message;

    /*######################## 一、根据用户id，接收 消息(用户信息)的 websocket服务端 ########################*/
    /**
     * 当前台用户终端【浏览器】页面，使用js WebSocket；与服务器建立连接并完成握手后，前台会回调ws.onopen；后台调用@OnOpen注解的方法。
     * @CodeSteps： 新建连接时需要判断是该用户当前是否第一次连接/是否已经在别的终端登录；
    如果该用户当前是第一次连接/没有在别的终端登录，则对Map增加一个userId；
    如果该用户当前不是第一次连接/已经在别的终端登录，将新的连接实例sessionid，添加入已有的用户Set中。
     * @param userId
     * @param session
     */
    @OnOpen
    public void openSession(@PathParam("groupCode") String groupCode,@PathParam("userId") String userId, Session session) {
        this.userId=userId;
        this.session=session;
        // 在线数加1
        onlineCount++;
        // 如果该用户当前是第一次连接/没有在别的终端登录
        if (!ONLINE_SESSIONS_TOWS3_MAP.containsKey(this.userId)) {
            logger.debug("当前用户 userId:{}第一个终端登录",this.userId);
            Set<TakeawayOrderWebSocket3Server> addUserSet = new HashSet<>();
            addUserSet.add(this);
            // 对Map增加一个userId
            ONLINE_SESSIONS_TOWS3_MAP.put(this.userId, addUserSet);
        }
        // 如果该用户当前不是第一次连接/已经在别的终端登录
        else {
            logger.debug("当前用户 userId:{}已有其他终端登录",this.userId);
            // 将新的连接实例sessionid，添加入已有的用户Set中
            ONLINE_SESSIONS_TOWS3_MAP.get(this.userId).add(this);
        }
        logger.debug("用户{}登录的终端个数是为{}",userId,ONLINE_SESSIONS_TOWS3_MAP.get(this.userId).size());
        logger.debug("当前所有在线用户数为：{},所有终端个数为：{}",ONLINE_SESSIONS_TOWS3_MAP.size(),onlineCount);
    }

    @OnMessage
    public void onMessage(@PathParam("groupCode") String groupCode,@PathParam("userId") String userId, String message) {
        // 前台用户终端【浏览器】页面，ws.send发送的消息（或心跳信息）
        if(message.startsWith(ConstantUtil.TO_WEBSOCKET_OF_CLIENT_TYPE1)){
            System.out.println(userId + "前台用户终端【浏览器】页面，ws.send发送的消息（或心跳信息）：" + message);
            this.userId=userId;
            this.message=message;
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
    /**
     * Description:
     * @CodeSteps： 连接关闭时，如果该用户当前没有连接了/没有在别的终端登录了/所有终端都下线了，移除Map中该用户的记录；
    其他情况，移除该用户Set中的记录。
     * @Param userId:
     * @Param session:
     * @return:
     */
    @OnClose
    public void onClose(@PathParam("groupCode") String groupCode,@PathParam("userId") String userId, Session session) {
        this.userId=userId;
        this.session=session;
        // 在线数减1
        onlineCount--;
        // 如果该用户当前没有连接了/没有在别的终端登录了/所有终端都下线了
        if (ONLINE_SESSIONS_TOWS3_MAP.get(this.userId).size() == 0) {
            // 移除Map中该用户的websocket session等记录
            ONLINE_SESSIONS_TOWS3_MAP.remove(this.userId);
        }else{
            // 移除该用户Set中的websocket session等记录
            ONLINE_SESSIONS_TOWS3_MAP.get(this.userId).remove(this);
        }
        logger.debug("用户{}登录的终端个数是为{}",this.userId,ONLINE_SESSIONS_TOWS3_MAP.get(this.userId).size());
        logger.debug("当前所有在线用户数为：{},所有终端个数为：{}",ONLINE_SESSIONS_TOWS3_MAP.size(),onlineCount);
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
     * @Param key:
     * @Param message:
     * @return: void
     * Author: ljx
     * Date: 2021/3/24 0024 下午 3:35
     */
    public static boolean sendMessageToWebsocketJs(String userId, String message) {
        if (ONLINE_SESSIONS_TOWS3_MAP.containsKey(userId)) {
            logger.debug(" 给用户 userId为：{}的所有终端发送消息：{}",userId,message);
            Set<TakeawayOrderWebSocket3Server> userWsSet=ONLINE_SESSIONS_TOWS3_MAP.get(userId);
            // 给用户的所有终端发送数据消息：遍历该用户的Set中的连接即可
            for (TakeawayOrderWebSocket3Server userWs : userWsSet) {
                logger.debug("sessionId为:{}",userWs.session.getId());
                final RemoteEndpoint.Basic basic = userWs.session.getBasicRemote();
                try {
                    basic.sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.debug(" 给用户 userId为：{}发送消息失败",userId);
                    return false;
                }
            }
            return true;
        }
        logger.debug("发送错误：当前连接不包含 userId为：{}的用户",userId);
        return false;
    }
}
