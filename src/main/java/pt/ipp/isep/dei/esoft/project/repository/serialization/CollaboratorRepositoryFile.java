package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.io.*;

/**
 * Class responsible for reading and saving {@link CollaboratorRepository} objects to and from a file.
 * The class provides methods to read and save the repository data using serialization.
 */
public class CollaboratorRepositoryFile {

    /**
     * The default file name used for storing the collaborator repository.
     */
    public static final String FILE_NAME = "CollaboratorRepository.ltf";

    /**
     * Constructs a new CollaboratorRepositoryFile object.
     */
    public CollaboratorRepositoryFile() {
    }

    /**
     * Reads the collaborator repository from the default file.
     *
     * @return The {@link CollaboratorRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link CollaboratorRepository} is returned.
     */
    public CollaboratorRepository read() {
        return read(FILE_NAME);
    }

    /**
     * Reads the collaborator repository from a specified file name.
     *
     * @param fileName The name of the file to read the collaborator repository from.
     * @return The {@link CollaboratorRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link CollaboratorRepository} is returned.
     */
    public CollaboratorRepository read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Reads the collaborator repository from a specified file.
     *
     * @param file The file to read the collaborator repository from.
     * @return The {@link CollaboratorRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link CollaboratorRepository} is returned.
     */
    public CollaboratorRepository read(File file) {
        CollaboratorRepository collaboratorRepository;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                collaboratorRepository = (CollaboratorRepository) in.readObject();
            } finally {
                in.close();
            }
            return collaboratorRepository;
        } catch (IOException | ClassNotFoundException ex) {
            return new CollaboratorRepository();
        }
    }

    /**
     * Saves the collaborator repository to the default file.
     *
     * @param collaboratorRepository The {@link CollaboratorRepository} object to save.
     * @return {@code true} if the collaborator repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(CollaboratorRepository collaboratorRepository) {
        return save(FILE_NAME, collaboratorRepository);
    }

    /**
     * Saves the collaborator repository to a specified file name.
     *
     * @param fileName The name of the file to save the collaborator repository to.
     * @param collaboratorRepository The {@link CollaboratorRepository} object to save.
     * @return {@code true} if the collaborator repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(String fileName, CollaboratorRepository collaboratorRepository) {
        return save(new File(fileName), collaboratorRepository);
    }

    /**
     * Saves the collaborator repository to a specified file.
     *
     * @param file The file to save the collaborator repository to.
     * @param collaboratorRepository The {@link CollaboratorRepository} object to save.
     * @return {@code true} if the collaborator repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(File file, CollaboratorRepository collaboratorRepository) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(collaboratorRepository);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
