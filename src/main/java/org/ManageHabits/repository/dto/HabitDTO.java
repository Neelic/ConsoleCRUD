package org.ManageHabits.repository.dto;

import org.ManageHabits.repository.entity.Frequency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HabitDTO {
    private String name;
    private String description;
    private Frequency frequency;
    private List<LocalDate> historyChecks = new ArrayList<>();
    private LocalDate created;
    private int id;
    private boolean completed;

//    public HabitDTO() {
//        this("", "", Frequency.DAILY, LocalDate.now(), false, new ArrayList<>(), 0);
//    }
//
//    public HabitDTO(String name, String description, Frequency frequency) {
//        this(name, description, frequency, LocalDate.now(), false, new ArrayList<>(), 0);
//    }
//
//    //    @Default
//    public HabitDTO(String name, String description, Frequency frequency, LocalDate created, boolean completed, List<LocalDate> historyChecks, int id) {
//
//        if (name == null || description == null || frequency == null) {
//            throw new IllegalArgumentException("Name, description and frequency cannot be null");
//        }
//
//        if (name.isEmpty() || description.isEmpty()) {
//            throw new IllegalArgumentException("Имя и описание привычки не могут быть пустыми");
//        }
//
//        this.name = name;
//        this.description = description;
//        this.frequency = frequency;
//        this.id = id;
//        this.created = created;
//        this.completed = completed;
//        this.historyChecks.addAll(historyChecks);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public List<LocalDate> getHistoryChecks() {
        return historyChecks;
    }

    public void setHistoryChecks(List<LocalDate> historyChecks) {
        this.historyChecks = historyChecks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HabitDTO dto = (HabitDTO) o;
        return id == dto.id && completed == dto.completed
                && name.equals(dto.name) && description.equals(dto.description) && frequency == dto.frequency
                && created.equals(dto.created) && historyChecks.equals(dto.historyChecks);
    }
}
