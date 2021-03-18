package com.icadelivery;

public class Main {
    public static void main(String[] args) throws Exception {
        Order order = new Order(1);
        order.add(1, 3);
        order.add(2, 4);
        order.add(3);
        order.add(4, 2);
        order.goToNextStatus();
        order.goToNextStatus();
        order.goToNextStatus();
        Bd bd = new Bd();
        bd.insert(order);
        Order deliveredOrder = new Order(2, 1);
        deliveredOrder.add(7, 1);
        deliveredOrder.add(2, 10);
        deliveredOrder.add(4, 7);
        deliveredOrder.add(5, 8);
        deliveredOrder.goToNextStatus();
        deliveredOrder.goToNextStatus();
        deliveredOrder.goToNextStatus();
        bd.insert(deliveredOrder);
        }
    }
