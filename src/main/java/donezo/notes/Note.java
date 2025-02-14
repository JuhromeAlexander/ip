package donezo.notes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Note {
    private String title;
    private LocalDateTime date;
    private String content;

    static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    public Note (String title, String date, String content) {
        this.title = title;
        this.date = LocalDateTime.parse(date, INPUT_TIME_FORMATTER);
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date.format(OUTPUT_TIME_FORMATTER);
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns a string representation of the note.
     * Format:
     * Note Title: <title>
     * Note Date: <date> (formatted as MMM dd yyyy)
     * Note Description: <description>
     */
    @Override
    public String toString() {
        return "Note Title: " + getTitle() + "\n"
                + "Note Date: " + getDate() + "\n"
                + "Note Content: " + getContent();
    }

}
