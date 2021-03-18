package com.icadelivery;

import java.util.Date;

class OrderStatus {
    Status status;
    Date date;
    int id;

    public OrderStatus(Status status) {
        this.status = status;
        this.date = new Date();
        this.id = status.id();
    }
}
