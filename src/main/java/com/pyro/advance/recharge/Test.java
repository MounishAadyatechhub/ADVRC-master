package com.pyro.advance.recharge;

import com.google.gson.Gson;
import com.pyro.advance.recharge.model.RechargeRequestBean;
import crypto.ST_decrypt;
import crypto.ST_encrypt;

public class Test {
    public static void main(String[] args) {
        try{
        RechargeRequestBean rechargeRequestBean = new RechargeRequestBean();
        rechargeRequestBean.setInputType("web");
        rechargeRequestBean.setDestMsisdn("9465982349");
        rechargeRequestBean.setmPin("");
        rechargeRequestBean.setPassword("2345");
        rechargeRequestBean.setRecordId("1234567890");
        rechargeRequestBean.setSrcMsisdn("");
        rechargeRequestBean.setUser("IMI");



        Gson g = new Gson();
        String jss=g.toJson(rechargeRequestBean);
        System.out.println("com.pyro.dvendortps.recharge.model.Test.main():"+jss);
        ST_encrypt en= new ST_encrypt();
        String strReq= en.encrypt(jss, "90028472125233882888");
        System.out.println("strReq:"+strReq);
        ST_decrypt st_decrypt = new ST_decrypt();
        String rr =st_decrypt.st_decrypt(strReq, "90028472125233882888");
        System.out.println("com.pyro.dvendortps.recharge.model.Test.main():"+rr);
    }catch(Exception e){
        e.printStackTrace();
    }
    }
}
