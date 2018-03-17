package io.pivotal.ecosystem.webstore;

import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by mambrose on 3/17/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderCostModel {

    private BigDecimal costFC1;
    private BigDecimal costFC2;

    public void setCostFC1(BigDecimal costFC1){
        this.costFC1 = costFC1;
    }

    public BigDecimal getCostFC1(){
        return costFC1;
    }

    public void setCostFC2(BigDecimal costFC2) {
        this.costFC2 = costFC2;
    }

    public BigDecimal getCostFC2() {
        return costFC2;
    }

    @Override
    public String toString(){
        return "Costs{" + "FC1=" + costFC1 + ", FC2=" + costFC2 + "}";
    }
}
