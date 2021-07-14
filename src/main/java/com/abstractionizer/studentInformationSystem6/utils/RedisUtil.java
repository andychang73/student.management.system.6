package com.abstractionizer.studentInformationSystem6.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@AllArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper;

    public boolean set(@NonNull final String key, @NonNull Object obj, @NonNull final Long duration, @NonNull final TimeUnit timeUnit){
        try{
            this.redisTemplate.boundValueOps(key).set(obj, duration, timeUnit);
            return true;
        }catch(Exception var1){
            try{
                log.error("RedisUtil set object failed, key: " + key + " obj: " + objectMapper.writeValueAsString(obj) + " ERROR: ", var1);
            }catch(JsonProcessingException var2){
                log.error("Object mapper write obj to string failed, obj: " + obj + " ERROR:", var2);
            }
        }
        return false;
    }

    public <T> T get(@NonNull final String key, @NonNull final Class<T> cls){
        Object obj = null;
        try{
            obj = this.redisTemplate.boundValueOps(key).get();
        }catch (Exception var3){
            log.error("RedisUtil get obj failed, key: " + key + " ERROR: ", var3);
        }
        return cls.cast(obj);
    }

    public Long increment(@NonNull final String key, @NonNull final Long interval){
        return this.redisTemplate.boundValueOps(key).increment(interval);
    }

    public boolean expire(@NonNull final String key, @NonNull final long duration, @NonNull TimeUnit timeUnit){
        return this.redisTemplate.expire(key, duration, timeUnit);
    }

    public boolean isKeyExists(@NonNull final String key){
        if(key.isEmpty()){
            return false;
        }
        return Objects.nonNull(this.get(key, Object.class));
    }

    public boolean deleteKey(@NonNull final String key){
        return this.redisTemplate.delete(key);
    }

    public String tryGetLock(@NonNull String key, @NonNull Long expire, @NonNull TimeUnit timeUnit) {
        String uuid = UUID.randomUUID().toString();
        Boolean result = this.redisTemplate.boundValueOps(key).setIfAbsent(uuid, expire, timeUnit);
        return Objects.nonNull(result) && result ? uuid : null;
    }

    public boolean doWithRedisLock(@NonNull String key, @NonNull Long expire, @NonNull TimeUnit timeUnit, @NonNull RedisUtil.DoInRedisLock doInRedisLock) {
        String uuid = null;
        boolean var11 = false;

        label139: {
            boolean var6;
            try {
                var11 = true;
                uuid = this.tryGetLock(key, expire, timeUnit);
                if (!Objects.isNull(uuid)) {
                    doInRedisLock.doSomeThing();
                    var11 = false;
                    break label139;
                }

                var6 = false;
                var11 = false;
            } finally {
                if (var11) {
                    if (uuid != null) {
                        String uuidInRedis = (String)this.get(key, String.class);
                        if (uuid.equals(uuidInRedis)) {
                            if (!this.deleteKey(key)) {
                                log.error("Del Redis lock Key Failure, key: {}", key);
                            }
                        } else {
                            log.error("Can not del redis lock key, because uuid did not match, may be timeout, key: {}  uuid: {} uuidInRedis: {}", new Object[]{key, uuid, uuidInRedis});
                        }
                    }

                }
            }

            if (uuid != null) {
                String uuidInRedis = (String)this.get(key, String.class);
                if (uuid.equals(uuidInRedis)) {
                    if (!this.deleteKey(key)) {
                        log.error("Del Redis lock Key Failure, key: {}", key);
                    }
                } else {
                    log.error("Can not del redis lock key, because uuid did not match, may be timeout, key: {}  uuid: {} uuidInRedis: {}", new Object[]{key, uuid, uuidInRedis});
                }
            }

            return var6;
        }

        if (uuid != null) {
            String uuidInRedis = (String)this.get(key, String.class);
            if (uuid.equals(uuidInRedis)) {
                if (!this.deleteKey(key)) {
                    log.error("Del Redis lock Key Failure, key: {}", key);
                }
            } else {
                log.error("Can not del redis lock key, because uuid did not match, may be timeout, key: {}  uuid: {} uuidInRedis: {}", new Object[]{key, uuid, uuidInRedis});
            }
        }

        return true;
    }

    public interface DoInRedisLock {
        void doSomeThing();
    }


}
