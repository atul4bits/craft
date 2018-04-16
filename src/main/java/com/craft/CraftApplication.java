package com.craft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import java.util.Date;

@SpringBootApplication
@EnableScheduling
//@EnableCaching
// this can be used as well as first level of cache
public class CraftApplication implements CommandLineRunner{
	private static final Logger log = LoggerFactory.getLogger(CraftApplication.class);

	@Autowired
	private  LoadXML loadXML;

	public static void main(String[] args) {
		SpringApplication.run(CraftApplication.class, args);
	}

	@Override
	public void run(String... args) {
		log.info("LOADING the XML file to cache, time is:" + new Date());
		loadXML.loadXMLData();
	}
}
