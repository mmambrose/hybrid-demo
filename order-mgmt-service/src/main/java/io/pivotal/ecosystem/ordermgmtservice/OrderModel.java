package io.pivotal.ecosystem.ordermgmtservice;

import java.io.Serializable;

/**
 * Created by mambrose on 3/17/18.
 */
public class OrderModel implements Serializable {

    private Integer productID;
    private Integer quantity;
    private String destinationZipCode;


    public Integer getProductID()
    {
        return productID;
    }
    public void setProductID(Integer productID)
    {
        this.productID = productID;
    }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public String getDestinationZipCode(){ return destinationZipCode; }
    public void setDestinationZipCode(String destinationZipCode) { this.destinationZipCode = destinationZipCode; }


    @Override
    public String toString()
    {
        return "OrderModel [productID=" + productID + ", Quantity=" + quantity + ", Shipping Address=" + destinationZipCode + "]";

    }
}
