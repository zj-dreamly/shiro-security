package com.github.zj.dreamly.util;

import com.github.zj.dreamly.exception.CustomUnauthorizedException;
import com.github.zj.dreamly.util.common.Base64ConvertUtil;
import com.github.zj.dreamly.util.common.HexConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Objects;

/**
 * AES加密解密工具类
 *
 * @author 苍海之南
 * @date 2018/8/31 16:39
 */
@Component
public class AesCipherUtil {

    /**
     * AES密码加密私钥(Base64加密)
     */
    private static String encryptAESKey;

    @Value("${encryptAESKey}")
    public void setEncryptAESKey(String encryptAESKey) {
        AesCipherUtil.encryptAESKey = encryptAESKey;
    }

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AesCipherUtil.class);

    /**
     * 加密
     *
     * @return java.lang.String
     * @author 苍海之南
     * @date 2018/8/31 16:56
     */
    public static String enCrypto(String str) {

        final SecretKey deskey = deskey();
        try {
            // 生成Cipher对象,指定其支持的AES算法，Cipher负责完成加密或解密工作
            Cipher c = Cipher.getInstance("AES");
            // 根据密钥，对Cipher对象进行初始化，ENCRYPT_MODE表示加密模式
            c.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] src = str.getBytes();
            // 该字节数组负责保存加密的结果
            byte[] cipherByte = c.doFinal(src);
            // 先将二进制转换成16进制，再返回Base64加密后的String
            return Base64ConvertUtil.encode(HexConvertUtil.parseByte2HexStr(cipherByte));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.error("getInstance()方法异常:" + e.getMessage());
            throw new CustomUnauthorizedException("getInstance()方法异常:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Base64加密异常:" + e.getMessage());
            throw new CustomUnauthorizedException("Base64加密异常:" + e.getMessage());
        } catch (InvalidKeyException e) {
            LOGGER.error("初始化Cipher对象异常:" + e.getMessage());
            throw new CustomUnauthorizedException("初始化Cipher对象异常:" + e.getMessage());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error("加密异常，密钥有误:" + e.getMessage());
            throw new CustomUnauthorizedException("加密异常，密钥有误:" + e.getMessage());
        }
    }

    /**
     * 解密
     *
     * @return java.lang.String
     * @author 苍海之南
     * @date 2018/8/31 16:56
     */
    public static String deCrypto(String str) {

        final SecretKey deskey = deskey();
        try {
            Cipher c = Cipher.getInstance("AES");
            // 根据密钥，对Cipher对象进行初始化，DECRYPT_MODE表示解密模式
            c.init(Cipher.DECRYPT_MODE, deskey);
            // 该字节数组负责保存加密的结果，先对str进行Base64解密，将16进制转换为二进制
            byte[] cipherByte = c.doFinal(Objects.requireNonNull(HexConvertUtil.parseHexStr2Byte(Base64ConvertUtil.decode(str))));
            return new String(cipherByte);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.error("getInstance()方法异常:" + e.getMessage());
            throw new CustomUnauthorizedException("getInstance()方法异常:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Base64加密异常:" + e.getMessage());
            throw new CustomUnauthorizedException("Base64加密异常:" + e.getMessage());
        } catch (InvalidKeyException e) {
            LOGGER.error("初始化Cipher对象异常:" + e.getMessage());
            throw new CustomUnauthorizedException("初始化Cipher对象异常:" + e.getMessage());
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            LOGGER.error("解密异常，密钥有误:" + e.getMessage());
            throw new CustomUnauthorizedException("解密异常，密钥有误:" + e.getMessage());
        }
    }

    private static SecretKey deskey() {

        try {
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            // 实例化支持AES算法的密钥生成器(算法名称命名需按规定，否则抛出异常)
            // KeyGenerator 提供对称密钥生成器的功能，支持各种算法
            KeyGenerator keygen = KeyGenerator.getInstance("AES");
            // 将私钥encryptAESKey先Base64解密后转换为byte[]数组按128位初始化
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(Base64ConvertUtil.decode(encryptAESKey).getBytes());
            keygen.init(128, secureRandom);
            return keygen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("getInstance()方法异常:" + e.getMessage());
            throw new CustomUnauthorizedException("getInstance()方法异常:" + e.getMessage());
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("Base64加密异常:" + e.getMessage());
            throw new CustomUnauthorizedException("Base64加密异常:" + e.getMessage());
        }
    }
}
