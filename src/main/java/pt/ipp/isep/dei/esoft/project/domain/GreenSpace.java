package pt.ipp.isep.dei.esoft.project.domain;

import java.io.Serializable;
import java.lang.IllegalArgumentException;

public class GreenSpace implements Serializable {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;
    private String gsmEmail;


    public GreenSpace(String name, String address, int area, String type, String gsmEmail) {
        if(name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("Green Spaces Names Cannot Be Empty");
        }
        if (address.isEmpty() || address.isBlank()) {
            throw new IllegalArgumentException("Green Spaces Addresses Cannot Be Empty");
        }
        if (area <= 0) {
            throw new IllegalArgumentException("Green Spaces Area Cannot Be Zero Or Negative");
        }

        this.name = name;
        this.address = address;
        this.area = area;
        setType(type);
        this.gsmEmail = gsmEmail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getType() {
        return type.toString();
    }

    public void setType(String type) {
        if(type.replaceAll("\\s+","").equalsIgnoreCase(GreenSpaceType.garden.toString()))
            this.type = GreenSpaceType.garden;
        else if(type.replaceAll("\\s+","").equalsIgnoreCase(GreenSpaceType.mediumSizedPark.toString()))
            this.type = GreenSpaceType.mediumSizedPark;
        else if(type.replaceAll("\\s+","").equalsIgnoreCase(GreenSpaceType.largeSizedPark.toString()))
            this.type = GreenSpaceType.largeSizedPark;
        else throw new IllegalArgumentException("Invalid green space type.");
    }

    public String getGsmEmail() {
        return gsmEmail;
    }

    public void setGsmEmail(String gsmEmail) {
        this.gsmEmail = gsmEmail;
    }

    public boolean hasUserEmail(String email){
        return this.gsmEmail.equalsIgnoreCase(email);
    }

    @Override
    public String toString() {
        return "GreenSpace{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", type=" + type +
                ", gsmEmail='" + gsmEmail + '\'' +
                '}';
    }
}
