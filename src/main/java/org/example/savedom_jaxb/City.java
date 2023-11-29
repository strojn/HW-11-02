package org.example.savedom_jaxb;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "city")
public class City {
    @XmlAttribute(name = "size")
    private String size;
    @XmlValue
    private String name;

    public City() {
    }

    public City(String size, String name) {
        this.size = size;
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "City{" +
                "size='" + size + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
