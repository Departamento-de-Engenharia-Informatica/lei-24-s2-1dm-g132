package pt.ipp.isep.dei.esoft.project.mapper.dto;

/**
 * Data Transfer Object (DTO) for representing Green Spaces.
 * This class holds information about a Green Space, including its name, address, area, and type.
 */
public class GreenSpaceDTO {

    /**
     * The name of the Green Space.
     */
    private String name;

    /**
     * The address of the Green Space.
     */
    private String address;

    /**
     * The area of the Green Space in hectares.
     */
    private int area;

    /**
     * The type of the Green Space.
     */
    private String  type;

    /**
     * Constructs a GreenSpaceDTO object with the provided parameters.
     *
     * @param name The name of the Green Space.
     * @param address The address of the Green Space.
     * @param area The area of the Green Space in square meters.
     * @param type The type of the Green Space.
     */
    public GreenSpaceDTO(String name, String address, int area, String type) {
        this.name = name;
        this.address = address;
        this.area = area;
        this.type = type;
    }

    /**
     * Retrieves the name of the Green Space.
     *
     * @return The name of the Green Space.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the address of the Green Space.
     *
     * @return The address of the Green Space.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Retrieves the area of the Green Space.
     *
     * @return The area of the Green Space in square meters.
     */
    public int getArea() {
        return area;
    }

    /**
     * Retrieves the type of the Green Space.
     *
     * @return The type of the Green Space.
     */
    public String getType() {
        return type;
    }

}
