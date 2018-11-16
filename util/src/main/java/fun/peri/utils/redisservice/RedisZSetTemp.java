package fun.peri.utils.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

@Component
public class RedisZSetTemp<K, V> {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ZSetOperations<K, V> zSetOperations;

    @Bean
    public ZSetOperations<K, V> ZSetOperations(){
        return redisTemplate.opsForZSet();
    }


    /**
     * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并
     * 通过重新插入这个成员元素，来保证该成员在正确的位置上。
     * 分数值可以是整数值或双精度浮点数。
     * 如果有序集合 key 不存在，
     * 则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean add(K key, V value, double score) {
        return zSetOperations.add(key,value,score);
    }


    /**
     * Redis Zadd 命令用于将一个或多个成员元素及其分数值加入到有序集当中。
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并
     * 通过重新插入这个成员元素，来保证该成员在正确的位置上。
     * 分数值可以是整数值或双精度浮点数。
     * 如果有序集合 key 不存在，
     * 则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * @param key
     * @param typedTuples
     * @return
     */
    public Long add(K key, Set<TypedTuple<V>> typedTuples) {
        return zSetOperations.add(key,typedTuples);
    }


    /**
     * Redis Zrem 命令用于移除有序集中的一个或多个成员，不存在的成员将被忽略。
     * @param key
     * @param values
     * @return
     */
    public Long remove(K key, Object... values) {
        return zSetOperations.remove(key,values);
    }


    /**
     * Redis Zincrby 命令对有序集合中指定成员的分数加上增量 increment
     * 可以通过传递一个负数值 increment ，让分数减去相应的值，
     * 比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
     * 当 key 不存在，或分数不是 key 的成员时，
     * ZINCRBY key increment member 等同于 ZADD key increment member 。
     * 当 key 不是有序集类型时，返回一个错误。
     * 分数值可以是整数值或双精度浮点数。
     * @param key
     * @param value
     * @param delta
     * @return
     */
    public Double incrementScore(K key, V value, double delta) {
        return zSetOperations.incrementScore(key,value,delta);
    }


    /**
     * Redis Zrank 返回有序集中指定成员的排名。
     * 其中有序集成员按分数值递增(从小到大)顺序排列。
     * @param key
     * @param o
     * @return
     */
    public Long rank(K key, Object o) {
        return zSetOperations.rank(key,o);
    }


    /**
     * Redis Zrevrank 命令返回有序集中成员的排名。
     * 其中有序集成员按分数值递减(从大到小)排序。
     * 排名以 0 为底，也就是说， 分数值最大的成员排名为 0 。
     * 使用 ZRANK 命令可以获得成员按分数值递增(从小到大)排列的排名。
     * @param key
     * @param o
     * @return
     */
    public Long reverseRank(K key, Object o) {
        return zSetOperations.reverseRank(key,o);
    }


    /**
     * Redis Zrange 返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递增(从小到大)来排序。
     * 具有相同分数值的成员按字典序(lexicographical order )来排列。
     * 如果你需要成员按
     * 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，
     * 以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<V> range(K key, long start, long end) {
        return zSetOperations.range(key,start,end);
    }


    /**
     * Redis Zrange 返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递增(从小到大)来排序。
     * 具有相同分数值的成员按字典序(lexicographical order )来排列。
     * 如果你需要成员按
     * 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
     * 下标参数 start 和 stop 都以 0 为底，也就是说，以 0 表示有序集第一个成员，
     * 以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Set<TypedTuple<V>> rangeWithScores(K key, long start, long end) {
        return zSetOperations.rangeWithScores(key,start,end);
    }


    /**
     * Redis Zrangebyscore 返回有序集合中指定分数区间的成员列表。
     * 有序集成员按分数值递增(从小到大)次序排列。
     * 具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，
     * 你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<V> rangeByScore(K key, double min, double max) {
        return zSetOperations.rangeByScore(key,min,max);
    }


    /**
     * Redis Zrangebyscore 返回有序集合中指定分数区间的成员列表。
     * 有序集成员按分数值递增(从小到大)次序排列。
     * 具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，
     * 你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max) {
        return zSetOperations.rangeByScoreWithScores(key,min,max);
    }


    /**
     *  Redis Zrangebyscore 返回有序集合中指定分数区间的成员列表。
     *  有序集成员按分数值递增(从小到大)次序排列。
     * 具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * 参数offset表示从符合条件的第offset个成员开始返回，同时返回count个成员。
     * @param key
     * @param min
     * @param max
     * @param offset 递减
     * @param count 返回数量
     * @return 只返回值
     */
    public Set<V> rangeByScore(K key, double min, double max, long offset, long count) {
        return zSetOperations.rangeByScore(key,min,max,offset,count);
    }

    /**
     *  Redis Zrangebyscore 返回有序集合中指定分数区间的成员列表。
     *  有序集成员按分数值递增(从小到大)次序排列。
     *  具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     *  默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     *  参数offset表示从符合条件的第offset个成员开始返回，同时返回count个成员。
     * @param key
     * @param min
     * @param max
     * @param offset
     * @param count
     * @return 返回值和评分
     */
    public Set<TypedTuple<V>> rangeByScoreWithScores(K key, double min, double max, long offset, long count) {
        return zSetOperations.rangeByScoreWithScores(key,min,max,offset,count);
    }


    /**
     * Redis Zrevrange 命令返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递减(从大到小)来排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     * @param key
     * @param start
     * @param end
     * @return 只返回值
     */
    public Set<V> reverseRange(K key, long start, long end) {
        return zSetOperations.reverseRange(key,start,end);
    }


