package com.ljx.tcwj4.websocket;
import javax.websocket.*;
import java.io.IOException;

/**
 * @author ljx
 * @Description: 后台服务器的websocket客户端|ClientEndpoint|Java服务器|数据库触发器的外部程序或接口
 * @FR功能需求：
        前台用户终端【浏览器】页面，使用js WebSocket；
        通过后台服务器的websocket服务器端|ServerEndpoint即UserWebSocketServer.java，维护Java服务器会话、用户终端的Sessions；
        与在后台服务器的websocket客户端|ClientEndpoint|Java服务器即UserWebSocketClient.java，进行双向通信；
        将UserWebSocketClient.java，构建成外部程序jar包；
        数据库建立insert触发器，当插入数据时调用外部程序jar包。
 * @ImportJar:
 * @ApiGrammer规则：
 * @Remark:
 * @AlibabaCodeStatuteScanError：
 * @CodeBug解决:
 * @Debug调试：
 * @date 2021/4/13 0013 上午 9:45
 */
@ClientEndpoint
public class TakeawayOrderWebSocket4Client {
        /*######################## 一、发送 消息的 websocket客户端 ########################*/
        /**
         * @Description:
         * @Param session:
         * @return:
          */
        @OnOpen
        public void open(Session session){
                System.out.println("Client WebSocket is opening...");
                System.out.println("Connected to endpoint:"+ session.getBasicRemote());
                try {
                        session.getBasicRemote().sendText("Hello");
                } catch (IOException ex) {

                }
        }

        @OnMessage
        public void onMessage(String message){
                System.out.println("Server send message: " + message);
        }

        @OnClose
        public void onClose(){
                System.out.println("Websocket closed");
        }
}
