/**
 * Copyright (c) 2017 Baozun All Rights Reserved.
 *
 * This software is the confidential and proprietary information of Baozun.
 * You shall not disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Baozun.
 *
 * BAOZUN MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF THE
 * SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE, OR NON-INFRINGEMENT. BAOZUN SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 *
 */
package com.baozun.incubator.systeminit;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.baozun.incubator.disruptor.DisruptorManager;
import com.baozun.incubator.dto.CreateOrderRequest;

public class DisruptorStartUp  implements ApplicationListener<ContextRefreshedEvent>{

	private AtomicBoolean hasStarted = new AtomicBoolean(false);
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(hasStarted.compareAndSet(false, true)){
			// TODO Auto-generated method stub
			@SuppressWarnings("unchecked")
			DisruptorManager<CreateOrderRequest> orderCreateReqDisruptorManager = (DisruptorManager<CreateOrderRequest>) event.getApplicationContext()
			.getBean("orderCreateReqDisruptorManager");
			// 开启 disruptor监听
			orderCreateReqDisruptorManager.startListening();
		}
	}

}
