package org.ConsoleCRUD.repository.entity;

public class Habit {

    private static int globalId = 0;

    private final int id;
    private final String name;
    private final String description;
    private final Frequency frequency;

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
}
