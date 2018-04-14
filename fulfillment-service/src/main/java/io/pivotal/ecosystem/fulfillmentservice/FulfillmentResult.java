package io.pivotal.ecosystem.fulfillmentservice;

/**
 * Created by mambrose on 4/11/18.
 */
public class FulfillmentResult {

    private Integer orderID;
    private String FC;
    private Boolean isFulfilled;

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

    public String getFC() { return FC;}

    public void setFC(String FC) { this.FC = FC;}

    @Override
    public String toString() {
        return "FulfillmentOrderModel [orderID=" + orderID + ", FC=" + FC + ", isFulfilled=" + isFulfilled + "]";

    }

}
