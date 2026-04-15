package dev.ivanov.grid.task;

import java.util.List;

import dev.ivanov.grid.api.annotations.SubtaskData;
import lombok.Getter;

@SubtaskData
@Getter
public class SubtaskDataExample {
    
    private Long startSequence;
    private Long endSequence;

}
