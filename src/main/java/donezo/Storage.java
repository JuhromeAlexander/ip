package donezo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import donezo.exceptions.DonezoException;
import donezo.parser.Parser;
import donezo.tasks.Task;

/**
 * The Storage class handles reading from and writing to a file to persist task data between sessions.
 */
public class Storage {
    //private ArrayList<Task> taskList;
    private String filePath;
    private TaskList taskListActual;

    /**
     * Constructs a Storage object with a specified filename.
     *
     * @param fileName The name of the file used for storing tasks.
     */
    public Storage(String fileName) {
        filePath = "data/" + fileName;
        taskListActual = new TaskList();
    }

    public String getFilePath() {
        return this.filePath;
    }

    public TaskList getTaskList() {
        return this.taskListActual;
    }

    /**
     * Appends a line of text to the specified file.
     *
     * @param filePath  The file path where the text should be written.
     * @param lineToAdd The text to add to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToFile(String filePath, String lineToAdd) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(lineToAdd + "\n");
        }
    }

    /**
     * Deletes and rewrites the file with the current tasks in the task list.
     *
     * @param filePath  The file path to overwrite.
     * @param taskList  The TaskList containing the tasks to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void deleteFromFile(String filePath, TaskList taskList) throws IOException {
        File tempFile = new File("data/tempFile.txt");
        try (FileWriter fileWriter = new FileWriter("data/tempFile.txt", true)) {
            
            for (int i = 0; i < taskList.getSizeTaskList(); i++) {
                String line = taskList.getTask(i).toString();
                fileWriter.write(line + "\n");
            }
        }
        Files.delete(Paths.get(filePath));
        File actualFile = new File(filePath);
        tempFile.renameTo(actualFile);
        
    }

    /**
     * Loads tasks from the file and populates the task list.
     *
     * @return The TaskList containing loaded tasks.
     * @throws DonezoException If there is an error reading the file.
     */
    public TaskList loadFromFile() throws DonezoException {
        try {
            checkFileExist();

            List<String> tasks = Files.readAllLines(Paths.get(this.filePath));
            for (int i = 0; i < tasks.size(); i++) {
                String line = tasks.get(i);
                Parser.parseStorageLine(line, this);
            }
            return taskListActual;
        } catch (IOException e) {
            throw new DonezoException("Oops boss, can't read from the existing file. Data may be lost!");
        }
    }

    /**
     * Ensures the storage file and its parent directory exist.
     * If they do not exist, they are created.
     *
     * @throws IOException If an error occurs while creating the file.
     */
    private void checkFileExist() throws IOException {
        File expectedFile = new File(getFilePath());
        File expectedFolder = expectedFile.getParentFile();

        if (!expectedFolder.exists()) {
            expectedFolder.mkdirs();
        }

        if (!expectedFile.exists()) {
            expectedFile.createNewFile();
        }
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        taskListActual.addTask(task);
    }
    
}
