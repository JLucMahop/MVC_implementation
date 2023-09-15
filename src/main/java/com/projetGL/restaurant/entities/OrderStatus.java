package com.projetGL.restaurant.entities;

public enum OrderStatus {

    ORDER("ORDER"),
    IN_PROGRESS("IN_PROGRESS"),
    FINISH("FINISH"),
    SERVED("SERVED"),
    PAID("PAID");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
