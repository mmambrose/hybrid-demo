package io.pivotal.ecosystem.webstore;

/**
 * Created by cstewart on 3/13/18.
 */
public class OrderModel {

        private Integer productID;
        private Integer quantity;
        private String shippingAddress;


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

        public String getShippingAddress(){ return shippingAddress; }
        public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }




        @Override
        public String toString()
        {
            return "OrderModel [productID=" + productID + ", Quantity=" + quantity + ", Shipping Address=" + shippingAddress + "]";

        }
}
