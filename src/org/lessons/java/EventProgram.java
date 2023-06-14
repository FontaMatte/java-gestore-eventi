package org.lessons.java;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventProgram {
    private String title;
    private List<Event> program;

    public EventProgram(String title) {
        this.title = title;
        program = new ArrayList<>();
    }

    public void addEvent(Event event) {
        program.add(event);
    }
    public List<Event> getEventsByDate(LocalDate date) {
        List<Event> eventList = new ArrayList<>();
        for (Event e : program) {
            if (e.getDate().isEqual(date)) {
                eventList.add(e);
            }
        }
        return eventList;
    }

    public int getNumberOfEvents() {
        return program.size();
    }

    public void clearProgram() {
        program.clear();
    }
    @Override
    public String toString() {
        String result = this.title + "\n";
        for (Event e : program) {
            result += "-" + e.toString() + "\n";
        }
        return result;
    }
}
