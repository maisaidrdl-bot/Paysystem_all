package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;



@Entity
@Table(name = "T_RULE")   // change to your table name
@IdClass(RuleEntityId.class)

public class RuleEntity {

    @Id
    @Column(name = "F_VC2RULENAME")   // change
    private String ruleType;

    @Id
    @Column(name = "F_NSRNO")   // change
    private Long id;

   

    @Column(name = "F_NPERCENTAGE")
    private Double percent;

    @Column(name = "F_NAMOUNT")
    private Double amount;

    @Column(name = "F_DTMONTHYEAR")
    private Date definedDate;

    @Column(name = "F_VC2REMARKS")
    private String remarks;

    @Column(name = "F_DTTRDATE")
    private Date systemDate;


    // --------- Constructors ---------

    public RuleEntity() {
    }

    public RuleEntity(Double amount) {
        this.amount = amount;
    }


    // --------- Getters and Setters --------


    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }


public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getDefinedDate() {
        return definedDate;
    }

    public void setDefinedDate(Date definedDate) {
        this.definedDate = definedDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(Date systemDate) {
        this.systemDate = systemDate;
    }
}