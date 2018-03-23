package io.pivotal.ecosystem.ordermgmtservice;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by mambrose on 3/17/18.
 */
@Entity
public class OrderModel implements Serializable {

    @Id
    @GeneratedValue
    private Integer orderID;

    @Column
    private Integer productID;

    @Column
    private Integer quantity;

    @Column
    private String destinationZipCode;

//    @Column
//    @JsonIgnore
//    private String Status;

    public OrderModel(){

    }

//    public OrderModel(Integer productID, Integer quantity, String destinationZipCode){
//        this.productID = productID;
//        this.quantity = quantity;
//        this.destinationZipCode = destinationZipCode;
//        this.Status = "SUBMITTED";
//    }


    public Integer getOrderID(){
        return orderID;
    }

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
//
//    public String getStatus(){return Status;}
//    public void setStatus(String status){this.Status=status;}

    @Override
    public String toString()
    {
        return "OrderModel [orderID=" + orderID + ", productID=" + productID + ", Quantity=" + quantity + ", Shipping Address=" + destinationZipCode + "]";

    }
}
