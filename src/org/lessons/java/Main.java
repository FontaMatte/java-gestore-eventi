package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the Title of the event: ");
        String eventTitle = scanner.nextLine();

        System.out.println("Insert the date of the event (dd/MM/yy): ");
        String eventDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
        LocalDate date = LocalDate.parse(eventDate, formatter);

        System.out.println("Insert the max number of seats for the event: ");
        int totalSeat = Integer.parseInt(scanner.nextLine());

        Event event = new Event(eventTitle, date, totalSeat);

        System.out.println(event);
    }
}
