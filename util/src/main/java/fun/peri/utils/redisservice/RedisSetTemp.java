package fun.peri.utils.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisSetTemp<K, V> {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SetOperations<K, V> setOperations;

    @Bean
    public SetOperations<K,V> SetOperations(){
        return redisTemplate.opsForSet();
    }

    /**
     * 向集合添加一个或多个成员
     * @param key
     * @param values
     * @return
     */
    public Long add(K key, V... values){
        return setOperations.add(key,values);
    }

    /**
     * 移除集合中一个或多个成员
     * @param key
     * @param values
     * @return
     */
    public Long remove(K key, Object... values){
        return setOperations.remove(key,values);
    }

    /**
     * 移除并返回集合中的一个随机元素
     * @param key
     * @return
     */
    public V pop(K key){
        return setOperations.pop(key);
    }

    /**
     * 将 value 元素从 key 集合移动到 destKey 集合
     * @param key
     * @param value
     * @param destKey
     * @return
     */
    public Boolean move(K key, V value, K destKey){
        return setOperations.move(key,value,destKey);
    }

    /**
     *获取集合的成员数
     * @param key
     * @return
     */
    public Long size(K key){
        return setOperations.size(key);
    }

    /**
     * 判断 member 元素是否是集合 key 的成员
     * @param key
     * @param o
     * @return
     */
    public  Boolean isMember(K key, Object o){
        return setOperations.isMember(key,o);
    }

    /**
     * 返回给定所有集合的交集
     * @param key
     * @param otherKey
     * @return
     */
    public  Set<V> intersect(K key, K otherKey){
        return setOperations.intersect(key,otherKey);
    }


    /**
     * 返回给定所有集合的交集
     * @param key
     * @param otherKeys
     * @return
     */
    public Set<V> intersect(K key, Collection<K> otherKeys){
        return setOperations.intersect(key,otherKeys);
    }

    /**
     * 集合的交集存储到目的集合中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long intersectAndStore(K key, K otherKey, K destKey){
        return setOperations.intersectAndStore(key,otherKey,destKey);
    }

    public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey){
        return setOperations.intersectAndStore(key,otherKeys,destKey);
    }


    /**
     * 返回所有给定集合的并集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<V> union(K key, K otherKey){
        return setOperations.union(key,otherKey);
    }

    public Set<V> union(K key, Collection<K> otherKeys){
        return setOperations.union(key,otherKeys);
    }

    public Long unionAndStore(K key, K otherKey, K destKey){
        return setOperations.unionAndStore(key,otherKey,destKey);
    }

    public Long unionAndStore(K key, Collection<K> otherKeys, K destKey){
        return setOperations.unionAndStore(key,otherKeys,destKey);
    }

    /**
     * 返回给定所有集合的差集
     * @param key
     * @param otherKey
     * @return
     */
    public Set<V> difference(K key, K otherKey){
        return setOperations.difference(key,otherKey);
    }

    public Set<V> difference(K key, Collection<K> otherKeys){
        return setOperations.difference(key,otherKeys);
    }

    public Long differenceAndStore(K key, K otherKey, K destKey){
        return setOperations.differenceAndStore(key,otherKey,destKey);
    }

    public Long differenceAndStore(K key, Collection<K> otherKeys, K destKey){
        return setOperations.differenceAndStore(key,otherKeys,destKey);
    }


    /**
     * 返回集合中的所有成员
     * @param key
     * @return
     */
    public Set<V> members(K key){
        return setOperations.members(key);
    }

    /**
     * 随机返回成员
     * @param key
     * @return
     */
    public V randomMember(K key){
        return setOperations.randomMember(key);
    }

    /**
     * 随机返回指定数量的不同成员
     * @param key
     * @param count
     * @return
     */
    public Set<V> distinctRandomMembers(K key, long count){
        return setOperations.distinctRandomMembers(key,count);
    }


    /**
     * 随机返回指定数量成员
     * @param key
     * @param count
     * @return
     */
    public List<V> randomMembers(K key, long count){
        return setOperations.randomMembers(key,count);
    }
    /**
     * 删除某个key
     * @param keys
     */
    public void del(K... keys){
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
    public Boolean hasKey(K key){
        return redisTemplate.hasKey(key);
    }


    /**
     * 为给定 key 设置过期时间。
     * @param key
     * @param timeout
     * @param unit 时间单位
     * @return
     */
    public Boolean expire(K key, final long timeout, final TimeUnit unit){
        return redisTemplate.expire(key,timeout,unit);
    }


    public Cursor<V> scan(K key, ScanOptions options){
        return setOperations.scan(key,options);
    }

}
