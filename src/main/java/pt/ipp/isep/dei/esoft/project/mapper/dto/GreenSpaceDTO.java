package pt.ipp.isep.dei.esoft.project.mapper.dto;

import pt.ipp.isep.dei.esoft.project.domain.GreenSpaceType;

public class GreenSpaceDTO {

    private String name;
    private String address;
    private int area;
    private GreenSpaceType type;

    public GreenSpaceDTO(String name, String address, int area, GreenSpaceType type) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.type = type;
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
}
