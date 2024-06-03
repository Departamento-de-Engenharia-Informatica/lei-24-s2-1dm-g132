package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.Agenda;

import java.io.*;

public class AgendaFile {
    public static final String FILE_NAME = "Agenda.ltf";

    public AgendaFile() {
    }

    public Agenda read() {
        return read(FILE_NAME);
    }

    public Agenda read(String fileName) {
        return read(new File(fileName));
    }

    public Agenda read(File file) {
        Agenda agenda;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                agenda = (Agenda) in.readObject();
            } finally {
                in.close();
            }
            return agenda;
        } catch (IOException | ClassNotFoundException ex) {
            return new Agenda();
        }
    }

    public boolean save(Agenda agenda) {
        return save(FILE_NAME, agenda);
    }

    public boolean save(String fileName, Agenda agenda) {
        return save(new File(fileName), agenda);
    }

    public boolean save(File file, Agenda agenda) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(agenda);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
