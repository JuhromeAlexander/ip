package donezo.storage;

import java.io.IOException;

public interface Storage {
    public String getFilePath();
    public void checkFileExist() throws IOException;
}
