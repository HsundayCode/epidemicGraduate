package com.hfz.epidemicmanage.WebScoket;

import com.hfz.epidemicmanage.Entity.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/websocket/{accountid}")
@Component
public class WebSocketServer {
    private static int onlineCount;

    private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

    private static ConcurrentHashMap<String,WebSocketServer> websocketMap = new ConcurrentHashMap<>();

    private Session session;
    private String accountid;

    @OnOpen
    public void open(Session session, @PathParam("accountid")String accountid)
    {
        this.session = session;
        this.accountid = accountid;
        websocketMap.put(accountid,this);
        System.out.println("用户"+accountid+"连接成功");
        addOnlineCount();
    }
    @OnClose
    public void close(){
        websocketMap.remove(accountid);
        System.out.println("用户"+accountid+"断开连接");
        subOnlineCount();
    }

    //遍历map发送所有人
    public void sendMessageAll(Message message){
        websocketMap.forEach((key,value)->{
            sendOnce(message.getContent(),key,value);
        });
    }

    public void sendOnce(String content,String key,WebSocketServer webSocketServer)
    {
        try {
            webSocketServer.session.getBasicRemote().sendText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public void sendMessage(String content){
//        try {
//            this.session.getBasicRemote().sendText(content);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public synchronized int getOnlineCount(){
        return WebSocketServer.onlineCount;
    }
    public synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }
    public synchronized void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }

}
