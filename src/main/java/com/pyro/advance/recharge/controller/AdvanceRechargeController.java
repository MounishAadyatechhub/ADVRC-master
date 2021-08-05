package com.pyro.advance.recharge.controller;


import com.pyro.advance.recharge.constant.CommonMethod;
import com.pyro.advance.recharge.constant.ErrorCodes;
import com.pyro.advance.recharge.model.*;
import com.pyro.advance.recharge.service.RechargeService;
import com.pyro.advance.recharge.utils.ProcessReq;
import crypto.ST_decrypt;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(value = {"/advanceRecharge"})
@Slf4j
public class AdvanceRechargeController {

    @Autowired
    private RechargeService rechargeService;

    Logger LOGGER = LoggerFactory.getLogger(EntityManager.class);

//http://localhost:8090/advanceRecharge/forceActivation?USER=OTA&PASS=1234&IT=APP&MSISDN=919418727508
    @PostMapping(value = {"/forceActivation"} , produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvRCActivationResp forceActivation(HttpServletRequest request, @RequestParam("RECORDID") String RECORDID,
                                               @RequestParam("SRCMSISDN") String SRCMSISDN, @RequestParam("MPIN") String MPIN,
                                               @RequestParam("USER") String USER, @RequestParam("PASS") String PASS,
                                               @RequestParam("IT") String IT, @RequestParam("DESTMSISDN") String DESTMSISDN) {
        RechargeRequestBean rb =new RechargeRequestBean();
        rb.setDestMsisdn(DESTMSISDN);
        rb.setInputType(IT);
        rb.setPassword(PASS);
        rb.setRecordId(RECORDID);
        rb.setSrcMsisdn(SRCMSISDN);
        rb.setUser(USER);
        rb.setmPin(MPIN);
        return rechargeService.doForceActivation(rb);
    }

    @PostMapping(value = {"/transdetails"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvRCHistoryResp getTransactionDetails(HttpServletRequest request, @RequestParam("DESTMSISDN") String DESTMSISDN,
                                                  @RequestParam("SRCMSISDN") String SRCMSISDN, @RequestParam("MPIN") String MPIN,
                                                  @RequestParam(name = "USER", required = false) String USER,
                                                  @RequestParam(name = "PASS", required = false) String PASS,
                                                  @RequestParam(name = "IT", required = false) String IT) {
        TransHistoryRequestBean th =new TransHistoryRequestBean();
        th.setDestMsisdn(DESTMSISDN);
        th.setInputType(IT);
        th.setPassword(PASS);
        th.setSrcMsisdn(SRCMSISDN);
        th.setUser(USER);
        th.setmPin(MPIN);
    return rechargeService.getTransactionDetails(th);

    }


    @GetMapping(value = {"/forceActivationTP"} , produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvRCActivationResp forceActivationTP(HttpServletRequest request, @RequestParam("strReq") String strReq) {
        ST_decrypt st_decrypt = new ST_decrypt();
        AdvRCActivationResp advRCActivationResp = null;
        ConcurrentHashMap<String, String> ehm = CommonMethod.errorcodeConcurrentHashMap;
        try{
            String ddc = st_decrypt.st_decrypt(strReq,"90028472125233882888");
            LOGGER.info("REQUEST :" + ddc);
            RechargeRequestBean rechargeRequestBean = ProcessReq.doActivationTP(ddc);
            advRCActivationResp = rechargeService.doForceActivationTP(rechargeRequestBean,request.getRemoteHost());
        }catch (Exception e){
            e.printStackTrace();
            advRCActivationResp = new AdvRCActivationResp(ErrorCodes.INTERNAL_ERROR,ehm.get(ErrorCodes.INTERNAL_ERROR));
        }
        LOGGER.info("RESPONSE: " + advRCActivationResp.toString());
        return advRCActivationResp;
        /*
        read the request as encrpyted strReq
        decrpyt and convert to req obj in the same req obj
        get the ip from req.remote pass to method
        no change in resp obj
         */
    }


    @GetMapping(value = {"/transdetailsTP"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public AdvRCHistoryResp getTransactionDetailsTP(HttpServletRequest request, @RequestParam("strReq") String strReq) {
        ST_decrypt st_decrypt = new ST_decrypt();
        AdvRCHistoryResp advRCHistoryResp = null;
        ConcurrentHashMap<String, String> ehm = CommonMethod.errorcodeConcurrentHashMap;
        try{
            String ddc = st_decrypt.st_decrypt(strReq,"90028472125233882888");
            LOGGER.info("REQUEST :" + ddc);
            TransHistoryRequestBean transHistoryRequestBean = ProcessReq.doTransactionTP(ddc);
            advRCHistoryResp = rechargeService.getTransactionDetailsTP(transHistoryRequestBean, request.getRemoteHost());
        }catch (Exception e){
            e.printStackTrace();
            advRCHistoryResp = new AdvRCHistoryResp(ErrorCodes.INTERNAL_ERROR,ehm.get(ErrorCodes.INTERNAL_ERROR));
        }
        LOGGER.info("RESPONSE: " + advRCHistoryResp.toString());
        return advRCHistoryResp;
        /*
        read the request as encrpyted strReq
        decrpyt and convert to req obj in the same req obj
        get the ip from req.remote pass to method
        no change in the resp obj
         */
    }
}
/*

 */
