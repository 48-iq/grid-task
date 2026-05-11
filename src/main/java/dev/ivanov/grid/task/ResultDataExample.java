package dev.ivanov.grid.task;

import java.util.List;

import dev.ivanov.grid.api.annotations.ResultData;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ResultData
public class ResultDataExample {
    private Integer total;
    private List<Integer> sequence;
}
