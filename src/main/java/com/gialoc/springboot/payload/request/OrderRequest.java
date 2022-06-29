package com.gialoc.springboot.payload.request;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gialoc.springboot.model.enums.DeliveryPeriod;
import com.gialoc.springboot.model.enums.StatusPayment;
import org.hibernate.validator.constraints.Length;

import java.util.*;

public class OrderRequest {
    @NotBlank(message = "tên người đặt hàng không được để trống")
    private String orderName;
    @NotBlank(message = "địa chỉ người đặt hàng không được để trống")
    private String orderAddress;
    @NotBlank(message = "số điện thoại người đặt hàng không được để trống")
    @Length(min = 10, max = 10, message = "Số điện thoại phải đủ 10 số")
    private String orderNumberPhone;
    private String note;
    @NotNull
    @Enumerated(EnumType.STRING)
    private DeliveryPeriod deliveryTime;
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusPayment payment;
    @NotNull
    private Set<OrderItemRequest> items;

    public Set<OrderItemRequest> getItems() {
        return items;
    }

    public void setItems(Set<OrderItemRequest> items) {
        this.items = items;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderNumberPhone() {
        return orderNumberPhone;
    }

    public void setOrderNumberPhone(String orderNumberPhone) {
        this.orderNumberPhone = orderNumberPhone;
    }

    public DeliveryPeriod getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(DeliveryPeriod deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public StatusPayment getPayment() {
        return payment;
    }

    public void setPayment(StatusPayment payment) {
        this.payment = payment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
