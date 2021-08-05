package com.pyro.advance.recharge.service;

import com.pyro.advance.recharge.constant.CommonMethod;
import com.pyro.advance.recharge.dbo.DatabaseOperation;
import com.pyro.advance.recharge.model.*;

import com.pyro.advance.recharge.utils.ProcessReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RechargeService {

    @Autowired
    private DatabaseOperation databaseOperation;

    @Autowired
    private CommonMethod commonMethod;

    Logger LOGGER = LoggerFactory.getLogger(RechargeService.class);

    public AdvRCActivationResp doForceActivation(RechargeRequestBean rechargeRequestBean) {
        LOGGER.info("doForceActivation : RechargeRequestBean :: " + rechargeRequestBean);
        AdvRCActivationResp advRCActivationResp = databaseOperation.callForceActivation(rechargeRequestBean);
        LOGGER.info("doForceActivation :: Response :: " + advRCActivationResp);
        return advRCActivationResp;
    }

    public AdvRCHistoryResp getTransactionDetails(TransHistoryRequestBean transHistoryRequestBean) {
        LOGGER.info("getTransactionDetails :TransHistoryRequestBean :: " + transHistoryRequestBean.toString());
        AdvRCHistoryResp rechargeResponse = databaseOperation.checkTransactionDetails(transHistoryRequestBean);
        LOGGER.info("getTransactionDetails :: Response :: " + rechargeResponse);
        return rechargeResponse;
    }

    public AdvRCActivationResp doForceActivationTP(RechargeRequestBean rechargeRequestBean, String ip) {
        LOGGER.info("doForceActivation : RechargeRequestBean :: " + rechargeRequestBean);
        AdvRCActivationResp advRCActivationResp = ProcessReq.processActivationTP(rechargeRequestBean);
        LOGGER.info("doForceActivation :: Response :: " + advRCActivationResp);
        return advRCActivationResp;
    }

    public AdvRCHistoryResp getTransactionDetailsTP(TransHistoryRequestBean transHistoryRequestBean, String ip) {
        LOGGER.info("getTransactionDetails :TransHistoryRequestBean :: " + transHistoryRequestBean.toString());
        AdvRCHistoryResp advRCHistoryResp = ProcessReq.processTransactionTP(transHistoryRequestBean);
        LOGGER.info("getTransactionDetails :: Response :: " + advRCHistoryResp);
        return advRCHistoryResp;
    }
}

/*
Write a reqProcessing in util package
doActivationTP and doTranscationTP
validate ip,username,password,servicekey
    if all are success call DB operation no changing the code

 */