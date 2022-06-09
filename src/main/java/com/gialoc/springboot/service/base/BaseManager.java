package com.gialoc.springboot.service.base;

import java.util.UUID;

public class BaseManager {
    public String generateId() {
        String id = UUID.randomUUID().toString();
        return id.replaceAll("-", "");
    }
}
