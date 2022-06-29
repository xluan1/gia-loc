package com.gialoc.springboot.service.order;

import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.model.Order;
import com.gialoc.springboot.payload.request.OrderRequest;
import com.gialoc.springboot.payload.request.OrderStatusRequest;
import com.gialoc.springboot.payload.response.OrderDetailResponse;

import java.util.List;

public interface OrderService {
    OrderDetailResponse getOrderDetail(String orderId) throws ResourceNotFoundException;

    Order getOrderById(String orderId) throws ResourceNotFoundException;

    List<Order> getAllOrder() throws ResourceNotFoundException;

    Order createOrderRequest(OrderRequest orderRequest) throws ResourceNotFoundException;

    Order updateStatusOrder(String orderId, OrderStatusRequest request) throws ResourceNotFoundException;
}
