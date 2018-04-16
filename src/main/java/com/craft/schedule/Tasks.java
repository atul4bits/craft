package com.craft.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class Tasks {

    private static final Logger log = LoggerFactory.getLogger(Tasks.class);
    @Autowired
    private LoadXML loadXML;


    @Scheduled(fixedRateString = "${cache.load.interval}",initialDelayString = "${cache.load.interval}" )
    //@CacheEvict(value="entities",  allEntries=true)
    public void reLoadCache() {
        log.info("LOADING the XML file to initialize the cache, time is:" + Calendar.getInstance().getTime());
        loadXML.loadXMLData();
    }
}
