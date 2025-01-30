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

public class Storage {
    private String filePath;
    private TaskList taskListActual;

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

    public void writeToFile(String filePath, String lineToAdd) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(lineToAdd + "\n");
        }
    }

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

    public void addTask(Task task) {
        taskListActual.addTask(task);
    }
    
}
