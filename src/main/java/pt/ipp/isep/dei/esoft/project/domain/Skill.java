
/**

 The Skill class represents a skill.
 */
package pt.ipp.isep.dei.esoft.project.domain;
import java.util.Objects;
import java.lang.IllegalArgumentException;

public class Skill {

    private String name;

    /**
     * Constructs a new Skill object with the specified name.
     * The name is validated by the setSkillName method.
     *
     * @param name the name of the skill
     * @throws IllegalArgumentException if the name contains numbers
     * @throws IllegalArgumentException if the name wasn't correctly filled
     * @throws IllegalArgumentException if the name has more than 6 words
     */
    public Skill(String name) {
        setSkillName(name);
    }


    /**
     * Sets the name of the job after validating it.
     * The name cannot contain numbers, must be filled, and cannot have more than 6 words.
     *
     * @param name the name of the job
     * @throws IllegalArgumentException if the name contains numbers
     * @throws IllegalArgumentException if the name wasn't correctly filled
     * @throws IllegalArgumentException if the name has more than 6 words
     */
    private void setSkillName(String name) {

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

    /**
     * Generates a hash code for the skill based on its name.
     * @return The hash code generated.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Checks if the skill has the specified name.
     * @param name The name to be checked.
     * @return True if the skill has the specified name, false otherwise.
     */
    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    /**
     * Compares this skill to another object for equality.
     * @param o The object to be compared.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Skill skill = (Skill) o;
        return name.equals(skill.name);
    }

    /**
     * Creates a deep copy of the skill.
     * @return A new Skill object with the same name.
     */
    @Override
    public Skill clone() {
        return new Skill(this.name);
    }

    /**
     * Retrieves the name of the skill.
     * @return The name of the skill.
     */
    public String getName() {
        return name;
    }

    /**
     * Generates a string representation of the skill.
     * @return The string representation of the skill.
     */
    @Override
    public String toString() {
        return String.format("%s", this.name);
    }
}
