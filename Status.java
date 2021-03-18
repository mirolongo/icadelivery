package com.icadelivery;

enum Status {
    Start(1), Plockas(2), Levereras(3), Levererad(4);
    private final int id;
    Status(int id) {
        this.id = id;
    }
    int id() {
        return id;
    }
}