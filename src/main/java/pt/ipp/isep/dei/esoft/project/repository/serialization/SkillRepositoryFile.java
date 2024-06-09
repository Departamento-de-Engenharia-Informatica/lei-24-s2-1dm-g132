package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.io.*;

/**
 * Class responsible for reading and saving {@link SkillRepository} objects to and from a file.
 * The class provides methods to read and save the repository data using serialization.
 */
public class SkillRepositoryFile {

    /**
     * The default file name used for storing the skill repository.
     */
    public static final String FILE_NAME = "SkillRepository.ltf";

    /**
     * Constructs a new SkillRepositoryFile object.
     */
    public SkillRepositoryFile() {
    }

    /**
     * Reads the skill repository from the default file.
     *
     * @return The {@link SkillRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link SkillRepository} is returned.
     */
    public SkillRepository read() {
        return read(FILE_NAME);
    }

    /**
     * Reads the skill repository from a specified file name.
     *
     * @param fileName The name of the file to read the skill repository from.
     * @return The {@link SkillRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link SkillRepository} is returned.
     */
    public SkillRepository read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Reads the skill repository from a specified file.
     *
     * @param file The file to read the skill repository from.
     * @return The {@link SkillRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link SkillRepository} is returned.
     */
    public SkillRepository read(File file) {
        SkillRepository skillRepository;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                skillRepository = (SkillRepository) in.readObject();
            } finally {
                in.close();
            }
            return skillRepository;
        } catch (IOException | ClassNotFoundException ex) {
            return new SkillRepository();
        }
    }

    /**
     * Saves the skill repository to the default file.
     *
     * @param skillRepository The {@link SkillRepository} object to save.
     * @return {@code true} if the skill repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(SkillRepository skillRepository) {
        return save(FILE_NAME, skillRepository);
    }

    /**
     * Saves the skill repository to a specified file name.
     *
     * @param fileName The name of the file to save the skill repository to.
     * @param skillRepository The {@link SkillRepository} object to save.
     * @return {@code true} if the skill repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(String fileName, SkillRepository skillRepository) {
        return save(new File(fileName), skillRepository);
    }

    /**
     * Saves the skill repository to a specified file.
     *
     * @param file The file to save the skill repository to.
     * @param skillRepository The {@link SkillRepository} object to save.
     * @return {@code true} if the skill repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(File file, SkillRepository skillRepository) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(skillRepository);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
