package com.gialoc.springboot.service.order;

import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.model.Order;
import com.gialoc.springboot.model.OrderItem;
import com.gialoc.springboot.model.Product;
import com.gialoc.springboot.model.enums.DeliveryPeriod;
import com.gialoc.springboot.model.enums.StatusOrder;
import com.gialoc.springboot.model.enums.StatusPayment;
import com.gialoc.springboot.payload.request.OrderItemRequest;
import com.gialoc.springboot.payload.request.OrderRequest;
import com.gialoc.springboot.payload.request.OrderStatusRequest;
import com.gialoc.springboot.payload.response.OrderDetailResponse;
import com.gialoc.springboot.payload.response.OrderItemResponse;
import com.gialoc.springboot.repository.OrderItemRepository;
import com.gialoc.springboot.repository.OrderRepository;
import com.gialoc.springboot.repository.ProductRepository;
import com.gialoc.springboot.service.base.BaseManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderServiceImpl extends BaseManager implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    private List<OrderItem> getOrderItemsByOrderId(String orderId) throws ResourceNotFoundException {
        List<OrderItem> orderItems = orderItemRepository.findAllByOrderId(orderId);
        if (orderItems.size() == 0) {
            throw new ResourceNotFoundException("not found orderItems of oderId" + orderId);
        }
        return orderItems;
    }

    @Override
    public OrderDetailResponse getOrderDetail(String orderId) throws ResourceNotFoundException {
        Order order = getOrderById(orderId);
        List<OrderItemResponse> orderItems = new ArrayList<>();
        for (OrderItem item : getOrderItemsByOrderId(orderId)) {
            Product product = getProductById(item.getProductId());
            if (product != null) {
                OrderItemResponse itemResponse = new OrderItemResponse();
                itemResponse.setCategoryName(product.getCate());
                itemResponse.setCreatedAt(item.getCreatedAt());
                itemResponse.setProductId(item.getProductId());
                itemResponse.setDiscount(product.getDiscount());
                itemResponse.setProductName(product.getName());
                itemResponse.setTotalPrice(item.getTotal());
                itemResponse.setTotalQuantity(item.getQuantity());
                orderItems.add(itemResponse);
            }
        }
        return new OrderDetailResponse(order, orderItems);
    }

    @Override
    public Order getOrderById(String orderId) throws ResourceNotFoundException {
        return orderRepository
                .findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("not found order of oderId: " + orderId));
    }

    @Override
    public List<Order> getAllOrder() throws ResourceNotFoundException {
        List<Order> orders = orderRepository.findAll();
        if (orders.size() == 0) {
            throw new ResourceNotFoundException("not available order");
        }
        return orders;
    }

    private Product getProductById(long productId) throws ResourceNotFoundException {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("not found product of productId" + productId));
    }

    @Override
    public Order createOrderRequest(OrderRequest orderRequest) throws ResourceNotFoundException {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        Order newOrder = new Order();
        //them du lieu cho order
        newOrder.setId(generateId());
        newOrder.setAccountName(currentUser.getName());
        newOrder.setCreatedAt(new Date());
        newOrder.setDeliveryTime(DeliveryPeriod.valueOfLabel(orderRequest.getDeliveryTime()));
        newOrder.setOrderAddress(orderRequest.getOrderAddress());
        newOrder.setOrderNumberPhone(orderRequest.getOrderNumberPhone());
        newOrder.setOrderName(orderRequest.getOrderName());
        newOrder.setPayment(StatusPayment.valueOfLabel(orderRequest.getPayment()));
        newOrder.setNote(orderRequest.getNote());
        //vi thanh toan online da tra tien, thi trang thai dat hang va van chuyen se la dang xu ly
        if (orderRequest.getPayment().equals(StatusPayment.ONLINE.name())) {
            newOrder.setStatus(StatusOrder.valueOfLabel(StatusOrder.processing.name()));
            newOrder.setDeliveryStatus(StatusOrder.valueOfLabel(StatusOrder.processing));
        } else {
            newOrder.setStatus(StatusOrder.valueOfLabel(StatusOrder.created));
            newOrder.setDeliveryStatus(StatusOrder.valueOfLabel(StatusOrder.created));
        }

        //them du lieu cho order item
        List<OrderItem> orderItems = new ArrayList<>();
        for (OrderItemRequest item : orderRequest.getItems()) {
            Product product = getProductById(item.getProductId());
            if (product != null) {
                long discount = 100 - product.getDiscount();
                double totalPriceWithDiscount = ((product.getPrice() * item.getQuantity()) * discount) / 100;

                OrderItem newOrderItem = new OrderItem();
                newOrderItem.setCreatedAt(new Date());
                newOrderItem.setId(generateId());
                newOrderItem.setOrderId(newOrder.getId());
                newOrderItem.setOrigin(product.getOrigin());
                newOrderItem.setPair(product.getPair());
                newOrderItem.setTotal(totalPriceWithDiscount);
                newOrderItem.setProductId(product.getId());
                newOrderItem.setQuantity(item.getQuantity());
                newOrderItem.setStatus(newOrder.getStatus());
                newOrderItem.setCreatedAt(new Date());
                //tinh tong du lieu cho order
                long totalQuantity = newOrderItem.getQuantity() + newOrder.getQuantity();
                double totalPrice = totalPriceWithDiscount + newOrder.getTotal();
                newOrder.setQuantity(totalQuantity);
                newOrder.setTotal(totalPrice);
                //them vao list
                orderItems.add(newOrderItem);
            }
        }
        orderItemRepository.saveAll(orderItems);
        orderRepository.save(newOrder);
        return newOrder;
    }

    @Override
    public Order updateStatusOrder(String orderId, OrderStatusRequest request) throws ResourceNotFoundException {
        Order order = getOrderById(orderId);
        if (order.getStatus().equals(StatusOrder.completed.name())) {
            throw new ResourceNotFoundException("unable to update status when it is completed order");
        }
        if (order.getStatus().equals(StatusOrder.cancelled.name())) {
            throw new ResourceNotFoundException("unable to update status when it is cancelled order");
        }
        if (order.getStatus().equals(request.getStatus().name())) {
            throw new ResourceNotFoundException("unable to update status same as before");
        }
        if (order.getStatus().equals(StatusOrder.created.name())) {
            if (!request.getStatus().equals(StatusOrder.processing.name())) {
                throw new ResourceNotFoundException("only can be update status from created -> processing");
            }
        }
        if (order.getStatus().equals(StatusOrder.processing.name())) {
            if (request.getStatus().equals(StatusOrder.created.name())) {
                throw new ResourceNotFoundException("unable to update status from processing -> created");
            }
        }
        //update order
        for (OrderItem item : getOrderItemsByOrderId(orderId)) {
            item.setUpdatedAt(new Date());
            item.setStatus(StatusOrder.valueOfLabel(request.getStatus()));
        }
        orderItemRepository.saveAll(getOrderItemsByOrderId(orderId));
        order.setNote(request.getNote());
        order.setUpdatedAt(new Date());
        order.setStatus(StatusOrder.valueOfLabel(request.getStatus()));
        return orderRepository.save(order);
    }
}
