package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.application.session.UserSession;

import java.io.Serializable;
import java.lang.IllegalArgumentException;

public class GreenSpace implements Serializable {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;
    private String gsmEmail;


    public GreenSpace(String name, String address, int area, GreenSpaceType type, String gsmEmail) {
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
        this.type = type;
        this.gsmEmail = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
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

    public GreenSpaceType getType() {
        return type;
    }

    public void setType(GreenSpaceType type) {
        this.type = type;
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
