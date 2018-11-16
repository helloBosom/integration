package fun.peri.utils.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 任何类型操作
 *
 * @param <K>
 * @param <V>
 */

@Component
public class RedisValueTemp<K, V> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<K, V> Value;

    @Bean
    public ValueOperations<K, V> ValueOperations() {
        return redisTemplate.opsForValue();
    }


    /**
     * 设置key value
     *
     * @param key
     * @param value
     */
    public void set(K key, V value) {
        Value.set(key, value);
    }

    /**
     * 设置key value 以及过期时间
     *
     * @param key
     * @param value
     * @param time
     */
    public void set(K key, V value, long time) {
        Value.set(key, value, time, TimeUnit.MILLISECONDS);
    }


    /**
     * 获取value
     *
     * @param key
     * @return
     */
    public V get(K key) {
        return Value.get(key);
    }

    /**
     * 批量增加
     *
     * @param map
     */
    public void multiSet(Map<? extends K, ? extends V> map) {
        Value.multiSet(map);
    }

    /**
     * 设置新值返回旧值
     *
     * @param key
     * @param value
     * @return
     */
    public V getAndSet(K key, V value) {
        return Value.getAndSet(key, value);
    }


    /**
     * 批量获取值
     *
     * @param keys
     * @return
     */
    List<V> multiGet(Collection<K> keys) {
        return Value.multiGet(keys);
    }

    /**
     * 增长器
     *
     * @param key
     * @param step
     * @return
     */
    public long incLong(K key, long step) {
        return Value.increment(key, step);
    }

    /**
     * 删除某个key
     *
     * @param keys
     */
    public void del(K... keys) {
        if(keys != null && keys.length > 0) {
            if(keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    /**
     * 是否包含某个key
     *
     * @param key
     * @return
     */
    public Boolean hasKey(K key) {
        return redisTemplate.hasKey(key);
    }


    /**
     * 为给定 key 设置过期时间。
     *
     * @param key
     * @param timeout
     * @param unit    时间单位
     * @return
     */
    public Boolean expire(K key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }
}
