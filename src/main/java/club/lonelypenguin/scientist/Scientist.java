/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club.lonelypenguin.scientist;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author dbundgaard
 */
@Entity
public class Scientist implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String website;
    private String country;
    
    @Version
    private long version;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    protected Scientist(){}

    public Scientist(String name, String website) {
        this(name,website,"undefined");
    }
    public Scientist(String name, String website, String country) {
        this.name = name;
        this.website = website;
        this.country = country;
    }

    @Override
    public String toString() {
        return String.format("Scientist[id=%d,name=%s,country=%s]", getId(), getName(),getCountry());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

}
