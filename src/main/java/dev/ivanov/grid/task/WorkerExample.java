package dev.ivanov.grid.task;

import java.util.ArrayList;
import java.util.List;

import dev.ivanov.grid.api.annotations.Do;
import dev.ivanov.grid.api.annotations.Task;

@Task
public class WorkerExample {

  private List<Long> factorials = new ArrayList<>();

  @Do
  public ResultDataExample work(SubtaskDataExample subtask, TaskDataExample task) {
    long factorial = 1;
    if (factorials.isEmpty()) {
      for (int i = 0; i <= task.getMatrix().size(); i++) {
        if (i == 0) {
          factorials.add(1l);
        } else {
          factorial *= i;
          factorials.add(factorial);
        }
      }
    }
    Long resultTotal = 0l;
    List<Integer> resultSequence = new ArrayList<>();
    for (long i = subtask.getStartSequence(); i <= subtask.getEndSequence(); i++) {
      List<Integer> sequence = calcSequence(i);
      long total = 0;
      boolean flag = true;
      for (int j = 0; j < sequence.size() - 1; j++) {
        Integer el1 = sequence.get(j);
        Integer el2 = sequence.get(j + 1);
        Integer val = task.getMatrix().get(el1).get(el2);
        if (val > 0) {
          total += val;
        }
        else {
          flag = false;
          break;
        }
      }
      if (flag && (total < resultTotal || resultTotal == 0)) {
        resultTotal = total;
        resultSequence = sequence;
      }
    }
    return new ResultDataExample(resultTotal, resultSequence);
  }

  private List<Integer> calcSequence(Long order) {
    List<Integer> sequence = new ArrayList<>();
    for (Long i: factorials) {
      Integer s = (int) (order / i);
      order = order % i;
      sequence.add(s);
    }
    return sequence;
  }
}
