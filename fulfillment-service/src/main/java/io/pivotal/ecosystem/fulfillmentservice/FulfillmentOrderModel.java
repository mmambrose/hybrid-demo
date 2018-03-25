package io.pivotal.ecosystem.fulfillmentservice;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * Created by mambrose on 3/17/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FulfillmentOrderModel implements Serializable {

   // private Integer orderID;
    private Integer productID;
    private Integer quantity;
    private String destinationZipCode;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDestinationZipCode() {
        return destinationZipCode;
    }

    public void setDestinationZipCode(String destinationZipCode) {
        this.destinationZipCode = destinationZipCode;
    }


    @Override
    public String toString() {
        return "FulfillmentOrderModel [productID=" + productID + ", Quantity=" + quantity + ", Shipping Address=" + destinationZipCode + "]";

    }
}