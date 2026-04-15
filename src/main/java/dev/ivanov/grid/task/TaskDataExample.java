package dev.ivanov.grid.task;

import java.util.List;

import dev.ivanov.grid.api.annotations.TaskData;
import lombok.Data;

@TaskData
@Data
public class TaskDataExample {
    private List<List<Integer>> matrix;
}
