package donezo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import donezo.lists.NoteList;
import donezo.exceptions.DonezoException;
import donezo.notes.Note;
import donezo.parser.ParserNoteStorage;

public class NoteStorage implements Storage {
    private String filePath;
    private NoteList notes;

    public NoteStorage(String fileName) {
        filePath = "data/" + fileName;
        notes = new NoteList();
    }

    @Override
    public String getFilePath() {
        return this.filePath;
    }

    public NoteList getNotes() {
        return notes;
    }

    /**
     * Appends a note to the specified file.
     *
     * @param filePath  The file path where the text should be written.
     * @param note The NoteList containing the notes.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void writeToFile(String filePath, Note note) throws IOException {
        try (FileWriter fileWriter = new FileWriter(filePath, true)) {
            fileWriter.write("<note>\n");
            fileWriter.write("Title: " + note.getTitle() + "\n");
            fileWriter.write("Date: " + note.getDate() + "\n");
            fileWriter.write("Content: " + note.getContent() + "\n");
            fileWriter.write("<note>\n");
        }
    }

    /**
     * Deletes and rewrites the file with the current notes in the note list.
     *
     * @param filePath  The file path to overwrite.
     * @param noteList  The NoteList containing the notes to save.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public void deleteFromFile(String filePath, NoteList noteList) throws IOException {
        File tempFile = new File("data/tempNoteFile.txt");
        for (int i = 0; i < noteList.getSizeNoteList(); i++) {
            Note note = noteList.getNote(i);
            writeToFile("data/tempNoteFile.txt", note);
        }

        Files.delete(Paths.get(getFilePath()));
        File actualFile = new File(filePath);
        tempFile.renameTo(actualFile);
    }

    /**
     * Loads notes from the file and populates the note list.
     *
     * @return The NoteList containing loaded notes.
     * @throws DonezoException If there is an error reading the file.
     */
    public NoteList loadFromFile() throws DonezoException {
        try {
            checkFileExist();
            ParserNoteStorage.parseNotes(getFilePath(), this);
            return this.notes;
        } catch (IOException e) {
            throw new DonezoException("Unable to Load From File");
        }
    }

    /**
     * Ensures the noteStorage file and its parent directory exist.
     * If they do not exist, they are created.
     *
     * @throws IOException If an error occurs while creating the file.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Override
    public void checkFileExist() throws IOException {
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
     * Adds a note to the note list.
     *
     * @param note The note to be added.
     */
    public void addNote(Note note) {
        notes.addNote(note);
    }
}
