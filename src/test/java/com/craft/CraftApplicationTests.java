package com.craft;

import com.craft.cache.AppCache;
import com.craft.controller.CacheRestController;
import com.craft.entity.Entity;
import com.craft.schedule.Tasks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Calendar;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

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

        when(mockLoadXML.isCacheLoading()).thenReturn(true);

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

    @Test
    public void testGetResponseWhenKeyIsNotInCache(){
	    String key = "K8";
        Object obj =  cacheRestController.getEntity(key);
        assertThat(obj).isEqualTo("Key: "+key + " doesn't exist");
    }

    @Test
    public void testGetEntityWhenKeyIsAvailableInCache(){
        Entity obj =  (Entity) cacheRestController.getEntity("t2");
        assertThat(obj.getValue()).isEqualTo("val2");
    }

    @Test
    public void testAddEntity(){
	    loadXML.loadXMLData();
	    assertThat(appCache.size()).isGreaterThanOrEqualTo(5);
        Entity obj =  (Entity) cacheRestController.addEntity("t7","val7");

        assertThat(obj).isNotNull();
        cacheRestController.addEntity("t10","val10");
        assertThat(appCache.size()).isEqualTo(7);
    }


    /*

    Mocked test cases
     */

    @InjectMocks
    CacheRestController cacheRestControllerInjected;

    @Mock
    LoadXML mockLoadXML;
    @Mock
    AppCache mockAppCache;

    @Test
    public void mockTestGetResponseWhenCacheIsLoading(){
	    when(mockLoadXML.isCacheLoading()).thenReturn(Boolean.TRUE);
	    Object obj =  cacheRestControllerInjected.getEntity("k1");
        assertThat(obj).isEqualTo("Cache is being loaded, please wait for few seconds..");
    }

    @Test
    public void mockTestGetResponseWhenKeyIsNotInCache(){
        String key = "K9";
        when(mockAppCache.get(key)).thenReturn(null);
        Object obj =  cacheRestControllerInjected.getEntity(key);
        assertThat(obj).isEqualTo("Key: "+key + " doesn't exist");
    }

    @Test
    public void mockTestGetResponseWhenKeyIsAvailable() {
        String key = "t4";
        when(mockAppCache.get(key)).thenReturn(new Entity("t4", "val4", Calendar.getInstance().getTime()));
        Entity obj = (Entity) cacheRestControllerInjected.getEntity(key);
        assertThat(obj.getValue()).isEqualTo("val4");
    }

}
