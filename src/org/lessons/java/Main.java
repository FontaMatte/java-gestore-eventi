package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Insert the Title of the event: ");
        String eventTitle = scanner.nextLine();

        System.out.println("Insert the date of the event (dd/MM/yyyy): ");
        String eventDate = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(eventDate, formatter);

        System.out.println("Insert the max number of seats for the event: ");
        int totalSeat = Integer.parseInt(scanner.nextLine());

        Event event = new Event(eventTitle, date, totalSeat);

        System.out.println("Event created: " + event.toString());

        // RESERVATION
        System.out.println("do you want to make a reservation? (Y/N): ");
        String response = scanner.nextLine();
        
        while (response.equalsIgnoreCase("Y")) {
            try {
                System.out.println("Insert the number of reservation you want to do: ");
                int reservationNumber = Integer.parseInt(scanner.nextLine());

                for (int i = 0; i < reservationNumber; i++) {
                    event.bookASeat();
                }

                System.out.println("reservation made successfully!");
            } catch (Exception e) {
                System.out.println("An error occurred during the reservation");
            }

            System.out.println("Reserved Seat: " + event.getBookedSeat());
            System.out.println("Seats available: " + (event.getTotalSeat() - event.getBookedSeat()));

            System.out.print("do you want to make a new reservation? (Y/N): ");
            response = scanner.nextLine();
        }

        scanner.close();
    }
}
