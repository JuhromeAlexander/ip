package donezo.parser;

import donezo.notes.Note;
import donezo.storage.NoteStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ParserNoteStorage {
    public static void parseNotes(String filePath, NoteStorage noteStorage) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        int i = 0;

        while (i < lines.size()) {
            String line = lines.get(i).trim();
            if (line.equals("<note>")) {
                if (i + 4 < lines.size() && lines.get(i + 4).trim().equals("<note>")) {
                    String title = lines.get(i + 1).trim().substring(7);
                    String date = lines.get(i + 2).trim().substring(6);
                    String description = lines.get(i + 3).trim().substring(9);

                    DateTimeFormatter storedFormat = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
                    DateTimeFormatter noteConstructorFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

                    LocalDateTime noteDateTime = LocalDateTime.parse(date, storedFormat);
                    String formattedNoteDateTime = noteDateTime.format(noteConstructorFormat);

                    Note note = new Note(title, formattedNoteDateTime, description);
                    noteStorage.addNote(note);
                    i += 5;
                } else {
                    i++;
                }
            } else {
                i++;
            }
        }
    }
}
