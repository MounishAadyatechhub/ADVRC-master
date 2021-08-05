/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyro.advance.recharge.dbo;

/**
 *
 * @author BALARAM
 */

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public class Authentication extends StoredProcedure
{
  public Authentication(DataSource ds, String spName)
  {
    if (ds != null) {
      super.setDataSource(ds);
      super.setSql(spName);
      
      declareParameter(new SqlParameter("MSISDN", 12));
      declareParameter(new SqlParameter("PASS", 12));
      
      declareParameter(new SqlOutParameter("NAME", 12));
      declareParameter(new SqlOutParameter("CTOPUP_BAL", 4));
      declareParameter(new SqlOutParameter("CBP_BAL", 4));
      declareParameter(new SqlOutParameter("MOBILE_NUMBER", 12));
      declareParameter(new SqlOutParameter("DEALERID", 4));
      declareParameter(new SqlOutParameter("CATEGORY", 12));
      declareParameter(new SqlOutParameter("DATE_OF_BIRTH", 12));
      declareParameter(new SqlOutParameter("ALTERNATE_NUMBER", 12));
      declareParameter(new SqlOutParameter("PAN_NUMBER", 12));
      declareParameter(new SqlOutParameter("MASTER_MOB", 12));
      declareParameter(new SqlOutParameter("MASTER_NAME", 12));
      declareParameter(new SqlOutParameter("REF_MOB", 12));
      declareParameter(new SqlOutParameter("REF_NAME", 12));
      declareParameter(new SqlOutParameter("LEVEL_ID", 4));
      
      declareParameter(new SqlOutParameter("ADDRESS1", 12));
      declareParameter(new SqlOutParameter("CIRCLE_NAME", 12));
      declareParameter(new SqlOutParameter("SSA_NAME", 12));
      declareParameter(new SqlOutParameter("STATUS", 4));
      declareParameter(new SqlOutParameter("STATUS_DESCRIPTION", 12));
      super.compile();
    } else {
     // Config.debuglog.writeDebugLog(getClass().getName() + "|" + "DataSource is NULL", Config.properties.getProperty("debugLog"));
    }
  }
  
  public Map<String, Object> execute(String deviceid, String pass) {
    System.out.println("deviceid >>>>>>> " + deviceid);
    
    Map<String, Object> inputs = new HashMap();
    inputs.put("MSISDN", deviceid);
    inputs.put("PASS", pass);
    try {
      return super.execute(inputs);
    }
    catch (EmptyResultDataAccessException e) {
      Map<String, Object> map = new HashMap();
      e.printStackTrace();
      map.put("STATUS", "-1");
      map.put("STATUS_DESCRIPTION", "No data available due to some internal problem. Please try after some time.");
      return map;
    } catch (Exception e) {
      Map<String, Object> map = new HashMap();
      map.put("STATUS", "-1");
      map.put("STATUS_DESCRIPTION", "Something went wrong. Please try after some time.");
      e.printStackTrace();
      return map;
    }
  }
}

