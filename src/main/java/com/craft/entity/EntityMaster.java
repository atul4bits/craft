package com.craft.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement( name = "ENTITIES" )
public class EntityMaster {

    List<Entity> entities;

    public List<Entity> getEntities() {
        return entities;
    }

    @XmlElement( name = "ENTITY" )
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void add( Entity museum ) {
        if (this.entities == null) {
            this.entities = new ArrayList<Entity>();
        }
        this.entities.add(museum);
    }
}
