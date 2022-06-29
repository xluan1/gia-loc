package com.gialoc.springboot.payload.response;

import java.util.List;

import com.gialoc.springboot.model.Order;

public class OrderDetailResponse {
	private Order order;
	private List<OrderItemResponse> orderItems;

	public OrderDetailResponse(Order order, List<OrderItemResponse> orderItems) {
		this.order = order;
		this.orderItems = orderItems;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<OrderItemResponse> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemResponse> orderItems) {
		this.orderItems = orderItems;
	}
}
