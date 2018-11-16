package fun.peri.utils.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisHashTemp<H, HK, HV> {

    @Autowired
    private RedisTemplate redisTemplate;


    @Autowired
    private HashOperations<H, HK, HV> Hash;

    @Bean
    public HashOperations<H, HK, HV> HashOperations(){
        return redisTemplate.opsForHash();
    }




    /**
     * hash 设置
     * @param hKey
     * @param key
     * @param value
     */
    public void setHash(H hKey, HK key, HV value) {
        Hash.put(hKey, key, value);
    }


    /**
     * hash 获取
     * @param hKey
     * @param key
     * @return
     */
    public HV getHash(H hKey, HK key) {
        return Hash.get(hKey, key);
    }


    /**
     * 获取所有的hash的key
     *
     * @param hKey
     * @return
     */
    public Set<HK> getHashByHKey(H hKey) {
        return Hash.keys(hKey);
    }

    /**
     * 删除某个hash
     * @param hKey
     * @param keys
     * @return
     */
    public long deleteHash(H hKey, HK... keys) {
        return Hash.delete(hKey, keys);
    }


    /**
     * 查询某个key是否存在
     * @param key
     * @param hashKey
     * @return
     */
    Boolean HashhasKey(H key, Object hashKey){
        return Hash.hasKey(key,hashKey);
    }

    /**
     * 删除某个Hash key
     * @param keys
     */
    public void del(H... keys){
        if(keys!=null && keys.length > 0){
            if(keys.length == 1){
                redisTemplate.delete(keys[0]);
            }else{
                redisTemplate.delete(CollectionUtils.arrayToList(keys));
            }
        }
    }

    /**
     * 是否包含某个key
     * @param key
     * @return
     */
    public Boolean hasKey(H key){
        return redisTemplate.hasKey(key);
    }


    /**
     * 为给定 key 设置过期时间。
     * @param key
     * @param timeout
     * @param unit 时间单位
     * @return
     */
    public Boolean expire(H key, final long timeout, final TimeUnit unit){
        return redisTemplate.expire(key,timeout,unit);
    }

    /**
     *
     * @param key
     * @param options
     * @return
     */
    public Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options){
        return Hash.scan(key,options);
    }
}
