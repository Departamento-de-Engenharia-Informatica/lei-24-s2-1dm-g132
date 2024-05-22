package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.CollaboratorRepository;

import java.io.*;

public class CollaboratorRepositoryFile {

    public static final String FILE_NAME = "CollaboratorRepository.ltf";

    public CollaboratorRepositoryFile() {
    }

    public CollaboratorRepository read() {
        return read(FILE_NAME);
    }

    public CollaboratorRepository read(String fileName) {
        return read(new File(fileName));
    }

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

    public boolean save(CollaboratorRepository collaboratorRepository) {
        return save(FILE_NAME, collaboratorRepository);
    }

    public boolean save(String fileName, CollaboratorRepository collaboratorRepository) {
        return save(new File(fileName), collaboratorRepository);
    }

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
