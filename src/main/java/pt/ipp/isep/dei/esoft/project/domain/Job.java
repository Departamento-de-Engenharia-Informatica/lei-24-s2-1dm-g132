
/**

 The Job class represents a job.
 */
package pt.ipp.isep.dei.esoft.project.domain;
import java.io.Serializable;
import java.util.Objects;
import java.lang.IllegalArgumentException;

public class Job implements Serializable {


    private String name;


    /**
     * Sets the name of the job after validating it.
     * The name cannot contain numbers, must be filled, and cannot have more than 6 words.
     *
     * @param name the name of the job
     * @throws IllegalArgumentException if the name contains numbers
     * @throws IllegalArgumentException if the name contains special characters
     * @throws IllegalArgumentException if the name wasn't correctly filled
     */
    private void setJobName(String name) {

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
     * Constructs a new Job object with the specified name.
     * The name is validated by the setJobName method.
     *
     * @param jobName the name of the job
     * @throws IllegalArgumentException if the name contains numbers, wasn't correctly filled, or has more than 6 words
     */

    public Job(String jobName){
        setJobName(jobName);
    }

    /**
     * Generates a hash code for the job based on its name.
     * @return The hash code generated.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /**
     * Checks if the job has the specified name.
     * @param name The name to be checked.
     * @return True if the job has the specified name, false otherwise.
     */
    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    /**
     * Compares this job to another object for equality.
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
        Job job = (Job) o;
        return name.equals(job.name);
    }

    /**
     * Creates a deep copy of the job.
     * @return A new Job object with the same name.
     */
    @Override
    public Job clone() {
        return new Job(this.name);
    }

    /**
     * Retrieves the name of the job.
     * @return The name of the job.
     */
    public String getJobName() {
        return name;
    }

    /**
     * Generates a string representation of the job.
     * @return The string representation of the job.
     */
    @Override
    public String toString() {
        return String.format("%s", this.name);
    }
}







