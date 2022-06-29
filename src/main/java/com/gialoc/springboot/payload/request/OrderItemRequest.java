package com.gialoc.springboot.payload.request;

import javax.validation.constraints.NotBlank;

public class OrderItemRequest {
    @NotBlank(message = "productId không được để trống")
    private long productId;
    @NotBlank(message = "số lượng sản phẩm không được để trống")
    private long quantity;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

}
