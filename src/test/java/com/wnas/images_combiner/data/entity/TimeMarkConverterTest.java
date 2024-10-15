package com.wnas.images_combiner.data.entity;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeMarkConverterTest {
    private static final String EXAMPLE = "[{\"startFrame\":0,\"endFrame\":100}]";

    @Test
    void convertToDatabaseColumnTest() {
        final List<TimeMark> timeMarks = Collections.singletonList(new TimeMark(0, 100));
        final String result = new TimeMarkConverter().convertToDatabaseColumn(timeMarks);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(EXAMPLE);
    }

    @Test
    void convertToEntityAttribute() {
        List<TimeMark> result = new TimeMarkConverter().convertToEntityAttribute(EXAMPLE);
        Assertions.assertThat(result).hasSize(1);
        Assertions.assertThat(result.getFirst()).isNotNull();
        Assertions.assertThat(result.getFirst().getStartFrame()).isEqualTo(0);
        Assertions.assertThat(result.getFirst().getEndFrame()).isEqualTo(100);
    }
}