package org.lessons.java;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event{

    private LocalTime time;
    private BigDecimal price;

    public Concert(String title, LocalDate date, int totalSeat, LocalTime time, BigDecimal price) throws IllegalArgumentException {
        super(title, date, totalSeat);
        this.time = time;
        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Price must not be negative");
        }
        this.price = price;

    }

    // GETTERS SETTERS
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) throws IllegalArgumentException {
        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new IllegalArgumentException("Price must not be negative");
        }
        this.price = price;
    }

    // FORMATTING METHODS
    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

    public String getFormattedPrice() {
        DecimalFormat df = new DecimalFormat("##,##€");
        return df.format(price);
    }

    @Override
    public String toString() {
        return getFormattedDate() + " - " + getFormattedTime() + " - " + getTitle() + " - " + getFormattedPrice();
    }

    public static void main(String[] args) {
        // CONCERT CREATION EXAMPLE
        LocalDate concertDate = LocalDate.of(2023, 6,23);
        LocalTime concertTime = LocalTime.of(20, 0);
        BigDecimal concertPrice = BigDecimal.valueOf(49.99);
        Concert concert = new Concert("Vasco Rossi", concertDate, 1000, concertTime, concertPrice);

        System.out.println(concert);
    }
}
