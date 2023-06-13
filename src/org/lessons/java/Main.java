package org.lessons.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        // EVENT GENERATION -------------------------------------------------

        String eventTitle = null;
        do {
            System.out.println("Insert the Title of the event: ");
            eventTitle = scanner.nextLine();

            if (eventTitle.isEmpty()) {
                System.out.println("Error: Title cannot be null");
            }
        } while (eventTitle.isEmpty());


        LocalDate date = null;
        boolean validDate = false;

        while (date == null) {
            System.out.println("Insert the date of the event (dd/MM/yyyy): ");
            String eventDate = scanner.nextLine();

            if (eventDate.isEmpty()) {
                System.out.println("Date cannot be empty!");
            } else {
                try {
                    // DATE FORMATTING
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    date = LocalDate.parse(eventDate, formatter);
                    // DATE VALIDATION
                    if (date.isBefore(LocalDate.now())) {
                        System.out.println("The date cannot be in the past!");
                        date = null;
                    } else {
                        validDate = true;
                    }

                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter the date in the format dd/MM/yyyy");
                }
            }
        }

        int totalSeat = 0;
        boolean validInput = false;

        do {
            System.out.println("Insert the max number of seats for the event: ");
            String input = scanner.nextLine();

            try {
                totalSeat = Integer.parseInt(input);
                validInput = true;

                if (totalSeat <= 0) {
                    System.out.println("Total Seats must be more than 0");
                    validInput = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number");
            }
        } while (!validInput);


        Event event = new Event(eventTitle, date, totalSeat);

        System.out.println("Event created: " + event.toString());


        // RESERVATION    ----------------------------------------------------

        int reservationNumber = 0;
        String response = null;

        do {
            try {
                System.out.println("do you want to make a reservation? (Y/N): ");
                response = scanner.nextLine();

                if (!response.equalsIgnoreCase("Y") && !response.equalsIgnoreCase("N")) {
                    throw new Exception("Invalid input. Please enter 'Y' or 'N'.");
                }

                if (response.equalsIgnoreCase("Y")) {
                    System.out.println("Insert the number of reservation you want to do: ");
                    reservationNumber = Integer.parseInt(scanner.nextLine());

                    if (reservationNumber > event.getAvailableSeat()) {
                        throw new Exception("Not enough seats available for the requested reservations");
                    }

                    for (int i = 0; i < reservationNumber; i++) {
                        event.bookASeat();
                    }

                    System.out.println("reservation made successfully!");
                }
            } catch (Exception e) {
                System.out.println("An error occurred during the reservation: " + e.getMessage());
            }

            System.out.println("Reserved Seat: " + event.getBookedSeat());
            System.out.println("Seats available: " + event.getAvailableSeat());

        } while (response.equalsIgnoreCase("Y") && event.getAvailableSeat() > 0);


        //CANCEL RESERVATION   ----------------------------------------------
        System.out.println("Do you want to cancel a reservation? (Y/N) ");
        response = scanner.nextLine();

        while (response.equalsIgnoreCase("y")) {
            try {
                System.out.println("How many cancellations do you want to make? (1-" + reservationNumber + ")");
                int cancellationNumber = Integer.parseInt(scanner.nextLine());

                if (cancellationNumber > reservationNumber) {
                    throw new Exception("You cannot cancel more reservations than the ones you have made");
                }

                for (int i = 0; i < cancellationNumber; i++) {
                    event.cancelSeat();
                }

                System.out.println("reservation cancelled successfully!");

            } catch (Exception e) {
                System.out.println("An error occurred during the cancellation: " + e.getMessage());
            }

            System.out.println("Reserved Seat: " + event.getBookedSeat());
            System.out.println("Seats available: " + event.getAvailableSeat());

            if (event.getBookedSeat() > 0) {
                System.out.print("do you want to make a new cancellation? (Y/N): ");
                response = scanner.nextLine();
            } else {
                System.out.println("No reservation available to cancel");
                break;
            }
        }

        scanner.close();
    }
}
