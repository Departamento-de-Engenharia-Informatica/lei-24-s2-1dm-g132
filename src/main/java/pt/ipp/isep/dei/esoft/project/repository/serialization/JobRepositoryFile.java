package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.JobRepository;

import java.io.*;

public class JobRepositoryFile {

    public static final String FILE_NAME = "JobRepository.ltf";

    public JobRepositoryFile() {
    }

    public JobRepository read() {
        return read(FILE_NAME);
    }

    public JobRepository read(String fileName) {
        return read(new File(fileName));
    }

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

    public boolean save(JobRepository jobRepository) {
        return save(FILE_NAME, jobRepository);
    }

    public boolean save(String fileName, JobRepository jobRepository) {
        return save(new File(fileName), jobRepository);
    }

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
