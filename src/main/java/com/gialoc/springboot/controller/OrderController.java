package com.gialoc.springboot.controller;

import com.gialoc.springboot.exception.InputDataException;
import com.gialoc.springboot.exception.ResourceNotFoundException;
import com.gialoc.springboot.model.Order;
import com.gialoc.springboot.payload.request.OrderRequest;
import com.gialoc.springboot.payload.request.OrderStatusRequest;
import com.gialoc.springboot.payload.response.OrderDetailResponse;
import com.gialoc.springboot.service.order.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping("detail/{orderId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public OrderDetailResponse getOrderDetail(@PathVariable String orderId) throws ResourceNotFoundException {
        return orderService.getOrderDetail(orderId);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Order> getAllOrder() throws ResourceNotFoundException {
        return orderService.getAllOrder();
    }

    @GetMapping("{orderId}")
    public Order getOrderById(@PathVariable String orderId) throws ResourceNotFoundException {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public Order createOrderRequest(@RequestBody @Valid OrderRequest orderRequest, BindingResult binding)
            throws ResourceNotFoundException, InputDataException {

        if (binding.hasErrors()) {
            LOGGER.error("Đặt hàng không thành công!");
            throw new InputDataException(binding);
        }
        return orderService.createOrderRequest(orderRequest);
    }

    @PutMapping("{orderId}")
    public Order updateStatusOrder(@PathVariable String orderId, @RequestBody OrderStatusRequest orderRequest)
            throws ResourceNotFoundException {
        return orderService.updateStatusOrder(orderId, orderRequest);
    }
}
