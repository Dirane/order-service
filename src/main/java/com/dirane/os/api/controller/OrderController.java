package com.dirane.os.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dirane.os.api.common.Payment;
import com.dirane.os.api.common.TransactionRequest;
import com.dirane.os.api.common.TransactionResponse;
import com.dirane.os.api.entity.Order;
import com.dirane.os.api.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService service;
	
	
	@PostMapping("/bookOrder")
	public TransactionResponse bookOrder(@RequestBody TransactionRequest request) {
	
		return  service.saveOrder(request);
		
	}

}
