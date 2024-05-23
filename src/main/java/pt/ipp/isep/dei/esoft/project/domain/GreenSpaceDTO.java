package pt.ipp.isep.dei.esoft.project.domain;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.util.ArrayList;
import java.util.Objects;
import java.lang.IllegalArgumentException;
import java.util.List;
import java.util.stream.Collectors;

public class GreenSpaceDTO {

    private String name;
    private String address;
    private int area;
    private String type;

    public GreenSpaceDTO(String name, String address, int area, String type) {
        setName(name);
        setAddress(address);
        setArea(area);
        setType(type);
    }



    public List<GreenSpaceDTO> greenSpaces(){
        GreenSpaceRepository greenSpaceRepository = new GreenSpaceRepository();
        return greenSpaceRepository.getGreenSpaces().stream()
                .map(greenSpace -> {
                    GreenSpaceDTO dto = new GreenSpaceDTO(address, name, area, type);
                    dto.setName(greenSpace.getName());
                    dto.setAddress(greenSpace.getAddress());
                    dto.setArea(greenSpace.getArea());
                    dto.setType(greenSpace.getType());
                    return dto;
                })
                .collect(Collectors.toList());
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public int getArea() {
        return area;
    }
    public String getType() {
        return type;
    }


    public void setName(String name) {

        if (name.matches(".*\\d.*")) {
            // If the input name contains numeric characters, throw an exception or handle it accordingly
            throw new IllegalArgumentException("Name cannot contain numbers.");
        }
        if (!name.matches("[a-zA-Z ]*" )) {
            throw new IllegalArgumentException("Name cannot contain special characters.");
        }

        String trimmedName = name.trim();

        int wordCount = trimmedName.isEmpty() ? 0 : trimmedName.split("\\s+").length;

        if (wordCount > 0) {
            this.name = name;
        }
        else
        {
            throw new IllegalArgumentException("Name wasn't correctly filled.");
        }

    }

    public void setAddress(String address) {
        String[] components = address.trim().split(",");

        if (components.length != 3) {
            throw new IllegalArgumentException("Error: Address must have the format 'Street, Zipcode, City'.");
        }

        String street = components[0].trim();
        String zipcode = components[1].trim();
        String city = components[2].trim();


        if (street.isEmpty()) {
            throw new IllegalArgumentException("Street cannot be empty.");
        }

        if (zipcode.isEmpty() || !zipcode.matches("\\d{4}-\\d{3}")) {
            throw new IllegalArgumentException("Zipcode must have the format '0000-000'.");
        }

        if (city.isEmpty()) {
            throw new IllegalArgumentException("City cannot be empty.");
        }

        this.address = address;
    }

    public void setArea(int area) {
        if (area == 0) {
            throw new IllegalArgumentException("Area cannot be zero.");
        }
        if (area < 0) {
            throw new IllegalArgumentException("Area cannot be negative.");
        }

        this.area = area;
    }

    public void setType(String type) {
        if (type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty.");
        }
        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null.");
        }
        if (type.isEmpty()) {
            throw new IllegalArgumentException("Type cannot be empty.");
        }
        if (type.matches(".*\\d.*")) {
            throw new IllegalArgumentException("Type cannot contain numbers.");
        }
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GreenSpaceDTO greenSpace = (GreenSpaceDTO) o;
        return name.equalsIgnoreCase(greenSpace.name);
    }

    @Override
    public GreenSpaceDTO clone() {
        return new GreenSpaceDTO(this.name, this.address, this.area, this.type);
    }

    public String toString() {
        return String.format("Green Space %s Data:\n" +
                        "Address: %s\n" +
                        "Area: %d\n" +
                        "Type: %s", this.name, this.address, this.area, this.type);

    }
}
