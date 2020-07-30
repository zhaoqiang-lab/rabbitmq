package com.mine;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @Description
 * @Author ZHAOQIANG
 * @Date 2020/07/10
 */
public class DESTest {

    public static void main(String[] args) {
        String greeting = "Hello World";
        String key = "1234567890";
        try {
            byte[] encryptArray = DESTest.encryptDES(greeting.getBytes(), key.getBytes());
            System.out.println(greeting + "加密后的结果为:" + new String(encryptArray));
            byte[] decryptArray = DESTest.decryptDES(encryptArray, key.getBytes());
            System.out.println(greeting + "解密后的结果为:" + new String(decryptArray));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * DES算法要有一个随机数源,因为Random是根据时间戳生成的有限随机数,比较容易破解,所以在这里使用SecureRandom
     */


    /**
     * 加密
     *
     * @param contentArray 需要加密的加密串【字节类型】
     * @param keyArray     加密需要的密钥
     */
    public static byte[] encryptDES(byte[] contentArray, byte[] keyArray) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        return des(contentArray, keyArray, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密操作
     *
     * @param encryptArray 需要解密的加密串【字节类型】
     * @param keyArray     解密需要的密钥
     */
    public static byte[] decryptDES(byte[] encryptArray, byte[] keyArray) throws NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException {
        return des(encryptArray, keyArray, Cipher.DECRYPT_MODE);
    }

    private static byte[] des(byte[] contentArray, byte[] keyArray, int mode) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException {
        SecretKey secretKey = getSecretKey(keyArray);
        //获取真正执行加/解密操作的Cipher
        Cipher cipher = Cipher.getInstance("DES");
        //执行加/解密操作
        cipher.init(mode, secretKey, new SecureRandom());
        return cipher.doFinal(contentArray);
    }

    private static SecretKey getSecretKey(byte[] keyArray) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec desKeySpec = new DESKeySpec(keyArray);
        //创建DES密钥工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        //用密钥工厂将DESKeySpec转换成密钥key
        return keyFactory.generateSecret(desKeySpec);
    }
}
