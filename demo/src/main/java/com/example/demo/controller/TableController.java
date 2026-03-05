package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.TableService;
 @CrossOrigin
@RestController
@RequestMapping("/api")
public class TableController {

    @Autowired
    private TableService serviceFun;

    
    // FETCH SECTION
    
   @GetMapping("/fetch")
public List<Map<String,Object>> fetchFun(
        @RequestParam String ruleName){

    return serviceFun.fetchLogic(ruleName);
}


    
    // ADD SECTION
    
    @PostMapping("/add")
    public String addFun(
            @RequestBody Map<String,Object> req){

        serviceFun.addLogic(req);
        return "saved";
    }


    
    // UPDATE NAVIGATION CHECK
    
    @GetMapping("/allowUpdate")
    public Boolean allowUpdate(
            @RequestParam String ruleName,
            @RequestParam Integer serialNo){

        return serviceFun.allowUpdateLogic(
                ruleName,serialNo);
    }


    
    // UPDATE SECTION
    
    @PostMapping("/update")
    public String updateFun(
            @RequestBody Map<String,Object> req){

        serviceFun.updateLogic(req);
        return "updated";
    }


    
    // DELETE NAVIGATION CHECK
    
    @GetMapping("/allowDelete")
    public Boolean allowDelete(
            @RequestParam String ruleName,
            @RequestParam Integer serialNo){

        return serviceFun.allowDeleteLogic(
                ruleName,serialNo);
    }


    
    // DELETE SECTION
    
    @PostMapping("/delete")
    public String deleteFun(
            @RequestBody Map<String,Object> req){

        serviceFun.deleteLogic(req);
        return "deleted";
    }
}