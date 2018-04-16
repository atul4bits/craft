package com.craft.entity;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.util.Date;

@XmlRootElement( name = "ENTITY" )
public class Entity {

    private String key;

    private String value;

    private Date dt;

    public Entity(String key, String value, Date dt) {
        this.key = key;
        this.value = value;
        this.dt = dt;
    }

    public Entity() {
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }


    public String getDt() {
        return dt.toString();
    }

    @XmlAttribute(name = "KEY")
    public void setKey(String key) {
        this.key = key;
    }

    @XmlAttribute(name = "VALUE")
    public void setValue(String value) {
        this.value = value;
    }

    @XmlAttribute(name = "TS")
    public void setDt(String dt) {
            this.dt = toDate(dt);
    }


    private static Date toDate(String ts){
        Date dt = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            dt = dateFormat.parse(ts);

        } catch(Exception e) { //this generic but you can control another types of exception
            // look the origin of excption
        }
        return dt;
    }
}

