package com.cykj.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;


/**
 * @version 1.0
 * @author: yitong
 * @date: 2021/8/9 22:11
 * @desc:
 */
@Configuration
public class WebSocketConfig {
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
