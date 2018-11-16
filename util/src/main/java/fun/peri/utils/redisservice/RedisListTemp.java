package fun.peri.utils.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisListTemp<K, V>{

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ListOperations<K, V> listOperations;

    @Bean
    public ListOperations<K, V> ListOperations(){
        return redisTemplate.opsForList();
    }


    /**
     * Redis Lrange 返回列表中指定区间内的元素，
     * 区间以偏移量 START 和 END 指定。
     * 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，
     * 以此类推。 你也可以使用负数下标，
     * 以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<V> range(K key, long start, long end) {
        return listOperations.range(key,start,end);
    }

    /**
     * Redis Ltrim 对一个列表进行修剪(trim)，就是说，
     * 让列表只保留指定区间内的元素，不
     * 在指定区间之内的元素都将被删除。
     * 下标 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，
     * 以此类推。
     * @param key
     * @param start
     * @param end
     */
    public void trim(K key, long start, long end) {
        listOperations.trim(key,start,end);
    }


    /**
     * Redis Llen 命令用于返回列表的长度。 如果列表 key 不存在，
     * 则 key 被解释为一个空列表，返回 0 。
     * 如果 key 不是列表类型，返回一个错误
     * @param key
     * @return
     */
    public Long size(K key) {
        return listOperations.size(key);
    }


    /**
     * Redis Lpush 命令将一个值插入到列表头部。 如果 key 不存在，
     * 一个空列表会被创建并执行 LPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误。
     * @param key
     * @param value
     * @return
     */
    public Long leftPush(K key, V value) {
        return listOperations.leftPush(key,value);
    }


    /**
     * Redis Lpush 命令将多个值插入到列表头部。 如果 key 不存在，
     * 一个空列表会被创建并执行 LPUSH 操作。
     * 当 key 存在但不是列表类型时，返回一个错误。
     * @param key
     * @param values
     * @return
     */
    public Long leftPushAll(K key, V... values) {
        return listOperations.leftPushAll(key,values);
    }

    /**
     * Redis Lpush 命令将多个值插入到列表头部。 如果 key 不存在，
     * 一个空列表会被创建并执行 LPUSH 操作。
     * 当 key 存在但不是列表类型时，返回一个错误。
     * @param key
     * @param values
     * @return
     */
    public Long leftPushAll(K key, Collection<V> values) {
        return listOperations.leftPushAll(key,values);
    }

    /**
     *Redis Lpushx 将一个值插入到已存在的列表头部，列表不存在时操作无效。
     * @param key
     * @param value
     * @return
     */
    public Long leftPushIfPresent(K key, V value) {
        return listOperations.leftPushIfPresent(key,value);
    }

    /**
     * 将一个值插入到列表指定值的前面(左边)
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    public Long leftPush(K key, V pivot, V value) {
        return listOperations.leftPush(key,pivot,value);
    }

    /**
     * 用于将一个或多个值插入到列表的尾部(最右边)。
     * 如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。
     * 当列表存在但不是列表类型时，返回一个错误。
     * @param key
     * @param value
     * @return
     */
    public Long rightPush(K key, V value) {
        return listOperations.rightPush(key,value);
    }

    /**
     * 用于将一个或多个值插入到列表的尾部(最右边)。
     * 如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。
     * 当列表存在但不是列表类型时，返回一个错误。
     * @param key
     * @param values
     * @return
     */
    public Long rightPushAll(K key, V... values) {
        return listOperations.rightPushAll(key,values);
    }


    /**
     * 用于将一个或多个值插入到列表的尾部(最右边)。
     * 如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。
     * 当列表存在但不是列表类型时，返回一个错误。
     * @param key
     * @param values
     * @return
     */
    public Long rightPushAll(K key, Collection<V> values) {
        return listOperations.rightPushAll(key,values);
    }

    /**
     * Redis Rpushx 命令用于将一个或多个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效。
     * @param key
     * @param value
     * @return
     */
    public Long rightPushIfPresent(K key, V value) {
        return listOperations.rightPushIfPresent(key,value);
    }

    /**
     *将一个值插入到列表指定值的后面(右边)
     * @param key
     * @param pivot
     * @param value
     * @return
     */
    public Long rightPush(K key, V pivot, V value) {
        return listOperations.rightPush(key,pivot,value);
    }

    /**
     * Redis Lset 通过索引来设置元素的值。当索引参数超出范围，或对一个空列表进行 LSET 时，返回一个错误。
     * @param key
     * @param index
     * @param value
     */
    public void set(K key, long index, V value) {
        listOperations.set(key,index,value);
    }


    /**
     * Redis Lrem 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     * COUNT 的值可以是以下几种：
     * count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     * count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     * count = 0 : 移除表中所有与 VALUE 相等的值。
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Long remove(K key, long count, Object value) {
        return listOperations.remove(key,count,value);
    }

    /**
     * Redis Lindex 命令用于通过索引获取列表中的元素。
     * 你也可以使用负数下标，
     * 以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * @param key
     * @param index
     * @return
     */
    public V index(K key, long index) {
        return listOperations.index(key,index);
    }

    /**
     * 用于移除并返回列表的第一个元素。
     * @param key
     * @return
     */
    public V leftPop(K key) {
        return listOperations.leftPop(key);
    }

    /**
     * Redis Blpop 命令移出并获取列表的第一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public V leftPop(K key, long timeout, TimeUnit unit) {
        return listOperations.leftPop(key,timeout,unit);
    }


    /**
     * Redis Rpop 命令用于移除并返回列表的最后一个元素。
     * @param key
     * @return
     */
    public V rightPop(K key) {
        return listOperations.rightPop(key);
    }


    /**
     * Redis Brpop 命令移出并获取列表的最后一个元素，
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public V rightPop(K key, long timeout, TimeUnit unit) {
        return listOperations.rightPop(key,timeout,unit);
    }

    /**
     * Redis Rpoplpush 命令用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回。
     * @param sourceKey
     * @param destinationKey
     * @return
     */
    public V rightPopAndLeftPush(K sourceKey, K destinationKey) {
        return listOperations.rightPopAndLeftPush(sourceKey,destinationKey);
    }

    /**
     * Redis Brpoplpush 命令从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它；
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param sourceKey
     * @param destinationKey
     * @param timeout
     * @param unit
     * @return
     */
    public V rightPopAndLeftPush(K sourceKey, K destinationKey, long timeout, TimeUnit unit) {
        return listOperations.rightPopAndLeftPush(sourceKey,destinationKey,timeout,unit);
    }
}
