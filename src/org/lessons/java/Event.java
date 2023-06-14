package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private final int TOTAL_SEAT;

    private int bookedSeat;

    // CONSTRUCTOR
    public Event(String title, LocalDate date, int totalSeat) throws IllegalArgumentException {

        validateTitle(title);

        validateDate(date);

        if (totalSeat > 0) {
            this.TOTAL_SEAT = totalSeat;
        } else {
            throw new IllegalArgumentException("Total seat must be > 0");
        }

        this.bookedSeat = 0;
    }

    // GETTERS & SETTERS   -----------------------------------------------------
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) throws IllegalArgumentException {
        validateTitle(title);
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) throws IllegalArgumentException {
        validateDate(date);
    }
    public int getTotalSeat() {
        return TOTAL_SEAT;
    }

    public int getBookedSeat() {
        return bookedSeat;
    }


    // BOOK A SEAT METHOD  ---------------------------------------------------------
    public void bookASeat() throws RuntimeException {
        if (date.isBefore(LocalDate.now())) {
            throw new RuntimeException("It is not possible to book a past event");
        }
        if (getAvailableSeat() == 0) {
            throw new RuntimeException("There are no more seats available for the event");
        }
        bookedSeat++;
    }

    // CANCEL A RESERVATION METHOD    --------------------------------------------------
    public void cancelSeat() throws RuntimeException {
        if (date.isBefore(LocalDate.now())) {
            throw new RuntimeException("It is not possible to cancel a reservation of a past event");
        }
        if (bookedSeat == 0) {
            throw new RuntimeException("There are no booked seats");
        }
        bookedSeat--;
    }

    // METHODS
    public int getAvailableSeat() {
        return TOTAL_SEAT - bookedSeat;
    }

    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return this.date.format(formatter);
    }


    // TO STING METHOD ----------------------------------------------------
    @Override
    public String toString() {
        return getFormattedDate() + "-" + getTitle();
    }

    // VALIDATION METHODS
    private void validateTitle(String title) {
        if (title != null && !title.equals("")) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Invalid title");
        }
    }

    private void validateDate(LocalDate date) {
        if (date != null) {
            if (date.isAfter(LocalDate.now())) {
                this.date = date;
            } else {
                throw new IllegalArgumentException("The date must be after today");
            }
        } else {
            throw new IllegalArgumentException("Date cannot be empty");
        }
    }
}
