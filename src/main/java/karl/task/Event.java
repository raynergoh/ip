package karl.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate from;
    private LocalDate to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Constructor accepts LocalDate for from and to
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from.trim(), INPUT_FORMAT);
        this.to = LocalDate.parse(to.trim(), INPUT_FORMAT);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                " (from: " + from.format(OUTPUT_FORMAT) +
                " to: " + to.format(OUTPUT_FORMAT) + ")";
    }
}
