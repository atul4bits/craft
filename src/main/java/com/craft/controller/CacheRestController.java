package com.craft.controller;


import com.craft.cache.AppCache;
import com.craft.entity.Entity;
import com.craft.LoadXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class CacheRestController {

    private final String CACHE_NOT_READY =  "Cache is being loaded, please wait for few seconds..";

    @Autowired
    private AppCache appCache;

    @Autowired
    private LoadXML loadXML;

    @RequestMapping(method = RequestMethod.GET, path = "/getEntity",produces = "application/json; charset=utf-8")
    //@Cacheable("entities") this can be used as well. If we use this, requests wont go to our own implemented cache
    // as this will be called before invoking this method
    public Object getEntity(@RequestParam(value="key") String key) {
        if(loadXML.isCacheLoading()){
            return CACHE_NOT_READY;
        }
        Entity e = appCache.get(key);
        return  (e  == null) ? "Key: " + key + " doesn't exist": e ;
    }


    //@CachePut("entities")
    @RequestMapping(method = RequestMethod.POST, path = "/addEntity" ,produces = "application/json; charset=utf-8")
    public Object addEntity(@RequestParam(value="key")  String key , @RequestParam(value="val") String val ) {
        if(loadXML.isCacheLoading()){
            return CACHE_NOT_READY;
        }
        Entity entity = new Entity(key,val,new Date());
        appCache.add(key,entity);
        return entity;
    }

}
