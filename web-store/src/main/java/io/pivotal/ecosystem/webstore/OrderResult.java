package io.pivotal.ecosystem.webstore;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mambrose on 3/17/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
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
