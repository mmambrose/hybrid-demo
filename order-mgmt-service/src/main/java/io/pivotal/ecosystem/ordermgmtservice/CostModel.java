package io.pivotal.ecosystem.ordermgmtservice;

/**
 * Created by mambrose on 3/17/18.
 */
public class CostModel {

    private Integer costFC1;
    private Integer costFC2;

    public CostModel(Integer costFC1, Integer costFC2){
        this.costFC1 = costFC1;
        this.costFC2 = costFC2;
    }

    public void setCostFC1(Integer costFC1){
        this.costFC1 = costFC1;
    }

    public Integer getCostFC1(){
        return costFC1;
    }

    public void setCostFC2(Integer costFC2) {
        this.costFC2 = costFC2;
    }

    public Integer getCostFC2() {
        return costFC2;
    }

    @Override
    public String toString(){
        return "Costs{" + "FC1=" + costFC1 + ", FC2=" + costFC2 + "}";
    }

}