    /**
     * Redis Zrevrange 命令返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递减(从大到小)来排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     * @param key
     * @param start 开始序列
     * @param end   结束序列
     * @return 返回值和评分
     */
    public Set<TypedTuple<V>> reverseRangeWithScores(K key, long start, long end) {
        return zSetOperations.reverseRangeWithScores(key,start,end);
    }


    /**
     * Redis Zrevrange 命令返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递减(从大到小)来排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     * @param key
     * @param min 最小评分
     * @param max 最大评分
     * @return 返回值
     */
    public Set<V> reverseRangeByScore(K key, double min, double max) {
        return zSetOperations.reverseRangeByScore(key,min,max);
    }


    /**
     * Redis Zrevrange 命令返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递减(从大到小)来排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     * @param key
     * @param min 最小评分
     * @param max 最大评分
     * @return 返回值和评分
     */
    public Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max) {
        return zSetOperations.reverseRangeByScoreWithScores(key,min,max);
    }


    /**
     * Redis Zrevrange 命令返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递减(从大到小)来排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     * 参数offset表示从符合条件的第offset个成员开始返回，同时返回count个成员。
     * @param key
     * @param min 最小评分
     * @param max 最大评分
     * @param offset 第offset个成员开始返回
     * @param count 返回count个成员。
     * @return 返回值
     */
    public Set<V> reverseRangeByScore(K key, double min, double max, long offset, long count) {
        return zSetOperations.reverseRangeByScore(key,min,max,offset,count);
    }


    /**
     * Redis Zrevrange 命令返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递减(从大到小)来排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     * 参数offset表示从符合条件的第offset个成员开始返回，同时返回count个成员。
     * @param key
     * @param min 最小评分
     * @param max 最大评分
     * @param offset 第offset个成员开始返回
     * @param count 返回count个成员。
     * @return 返回值和评分
     */
    public Set<TypedTuple<V>> reverseRangeByScoreWithScores(K key, double min, double max, long offset, long count) {
        return zSetOperations.reverseRangeByScoreWithScores(key,min,max,offset,count);
    }


    /**
     * Redis Zcount 命令用于计算有序集合中指定分数区间的成员数量。
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long count(K key, double min, double max) {
        return zSetOperations.count(key,min,max);
    }

    /**
     * Redis Zcard 命令用于计算集合中元素的数量。
     * @param key
     * @return
     */
    public Long size(K key) {
        return zSetOperations.size(key);
    }


    /**
     * Redis Zcard 命令用于计算集合中元素的数量。
     * @param key
     * @return
     */
    public Long zCard(K key) {
        return zSetOperations.zCard(key);
    }


    /**
     * Redis Zrem 命令用于移除有序集中的一个或多个成员，不存在的成员将被忽略。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * @param key
     * @param o
     * @return
     */
    public Double score(K key, Object o) {
        return zSetOperations.score(key,o);
    }


    /**
     * Redis Zremrangebyrank 命令用于移除有序集中，指定排名(rank)区间内的所有成员。
     * @param key
     * @param start
     * @param end
     * @return
     */
    public Long removeRange(K key, long start, long end) {
        return zSetOperations.removeRange(key,start,end);
    }

    /**
     * Redis Zremrangebyscore 命令用于移除有序集中，指定分数（score）区间内的所有成员。
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long removeRangeByScore(K key, double min, double max) {
        return zSetOperations.removeRangeByScore(key,min,max);
    }

    /**
     * Redis Zunionstore 命令计算给定的一个或多个有序集的并集，
     * 其中给定 key 的数量必须以 numkeys 参数指定，
     * 并将该并集(结果集)储存到 destination 。
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long unionAndStore(K key, K otherKey, K destKey) {
        return zSetOperations.unionAndStore(key,otherKey,destKey);
    }

    /**
     * Redis Zunionstore 命令计算给定的一个或多个有序集的并集，
     * 其中给定 key 的数量必须以 numkeys 参数指定，
     * 并将该并集(结果集)储存到 destination 。
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long unionAndStore(K key, Collection<K> otherKeys, K destKey) {
        return zSetOperations.unionAndStore(key,otherKeys,destKey);
    }

    /**
     * Redis Zinterstore 命令计算给定的一个或多个有序集的交集，
     * 其中给定 key 的数量必须以 numkeys 参数指定，并
     * 将该交集(结果集)储存到 destination 。
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     */
    public Long intersectAndStore(K key, K otherKey, K destKey) {
        return zSetOperations.intersectAndStore(key, otherKey, destKey);
    }


    /**
     * Redis Zinterstore 命令计算给定的一个或多个有序集的交集，
     * 其中给定 key 的数量必须以 numkeys 参数指定，并
     * 将该交集(结果集)储存到 destination 。
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     */
    public Long intersectAndStore(K key, Collection<K> otherKeys, K destKey) {
        return zSetOperations.intersectAndStore(key,otherKeys,destKey);
    }


    /**
     * Redis Zrangebylex 通过字典区间返回有序集合的成员。
     * @param key
     * @param range
     * @return
     */
    public Set<V> rangeByLex(K key, RedisZSetCommands.Range range) {
        return zSetOperations.rangeByLex(key,range);
    }

    /**
     * Redis Zrangebylex 通过字典区间返回有序集合的成员。
     * 参数offset表示从符合条件的第offset个成员开始返回，同时返回count个成员。
     * @param key
     * @param range
     * @param limit
     * @return
     */
    public Set<V> rangeByLex(K key, RedisZSetCommands.Range range, RedisZSetCommands.Limit limit) {
        return zSetOperations.rangeByLex(key,range,limit);
    }


    /**
     * 扫描
     * @param key
     * @param options
     * @return
     */
    public Cursor<TypedTuple<V>> scan(K key, ScanOptions options){
        return zSetOperations.scan(key,options);
    }
}
