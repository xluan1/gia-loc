package com.gialoc.springboot.model.enums;

public enum StatusOrder {
    created("Mới Tạo"),
    processing("Đang Xử Lý"),
    completed("Đã Hoàn Thành"),
    cancelled("Đã Hủy Bỏ");

    public final String label;

    StatusOrder(String label) {
        this.label = label;
    }

    public static String valueOfLabel(Object status) {
        for (StatusOrder period : values()) {
            if (period.equals(status)) {
                return period.label;
            }
        }
        return null;
    }
}
