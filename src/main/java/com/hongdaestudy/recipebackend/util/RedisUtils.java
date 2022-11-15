package com.hongdaestudy.recipebackend.util;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public String getData(String key) { // key를 통해 value(데이터)를 얻는다.
        return redisTemplate.opsForValue().get(key);
    }

    public void setDataExpire(String key, String value, long duration) {
        //  duration 동안 (key, value)를 저장한다.
        redisTemplate.opsForValue().set(key, value,duration, TimeUnit.SECONDS);
    }

    public void deleteData(String key) {
        // 데이터 삭제
        redisTemplate.delete(key);
    }
}
