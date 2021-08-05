package com.pyro.advance.recharge.utils;

import com.google.gson.Gson;
import com.pyro.advance.recharge.constant.CommonMethod;
import com.pyro.advance.recharge.constant.ErrorCodes;
import com.pyro.advance.recharge.controller.AdvanceRechargeController;
import com.pyro.advance.recharge.dbo.DatabaseOperation;
import com.pyro.advance.recharge.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ProcessReq {

    @Autowired
    TrustedUsersDirect trustedUsersDirect;

    @Autowired
    static RechargeRequestBean rechargeRequestBean;

    @Autowired
    static DatabaseOperation databaseOperation;

    @Autowired
    static HttpServletRequest request;

    public static Logger LOGGER = LoggerFactory.getLogger(AdvanceRechargeController.class);

    public static AdvRCActivationResp processActivationTP(RechargeRequestBean rechargeRequestBean) {
        String ip = request.getRemoteHost();
        AdvRCActivationResp advRCActivationResp = new AdvRCActivationResp("2", "unable to process request now");
        Random random = new Random();
        String recordId = System.nanoTime() + "" + random.nextInt(1000);
        try {
            if (null != rechargeRequestBean) {
                advRCActivationResp.setRecordId(recordId);
                ConcurrentHashMap<String, String> ehm = CommonMethod.errorcodeConcurrentHashMap;
                if (null != ehm) {
                    TrustedUsersDirect trustedUsersDirect = CommonMethod.trustedUsersDirectConcurrentHashMap.get(rechargeRequestBean.getUser());
                    if (trustedUsersDirect != null) {
                        if (trustedUsersDirect.getPassword().equals(rechargeRequestBean.getPassword())) {
                            if (trustedUsersDirect.getAllowedIPS().contains(ip)) {
                                if (rechargeRequestBean.getDestMsisdn() != null && !rechargeRequestBean.getDestMsisdn().equalsIgnoreCase("")) {
                                    advRCActivationResp = databaseOperation.callForceActivation(rechargeRequestBean);
                                    advRCActivationResp = new AdvRCActivationResp(ErrorCodes.SUCCESS, advRCActivationResp.getDesc(), advRCActivationResp.getRecordId());
                                } else {
                                    advRCActivationResp = new AdvRCActivationResp(ErrorCodes.DEST_MSISDN_NOTFOUND, advRCActivationResp.getDesc(), advRCActivationResp.getRecordId());
                                }
                            } else {
                                advRCActivationResp = new AdvRCActivationResp(ErrorCodes.INVALID_IP, ehm.get(ErrorCodes.SUBMAISDN_EMPTY), advRCActivationResp.getRecordId());

                            }
                        } else {
                            advRCActivationResp = new AdvRCActivationResp(ErrorCodes.INVALID_USER_PASSWORD, ehm.get(ErrorCodes.INVALID_SERVICE), advRCActivationResp.getRecordId());

                        }
                    } else {
                        advRCActivationResp = new AdvRCActivationResp(ErrorCodes.INVALID_CREDENTIALS, ehm.get(ErrorCodes.INVALID_CREDENTIALS), advRCActivationResp.getRecordId());

                    }
                } else {
                    advRCActivationResp = new AdvRCActivationResp(ErrorCodes.INVALID_USER, ehm.get(ErrorCodes.INVALID_USER), advRCActivationResp.getRecordId());

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return advRCActivationResp;
    }


    public static AdvRCHistoryResp processTransactionTP(TransHistoryRequestBean transHistoryRequestBean) {
        String ip = request.getRemoteHost();
        AdvRCHistoryResp advRCHistoryResp = new AdvRCHistoryResp("2", "unable to process request now");
        Random random = new Random();
        String recordId = System.nanoTime() + "" + random.nextInt(1000);
        try {
            if (null != transHistoryRequestBean) {
                ConcurrentHashMap<String, String> ehm = CommonMethod.errorcodeConcurrentHashMap;
                if (null != ehm) {
                    TrustedUsersDirect trustedUsersDirect = CommonMethod.trustedUsersDirectConcurrentHashMap.get(transHistoryRequestBean.getUser());
                    if (trustedUsersDirect != null) {
                        if (trustedUsersDirect.getPassword().equals(transHistoryRequestBean.getPassword())) {
                            if (trustedUsersDirect.getAllowedIPS().contains(ip)) {
                                if (transHistoryRequestBean.getDestMsisdn() != null && !transHistoryRequestBean.getDestMsisdn().equalsIgnoreCase("")) {
                                    advRCHistoryResp = databaseOperation.checkTransactionDetails(transHistoryRequestBean);
                                    advRCHistoryResp = new AdvRCHistoryResp(ErrorCodes.SUCCESS, advRCHistoryResp.getDesc());
                                } else {
                                    advRCHistoryResp = new AdvRCHistoryResp(ErrorCodes.DEST_MSISDN_NOTFOUND, advRCHistoryResp.getDesc());
                                }
                            } else {
                                advRCHistoryResp = new AdvRCHistoryResp(ErrorCodes.INVALID_IP, ehm.get(ErrorCodes.SUBMAISDN_EMPTY));

                            }
                        } else {
                            advRCHistoryResp = new AdvRCHistoryResp(ErrorCodes.INVALID_USER_PASSWORD, ehm.get(ErrorCodes.INVALID_SERVICE));

                        }
                    } else {
                        advRCHistoryResp = new AdvRCHistoryResp(ErrorCodes.INVALID_CREDENTIALS, ehm.get(ErrorCodes.INVALID_CREDENTIALS));

                    }
                } else {
                    advRCHistoryResp = new AdvRCHistoryResp(ErrorCodes.INVALID_USER, ehm.get(ErrorCodes.INVALID_USER));

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return advRCHistoryResp;
    }

    public static RechargeRequestBean doActivationTP(String srtReq) {
        Gson g = new Gson();
        String login_id = "";
        String passwd = "";
        RechargeRequestBean req = null;
        try {
            if (null != srtReq) {
                req = g.fromJson(srtReq, RechargeRequestBean.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req;
    }

    public static TransHistoryRequestBean doTransactionTP(String srtReq) {
        Gson g = new Gson();
        String login_id = "";
        String passwd = "";
        TransHistoryRequestBean req = null;
        try {
            if (null != srtReq) {
                req = g.fromJson(srtReq, TransHistoryRequestBean.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return req;
    }
}
