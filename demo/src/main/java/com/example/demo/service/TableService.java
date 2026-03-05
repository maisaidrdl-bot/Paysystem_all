package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.TableRepository;

@Service
public class TableService {

    @Autowired
    private TableRepository repo;

    
    // FETCH LOGIC
    
    public List<Map<String,Object>> fetchLogic(String ruleName){
        
        // 🔁 ruleName is business name
        // Repository uses col1 internally
        return repo.fetchQuery(ruleName);
    }


    
    // ADD LOGIC
    
    //public void addLogic(Map<String,Object> req){

        // 🔁 CHANGE HERE if your frontend uses different name
        //String ruleName = (String) req.get("ruleName");

        // get max serial for this rule
        // Integer maxSerial = repo.getMaxSerial(ruleName);

        //Integer newSerial = maxSerial + 1;

        // 🔁 If your date key is different, change below
        //Date monthYear = (Date) req.get("monthYear");

        //repo.insertQuery(req, newSerial, monthYear);
    //}
   public void addLogic(Map<String,Object> req) {

    //  1. ruleName (REQUIRED) 
    String ruleName = req.get("ruleName") != null 
            ? req.get("ruleName").toString().trim() 
            : null;

    if(ruleName == null || ruleName.isEmpty()){
        throw new RuntimeException("ruleName is required");
    }

    //  2. serial (SAFE) 
    Integer maxSerial = repo.getMaxSerial(ruleName);
    if(maxSerial == null){
        maxSerial = 0;
    }
    Integer newSerial = maxSerial + 1;

    //  3. percent (SAFE NUMBER) 
    Double percent = null;
    if(req.get("percent") != null){
        try{
            percent = Double.valueOf(req.get("percent").toString());
        }catch(NumberFormatException ex){
            percent = null;
        }
    }

    //  4. amount (SAFE NUMBER) 
    Double amount = null;
    if(req.get("amount") != null){
        try{
            amount = Double.valueOf(req.get("amount").toString());
        }catch(NumberFormatException ex){
            amount = null;
        }
    }

    //  5. monthYear (SAFE DATE) 
    java.sql.Date monthYear = null;
    if(req.get("monthYear") != null){
        try{
            monthYear = java.sql.Date.valueOf(req.get("monthYear").toString());
        }catch(Exception ex){
            monthYear = null;   // fallback
        }
    }

    // 6. remark 
    String remark = req.get("remark") != null 
            ? req.get("remark").toString() 
            : null;

    // 7. Create new safe map
    Map<String,Object> data = new HashMap<>();
    data.put("ruleName", ruleName);
    data.put("percent", percent);
    data.put("amount", amount);
    data.put("remark", remark);

    // 8. FINAL CALL 
    try{
        repo.insertQuery(data, newSerial, monthYear);
    }catch(Exception e){
        e.printStackTrace();
        throw new RuntimeException("Database Insert Failed: " + e.getMessage());
    }
}


    
    // UPDATE NAVIGATION CHECK
    
    public Boolean allowUpdateLogic(
            String ruleName,
            Integer serialNo){

        Integer count;
        count = repo.checkUpdateAllowed(
                ruleName,
                serialNo);

        return count == 0;
    }


    
    // UPDATE LOGIC
    
    public void updateLogic(Map<String,Object> req){

        // 🔁 CHANGE if frontend uses different key names
        repo.updateQuery(req);
    }


    
    // DELETE NAVIGATION CHECK
    
    public Boolean allowDeleteLogic(
            String ruleName,
            Integer serialNo){

        Integer count = repo.checkDeleteAllowed(
                ruleName,
                serialNo);

        return count == 0;
    }


    
    // DELETE LOGIC
    
    //public void deleteLogic(Map<String,Object> req){

        // 🔁 CHANGE if frontend keys are different
      //  String ruleName = (String) req.get("ruleName");
      //  Integer serialNo =
    //Integer.parseInt(req.get("serialNo").toString());

        //repo.deleteQuery(ruleName, serialNo);
    //}
    public void deleteLogic(Map<String,Object> req){

    String ruleName = req.get("ruleName") != null 
            ? req.get("ruleName").toString() 
            : null;

    if(ruleName == null){
        throw new RuntimeException("ruleName required");
    }

    Integer serialNo = null;
    if(req.get("serialNo") != null){
        try{
            serialNo = Integer.valueOf(req.get("serialNo").toString());
        }catch(NumberFormatException ex){
            throw new RuntimeException("Invalid serialNo");
        }
    }

    repo.deleteQuery(ruleName, serialNo);
}
}