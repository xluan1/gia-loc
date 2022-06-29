package com.gialoc.springboot.model;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gialoc.springboot.model.enums.StatusOrder;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
@Table(name = "order_item")
public class OrderItem {
	@Id
	private String id;
	@JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
	private Date createdAt;
	@JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
	private Date updatedAt;

	private String status;
	private long productId;
	private String orderId;
	private long quantity;
	private double total;
	private String pair;
	private String origin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getPair() {
		return pair;
	}

	public void setPair(String pair) {
		this.pair = pair;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
