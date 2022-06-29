package com.gialoc.springboot.payload.request;

import com.gialoc.springboot.model.enums.StatusOrder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

public class OrderStatusRequest {
    @NotNull(message = "status không được trống")
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    private String note;

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
