package com.example.demo.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.RuleResponse;
import com.example.demo.entity.RuleEntity;
import com.example.demo.entity.RuleEntityId;
import com.example.demo.repository.TableRepository;

@Service
public class TableService {

    @Autowired
    private TableRepository repo;



    // FETCH LOGIC

    public List<RuleResponse> fetchLogic(String ruleName){

        List<RuleEntity> list = repo.findByRuleTypeOrderByIdDesc(ruleName);

        List<RuleResponse> result = new ArrayList<>();

        for(RuleEntity e : list){

            // RULE 1
            if(ruleName.equals("HRA")){

                result.add(new RuleResponse(
                        e.getRuleType(),
                        e.getId(),
                        e.getPercent(),
                        null,
                        String.valueOf(e.getDefinedDate()),
                        String.valueOf(e.getSystemDate()),
                        e.getRemarks()
                ));
            }

            // RULE 2
            else if(ruleName.equals("DA")){

                result.add(new RuleResponse(
                        e.getRuleType(),
                        e.getId(),
                        e.getPercent(),
                         null,
                       String.valueOf(e.getDefinedDate()),
                       String.valueOf(e.getSystemDate()),
                        e.getRemarks()
                ));
            }

            // RULE 3
            else if(ruleName.equals("RELIEF")){

                result.add(new RuleResponse(
                        e.getRuleType(),
                        e.getId(),
                        null,
                        e.getAmount(),
                        String.valueOf(e.getDefinedDate()),
                        String.valueOf(e.getSystemDate()),
                        e.getRemarks()
                ));
            }
        }

        return result;
    }



    // ADD LOGIC

    public void addLogic(RuleEntity entity){

        LocalDate sysDate = LocalDate.now();

        //LocalDate effDate =  entity.getDefinedDate().toLocalDate();
        LocalDate effDate =
        entity.getDefinedDate().toInstant()
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalDate().withDayOfMonth(1);

        // Month Year validation
        if(sysDate.getMonthValue() != effDate.getMonthValue()
                ||
                sysDate.getYear() != effDate.getYear()){

            throw new RuntimeException(
                    "Effective month must match system month");
        }

        // Serial generation
        List<RuleEntity> list =
                repo.findByRuleTypeOrderByIdDesc(entity.getRuleType());

        //int maxSerial = 0;
        Long maxSerial = 0L;

        for(RuleEntity e : list){

            if(e.getId() > maxSerial){

                maxSerial = e.getId();
            }
        }

        entity.setId(maxSerial + 1L);

        entity.setSystemDate(
                new Date(System.currentTimeMillis()));

        repo.save(entity);
    }



    // UPDATE NAVIGATION CHECK

    public boolean allowUpdate(String ruleName,
                               Long serialNo){

        RuleEntityId id =
                new RuleEntityId((long) serialNo, ruleName);

        RuleEntity entity =
                repo.findById(id).orElse(null);

        if(entity == null) return false;

        LocalDate sysDate = LocalDate.now();

        //LocalDate effDate =
           //     entity.getDefinedDate().toLocalDate();

           LocalDate effDate =
        entity.getDefinedDate().toInstant()
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalDate();

        return sysDate.getMonthValue()
                ==
                effDate.getMonthValue()
                &&
                sysDate.getYear()
                ==
                effDate.getYear();
    }



    // UPDATE LOGIC

    public void updateLogic(RuleEntity newData){

        RuleEntityId id =
                new RuleEntityId(
        newData.getId(),
        newData.getRuleType());

        RuleEntity old =
                repo.findById(id).orElseThrow();

        // Only allowed fields

        old.setPercent(newData.getPercent());
        old.setAmount(newData.getAmount());
        old.setRemarks(newData.getRemarks());

        old.setSystemDate(
                new Date(System.currentTimeMillis()));

        repo.save(old);
    }



    // DELETE NAVIGATION CHECK

    public boolean allowDelete(String ruleName,
                               Long serialNo){

        return allowUpdate(ruleName,serialNo);
    }



    // DELETE LOGIC

    public void deleteLogic(String ruleName,
                            Long serialNo){

        
           RuleEntityId id =
        new RuleEntityId(serialNo,ruleName );

        repo.deleteById(id);
    }

}