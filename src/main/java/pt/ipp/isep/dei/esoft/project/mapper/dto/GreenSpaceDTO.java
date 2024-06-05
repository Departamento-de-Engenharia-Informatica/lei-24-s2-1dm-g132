package pt.ipp.isep.dei.esoft.project.mapper.dto;

import pt.ipp.isep.dei.esoft.project.application.session.ApplicationSession;
import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

public class GreenSpaceDTO {

    private String name;
    private String address;
    private int area;
    private String  type;
    private String gsmEmail;

    public GreenSpaceDTO(String name, String address, int area, String type) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.type = type;
        //this.gsmEmail = ApplicationSession.getInstance().getCurrentSession().getUserEmail();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getArea() {
        return area;
    }

    public String getType() {
        return type;
    }

    public String getGsmEmail() {
        return gsmEmail;
    }
}
