package io.pivotal.ecosystem.webstore;

import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * Created by cstewart on 3/13/18.
 */
public class OrderModel {



        private Integer productID;
        private Integer quantity;
        private String destinationZipCode;
        private Integer orderID;
        private String orderStatus;
        private String fulfilledBy;

        public OrderModel() {

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

        public Integer getOrderID() { return orderID; }
        public void setOrderID(Integer orderID) { this.orderID = orderID; }

        public String getOrderStatus() { return orderStatus; }
        public void setOrderStatus(String orderStatus) { this.orderStatus = orderStatus; }

        public String getFulfilledBy() {
                return fulfilledBy;
        }

        public void setFulfilledBy(String fulfilledBy) {
                this.fulfilledBy = fulfilledBy;
        }


        @Override
        public String toString()
        {
            return "OrderModel [productID=" + productID + ", Quantity=" + quantity + ", Shipping Address=" + destinationZipCode + "]";

        }
}
