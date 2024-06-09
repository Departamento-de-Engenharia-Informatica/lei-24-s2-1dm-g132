package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.io.*;

/**
 * Class responsible for reading and saving {@link JobRepository} objects to and from a file.
 * The class provides methods to read and save the repository data using serialization.
 */
public class JobRepositoryFile {

    /**
     * The default file name used for storing the job repository.
     */
    public static final String FILE_NAME = "JobRepository.ltf";

    /**
     * Constructs a new JobRepositoryFile object.
     */
    public JobRepositoryFile() {
    }

    /**
     * Reads the job repository from the default file.
     *
     * @return The {@link JobRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link JobRepository} is returned.
     */
    public JobRepository read() {
        return read(FILE_NAME);
    }

    /**
     * Reads the job repository from a specified file name.
     *
     * @param fileName The name of the file to read the job repository from.
     * @return The {@link JobRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link JobRepository} is returned.
     */
    public JobRepository read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Reads the job repository from a specified file.
     *
     * @param file The file to read the job repository from.
     * @return The {@link JobRepository} object read from the file. If the file does not exist or an error occurs, a new empty {@link JobRepository} is returned.
     */
    public JobRepository read(File file) {
        JobRepository jobRepository;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                jobRepository = (JobRepository) in.readObject();
            } finally {
                in.close();
            }
            return jobRepository;
        } catch (IOException | ClassNotFoundException ex) {
            return new JobRepository();
        }
    }

    /**
     * Saves the job repository to the default file.
     *
     * @param jobRepository The {@link JobRepository} object to save.
     * @return {@code true} if the job repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(JobRepository jobRepository) {
        return save(FILE_NAME, jobRepository);
    }

    /**
     * Saves the job repository to a specified file name.
     *
     * @param fileName The name of the file to save the job repository to.
     * @param jobRepository The {@link JobRepository} object to save.
     * @return {@code true} if the job repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(String fileName, JobRepository jobRepository) {
        return save(new File(fileName), jobRepository);
    }

    /**
     * Saves the job repository to a specified file.
     *
     * @param file The file to save the job repository to.
     * @param jobRepository The {@link JobRepository} object to save.
     * @return {@code true} if the job repository was successfully saved; {@code false} otherwise.
     */
    public boolean save(File file, JobRepository jobRepository) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(jobRepository);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
