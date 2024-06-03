package pt.ipp.isep.dei.esoft.project.repository.serialization;

import pt.ipp.isep.dei.esoft.project.repository.ToDoList;

import java.io.*;

public class ToDoListFile {
    public static final String FILE_NAME = "ToDoList.ltf";

    public ToDoListFile() {
    }

    public ToDoList read() {
        return read(FILE_NAME);
    }

    public ToDoList read(String fileName) {
        return read(new File(fileName));
    }

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

    public boolean save(ToDoList toDoList) {
        return save(FILE_NAME, toDoList);
    }

    public boolean save(String fileName, ToDoList toDoList) {
        return save(new File(fileName), toDoList);
    }

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
