import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private File storageFile;
    private ArrayList<Task> taskList;
    private String filePath;

    public Storage(String fileName) {
        filePath = "data/" + fileName;
        storageFile = new File(filePath);
        taskList = new ArrayList<Task>();
    }

    public String getFilePath() {
        return this.filePath;
    }

    public void writeToFile(String filePath, String lineToAdd) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write(lineToAdd + "\n");
        }
    }

    public void deleteFromFile(String filePath, ArrayList<Task> taskList) throws IOException {
        try (FileWriter fileWriter = new FileWriter("data/tempFile.txt", true)) {
            File tempFile = new File("data/tempFile.txt");

            for (int i = 0; i < taskList.size(); i++) {
                String line = taskList.get(i).toString();
                fileWriter.write(line + "\n");
            }

            Files.delete(Paths.get(filePath));
            File actualFile = new File(filePath);
            tempFile.renameTo(actualFile);
        }
        
    }

    public ArrayList<Task> loadFromFile() throws DonezoException {
        try {
            List<String> tasks = Files.readAllLines(Paths.get(this.filePath));
            for (int i = 0; i < tasks.size(); i++) {
                String line = tasks.get(i);
                Parser.parseStorageLine(line, this);
            }
            return taskList;
        } catch (IOException e) {
            throw new DonezoException("Oops boss, can't read from the existing file. Data may be lost!");
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
    
}
