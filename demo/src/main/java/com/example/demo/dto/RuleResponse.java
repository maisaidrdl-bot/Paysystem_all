package com.example.demo.dto;

public class RuleResponse {

    private String ruleName;
    private Long serialNo;
    private Double percent;
    private Double amount;
    private String definedDate;
    private String systemDate;
    private String remark;


    public RuleResponse() {
    }

    public RuleResponse(String ruleName,
                        Long serialNo,
                        Double percent,
                        Double amount,
                        String definedDate,
                        String systemDate,
                        String remark) {

        this.ruleName = ruleName;
        this.serialNo = serialNo;
        this.percent = percent;
        this.amount = amount;
        this.definedDate= definedDate;
        this.systemDate = systemDate;
        this.remark = remark;
    }


    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
     
     public String getDefinedDate()
     {
        return definedDate;
     }
     public void setDefinedDate(String definedDate)
     {
        this.definedDate= definedDate;
     }


     public String  getSystemDate()
     {
        return systemDate;
     }
     public void setSystemDate(String systemDate)
     {
        this.systemDate= systemDate;
     }










    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}