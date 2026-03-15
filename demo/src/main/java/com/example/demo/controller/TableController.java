package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.RuleResponse;
import com.example.demo.entity.RuleEntity;
import com.example.demo.service.TableService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class TableController {

    @Autowired
    private TableService service;



    // 1️⃣ FETCH DATA
    // frontend calls: /rule/fetch?ruleName=RULE1

    @GetMapping("/fetch")
    public List<RuleResponse> fetch(
            @RequestParam String ruleName){

        return service.fetchLogic(ruleName);
    }



    // 2️⃣ ADD ROW
    // frontend sends JSON

    @PostMapping("/add")
    public String add(
            @RequestBody RuleEntity entity){

        service.addLogic(entity);

        return "Row Added Successfully";
    }



    // 3️⃣ UPDATE NAVIGATION CHECK
    // frontend checks before opening update page

    @GetMapping("/allowUpdate")

    public boolean allowUpdate(
            @RequestParam String ruleName,
            @RequestParam Long serialNo){

        return service.allowUpdate(ruleName, serialNo);
    }



    // 4️⃣ UPDATE DATA

    @PutMapping("/update")

    public String update(
            @RequestBody RuleEntity entity){

        service.updateLogic(entity);

        return "Row Updated Successfully";
    }



    // 5️⃣ DELETE NAVIGATION CHECK

    @GetMapping("/allowDelete")

    public boolean allowDelete(
            @RequestParam String ruleName,
            @RequestParam Long serialNo){

        return service.allowDelete(ruleName, serialNo);
    }



    // 6️⃣ DELETE ROW

    @DeleteMapping("/delete")

    public String delete(
            @RequestParam String ruleName,
            @RequestParam Long serialNo){

        service.deleteLogic(ruleName, serialNo);

        return "Row Deleted Successfully";
    }

}