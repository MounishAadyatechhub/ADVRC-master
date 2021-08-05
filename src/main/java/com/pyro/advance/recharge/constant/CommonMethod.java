package com.pyro.advance.recharge.constant;


import com.pyro.advance.recharge.model.TrustedUsersDirect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class CommonMethod {

    @Value("${tps.otaXmlResponse}")
    private int otaXmlResponse;

    Logger LOGGER = LoggerFactory.getLogger(CommonMethod.class);

    public static ConcurrentHashMap<String, TrustedUsersDirect> trustedUsersDirectConcurrentHashMap = new ConcurrentHashMap<String, TrustedUsersDirect>();

    public static ConcurrentHashMap<String, String> errorcodeConcurrentHashMap = new ConcurrentHashMap<String, String>();

    public String convertToXml(String oformat, String procout, String intype, String msisdn) {
        String formated = null;

        try {
            oformat.trim();
            procout.trim();
            if (oformat.length() <= 1 && (intype.equalsIgnoreCase("OTA") || intype.equalsIgnoreCase("USSD") || intype.equalsIgnoreCase("SMS") || intype.equalsIgnoreCase("GPRS"))) {
                oformat = "XML";
            }

            if (otaXmlResponse == 1) {
                if (oformat.equals("XML")) {
                    formated = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?> <!DOCTYPE wml PUBLIC \"-//SmartTrust//DTD WIG-WML 4.0//EN\" \"http://www.smarttrust.com/DTD/WIG-WML4.0.dtd\"><wml> \t <wigplugin name=\"sendserverdatasm\"> \t\t <param name=\"userdata\" value=\"" + procout + "\"/>" + "<param name=\"encoding\" value=\"SMS-DEFAULT\"/> " + "<param name=\"dcs\" value=\"0\"/> " + "<param name=\"destaddress\" value=\"" + msisdn + "\"/> " + "</wigplugin> " + "<card> <p>" + procout + "</p></card>" + "</wml>";
                } else {
                    formated = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?> <wml><card><p>" + procout + "</p></card></wml>\n";
                }
            } else {
                formated = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" ?> <wml><card><p>" + procout + "</p></card></wml>\n";
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return formated;
    }


}
/*
add hash map for all trusted users - old
 */