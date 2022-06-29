package com.gialoc.springboot.payload.request;

public class OrderDetailRequest {
    private OrderRequest order;
    private OrderItemRequest orderItem;

    public OrderDetailRequest(OrderRequest order, OrderItemRequest orderItem) {
        this.order = order;
        this.orderItem = orderItem;
    }

    public OrderRequest getOrder() {
        return order;
    }

    public OrderItemRequest getOrderItem() {
        return orderItem;
    }
}
