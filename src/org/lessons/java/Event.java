package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event {
    private String title;
    private LocalDate date;
    private final int TOTAL_SEAT;

    private int bookedSeat;

    // CONSTRUCTOR
    public Event(String title, LocalDate date, int totalSeat) throws Exception {
        if (title != null && !title.equals("")) {
            this.title = title;
        } else {
            throw new Exception("Invalid title");
        }

        if (date.isAfter(LocalDate.now())) {
            this.date = date;
        } else {
            throw new Exception("The date must be after today");
        }

        if (totalSeat > 0) {
            this.TOTAL_SEAT = totalSeat;
        } else {
            throw new Exception("Total seat must be > 0");
        }

        this.bookedSeat = 0;
    }

    // GETTERS & SETTERS   -----------------------------------------------------
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) throws Exception {
        if (title != null && !title.equals("")) {
            this.title = title;
        } else {
            throw new Exception("Invalid title");
        }
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) throws Exception {
        if (date.isAfter(LocalDate.now())) {
            this.date = date;
        } else {
            throw new Exception("The date must be after today");
        }

    }
    public int getTotalSeat() {
        return TOTAL_SEAT;
    }

    public int getBookedSeat() {
        return bookedSeat;
    }


    // BOOK A SEAT METHOD  ---------------------------------------------------------
    public void bookASeat() throws Exception {
        if (date.isBefore(LocalDate.now())) {
            throw new Exception("It is not possible to book a past event");
        }
        if (bookedSeat >= TOTAL_SEAT) {
            throw new Exception("There are no more seats available for the event");
        }

        bookedSeat++;
    }

    // CANCEL A RESERVATION METHOD    --------------------------------------------------
    public void cancelSeat() throws Exception {
        if (date.isBefore(LocalDate.now())) {
            throw new Exception("It is not possible to cancel a reservation of a past event");
        }
        if (bookedSeat <= 0) {
            throw new Exception("There are no booked seats");
        }

        bookedSeat--;
    }

    // TO STING METHOD ----------------------------------------------------
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = date.format(formatter);
        return "Event{" +
                "title='" + title + '\'' +
                ", date=" + formattedDate +
                '}';
    }
}
