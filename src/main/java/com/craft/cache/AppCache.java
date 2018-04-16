package com.craft.cache;

import com.craft.entity.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AppCache {
    private static final Logger log = LoggerFactory.getLogger(AppCache.class);
    private Map<String, Entity> _cache ;

    @Value("${cache.size}")
    private int MAX_CACHE_SIZE;

    public void add(String k, Entity v){
        /*if(_cache.size() == MAX_CACHE_SIZE && _cache.containsKey(k)){
            // put a better implementation here
            _cache.remove();
        }*/
        _cache.put(k, v);
    }

    public Entity get(String k){
        log.info("Cache got request for Key: "+k);
        return _cache.getOrDefault(k, null);
    }

    // cleans the existing cache and initializes new one
    public void initialize(){
        _cache = null;
        _cache = new LinkedHashMap<>();
    }

    public int size(){
        return _cache.size();
    }
}
