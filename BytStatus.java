package com.icadelivery;

import java.util.Date;

class BytStatus {
    int orderId;
    int orderStatusId;
    Date datum;
    public BytStatus(int orderId, int orderStatusId, Date datum) {
        this.orderId = orderId;
        this.orderStatusId = orderStatusId;
        this.datum = datum;
    }
}
