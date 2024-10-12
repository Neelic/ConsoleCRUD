package org.ConsoleCRUD.repository.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Habit {

    private static int globalId = 0;

    private final int id;
    private final String name;
    private final String description;
    private final Frequency frequency;
    private final List<LocalDate> historyChecks = new ArrayList<>();

    private boolean completed = false;
    private LocalDate created = LocalDate.now();

    public Habit(String name, String description, Frequency frequency) {

        if (name == null || description == null || frequency == null) {
            throw new IllegalArgumentException("Name, description and frequency cannot be null");
        }

        if (name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("Имя и описание привычки не могут быть пустыми");
        }

        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.id = ++globalId;
    }

    public Habit(String name, String description, Frequency frequency, LocalDate created, boolean completed, List<LocalDate> historyChecks) {

        if (name == null || description == null || frequency == null) {
            throw new IllegalArgumentException("Name, description and frequency cannot be null");
        }

        if (name.isEmpty() || description.isEmpty()) {
            throw new IllegalArgumentException("Имя и описание привычки не могут быть пустыми");
        }

        this.name = name;
        this.description = description;
        this.frequency = frequency;
        this.id = ++globalId;
        this.created = created;
        this.completed = completed;
        this.historyChecks.addAll(historyChecks);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public int getId() {
        return id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        if (completed && !historyChecks.contains(LocalDate.now())) {
            historyChecks.add(LocalDate.now());
        }

        this.completed = completed;
    }

    public LocalDate getCreated() {
        return created;
    }

    public List<LocalDate> getHistoryChecks() {
        return historyChecks;
    }
}
