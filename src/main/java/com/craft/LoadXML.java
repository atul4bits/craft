package com.craft;

import com.craft.cache.AppCache;
import com.craft.entity.EntityMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

@Component
public class LoadXML {

    @Autowired
    private AppCache appCache;

    private Boolean isCacheLoading = Boolean.FALSE;

    @Value("${cache.load.file}")
    private String CACHE_DATA_FILE_PATH;

    public void loadXMLData(){

        File file = new File(CACHE_DATA_FILE_PATH);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(EntityMaster.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            EntityMaster em = (EntityMaster) jaxbUnmarshaller.unmarshal(file);

            setCacheLoading(Boolean.TRUE);
            appCache.initialize();
            // todo limit the cache enteries to max cache size if file has more elements
            em.getEntities().stream().forEach(x-> appCache.add(x.getKey(),x));

        }catch (JAXBException jaxbe){
            System.out.println("JAXB Error, take some action or log it");
        }catch (Exception e){
            System.out.println("bigger error, take some action or log it");
        }finally {
            setCacheLoading(Boolean.FALSE);
        }
    }

    public Boolean isCacheLoading() {
        return isCacheLoading;
    }

    private void setCacheLoading(Boolean cacheLoading) {
        isCacheLoading = cacheLoading;
    }

    public String getFilePath(){
        return CACHE_DATA_FILE_PATH;
    }
}

