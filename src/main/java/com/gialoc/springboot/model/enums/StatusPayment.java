package com.gialoc.springboot.model.enums;

public enum StatusPayment {
	CASH("Thanh Toán Tiền Mặt"), ONLINE("Thanh Toán Online");

	public final String label;

	StatusPayment(String label) {
		this.label = label;
	}

	public static String valueOfLabel(Object status) {
		for (StatusPayment period : values()) {
			if (period.equals(status)) {
				return period.label;
			}
		}
		return null;
	}
}
