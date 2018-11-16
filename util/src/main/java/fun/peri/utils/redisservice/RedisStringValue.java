package fun.peri.utils.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisStringValue {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ValueOperations<String, String> Value;

    @Bean
    public ValueOperations<String, String> StringValueOperations(){
        return stringRedisTemplate.opsForValue();
    }

    /**
     * 设置key value
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        Value.set(key,value);
    }

    /**
     * 设置key value 以及过期时间
     * @param key
     * @param value
     * @param time
     */
    public void set(String key, String value, long time) {
        Value.set(key,value, time, TimeUnit.MILLISECONDS);
    }


    /**
     * 获取value
     * @param key
     * @return
     */
    public String get(String key) {
        return Value.get(key);
    }

    /**
     * 批量增加
     * @param map
     */
    public void multiSet(Map<? extends String, ? extends String> map){
        Value.multiSet(map);
    }

    /**
     * 设置新值返回旧值
     * @param key
     * @param value
     * @return
     */
    public String getAndSet(String key, String value){
        return Value.getAndSet(key,value);
    }


    /**
     * 批量获取值
     * @param keys
     * @return
     */
    public List<String> multiGet(Collection<String> keys){
        return Value.multiGet(keys);
    }

    /**
     * 增长器
     * @param key
     * @param step
     * @return
     */
    public long incLong(String key, long step) {
        return Value.increment(key, step);
    }

    /**
     * 删除某个key
     * @param keys
     */
    public void del(String... keys){
        if(keys!=null && keys.length > 0){
            if(keys.length == 1){
                stringRedisTemplate.delete(keys[0]);
            }else{
                stringRedisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    /**
     * 是否包含某个key
     * @param key
     * @return
     */
    public Boolean hasKey(String key){
        return stringRedisTemplate.hasKey(key);
    }


    /**
     * 为给定 key 设置过期时间。
     * @param key
     * @param timeout
     * @param unit 时间单位
     * @return
     */
    public Boolean expire(String key, final long timeout, final TimeUnit unit){

        return stringRedisTemplate.expire(key,timeout,unit);
    }


    /**
     * key模糊查询
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern){
        return  stringRedisTemplate.keys(pattern);
    }

    /**
     * 模糊删除
     * @param pattern
     */
    public void  delBykeys(String pattern){
        stringRedisTemplate.delete(stringRedisTemplate.keys(pattern));
    }


    /**
     * 模糊查询value
     * @param pattern
     * @return
     */
    public List<String> multiGetByKeys(String pattern){
        return Value.multiGet(stringRedisTemplate.keys(pattern));
    }

}
