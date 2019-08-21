package com.github.zj.dreamly.config.shiro.cache;

import com.github.zj.dreamly.model.common.Constant;
import com.github.zj.dreamly.util.JedisUtil;
import com.github.zj.dreamly.util.JwtUtil;
import com.github.zj.dreamly.util.common.PropertiesUtil;
import com.github.zj.dreamly.util.common.SerializableUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.*;

/**
 * 重写Shiro的Cache保存读取
 *
 * @author 苍海之南
 * @date 2018/9/4 17:31
 */
public class CustomCache<K, V> implements Cache<K, V> {

    /**
     * 缓存的key名称获取为shiro:cache:account
     *
     * @return java.lang.String
     * @author 苍海之南
     * @date 2018/9/4 18:33
     */
    private String getKey(Object key) {
        return Constant.PREFIX_SHIRO_CACHE + JwtUtil.getClaim(key.toString(), Constant.ACCOUNT);
    }

    /**
     * 获取缓存
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object get(Object key) throws CacheException {

        if (!JedisUtil.exists(this.getKey(key))) {
            return null;
        }
        return JedisUtil.getObject(this.getKey(key));
    }

    /**
     * 保存缓存
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object put(Object key, Object value) throws CacheException {

        // 读取配置文件，获取Redis的Shiro缓存过期时间
        PropertiesUtil.readProperties("config.properties");
        String shiroCacheExpireTime = PropertiesUtil.getProperty("shiroCacheExpireTime");

        // 设置Redis的Shiro缓存
        return JedisUtil.setObject(this.getKey(key), value, Integer.parseInt(shiroCacheExpireTime));
    }

    /**
     * 移除缓存
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object remove(Object key) throws CacheException {

        if (!JedisUtil.exists(this.getKey(key))) {
            return null;
        }
        JedisUtil.delKey(this.getKey(key));
        return null;
    }

    /**
     * 清空所有缓存
     */
    @Override
    public void clear() throws CacheException {
        Objects.requireNonNull(JedisUtil.getJedis()).flushDB();
    }

    /**
     * 缓存的个数
     */
    @Override
    public int size() {
        Long size = Objects.requireNonNull(JedisUtil.getJedis()).dbSize();
        return size.intValue();
    }

    /**
     * 获取所有的key
     */
    @Override
    @SuppressWarnings("unchecked")
    public Set keys() {
        Set<byte[]> keys = Objects.requireNonNull(JedisUtil.getJedis()).keys("*".getBytes());
        Set<Object> set = new HashSet<>();
        for (byte[] bs : keys) {
            set.add(SerializableUtil.unserializable(bs));
        }
        return set;
    }

    /**
     * 获取所有的value
     */
    @Override
    @SuppressWarnings("unchecked")
    public Collection values() {
        Set keys = this.keys();
        List<Object> values = new ArrayList<>();
        for (Object key : keys) {
            values.add(JedisUtil.getObject(this.getKey(key)));
        }
        return values;
    }
}
