package io.pivotal.ecosystem.ordermgmtservice;

/**
 * Created by mambrose on 3/25/18.
 */
public class OrderResult {

    Integer orderID;
    String orderStatus;
    String fulfilledBy;

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

    public String getFulfilledBy() {return fulfilledBy;}
    public void setFulfilledBy(String fulfilledBy) {this.fulfilledBy = fulfilledBy;}

    @Override
    public String toString()
    {
        return "OrderResult [orderID=" + orderID + ", Status Code=" + orderStatus + "]";

    }

}
