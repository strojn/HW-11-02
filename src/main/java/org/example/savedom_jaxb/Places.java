package org.example.savedom_jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "places")
public class Places {
    //список адрес, за допомогою анотації буде сгенировано елемент address
    @XmlElement(name = "address")
    private List<Address> addresses = new ArrayList<>();
    public void add(Address address) { addresses.add(address); }
    public String toString() { return Arrays.deepToString( addresses.toArray() ); }
}
