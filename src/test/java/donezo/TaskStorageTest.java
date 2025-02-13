package donezo;

import donezo.storage.TaskStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskStorageTest {

    @TempDir
    Path tempDirectory;

    @Test
    public void writeToFile_singleEventLine_success() throws IOException {
        Path tempFile = tempDirectory.resolve("testFile.txt");
        String expectedContent = "[E][ ] test event (from: 4 Dec 2005, 6:00 pm to: 4 Dec 2005, 7:00 pm)";

        TaskStorage taskStorage = new TaskStorage("testFile.txt");
        taskStorage.writeToFile(tempFile.toString(), expectedContent);

        String actualContent = Files.readString(tempFile).trim();
        assertEquals(expectedContent, actualContent);
    }

    @Test
    public void writeToFile_directoryPath_exceptionThrown() {
        Path tempDirectoryPath = tempDirectory;
        String lineToAdd = "Should Throw Exception";

        TaskStorage taskStorage = new TaskStorage("testFile.txt");
        assertThrows(IOException.class, () -> taskStorage.writeToFile(tempDirectoryPath.toString(), lineToAdd));
    }

}
