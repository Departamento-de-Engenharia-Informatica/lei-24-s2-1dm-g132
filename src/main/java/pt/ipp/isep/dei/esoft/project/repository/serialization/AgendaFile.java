package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.Agenda;

import java.io.*;

/**
 * Class responsible for reading and saving {@link Agenda} objects to and from a file.
 * The class provides methods to read and save the agenda data using serialization.
 */
public class AgendaFile {

    /**
     * The default file name used for storing the agenda.
     */
    public static final String FILE_NAME = "Agenda.ltf";

    /**
     * Constructs a new AgendaFile object.
     */
    public AgendaFile() {
    }

    /**
     * Reads the agenda from the default file.
     *
     * @return The {@link Agenda} object read from the file. If the file does not exist or an error occurs, a new empty {@link Agenda} is returned.
     */
    public Agenda read() {
        return read(FILE_NAME);
    }

    /**
     * Reads the agenda from a specified file name.
     *
     * @param fileName The name of the file to read the agenda from.
     * @return The {@link Agenda} object read from the file. If the file does not exist or an error occurs, a new empty {@link Agenda} is returned.
     */
    public Agenda read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Reads the agenda from a specified file.
     *
     * @param file The file to read the agenda from.
     * @return The {@link Agenda} object read from the file. If the file does not exist or an error occurs, a new empty {@link Agenda} is returned.
     */
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

    /**
     * Saves the agenda to the default file.
     *
     * @param agenda The {@link Agenda} object to save.
     * @return {@code true} if the agenda was successfully saved; {@code false} otherwise.
     */
    public boolean save(Agenda agenda) {
        return save(FILE_NAME, agenda);
    }

    /**
     * Saves the agenda to a specified file name.
     *
     * @param fileName The name of the file to save the agenda to.
     * @param agenda   The {@link Agenda} object to save.
     * @return {@code true} if the agenda was successfully saved; {@code false} otherwise.
     */
    public boolean save(String fileName, Agenda agenda) {
        return save(new File(fileName), agenda);
    }

    /**
     * Saves the agenda to a specified file.
     *
     * @param file   The file to save the agenda to.
     * @param agenda The {@link Agenda} object to save.
     * @return {@code true} if the agenda was successfully saved; {@code false} otherwise.
     */
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
