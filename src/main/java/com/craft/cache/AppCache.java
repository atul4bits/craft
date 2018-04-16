package com.craft.cache;

import com.craft.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class AppCache {
    private static final Logger log = LoggerFactory.getLogger(AppCache.class);
    private Map<String, Entity> _cache ;

    public void add(String k, Entity v){
        _cache.put(k,v);
    }

    public Entity get(String k){
        log.info("Logging request for Key: "+k +" @ time: " );
        return _cache.getOrDefault(k, null);
    }

    public void initialize(){
        _cache = null;
        _cache = new ConcurrentHashMap<>();
    }

    public int size(){
        return _cache.size();
    }
}
