package com.icadelivery;

import java.util.ArrayList;

class Order {
    int id;
    int kundId;
    int deliveryId;
    Status currentStatus;
    ArrayList<BeställningsArtikel> artiklar = new ArrayList<BeställningsArtikel>();
    ArrayList<OrderStatus> statuses = new ArrayList<OrderStatus>();
    int budFirmorId;
    private Status Start;

    /***
     * Creates new Order to be picked up by the customer
     *
     * @param kundId
     */
    public Order(int kundId) {
        this.kundId = kundId;
        this.deliveryId = DeliveryType.PLOCKA_UPP.id;
        statuses.add(new OrderStatus(Status.Start));
        currentStatus = Status.Start;
    }
    /***
     * Creates new Order to be delivered by the given BudFirmor
     *
     * @param kundId
     * @param budFirmorId
     */
    public Order(int kundId, int budFirmorId) {
        this(kundId);
        this.budFirmorId = budFirmorId;
        this.deliveryId = DeliveryType.LEVERANS.id;
    }
    public void goToNextStatus() {
        switch (currentStatus) {
            case Start:
                currentStatus = Status.Plockas;
                statuses.add(new OrderStatus(Status.Plockas));
                break;
            case Plockas:
                currentStatus = Status.Levereras;
                statuses.add(new OrderStatus(Status.Levereras));
                break;
            case Levereras:
                currentStatus = Status.Levererad;
                statuses.add(new OrderStatus(Status.Levererad));
                break;
        }
    }
    public void add(int varorId, int antal) {
        this.artiklar.add(new BeställningsArtikel(this.id, varorId, antal));
    }
    public void add(int varorId) {
        add(varorId, 1);
    }
}
