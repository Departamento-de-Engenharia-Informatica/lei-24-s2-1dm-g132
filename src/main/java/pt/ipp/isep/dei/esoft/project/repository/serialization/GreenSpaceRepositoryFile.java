package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.io.*;


/**
 * Class responsible for reading and saving {@link GreenSpaceRepository} objects to and from a file.
 * The class provides methods to read and save the repository data using serialization.
 */
public class GreenSpaceRepositoryFile {

    /**
     * The default file name used for storing the green space repository.
     */
    public static final String FILE_NAME = "GreenSpaceRepository.ltf";

    /**
     * Constructs a new GreenSpaceRepositoryFile object.
     */
    public GreenSpaceRepositoryFile() {
    }

    /**
     * Reads the green space repository from the default file.
     *
     * @return The {@link GreenSpaceRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link GreenSpaceRepository} is returned.
     */
    public GreenSpaceRepository read() {
        return read(FILE_NAME);
    }

    /**
     * Reads the green space repository from a specified file name.
     *
     * @param fileName The name of the file to read the green space repository from.
     * @return The {@link GreenSpaceRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link GreenSpaceRepository} is returned.
     */
    public GreenSpaceRepository read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Reads the green space repository from a specified file.
     *
     * @param file The file to read the green space repository from.
     * @return The {@link GreenSpaceRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link GreenSpaceRepository} is returned.
     */
    public GreenSpaceRepository read(File file) {
        GreenSpaceRepository greenSpaceRepository;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                greenSpaceRepository = (GreenSpaceRepository) in.readObject();
            } finally {
                in.close();
            }
            return greenSpaceRepository;
        } catch (IOException | ClassNotFoundException ex) {
            return new GreenSpaceRepository();
        }
    }

    /**
     * Saves the green space repository to the default file.
     *
     * @param greenSpaceRepository The {@link GreenSpaceRepository} object to save.
     * @return {@code true} if the green space repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(GreenSpaceRepository greenSpaceRepository) {
        return save(FILE_NAME, greenSpaceRepository);
    }

    /**
     * Saves the green space repository to a specified file name.
     *
     * @param fileName The name of the file to save the green space repository to.
     * @param greenSpaceRepository The {@link GreenSpaceRepository} object to save.
     * @return {@code true} if the green space repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(String fileName, GreenSpaceRepository greenSpaceRepository) {
        return save(new File(fileName), greenSpaceRepository);
    }

    /**
     * Saves the green space repository to a specified file.
     *
     * @param file The file to save the green space repository to.
     * @param greenSpaceRepository The {@link GreenSpaceRepository} object to save.
     * @return {@code true} if the green space repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(File file, GreenSpaceRepository greenSpaceRepository) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(greenSpaceRepository);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
