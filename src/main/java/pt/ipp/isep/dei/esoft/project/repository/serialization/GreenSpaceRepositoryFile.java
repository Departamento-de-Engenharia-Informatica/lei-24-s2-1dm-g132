package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.GreenSpaceRepository;

import java.io.*;

public class GreenSpaceRepositoryFile {
    public static final String FILE_NAME = "GreenSpaceRepository.ltf";

    public GreenSpaceRepositoryFile() {
    }

    public GreenSpaceRepository read() {
        return read(FILE_NAME);
    }

    public GreenSpaceRepository read(String fileName) {
        return read(new File(fileName));
    }

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

    public boolean save(GreenSpaceRepository greenSpaceRepository) {
        return save(FILE_NAME, greenSpaceRepository);
    }

    public boolean save(String fileName, GreenSpaceRepository greenSpaceRepository) {
        return save(new File(fileName), greenSpaceRepository);
    }

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
