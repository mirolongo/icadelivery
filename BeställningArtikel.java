package com.icadelivery;

class BeställningsArtikel {
    int id;
    int orderId;
    int varorId;
    int antal;
    public BeställningsArtikel(int orderId, int varorId, int antal) {
        this.orderId = orderId;
        this.varorId = varorId;
        this.antal = antal;
    }
}
