/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pyro.advance.recharge.utils;

import java.io.BufferedReader;
import java.security.MessageDigest;


/**
 *
 * @author BALARAM
 */
public class SHA1Convert
{
  public SHA1Convert() {}
  
  private static String convertToHex(byte[] data)
  {
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < data.length; i++) {
      int halfbyte = data[i] >>> 4 & 0xF;
      int two_halfs = 0;
      do {
        if ((halfbyte >= 0) && (halfbyte <= 9)) {
          buf.append((char)(48 + halfbyte));
        } else
          buf.append((char)(97 + (halfbyte - 10)));
        halfbyte = data[i] & 0xF;
      } while (two_halfs++ < 1);
    }
    return buf.toString();
  }
  
  public static String SHA1(String text)
    throws java.security.NoSuchAlgorithmException, java.io.UnsupportedEncodingException
  {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
    byte[] sha1hash = new byte[40];
    md.update(text.getBytes("iso-8859-1"), 0, text.length());
    sha1hash = md.digest();
    return convertToHex(sha1hash).toUpperCase();
  }
  
  public static void main(String[] args)
    throws Exception
  {
//      BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
//    
//    System.out.println("Enter Msisdn with 91 :: ");
//    String msisdn = br.readLine();
//    
//    BufferedReader br1 = new BufferedReader(new java.io.InputStreamReader(System.in));
//    System.out.println(" Eneter Mpin :: ");
//    String mpin = br1.readLine();
      
      String msisdn="9465982349";
      String mpin="121314";
    String sha1_ad2 = SHA1(msisdn + mpin);
    System.out.println(" SHA1 Data :" + sha1_ad2);
    //52C1B534FC9F7574D28943A655122ECD0E8F3224
    //52C1B534FC9F7574D28943A655122ECD0E8F3224
  }
}
