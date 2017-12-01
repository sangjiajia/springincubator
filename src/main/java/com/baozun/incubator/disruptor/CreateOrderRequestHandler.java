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
import com.lmax.disruptor.WorkHandler;

public class CreateOrderRequestHandler implements WorkHandler<CreateOrderRequest>{

	@Override
	public void onEvent(CreateOrderRequest event) throws Exception {
		// TODO Auto-generated method stub
		//去创单
		//validate
		// insert db
		//抛出 orderCreateEvent
		System.out.println("i get a order request event,let me create a order");
	}

}
