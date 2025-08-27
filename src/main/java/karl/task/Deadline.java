package karl.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate by;  // store deadline as LocalDate
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    // Constructor now accepts LocalDate for 'by'
    public Deadline(String description, String by) {
        super(description);
        // parse input date string to LocalDate
        this.by = LocalDate.parse(by.trim(), INPUT_FORMAT);
    }

    public LocalDate getBy() {
        return by;
    }

    @Override
    public String toString() {
        // format date as 'MMM dd yyyy' e.g. Oct 15 2019
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
}
