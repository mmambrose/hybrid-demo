package io.pivotal.ecosystem.ordermgmtservice;

/**
 * Created by mambrose on 3/17/18.
 */
public class CostModel {

    private Double costFC1;
    private Double costFC2;

    public CostModel(Double costFC1, Double costFC2){
        this.costFC1 = costFC1;
        this.costFC2 = costFC2;
    }

    public void setCostFC1(Double costFC1){
        this.costFC1 = costFC1;
    }

    public Double getCostFC1(){
        return costFC1;
    }

    public void setCostFC2(Double costFC2) {
        this.costFC2 = costFC2;
    }

    public Double getCostFC2() {
        return costFC2;
    }

    @Override
    public String toString(){
        return "Costs{" + "FC1=" + costFC1 + ", FC2=" + costFC2 + "}";
    }

}
