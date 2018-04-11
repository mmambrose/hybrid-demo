package io.pivotal.ecosystem.ordermgmtservice;

/**
 * Created by mambrose on 4/11/18.
 */
public class FulfillmentResult {

    private Integer orderID;
    private Boolean isFulfilled;

    public FulfillmentResult() {
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Boolean getFulfilled() {
        return isFulfilled;
    }

    public void setFulfilled(Boolean fulfilled) {
        isFulfilled = fulfilled;
    }

    public FulfillmentResult(Integer orderID){
        this.orderID=orderID;
    }

    @Override
    public String toString() {
        return "FulfillmentOrderModel [orderID=" + orderID + ", isFulfilled=" + isFulfilled + "]";

    }
}
