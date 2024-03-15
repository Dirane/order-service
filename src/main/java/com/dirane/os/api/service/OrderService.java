package com.dirane.os.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.dirane.os.api.common.Payment;
import com.dirane.os.api.common.TransactionRequest;
import com.dirane.os.api.common.TransactionResponse;
import com.dirane.os.api.entity.Order;
import com.dirane.os.api.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Autowired
	private RestTemplate template;
	
	
	public TransactionResponse saveOrder(TransactionRequest request) {
		
		String response = "";
		Order order = request.getOrder();
		Payment payment = request.getPayment();
		payment.setOrderId(order.getId());
		payment.setAmount(order.getPrice());
		
		//rest call
		Payment paymentResponse = template.postForObject("localhost:9191/payment/doPayment", payment, Payment.class);
		
		response = paymentResponse.getPaymentStatus().equals("sucess")?"Payment processing successful and order placed":"there is a failure in payment api  order added to caet";
		
		
		repository.save(order);
		return new TransactionResponse(order, paymentResponse.getAmount(), paymentResponse.getTransactionId(),response);
	}

}
