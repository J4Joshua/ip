package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import exception.EkkoException;
import task.*;

/**
 * Handles loading tasks from a file and saving tasks to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a new {@code Storage} object with the specified file path.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File file = new File(filePath);
            File directory = file.getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while checking/creating the file.");
            e.printStackTrace();
        }
    }

    /**
     * Loads tasks from the file and returns them as an ArrayList.
     *
     * @return An {@link ArrayList} of tasks loaded from the file.
     * @throws EkkoException If an error occurs while reading the file.
     */
    public ArrayList<Task> load() throws EkkoException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length < 3) {
                    continue;
                }

                String type = parts[0];
                boolean isDone = parts[1].equals("X");
                String description = parts[2];
                Task task = null;

                if (type.equals("T")) {
                    task = new ToDo(description);
                } else if (type.equals("D") && parts.length == 4) {
                    task = new Deadline(description, parts[3]);
                } else if (type.equals("E") && parts.length == 5) {
                    task = new Event(description, parts[3], parts[4]);
                }

                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
            System.out.println("Tasks successfully loaded from file.");
        } catch (IOException e) {
            throw new EkkoException("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the provided list of tasks to the file.
     *
     * @param tasks The {@link ArrayList} of tasks to be saved.
     * @throws EkkoException If an error occurs while writing to the file.
     */
    public void save(TaskList tasks) throws EkkoException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i =0; i<tasks.size(); i++) {
                Task task = tasks.getTask(i);
                String type = task.getCategory().toString();
                String status = task.isDone() ? "X" : " ";
                String description = task.getDescription();
                if (task instanceof Deadline) {
                    writer.write(type + " | " + status + " | " + description + " | "
                            + ((Deadline) task).getFormattedDate());
                } else if (task instanceof Event) {
                    writer.write(type + " | " + status + " | " + description + " | "
                            + ((Event) task).getFormattedFrom() + " | " + ((Event) task).getFormattedTo());
                } else {
                    writer.write(type + " | " + status + " | " + description);
                }
                writer.newLine();
            }
        } catch (IOException e) {
            throw new EkkoException("Error saving tasks: " + e.getMessage());
        }
    }
}
