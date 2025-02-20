package donezo.commands;

import donezo.lists.ItemList;
import donezo.exceptions.DonezoException;
import donezo.notes.Note;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class NoteCommand extends Command {

    @Override
    public void executeCommand(String userInput, ItemList itemList) throws DonezoException {
        assertCheck(userInput, itemList);

        if (!userInput.contains("/title")) {
            throw new DonezoException("Hey boss, the '/title' argument ain't here. Add it in!");
        }

        if (!userInput.contains("/date")) {
            throw new DonezoException("Hey boss, the '/date' argument ain't here. Add it in!");
        }

        if (!userInput.contains("/content")) {
            throw new DonezoException("Hey boss, the '/content' argument ain't here. Add it in!");
        }

        String noteTitle = userInput.substring(userInput.indexOf("/title") + 7, userInput.indexOf("/date")).trim();
        if (noteTitle.isEmpty()) {
            throw new DonezoException("Hey boss, I ain't no mind reader, add the content for the /title field.");
        }

        String noteDate = userInput.substring(userInput.indexOf("/date") + 6, userInput.indexOf("/content")).trim();
        if (noteDate.isEmpty()) {
            throw new DonezoException("Hey boss, I ain't no mind reader, add the content for the /date field.");
        }

        String noteContent = userInput.substring(userInput.indexOf("/content") + 9).trim();
        if (noteContent.isBlank()) {
            throw new DonezoException("Hey Boss, I ain't no mind reader, add the contents of the note in");
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime.parse(noteDate, formatter);
        } catch (DateTimeParseException e) {
            throw new DonezoException(
                    "Hey boss, that note date format ain't right! " +
                            "Use this format: d/M/yyyy HHmm (e.g., 15/2/2025 1800)");
        }

        Note note = new Note(noteTitle, noteDate, noteContent);
        itemList.addItem(note);
        ui.printAddNote(note);

        try {
            noteStorage.writeToFile(noteStorage.getFilePath(), note);
        } catch (IOException e) {
            ui.printUnableToSaveTaskFile();
        }

    }
}
