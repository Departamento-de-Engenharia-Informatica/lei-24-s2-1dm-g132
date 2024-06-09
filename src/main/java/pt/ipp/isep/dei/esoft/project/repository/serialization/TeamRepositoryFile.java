package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.io.*;

/**
 * Class responsible for reading and saving {@link TeamRepository} objects to and from a file.
 * The class provides methods to read and save the repository data using serialization.
 */
public class TeamRepositoryFile {

    /**
     * The default file name used for storing the team repository.
     */
    public static final String FILE_NAME = "TeamRepository.ltf";

    /**
     * Constructs a new TeamRepositoryFile object.
     */
    public TeamRepositoryFile() {
    }

    /**
     * Reads the team repository from the default file.
     *
     * @return The {@link TeamRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link TeamRepository} is returned.
     */
    public TeamRepository read() {
        return read(FILE_NAME);
    }

    /**
     * Reads the team repository from a specified file name.
     *
     * @param fileName The name of the file to read the team repository from.
     * @return The {@link TeamRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link TeamRepository} is returned.
     */
    public TeamRepository read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Reads the team repository from a specified file.
     *
     * @param file The file to read the team repository from.
     * @return The {@link TeamRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link TeamRepository} is returned.
     */
    public TeamRepository read(File file) {
        TeamRepository teamRepository;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                teamRepository = (TeamRepository) in.readObject();
            } finally {
                in.close();
            }
            return teamRepository;
        } catch (IOException | ClassNotFoundException ex) {
            return new TeamRepository();
        }
    }

    /**
     * Saves the team repository to the default file.
     *
     * @param teamRepository The {@link TeamRepository} object to save.
     * @return {@code true} if the team repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(TeamRepository teamRepository) {
        return save(FILE_NAME, teamRepository);
    }

    /**
     * Saves the team repository to a specified file name.
     *
     * @param fileName The name of the file to save the team repository to.
     * @param teamRepository The {@link TeamRepository} object to save.
     * @return {@code true} if the team repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(String fileName, TeamRepository teamRepository) {
        return save(new File(fileName), teamRepository);
    }

    /**
     * Saves the team repository to a specified file.
     *
     * @param file The file to save the team repository to.
     * @param teamRepository The {@link TeamRepository} object to save.
     * @return {@code true} if the team repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(File file, TeamRepository teamRepository) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(teamRepository);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
