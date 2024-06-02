package pt.ipp.isep.dei.esoft.project.mapper.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

public class GreenSpaceDTO {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;
    private String gsmEmail;

    public GreenSpaceDTO(String name, String address, int area, GreenSpaceType type, String gsmEmail) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.type = type;
        this.gsmEmail = gsmEmail;
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

    public GreenSpaceType getType() {
        return type;
    }

    public String getGsmEmail() {
        return gsmEmail;
    }
}
