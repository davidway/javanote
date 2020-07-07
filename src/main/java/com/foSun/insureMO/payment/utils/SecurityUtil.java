/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.foSun.insureMO.payment.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class SecurityUtil {
    private static Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    /**
     * @Description: md5加密
     * @author tit_xuemenglin
     * @date 2019年3月21日 下午1:25:25
     * @version V1.0
     * @param
     * @return String
     */
    public static String md5(String cPlainText) {
        StringBuffer tBuf = new StringBuffer();
        try {
            MessageDigest tMd = MessageDigest.getInstance("MD5");
            tMd.update(cPlainText.getBytes("utf-8"));
            byte[] tByte = tMd.digest();

            for (int j = 0; j < tByte.length; ++j) {
                int i = tByte[j];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    tBuf.append("0");
                }
                tBuf.append(Integer.toHexString(i));
            }
        } catch (Exception e) {
            logger.error("加密错误，{}" , e);
        }
        return tBuf.toString();
    }

    /**
     * @Description:  aes加密
     * @author tit_xuemenglin
     * @date 2019年3月21日 下午1:25:39
     * @version V1.0
     * @param
     * @return String
     */
    public static String aesEncrypt(String content, String encryptKey){
        try {
            return base64Encode(aesEncryptToBytes(content, encryptKey));
        } catch (Exception e) {
            logger.error("加密失败,{}", e);
        }
        return null;
    }

    /**
     * @Description: aes加密
     * @author tit_xuemenglin
     * @date 2019年3月21日 下午1:27:12
     * @version V1.0
     * @param
     * @return byte[]
     */
    private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(1, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    /**
     * @Description:  base64加密
     * @author tit_xuemenglin
     * @date 2019年3月21日 下午1:27:20
     * @version V1.0
     * @param
     * @return String
     */
    private static String base64Encode(byte[] bytes) {
        return (new BASE64Encoder()).encode(bytes);
    }

    /**
     * aes解密
     * @Description:
     * @author tit_xuemenglin
     * @date 2019年3月21日 下午1:34:45
     * @version V1.0
     * @param
     * @return String
     */
    public static String aesDecrypt(String encryptStr, String decryptKey){
        if(StringUtils.isNotBlank(encryptStr)) {
            try {
                return aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * @Description:  base64解密
     * @author tit_xuemenglin
     * @date 2019年3月21日 下午1:34:58
     * @version V1.0
     * @param base64Code
     * @return byte[]
     */
    private static byte[] base64Decode(String base64Code) throws Exception {
        if(StringUtils.isNotBlank(base64Code)) {
            return (new BASE64Decoder()).decodeBuffer(base64Code);
        }
        return null;
    }

    /**
     * @Description:  aes解密
     * @author tit_xuemenglin
     * @date 2019年3月21日 下午1:36:30
     * @version V1.0
     * @param
     * @return String
     */
    private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(2, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String encryMD5(Map<String,String> encryMsgs,String signKey){
        //校验信息完整性
        if(signKey==null || "".equals(signKey.trim()) || encryMsgs==null || encryMsgs.size()<1){
            logger.info("签名信息不完整");
            return null;
        }

        //首先对非空数据进行排序
        TreeMap<String,String> treeMap = new TreeMap<String, String>();
        for (String tKey : encryMsgs.keySet()) {
            String tValue = encryMsgs.get(tKey);
            if(tValue!=null && !"".equals(tValue)){
                treeMap.put(tKey, encryMsgs.get(tKey));
            }
        }
        treeMap.put("key",signKey);
        //拼接字符串
        StringBuffer sb = new StringBuffer();
        for (String tKey : treeMap.keySet()) {
            String value = treeMap.get(tKey);
            if("payAmount".equals(tKey)){
                value = transAmount(new BigDecimal(value));
            }
            sb.append(tKey);
            sb.append("=");
            sb.append(value);
            sb.append("|");
        }
        String tToEncryStr = sb.toString().substring(0, sb.length()-1);
        logger.info("待加签字符串：{}" ,tToEncryStr);
        //签名
        String md5Check = md5(tToEncryStr+signKey);
        return md5Check;
    }

    public static Map<String, String> getValueMap(Object obj) {

        Map<String, String> map = new HashMap<String, String>();
        // System.out.println(obj.getClass());  
        // 获取f对象对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            String varName = fields[i].getName();
            try {
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();
                // 修改访问控制权限  
                fields[i].setAccessible(true);
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);
                if (o != null && !"".equals(o)){
                    map.put(varName, o.toString());
                }

                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;

    }
    private static String transAmount(BigDecimal tPayAmount) {
        BigDecimal fen = tPayAmount.multiply(new BigDecimal("100"));
        long fenAmount = fen.intValue();
        String money = String.valueOf(fenAmount);
        return money;
    }

    public static void main(String[] args) {
        String paykey = "abc123";
        try {
            String a = "WUz7gfTKSy4MLLbLOUchK6UVerFAbsqxdu4+CVY1JM9MdPurDyzQizQBkdKQ1SalpUWOy4uJyVgPU7y+iMYOqLkscWphBEjmljN3HzuDLNirdqddwDxgg/XZI+L7PnTCnUjGi5ge26eoqzI5oMtzKsjqWWefIIUs9vKskuWiiwN7Z+7VZuShptqtwIz+C5xNejDNj/k4cflz/RxE2GRqPikxPmUdHebrpm+w0xrlkzpKsvHJAineqNIuvMwh8MaFWB3qTxz0dk20MZqHS3M2nAOSOLg4ehj+vRnji8lEPYDFjjjzWxH2K5TrDuPHlW+Icfa0EgaP4PhAY20fQUvNIfiC0RG4aJIM6nEYbZ40its=";
//			a = URLDecoder.decode(a, "utf-8");
            String deStr = aesDecrypt(a, paykey);
            System.out.println("deStr=" + deStr);
            JSONObject json = JSONObject.parseObject(deStr);
            json.put("payAmount", json.get("payAmount").toString());
            json.remove("payDate");
            json.remove("accountNo");
            json.remove("bankCode");
            json.remove("bankName");
            System.out.println(json);

            //String sign_msg = SecurityUtil.encryMD5(map, "abc123");
//            System.out.println(sign_msg);
//            if("663942b4e0627f0f5d16b6f35175ee70".equals(sign_msg)) {
//                logger.info("验签成功");
//            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}