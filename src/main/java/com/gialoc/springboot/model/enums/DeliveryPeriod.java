package com.gialoc.springboot.model.enums;

public enum DeliveryPeriod {
    IN_12H("Trong vòng 12 giờ"),
    IN_1DAY("Trong vòng 1 ngày"),
    IN_2DAY("Trong vòng 2 ngày"),
    IN_3DAY("Trong vòng 3 ngày"),
    IN_4DAY("Trong vòng 4 ngày"),
    IN_5DAY("Trong vòng 5 ngày");

    public final String label;

    DeliveryPeriod(String label) {
        this.label = label;
    }

    public static String valueOfLabel(Object status) {
        for (DeliveryPeriod period : values()) {
            if (period.equals(status)) {
                return period.label;
            }
        }
        return null;
    }
}
