package com.example.demo.entity;

import java.io.Serializable;
import java.util.Objects;

public class RuleEntityId implements Serializable {

    private Long id;
    private String ruleType;

    public RuleEntityId() {
    }

    public RuleEntityId(Long id, String ruleType) {
        this.id = id;
        this.ruleType = ruleType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RuleEntityId that = (RuleEntityId) o;

        return Objects.equals(id, that.id) &&
               Objects.equals(ruleType, that.ruleType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ruleType);
    }
}