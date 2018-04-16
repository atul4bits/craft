package com.craft;

import com.craft.cache.AppCache;
import com.craft.controller.CacheRestController;
import com.craft.entity.Entity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CraftApplicationTests {


    @Autowired
    private WebApplicationContext webApplicationContext;

	@Autowired
	private CacheRestController cacheRestController;

	@Autowired
    AppCache appCache;

	@Autowired
    LoadXML loadXML;

	@Test
	public void contextLoads() {
	    assertThat(cacheRestController).isNotNull();
	    assertThat(appCache).isNotNull();
	    assertThat(loadXML).isNotNull();
	}

	@Test
    public void testsForAppCache(){
	    assertThat(appCache.size() == 5);
	    assertThat(appCache.get("t1").getValue().equals("val1"));
	    appCache.add("T6", new Entity("T6", "val6", Calendar.getInstance().getTime()));
	    assertThat(appCache.size() == 6);
    }

    @Test
    public void testsForLoadXML(){
        assertThat(loadXML.getFilePath().equals("./cache_data_test.xml"));

	}



/*    @Before
    public void setup() throws Exception {
	    loadXML.loadXMLData();
    }*/

	/*
    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello World")));
    }*/
}
