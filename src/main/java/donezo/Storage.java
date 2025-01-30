package donezo;

import donezo.exceptions.DonezoException;
import donezo.parser.Parser;
import donezo.tasks.Task;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * The Storage class is responsible for handling the reading, writing, and manipulation
 * of task data stored in a file. It interacts with a TaskList that includes all the tasks
 * and handles the persistence of task information to and from a file.
 */
public class Storage {

    private String filePath;
    private TaskList taskListActual;

    /**
     * Constructs a Storage object using the specified file name.
     * This initializes the file path and task list, enabling future operations
     * related to task storage and retrieval.
     *
     * @param fileName the name of the file where tasks will be stored and retrieved from
     */
    public Storage(String fileName) {
        filePath = "data/" + fileName;
        //taskList = new ArrayList<Task>();
        taskListActual = new TaskList();
    }

    /**
     * Retrieves the file path where task data is stored.
     *
     * @return the file path as a String
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Retrieves the current TaskList that is being managed by the Storage class.
     *
     * @return the TaskList object containing the collection of tasks
     */
    public TaskList getTaskList() {
        return this.taskListActual;
    }

    /**
     * Appends a line of text to the specified file. If the file does not exist, it
     * will be created. The content will be appended, ensuring previous file data
     * is preserved.
     *
     * @param filePath the path of the file to which the text will be written
     * @param lineToAdd the line of text to append to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void writeToFile(String filePath, String lineToAdd) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(lineToAdd + "\n");
        }
    }

    /**
     * Deletes the content of the specified file, represented by the provided file path,
     * and replaces it with the current task list content. This process involves creating
     * a temporary file to hold the updated task list content before replacing the original file.
     *
     * @param filePath the path of the file to be replaced with the updated task list
     * @param taskList the TaskList containing the updated tasks to be saved to the file
     * @throws IOException if an I/O error occurs during file operations such as writing
     *                     to the temporary file, deleting the original file, or renaming
     *                     the temporary file
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
     * Loads tasks from the file specified by the file path and populates the TaskList.
     * This method reads each line from the file, parses it to construct tasks,
     * and adds them to the TaskList. If the file or its directory does not exist,
     * it ensures their creation. In case of an I/O issue during reading, it throws
     * a DonezoException.
     *
     * @return the TaskList object populated with tasks read from the file
     * @throws DonezoException if an I/O error occurs during file operations
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
     * Ensures that the file and its parent directory specified by the file path exist.
     *
     * This method performs the following:
     * - Retrieves the file path using the class's filePath field.
     * - Checks if the parent directory of the file exists. If not, creates the necessary directories.
     * - Checks if the file itself exists. If not, creates a new file.
     *
     * @throws IOException if an error occurs while creating the directories or the file.
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
     * Adds a task to the current task list being managed.
     *
     * @param task the Task object to be added to the task list
     */
    public void addTask(Task task) {
        taskListActual.addTask(task);
    }
    
}
