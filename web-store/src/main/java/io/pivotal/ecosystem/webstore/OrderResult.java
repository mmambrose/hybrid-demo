package io.pivotal.ecosystem.webstore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mambrose on 3/17/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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

    public String getFulfilledBy() {
        return fulfilledBy;
    }

    public void setFulfilledBy(String fulfilledBy) {
        this.fulfilledBy = fulfilledBy;
    }

    @Override
    public String toString()
    {
        return "OrderResult [orderID=" + orderID + ", Status Code=" + orderStatus + "]";

    }
}
