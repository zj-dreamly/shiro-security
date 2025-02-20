package com.github.zj.dreamly.util.common;

import com.github.zj.dreamly.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Serializable工具(JDK)(也可以使用Protobuf自行百度)
 *
 * @author 苍海之南
 * @date 2018/9/4 15:13
 */
public class SerializableUtil {

    /**
     * LOGGER
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(SerializableUtil.class);

    /**
     * 序列化
     *
     * @return byte[]
     * @author 苍海之南
     * @date 2018/9/4 15:14
     */
    public static byte[] serializable(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            return baos.toByteArray();
        } catch (IOException e) {
            LOGGER.error("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
            throw new CustomException("SerializableUtil工具类序列化出现IOException异常:" + e.getMessage());
        }
    }

    /**
     * 反序列化
     *
     * @return java.lang.Object
     * @author 苍海之南
     * @date 2018/9/4 15:14
     */
    public static Object unserializable(byte[] bytes) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            LOGGER.error("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
            throw new CustomException("SerializableUtil工具类反序列化出现ClassNotFoundException异常:" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
            throw new CustomException("SerializableUtil工具类反序列化出现IOException异常:" + e.getMessage());
        }
    }

}
