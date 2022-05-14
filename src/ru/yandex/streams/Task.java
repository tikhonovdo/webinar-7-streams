package ru.yandex.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Task {
    private final int id;
    private List<Integer> subtasksIds = new ArrayList<>();

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getSubtasksIds() {
        return subtasksIds;
    }

    public void setSubtasksIds(List<Integer> subtasksIds) {
        this.subtasksIds = subtasksIds;
    }

    @Override
    public String toString() {
        return "Task" + id + " " + Optional.of(subtasksIds).orElse(List.of());
    }

}


