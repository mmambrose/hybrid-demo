package io.pivotal.ecosystem.ordermgmtservice;

/**
 * Created by mambrose on 3/25/18.
 */
public class OrderResult {

    Integer orderID;

    String statusCode;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
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
        return "OrderResult [orderID=" + orderID + ", Status Code=" + statusCode + "]";

    }

}
