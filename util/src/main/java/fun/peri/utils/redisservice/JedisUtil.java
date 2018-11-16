package fun.peri.utils.redisservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.List;

@Component
public class JedisUtil {

    @Autowired
    private JedisCluster jedisCluster;


    /**
     *
     * @param key
     * @param value
     */
    public void lpush(String key,String... value){
        jedisCluster.lpush(key,value);
    }


    /**
     * 可以监测key
     * @param time
     * @param key
     * @return
     */
    public List<String> blpop(int time , String key){
        return  jedisCluster.blpop(time,key);
    }
}
