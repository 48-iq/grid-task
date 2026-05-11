package dev.ivanov.grid.task;

import java.util.ArrayList;
import java.util.List;

import dev.ivanov.grid.api.annotations.Do;
import dev.ivanov.grid.api.annotations.Task;

@Task
public class TaskExample {

  @Do
  public ResultDataExample work(SubtaskDataExample subtask, TaskDataExample task) {

    // валидация
    validate(subtask, task);

    // инициализация
    List<Integer> currentSequence = new ArrayList<>(subtask.getStartSequence());

    Integer currentCity = null;

    Integer bestScore = null;
    List<Integer> bestSequence = null;

    while(true) {

      if (isValid(currentSequence, task.getMatrix().size())) {
        Integer currentScore = getScore(currentSequence, task.getMatrix());
        if (bestScore == null || currentScore < bestScore) {
          bestScore = currentScore;
          bestSequence = new ArrayList<>(currentSequence);
        }
      }

      Integer lastCity = currentSequence.getLast();

      List<Integer> allowedCities = getAllowedCities(task.getMatrix(), lastCity);

      // если не подошли к ограничению
      if (currentSequence.size() <= subtask.getMaxSequenceLength()) {
        Integer nextCity = getNextCity(allowedCities, currentCity);
        if (allowedCities.getLast() != currentCity) {

          currentSequence.add(nextCity);
          currentCity = null;

        } else if (currentSequence.size() > subtask.getStartSequence().size()) {
          currentCity = currentSequence.getLast();
          currentSequence.removeLast();
        } else {
          break;
        }

      } else {
        currentCity = currentSequence.getLast();
        currentSequence.removeLast();
      }

    }

    return new ResultDataExample(bestScore, bestSequence);

  }

  private Integer getScore(List<Integer> sequence, List<List<Integer>> matrix) {
    Integer score = 0;
    for (int i = 0; i < sequence.size() - 1; i++) {
      Integer from = sequence.get(i);
      Integer to = sequence.get(i + 1);
      score += matrix.get(from).get(to);
    }
    return score;
  }

  private Integer getNextCity(List<Integer> allowedCities, Integer currentCity) {
    Integer nextCity = null;
    if (currentCity == null) {
      nextCity = allowedCities.getFirst();
    } else {
      for (int i = 0; i < allowedCities.size() - 1; i++) {
        if (allowedCities.get(i) == currentCity) {
          nextCity = allowedCities.get(i + 1);
        }
      }
    }
    return nextCity;
  }

  private List<Integer> getAllowedCities(List<List<Integer>> matrix, Integer city) {
    List<Integer> relations = matrix.get(city);
    List<Integer> allowedCities = new ArrayList<>();
    for (int i = 0; i < relations.size(); i++) {
      if (relations.get(i) != null) {
        allowedCities.add(i);
      }
    }
    if (allowedCities.isEmpty()) {
      throw new InvalidTaskException("Город " + city + " не имеет связей");
    }
    return allowedCities;
  }

  private boolean isValid(List<Integer> sequence, int totalCities) {
    boolean result = true;
    for (int i = 0; i < totalCities; i++) {
      if (!sequence.contains(i)) {
        result = false;
        break;
      }
    }
    if (sequence.getFirst() != sequence.getLast()) {
      result = false;
    }
    return result;
  }

  private void validate(SubtaskDataExample subtask, TaskDataExample task) {
    // валидация

    if (task.getMatrix().size() == 0) {
      throw new InvalidTaskException("Матрица не должна быть пустой");
    }

    if (task.getMatrix().getFirst().size() != task.getMatrix().size()) {
      throw new InvalidTaskException("Матрица должна быть квадратной");
    }

    if (subtask.getMaxSequenceLength() < task.getMatrix().size()) {
      throw new InvalidTaskException(
          "Максимальная длина последовательности не должна быть меньше количества городов");
    }

  }

}
