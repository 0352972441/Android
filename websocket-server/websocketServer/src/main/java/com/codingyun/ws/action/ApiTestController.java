package com.codingyun.ws.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingyun.ws.entity.ServerMessage;

@RestController
@RequestMapping("/test")
public class ApiTestController {
	
	@Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("ok")
    public String userInfo(){
    	messagingTemplate.convertAndSend("/topic/subscribeTest", new ServerMessage("服务器主动推的数据"));
        return "ok";
    }

}
