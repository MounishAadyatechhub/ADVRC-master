package com.pyro.advance.recharge.dbo;


import com.pyro.advance.recharge.constant.CommonMethod;
import com.pyro.advance.recharge.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DatabaseOperation {

    private final EntityManager entityManager;
    Logger LOGGER = LoggerFactory.getLogger(EntityManager.class);

    @Autowired
    public DatabaseOperation(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public AdvRCHistoryResp checkTransactionDetails(TransHistoryRequestBean transHistoryRequestBean) {
        List<AdvRCTransHistoryDetails> responseList = new ArrayList<AdvRCTransHistoryDetails>();
        AdvRCHistoryResp rep = new AdvRCHistoryResp();
        try {
            StoredProcedureQuery q = entityManager.createStoredProcedureQuery("pkg_advance_rc.get_req_list");
            q.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter(2, Class.class, ParameterMode.REF_CURSOR);
            q.setParameter(1, transHistoryRequestBean.getDestMsisdn());
            q.execute();
            List<Object[]> postComments = q.getResultList();
            for (Object[] dd : postComments) {
                String id = (String) dd[0];
                String recordId = (String) dd[1];
                String amount = (String) dd[2];
                String transactionDate = (String) dd[3];
                AdvRCTransHistoryDetails ss = new AdvRCTransHistoryDetails(id, recordId, amount, transactionDate, "", "");
                responseList.add(ss);
            }

            rep.setStatus("0");
            rep.setDesc("success");
            rep.setAdvRCTransHistoryDetails(responseList);
            return rep;


        } catch (Exception e) {
            LOGGER.error("Error while calling checkTransactionDetails procedure : " + e.getMessage());
            rep.setStatus("201");
            rep.setDesc(e.getMessage());
            return rep;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public AdvRCActivationResp callForceActivation(RechargeRequestBean rechargeRequestBean) {
        AdvRCActivationResp advRCActivationResp = new AdvRCActivationResp();
        try {
            StoredProcedureQuery q = entityManager.createStoredProcedureQuery("pkg_advance_rc.force_activate");
            q.registerStoredProcedureParameter("p_recordid", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("p_dest_msisdn", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("p_response", String.class, ParameterMode.OUT);
            q.setParameter("p_recordid", rechargeRequestBean.getRecordId());
            q.setParameter("p_dest_msisdn", rechargeRequestBean.getDestMsisdn());
            q.execute();

            advRCActivationResp.setDesc((String) q.getOutputParameterValue("p_response"));
            advRCActivationResp.setRecordId(rechargeRequestBean.getRecordId());
            advRCActivationResp.setStatus("0");

            LOGGER.info("callForceActivation Procedure response : " + advRCActivationResp.toString());
            return advRCActivationResp;
        } catch (Exception e) {
            LOGGER.error("Error while calling callForceActivation procedure : " + e.getMessage());
            advRCActivationResp = new AdvRCActivationResp(e.getMessage(), "201");
            return advRCActivationResp;
        } finally {
            if (entityManager.isOpen()) {
                entityManager.close();
            }
        }
    }

    public void getAllTrustedUsers() {
        String sql = "";
        try {
            //where trim(login_id) = trim('" + userid + "') and  trim('" + passwd + "')=trim(MVOUCHER.DES3_INLINE_DECRYPT(PASSWORD,'12345678901234567890'))
            sql = "select user_id,trim(MVOUCHER.DES3_INLINE_DECRYPT(PASSWORD,'12345678901234567890')),allowed_ips,status,allowed_services,iccid from mvoucher.TRUSTED_USERS_DIRECT ";
            Query query = this.entityManager.createNativeQuery(sql);
            System.out.println("**** Direct User Info****: " + sql);
            List<Object[]> resp = (List<Object[]>) query.getResultList();
            System.out.println("****list*****:" + resp);
            for (Object[] item : resp) {
                TrustedUsersDirect trustedUsersDirect = new TrustedUsersDirect();
                String userId = (String) item[0];
                String password = (String) item[1];
                String allowedIPS = (String) item[2];
                String status = (String) item[3];
                String allowedServices = (String) item[4];
                String iccid = (String) item[5];

                trustedUsersDirect.setUserId(userId);
                trustedUsersDirect.setPassword(password);
                trustedUsersDirect.setAllowedIPS(allowedIPS);
                trustedUsersDirect.setIccid(iccid);
                trustedUsersDirect.setStatus(status);
                trustedUsersDirect.setAllowedServices(allowedServices);

                CommonMethod.trustedUsersDirectConcurrentHashMap.put(userId, trustedUsersDirect);

                LOGGER.info("******Direct User Data*********: " + resp.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
