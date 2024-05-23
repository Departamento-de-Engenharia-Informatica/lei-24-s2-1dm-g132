package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.TeamRepository;

import java.io.*;

public class TeamRepositoryFile {

    public static final String FILE_NAME = "TeamRepository.ltf";

    public TeamRepositoryFile() {
    }

    public TeamRepository read() {
        return read(FILE_NAME);
    }

    public TeamRepository read(String fileName) {
        return read(new File(fileName));
    }

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

    public boolean save(TeamRepository teamRepository) {
        return save(FILE_NAME, teamRepository);
    }

    public boolean save(String fileName, TeamRepository teamRepository) {
        return save(new File(fileName), teamRepository);
    }

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
