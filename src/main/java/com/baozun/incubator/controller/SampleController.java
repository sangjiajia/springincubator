package com.baozun.incubator.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baozun.incubator.disruptor.DisruptorManager;
import com.baozun.incubator.dto.CreateOrderRequest;

@RestController
public class SampleController {

    @Resource
    private DisruptorManager<CreateOrderRequest> orderReqDisruptorManager;

    private AtomicLong orderId = new AtomicLong();
	
    @RequestMapping("/")
    String home() throws IOException {
    	CreateOrderRequest createOrderRequest = new CreateOrderRequest();
    	createOrderRequest.setId(orderId.incrementAndGet());
    	createOrderRequest.setLoginName(UUID.randomUUID().toString());
    	createOrderRequest.setQty(3);
    	createOrderRequest.setSkuCode("NBP"+createOrderRequest.getId());
    	createOrderRequest.setRemark("这是个测试单"+createOrderRequest.getId());
    	orderReqDisruptorManager.publishEvent(createOrderRequest);
        return "Hello World!";
    }

}