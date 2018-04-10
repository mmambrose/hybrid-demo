package io.pivotal.ecosystem.ordermgmtservice;

/**
 * Created by mambrose on 3/25/18.
 */
public class OrderResult {

    Integer orderID;
    String orderStatus;

    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderID() {
        return orderID;
    }
    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    @Override
    public String toString()
    {
        return "OrderResult [orderID=" + orderID + ", Status Code=" + orderStatus + "]";

    }

}
