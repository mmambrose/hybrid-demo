package io.pivotal.ecosystem.webstore;

import java.awt.*;

/**
 * Created by cstewart on 4/16/18.
 */
public class ProductModel {

    private Integer productID;
    private String productDescription;
    private Image productImage;

    public ProductModel(){
        this.productID = 1;
        this.productDescription = "blue T pot";
    }

    public Integer getProductID() { return productID; }
    public void setProductID(Integer productID) { this.productID = productID; }

    public String getProductDescription() { return productDescription; }
    public void setProductDescription(String productDescription) { this.productDescription = productDescription; }

    public Image getProductImage() { return productImage; }
    public void setProductImage(Image productImage) { this.productImage = productImage; }

    @Override
    public String toString()
    {
        return "ProductModel [productID=" + productID + ", Description=" + productDescription + ", Image=" + productImage + "]";

    }
}
