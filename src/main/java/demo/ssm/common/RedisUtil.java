package demo.ssm.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 邝明山
 * on 2020/10/7 10:48
 */
@Component
public class RedisUtil {
    @Autowired
    RedisTemplate redisTemplate;

    public String getString(String key){
        Object value=redisTemplate.opsForValue().get(key);
        return value.toString();
    }
    public boolean setString(String key,String value){
        redisTemplate.opsForValue().set(key,value,300, TimeUnit.SECONDS);
        return true;
    }
}
