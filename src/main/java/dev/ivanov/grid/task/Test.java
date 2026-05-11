package dev.ivanov.grid.task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test {
  public static void main(String[] args) {
    TaskExample task = new TaskExample();
    TaskDataExample taskData = new TaskDataExample(List.of(
      Arrays.asList(null, 2, 2, null, null),
      Arrays.asList(2, null, 3, null, null),
      Arrays.asList(2, 3, null, 1, 3),
      Arrays.asList(null, null, 1, null, 1),
      Arrays.asList(null, null, 3, 1, null)
    ));
    SubtaskDataExample subtask = new SubtaskDataExample(List.of(4), 10);
    ResultDataExample result = task.work(subtask, taskData);
    System.out.println(result);
  }
}
