package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RuleEntity;
import com.example.demo.entity.RuleEntityId;

public interface TableRepository 
        extends JpaRepository<RuleEntity, RuleEntityId> {

    // JPA will auto create query
    // List<RuleEntity> findByRuleType(String ruleType);

    public List<RuleEntity> findByRuleTypeOrderByIdDesc(String ruleType);

}