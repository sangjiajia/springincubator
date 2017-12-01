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
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

public class OrderCreateRequestEventProducerWithTranslator {

    private final RingBuffer<CreateOrderRequest> ringBuffer;

    public OrderCreateRequestEventProducerWithTranslator(Disruptor<CreateOrderRequest> disruptor){

        this.ringBuffer = disruptor.getRingBuffer();
    }

    private static final EventTranslatorOneArg<CreateOrderRequest, CreateOrderRequest> TRANSLATOR = new EventTranslatorOneArg<CreateOrderRequest, CreateOrderRequest>(){

        public void translateTo(CreateOrderRequest mrc,long sequence,CreateOrderRequest memberRequestCommand){
        	mrc.setId(memberRequestCommand.getId());
        	mrc.setLoginName(memberRequestCommand.getLoginName());
        	mrc.setQty(memberRequestCommand.getQty());
        	mrc.setRemark(memberRequestCommand.getRemark());
        	mrc.setSkuCode(memberRequestCommand.getSkuCode());
        }
    };

    public void onData(CreateOrderRequest memberRequestCommand){
        ringBuffer.publishEvent(TRANSLATOR, memberRequestCommand);
    }
}
