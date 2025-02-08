package com.shuzhi.system_objclasses.Entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.Date;

/**
 * Description:
 * Author: SHUZHI
 * Date: 2024/4/1
 *
 * @version 1.0
 */
@Document("mg_master_classes")
public class MongoClasses {
    @Id
    private ObjectId id;
    private String IP;
    private String URL;
    private String HEADER;
    private Date TIME;
    private String STATUS;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getHEADER() {
        return HEADER;
    }

    public void setHEADER(String HEADER) {
        this.HEADER = HEADER;
    }

    public Date getTIME() {
        return TIME;
    }

    public void setTIME(Date TIME) {
        this.TIME = TIME;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    @Override
    public String toString() {
        return "MongoUser{" +
                "id=" + id +
                ", IP='" + IP + '\'' +
                ", URL='" + URL + '\'' +
                ", HEADER='" + HEADER + '\'' +
                ", TIME=" + TIME +
                ", STATUS='" + STATUS + '\'' +
                '}';
    }
}
