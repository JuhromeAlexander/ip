package donezo.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected static final DateTimeFormatter INPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    protected static final DateTimeFormatter OUTPUT_TIME_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, INPUT_TIME_FORMATTER);
        
        if (!to.contains("/")) {
            String fromDate = this.from.toLocalDate().toString();
            this.to = LocalDateTime.parse(fromDate + " " + to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else {
            this.to = LocalDateTime.parse(to, INPUT_TIME_FORMATTER);
        }
    }

    public LocalDateTime getFrom() {
        return this.from;
    }

    public LocalDateTime getTo() {
        return this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom().format(OUTPUT_TIME_FORMATTER) 
                + " to: " + getTo().format(OUTPUT_TIME_FORMATTER) + ")";
    }
}
