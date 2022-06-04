package ru.yandex.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskUtils {

    public static Stream<Task> getTasksStream() {
        // TODO: переписать на стримы
        List<Task> tasks = new ArrayList<>();
        for (int id = 1; id <= 10; id++) {
            Task task = new Task(id);
            tasks.add(task);

            List<Integer> subtasks = new ArrayList<>();
            for (int sid = 1; sid <= 10; sid++) {
                subtasks.add(100 * task.getId() + sid);
            }
            task.setSubtasksIds(subtasks);
        }
        return tasks.stream();
    }

    public static Stream<Task> getTasksStreamNew() {
        return Stream.iterate(1, i -> i <= 10, i -> i + 1)
                .map(id -> new Task(id)) // Task::new
                .peek(t -> t.setSubtasksIds(
                        Stream.iterate(1, i -> i <= 10, i -> i + 1)
                                .map(sid -> 100 * t.getId() + sid)
                                .collect(Collectors.toList())));
    }
}
