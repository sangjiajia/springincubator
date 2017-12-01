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
package com.baozun.incubator.disruptor;

import com.baozun.incubator.dto.CreateOrderRequest;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;

public class OrderCreateReqDisruptorManagerImpl implements DisruptorManager<CreateOrderRequest>{
    private Disruptor<CreateOrderRequest> disruptor;
    
    private OrderCreateRequestEventProducerWithTranslator memTranslator;
    
    private WorkHandler<CreateOrderRequest> workhandler;
	@SuppressWarnings("unchecked")
	@Override
	public void startListening() {
    	//忽略 eventHandler中的異常，不然它會跑出，并跳出循環，就完蛋了
    	disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
    	//多个消费者 另外 ClearingEventHandler 里面的置NULL,没有用，只有 将数组内的指针置NULL 才能释放内存
    	disruptor.handleEventsWithWorkerPool(new WorkHandler<CreateOrderRequest>() {
			@Override
			public void onEvent(CreateOrderRequest event)
					throws Exception {
				workhandler.onEvent(event);
			}
		}/*,
		new WorkHandler<CreateOrderRequest>() {
			@Override
			public void onEvent(CreateOrderRequest event)
					throws Exception {
				workhandler.onEvent(event);
			}
		},
		new WorkHandler<CreateOrderRequest>() {
			@Override
			public void onEvent(CreateOrderRequest event)
					throws Exception {
				workhandler.onEvent(event);
			}
		}*/
    			);
    	
    	
        //disruptor.handleEventsWith(eventhandler).then(new ClearingEventHandler<MemberRequestRedisCommand>());
        disruptor.start();
    }

	@Override
	public void shutDown() {
		disruptor.shutdown();
	}

	@Override
	public void publishEvent(CreateOrderRequest command) {
		memTranslator.onData(command);
	}

	public Disruptor<CreateOrderRequest> getDisruptor() {
		return disruptor;
	}

	public void setDisruptor(Disruptor<CreateOrderRequest> disruptor) {
		this.disruptor = disruptor;
	}

	public OrderCreateRequestEventProducerWithTranslator getMemTranslator() {
		return memTranslator;
	}

	public void setMemTranslator(
			OrderCreateRequestEventProducerWithTranslator memTranslator) {
		this.memTranslator = memTranslator;
	}

	public WorkHandler<CreateOrderRequest> getWorkhandler() {
		return workhandler;
	}

	public void setWorkhandler(WorkHandler<CreateOrderRequest> workhandler) {
		this.workhandler = workhandler;
	}

	
	
}
