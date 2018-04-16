package com.craft;

import com.craft.cache.AppCache;
import com.craft.controller.CacheRestController;
import com.craft.entity.Entity;
import com.craft.schedule.Tasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CraftApplicationTests {

	@Autowired
	private CacheRestController cacheRestController;

	@Autowired
    AppCache appCache;

	@Autowired
    LoadXML loadXML;

	@Autowired
    Tasks tasks;

	@Test
	public void contextLoads() {
	    assertThat(cacheRestController).isNotNull();
	    assertThat(appCache).isNotNull();
	    assertThat(loadXML).isNotNull();
	}

    @Test
    public void testsForLoadXML(){
        assertThat(loadXML.getFilePath()).isEqualTo("./cache_data_test.xml");
        assertThat(appCache.size()).isGreaterThanOrEqualTo(5);
        appCache.initialize();
        assertThat(appCache.size()).isEqualTo(0);
        loadXML.loadXMLData();
        assertThat(appCache.size()).isEqualTo(5);

    }

    @Test
    public void testsForAppCache(){
        assertThat(appCache.size()).isEqualTo(5);
	    assertThat(appCache.get("t1").getValue()).isNotNull();

	    appCache.add("T6", new Entity("T6", "val6", Calendar.getInstance().getTime()));
        assertThat(appCache.size()).isEqualTo(6);
    }

    @Test
    public void testsForTaskReLoadCache(){
        assertThat(appCache.size()).isGreaterThanOrEqualTo(5);
        appCache.initialize();
        assertThat(appCache.size()).isEqualTo(0);
        tasks.reLoadCache();
        assertThat(appCache.size()).isEqualTo(5);
    }


}
