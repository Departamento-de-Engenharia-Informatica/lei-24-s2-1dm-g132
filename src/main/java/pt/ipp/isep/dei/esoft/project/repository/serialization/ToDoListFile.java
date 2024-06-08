package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.io.*;

/**
 * Class responsible for reading and saving {@link ToDoList} objects to and from a file.
 * The class provides methods to read and save the to-do list data using serialization.
 */
public class ToDoListFile {

    /**
     * The default file name used for storing the to-do list.
     */
    public static final String FILE_NAME = "ToDoList.ltf";

    /**
     * Constructs a new ToDoListFile object.
     */
    public ToDoListFile() {
    }

    /**
     * Reads the to-do list from the default file.
     *
     * @return The {@link ToDoList} object read from the file. If the file does not exist or an error occurs, a new empty {@link ToDoList} is returned.
     */
    public ToDoList read() {
        return read(FILE_NAME);
    }

    /**
     * Reads the to-do list from a specified file name.
     *
     * @param fileName The name of the file to read the to-do list from.
     * @return The {@link ToDoList} object read from the file. If the file does not exist or an error occurs, a new empty {@link ToDoList} is returned.
     */
    public ToDoList read(String fileName) {
        return read(new File(fileName));
    }

    /**
     * Reads the to-do list from a specified file.
     *
     * @param file The file to read the to-do list from.
     * @return The {@link ToDoList} object read from the file. If the file does not exist or an error occurs, a new empty {@link ToDoList} is returned.
     */
    public ToDoList read(File file) {
        ToDoList toDoList;
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(file));
            try {
                toDoList = (ToDoList) in.readObject();
            } finally {
                in.close();
            }
            return toDoList;
        } catch (IOException | ClassNotFoundException ex) {
            return new ToDoList();
        }
    }

    /**
     * Saves the to-do list to the default file.
     *
     * @param toDoList The {@link ToDoList} object to save.
     * @return {@code true} if the to-do list was successfully saved; {@code false} otherwise.
     */
    public boolean save(ToDoList toDoList) {
        return save(FILE_NAME, toDoList);
    }

    /**
     * Saves the to-do list to a specified file name.
     *
     * @param fileName The name of the file to save the to-do list to.
     * @param toDoList The {@link ToDoList} object to save.
     * @return {@code true} if the to-do list was successfully saved; {@code false} otherwise.
     */
    public boolean save(String fileName, ToDoList toDoList) {
        return save(new File(fileName), toDoList);
    }

    /**
     * Saves the to-do list to a specified file.
     *
     * @param file The file to save the to-do list to.
     * @param toDoList The {@link ToDoList} object to save.
     * @return {@code true} if the to-do list was successfully saved; {@code false} otherwise.
     */
    public boolean save(File file, ToDoList toDoList) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(file));
            try {
                out.writeObject(toDoList);
            } finally {
                out.close();
            }
            return true;
        } catch (IOException ex) {
            return false;
        }
    }
}
