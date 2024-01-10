package com.basic.encryption;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 加密/解密算法
 * 消息摘要：MD5、SHA-1、SHA-256
 * 对称：AES、DES
 * 非对称：RSA
 *
 * @author ZL
 */
public class LoginService {

    /**
     * 消息摘要
     * 解决方案：使用密码哈希和盐值加密技术进行密码处理，以避免针对单一密钥的攻击和彩虹表攻击
     * 角色和设定：程序猿需要与安全管理员共同开发或实现密码处理方案，并规定随机盐值的生成方法
     * @param password
     * @param salt
     * @return
     * @throws NoSuchAlgorithmException
     *
     */
    public String getHashedPassword(String password, String salt, String algorithm) {
        String saltedPassword = password + salt;
        byte[] hash = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);// MD5 or SHA-1 or SHA-256
            hash = messageDigest.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new String(Base64.getEncoder().encode(hash));
    }


    /**
     * MD5
     * @param passwd
     * @param username
     * @return
     */
    public String encode(String passwd, String username) {

        Base64.Encoder encoder = Base64.getEncoder();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("encode()加密失败");//一般使用logger.error("encode()加密失败", e);
        }
        try {
            if (md5 != null) {
                passwd = encoder.encodeToString(md5.digest((passwd + username).getBytes("utf-8")));
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("encode()加密失败");//一般使用logger.error("encode()加密失败", e);
        }

        return passwd; //存数据库中
    }

    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        System.out.println(loginService.getHashedPassword("123456", "111", "SHA-256"));
        System.out.println(loginService.encode("123456", "zz"));
    }

}
