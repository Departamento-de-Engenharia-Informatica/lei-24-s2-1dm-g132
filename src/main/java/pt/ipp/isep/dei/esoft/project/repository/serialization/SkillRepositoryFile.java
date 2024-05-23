package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.SkillRepository;

import java.io.*;

public class SkillRepositoryFile {

    public static final String FILE_NAME = "SkillRepository.ltf";

    public SkillRepositoryFile() {
    }

    public SkillRepository read() {
        return read(FILE_NAME);
    }

    public SkillRepository read(String fileName) {
        return read(new File(fileName));
    }

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

    public boolean save(SkillRepository skillRepository) {
        return save(FILE_NAME, skillRepository);
    }

    public boolean save(String fileName, SkillRepository skillRepository) {
        return save(new File(fileName), skillRepository);
    }

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
