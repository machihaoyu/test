package com.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

/**
 * Created by yinzf on 2018/7/19.
 */
@Controller
public class WebSocketAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @MessageMapping("/sendTest")
    @SendTo("/topic/subscribeTest")
    public ServerMessage sendDemo(ClientMessage message) {
        logger.info("接收到了信息" + message.getName());
        return new ServerMessage("服务端发送的消息为:" + message.getName());
    }

    @SubscribeMapping("/subscribeTest")
    public ServerMessage sub() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }

    @SubscribeMapping("/subscribeService")
    public ServerMessage sub2() {
        logger.info("订阅了消息协议");
        return new ServerMessage("协议感谢你订阅了我");
    }

    @MessageMapping("/sendService")
    @SendTo("/topic/subscribeService")
    public ServerMessage sendDemo2(ClientMessage message) {
        logger.info("协议接收到了信息" + message.getName());
        return new ServerMessage("协议服务端发送的消息:" + message.getName());
    }
}
