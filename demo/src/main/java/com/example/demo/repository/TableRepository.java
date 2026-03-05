package com.example.demo.repository;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class TableRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    
    // FETCH QUERY
    
    public List<Map<String,Object>>
        fetchQuery(String F_VC2RULENAME){

        String sql =
        "SELECT * FROM T_RULE "+
        "WHERE UPPER(F_VC2RULENAME)=UPPER(?) "+
        "ORDER BY F_NSRNO DESC";

        return jdbcTemplate
                .queryForList(sql,F_VC2RULENAME);
    }


    
    // MAX SERIAL
    
    public Integer getMaxSerial(
            String  F_VC2RULENAME ){

        String sql =
        "SELECT NVL(MAX(F_NSRNO),0) "+
        "FROM T_RULE "+
        "WHERE UPPER(F_VC2RULENAME)=UPPER(?)";

        return jdbcTemplate
            .queryForObject(
            sql,Integer.class,
            F_VC2RULENAME);
    }


    
    // INSERT QUERY
    
    public void insertQuery(
        Map<String,Object> req,
        Integer serial,
        Date d){

        String sql =
        "INSERT INTO T_RULE "+
        "(F_VC2RULENAME,F_NSRNO,F_NPERCENTAGE,F_NAMOUNT,F_DTMONTHYEAR,F_VC2REMARKS,F_DTTRDATE) "+
        "VALUES(?,?,?,?,?,?,?,TRUNC(SYSDATE))";

        jdbcTemplate.update(sql,
        
        req.get("F_VC2RULENAME"),
        serial,
        req.get("F_NPERCENTAGE"),
        req.get("F_NAMOUNT"),
        d,
        req.get("F_VC2REMARKS"));
    }


    
    // UPDATE VALIDATION
    
    public Integer checkUpdateAllowed(
        String  F_VC2RULENAME,
        Integer F_NSRNO){

        String sql =
        "SELECT COUNT(*) FROM TABLE_NAME "+
        "WHERE UPPER(F_VC2RULENAME)=(?) AND F_NSRNO=? "+
        "AND EXTRACT(MONTH FROM F_DTTRDATE)="
        +"EXTRACT(MONTH FROM F_DTMONTHYEAR)";

        return jdbcTemplate
        .queryForObject(
        sql,Integer.class,
        F_VC2RULENAME,F_NSRNO);
    }


    
    // UPDATE QUERY
    
    public void updateQuery(
            Map<String,Object> req){

        String sql =
        "UPDATE TABLE_NAME SET "+
        "F_NPERCENTAGE=?,F_NAMOUNT=?,F_DTMONTHYEAR=?,F_VC2REMARKS=? "+
        "WHERE UPPER(F_VC2RULENAME)=UPPER(?) AND F_NSRNO=?";

        jdbcTemplate.update(sql,
        req.get("F_NPERCENTAGE"),
        req.get("F_NAMOUNT"),
        req.get("F_DTMONTHYEAR"),
        req.get("F_VC2REMARKS"),
        req.get("F_VC2RULENAME"),
        req.get("F_NSRNO"));
    }


    
    // DELETE VALIDATION
    
    public Integer checkDeleteAllowed(
        String ruleName, Integer F_VC2RULENAME){

        String sql =
        "SELECT COUNT(*) FROM TABLE_NAME "+
        "WHERE UPPER(F_VC2RULENAME)=UPPER(?) AND F_NSRNO=? "+
        "AND EXTRACT(MONTH FROM F_DTTRDATE)="
        +"EXTRACT(MONTH FROM F_DTMONTHYEAR)";

        return jdbcTemplate
        .queryForObject(
        sql,Integer.class,
        F_VC2RULENAME);
    }


    
    // DELETE QUERY
    
    public void deleteQuery(
        String F_VC2RULENAME,
        Integer F_NSRNO){

        String sql =
        "DELETE FROM TABLE_NAME "+
        "WHERE UPPER(F_VC2RULENAME)=UPPER(?) AND F_NSRNO=?";

        jdbcTemplate.update(
                sql,F_VC2RULENAME,F_NSRNO);
    }

   
}