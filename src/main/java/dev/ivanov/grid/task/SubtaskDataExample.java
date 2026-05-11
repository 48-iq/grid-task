package dev.ivanov.grid.task;

import java.util.List;

import dev.ivanov.grid.api.annotations.SubtaskData;
import lombok.Getter;

@SubtaskData
@Getter
public class SubtaskDataExample {

    private List<Integer> startSequence;

    private Integer maxSequenceLength;

    public SubtaskDataExample(
            List<Integer> startSequence,
            Integer maxSequenceLength) {
        this.startSequence = startSequence;
        this.maxSequenceLength = maxSequenceLength;
    }

}
